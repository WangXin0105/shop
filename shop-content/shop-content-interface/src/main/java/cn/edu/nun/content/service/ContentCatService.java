package cn.edu.nun.content.service;

import cn.edu.nun.common.pojo.TreeDataModel;
import cn.edu.nun.common.utils.ResultModel;

import java.util.List;

public interface ContentCatService {

    List<TreeDataModel> getContentCatList(long parentId);
    ResultModel addContentCategory(long parentId, String name);

}
