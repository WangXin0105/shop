package cn.edu.nun.service;

import cn.edu.nun.common.pojo.TreeDataModel;

import java.util.List;

public interface ItemCatService {

    List<TreeDataModel> getItemCatList(Long parentId);

}
