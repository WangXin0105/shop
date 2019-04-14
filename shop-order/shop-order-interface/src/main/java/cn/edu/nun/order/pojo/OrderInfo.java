package cn.edu.nun.order.pojo;

import cn.edu.nun.pojo.TbOrder;
import cn.edu.nun.pojo.TbOrderItem;
import cn.edu.nun.pojo.TbOrderShipping;

import java.io.Serializable;
import java.util.List;

public class OrderInfo extends TbOrder implements Serializable {

    private List<TbOrderItem> orderItems;
    private TbOrderShipping orderShipping;
    public List<TbOrderItem> getOrderItems() {
        return orderItems;
    }
    public void setOrderItems(List<TbOrderItem> orderItems) {
        this.orderItems = orderItems;
    }
    public TbOrderShipping getOrderShipping() {
        return orderShipping;
    }
    public void setOrderShipping(TbOrderShipping orderShipping) {
        this.orderShipping = orderShipping;
    }

}
