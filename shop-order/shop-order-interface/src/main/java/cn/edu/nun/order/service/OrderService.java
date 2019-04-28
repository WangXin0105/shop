package cn.edu.nun.order.service;

import cn.edu.nun.common.pojo.DataModel;
import cn.edu.nun.common.utils.ResultModel;
import cn.edu.nun.order.pojo.OrderInfo;
import cn.edu.nun.pojo.TbOrderItem;

import java.util.List;

public interface OrderService {

    ResultModel createOrder(OrderInfo orderInfo);
    DataModel getOrderList(int page, int rows);
    DataModel dectOrder(String ids, int page, int rows);
    ResultModel sendOrder(String ids);
    ResultModel endOrder(String ids);
    ResultModel deleteOrder(String ids);
}
