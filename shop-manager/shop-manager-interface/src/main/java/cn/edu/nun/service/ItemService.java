package cn.edu.nun.service;

import cn.edu.nun.common.pojo.DataModel;
import cn.edu.nun.common.utils.ResultModel;
import cn.edu.nun.pojo.TbItem;

public interface ItemService {

    ResultModel getItemById(long itemId);
    DataModel getItemList(int page, int rows);
    ResultModel addItem(TbItem item, String desc);
    ResultModel getItemDescById(long itemId);
    ResultModel updateItem(TbItem item, String desc);
    ResultModel deleteItem(long ids);

}
