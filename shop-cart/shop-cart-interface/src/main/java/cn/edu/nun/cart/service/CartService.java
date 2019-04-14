package cn.edu.nun.cart.service;

import cn.edu.nun.common.utils.ResultModel;
import cn.edu.nun.pojo.TbItem;

import java.util.List;

public interface CartService {

    ResultModel addCart(long userId, long itemId, int num);
    ResultModel mergeCart(long userId, List<TbItem> itemList);
    List<TbItem> getCartList(long userId);
    ResultModel updateCartNum(long userId, long itemId, int num);
    ResultModel deleteCartItem(long userId, long itemId);
    ResultModel clearCartItem(long userId);

}
