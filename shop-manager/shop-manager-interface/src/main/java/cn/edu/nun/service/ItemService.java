package cn.edu.nun.service;

import cn.edu.nun.common.pojo.DataModel;
import cn.edu.nun.pojo.TbItem;

public interface ItemService {

    TbItem getItemById(long itemId);
    DataModel getItemList(int page, int rows);

}
