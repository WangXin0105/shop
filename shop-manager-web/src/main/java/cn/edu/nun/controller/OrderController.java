package cn.edu.nun.controller;

import cn.edu.nun.common.pojo.DataModel;
import cn.edu.nun.common.utils.ResultModel;
import cn.edu.nun.order.service.OrderService;
import cn.edu.nun.pojo.TbOrder;
import cn.edu.nun.pojo.TbOrderItem;
import cn.edu.nun.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/order/list")
    @ResponseBody
    public DataModel getItemList(Integer page, Integer rows) {
        DataModel dataModel = orderService.getOrderList(page, rows);
        return dataModel;
    }

    @RequestMapping("/order/dect")
    @ResponseBody
    public DataModel getOrderDect(String ids, Integer page, Integer rows) {
        DataModel result = orderService.dectOrder(ids,page,rows);
        return result;
    }

    @RequestMapping("/order/send")
    @ResponseBody
    public ResultModel sendOrder(String ids) {
        ResultModel result = orderService.sendOrder(ids);
        return result;
    }

    @RequestMapping("/order/end")
    @ResponseBody
    public ResultModel endOrder(String ids) {
        ResultModel result = orderService.endOrder(ids);
        return result;
    }

    @RequestMapping("/order/delete")
    @ResponseBody
    public ResultModel deleteItem(String ids) {
        ResultModel result = orderService.deleteOrder(ids);
        return result;
    }

}
