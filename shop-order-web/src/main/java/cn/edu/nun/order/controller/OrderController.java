package cn.edu.nun.order.controller;

import cn.edu.nun.cart.service.CartService;
import cn.edu.nun.common.utils.ResultModel;
import cn.edu.nun.order.pojo.OrderInfo;
import cn.edu.nun.order.service.OrderService;
import cn.edu.nun.pojo.TbItem;
import cn.edu.nun.pojo.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @RequestMapping("/order/order-cart")
    public String showOrderCart(HttpServletRequest request) {
        TbUser user= (TbUser) request.getAttribute("user");
        List<TbItem> cartList = cartService.getCartList(user.getId());
        request.setAttribute("cartList", cartList);
        return "order-cart";
    }

    @RequestMapping(value="/order/create", method=RequestMethod.POST)
    public String createOrder(OrderInfo orderInfo, HttpServletRequest request) {
        TbUser user = (TbUser) request.getAttribute("user");
        orderInfo.setUserId(user.getId());
        orderInfo.setBuyerNick(user.getUsername());
        ResultModel result = orderService.createOrder(orderInfo);
        //如果订单生成成功，需要删除购物车
        if (result.getStatus() == 200) {
            cartService.clearCartItem(user.getId());
        }
        request.setAttribute("orderId", result.getData());
        request.setAttribute("payment", orderInfo.getPayment());
        return "success";
    }
}

