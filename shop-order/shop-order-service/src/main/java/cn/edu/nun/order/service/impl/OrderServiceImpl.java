package cn.edu.nun.order.service.impl;

import cn.edu.nun.common.jedis.JedisClient;
import cn.edu.nun.common.pojo.DataModel;
import cn.edu.nun.common.utils.ResultModel;
import cn.edu.nun.mapper.TbOrderItemMapper;
import cn.edu.nun.mapper.TbOrderMapper;
import cn.edu.nun.mapper.TbOrderShippingMapper;
import cn.edu.nun.order.pojo.OrderInfo;
import cn.edu.nun.order.service.OrderService;
import cn.edu.nun.pojo.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    @Override
    public DataModel getOrderList(int page, int rows) {
        DataModel dataModel = new DataModel();
        PageHelper.startPage(page, rows);
        TbOrderExample tbItemExample = new TbOrderExample();
        List<TbOrder> tbOrders = orderMapper.selectByExample(tbItemExample);
        PageInfo pageInfo = new PageInfo(tbOrders);
        dataModel.setTotal(pageInfo.getTotal());
        dataModel.setRows(tbOrders);
        return dataModel;
    }

    @Override
    public DataModel dectOrder(String ids, int page, int rows) {
        DataModel dataModel = new DataModel();
        PageHelper.startPage(page, rows);
        TbOrderItemExample orderItemExample = new TbOrderItemExample();
        TbOrderItemExample.Criteria criteria = orderItemExample.createCriteria();
        criteria.andOrderIdEqualTo(ids);
        List<TbOrderItem> list = orderItemMapper.selectByExample(orderItemExample);
        PageInfo pageInfo = new PageInfo(list);
        dataModel.setTotal(pageInfo.getTotal());
        dataModel.setRows(list);
        return dataModel;
    }

    @Override
    public ResultModel sendOrder(String ids) {
        TbOrder tbOrder = orderMapper.selectByPrimaryKey(ids);
        tbOrder.setStatus(4);
        orderMapper.updateByPrimaryKeySelective(tbOrder);
        return ResultModel.ok();
    }

    @Override
    public ResultModel endOrder(String ids) {
        TbOrder tbOrder = orderMapper.selectByPrimaryKey(ids);
        tbOrder.setStatus(5);
        orderMapper.updateByPrimaryKeySelective(tbOrder);
        return ResultModel.ok();
    }

    @Override
    public ResultModel deleteOrder(String ids) {
        orderMapper.deleteByPrimaryKey(ids);
        return ResultModel.ok();
    }

}

