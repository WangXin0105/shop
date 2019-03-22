package cn.edu.nun.controller;

import cn.edu.nun.common.pojo.TreeDataModel;
import cn.edu.nun.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping("/item/cat/list")
    @ResponseBody
    public List<TreeDataModel> getItemCatList(@RequestParam(value="id", defaultValue="0")Long parentId) {

        List<TreeDataModel> list = itemCatService.getItemCatList(parentId);
        return list;
    }

}

