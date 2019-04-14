package cn.edu.nun.order.service;

import cn.edu.nun.common.utils.ResultModel;
import cn.edu.nun.order.pojo.OrderInfo;

public interface OrderService {

    ResultModel createOrder(OrderInfo orderInfo);

}
