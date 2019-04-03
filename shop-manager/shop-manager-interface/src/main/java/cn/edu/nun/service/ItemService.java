package cn.edu.nun.service;

import cn.edu.nun.common.pojo.DataModel;
import cn.edu.nun.common.utils.ResultModel;
import cn.edu.nun.pojo.TbItem;
import cn.edu.nun.pojo.TbItemDesc;

public interface ItemService {

    TbItem getItemById(long itemId);
    DataModel getItemList(int page, int rows);
    ResultModel addItem(TbItem item, String desc);
    TbItemDesc getItemDescById(long itemId);
    ResultModel updateItem(TbItem item, String desc);
    ResultModel deleteItem(long ids);

}
