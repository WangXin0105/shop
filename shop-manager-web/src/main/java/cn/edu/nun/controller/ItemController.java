package cn.edu.nun.controller;

import cn.edu.nun.common.pojo.DataModel;
import cn.edu.nun.common.utils.ResultModel;
import cn.edu.nun.pojo.TbItem;
import cn.edu.nun.pojo.TbItemDesc;
import cn.edu.nun.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/item/query/{itemId}")
    @ResponseBody
    public ResultModel getItemById(@PathVariable Long itemId) {
        ResultModel result = itemService.getItemById(itemId);
        return result;
    }

    @RequestMapping("/item/list")
    @ResponseBody
    public DataModel getItemList(Integer page, Integer rows) {
        DataModel dataModel = itemService.getItemList(page, rows);
        return dataModel;
    }

    @RequestMapping("/item/save")
    @ResponseBody
    public ResultModel saveItem(TbItem item, String desc) {
        ResultModel result = itemService.addItem(item, desc);
        return result;
    }

    @RequestMapping("/item/desc/{itemId}")
    @ResponseBody
    public ResultModel getItemDescById(@PathVariable Long itemId) {
        ResultModel result = itemService.getItemDescById(itemId);
        return result;
    }

    @RequestMapping("/item/update")
    @ResponseBody
    public ResultModel updateItem(TbItem item, String desc) {
        ResultModel result = itemService.updateItem(item, desc);
        return result;
    }

    @RequestMapping("/item/delete")
    @ResponseBody
    public ResultModel deleteItem(Long ids) {
        ResultModel result = itemService.deleteItem(ids);
        return result;
    }
}
