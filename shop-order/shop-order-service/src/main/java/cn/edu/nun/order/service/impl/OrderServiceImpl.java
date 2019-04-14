package cn.edu.nun.order.service.impl;

import cn.edu.nun.common.jedis.JedisClient;
import cn.edu.nun.common.utils.ResultModel;
import cn.edu.nun.mapper.TbOrderItemMapper;
import cn.edu.nun.mapper.TbOrderMapper;
import cn.edu.nun.mapper.TbOrderShippingMapper;
import cn.edu.nun.order.pojo.OrderInfo;
import cn.edu.nun.order.service.OrderService;
import cn.edu.nun.pojo.TbOrderItem;
import cn.edu.nun.pojo.TbOrderShipping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private TbOrderMapper orderMapper;

    @Autowired
    private TbOrderItemMapper orderItemMapper;

    @Autowired
    private TbOrderShippingMapper orderShippingMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${ORDER_ID_GEN_KEY}")
    private String ORDER_ID_GEN_KEY;

    @Value("${ORDER_ID_START}")
    private String ORDER_ID_START;

    @Value("${ORDER_DETAIL_ID_GEN_KEY}")
    private String ORDER_DETAIL_ID_GEN_KEY;

    @Override
    public ResultModel createOrder(OrderInfo orderInfo) {
        if (!jedisClient.exists(ORDER_ID_GEN_KEY)) {
            jedisClient.set(ORDER_ID_GEN_KEY, ORDER_ID_START);
        }
        String orderId = jedisClient.incr(ORDER_ID_GEN_KEY).toString();
        orderInfo.setOrderId(orderId);
        //1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
        orderInfo.setStatus(1);
        orderInfo.setCreateTime(new Date());
        orderInfo.setUpdateTime(new Date());

        orderMapper.insert(orderInfo);
        List<TbOrderItem> orderItems = orderInfo.getOrderItems();
        for (TbOrderItem tbOrderItem : orderItems) {
            String odId = jedisClient.incr(ORDER_DETAIL_ID_GEN_KEY).toString();
            tbOrderItem.setId(odId);
            tbOrderItem.setOrderId(orderId);
            orderItemMapper.insert(tbOrderItem);
        }
        TbOrderShipping orderShipping = orderInfo.getOrderShipping();
        orderShipping.setOrderId(orderId);
        orderShipping.setCreated(new Date());
        orderShipping.setUpdated(new Date());
        orderShippingMapper.insert(orderShipping);
        return ResultModel.ok(orderId);
    }

}

