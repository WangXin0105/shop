package cn.edu.nun.content.service;

import cn.edu.nun.common.pojo.DataModel;
import cn.edu.nun.common.utils.ResultModel;
import cn.edu.nun.pojo.TbContent;

import java.util.List;

public interface ContentService {

    ResultModel addContent(TbContent content);
    List<TbContent> getContentListByCid(long cid);
    DataModel getContentList(int page, int rows);
    ResultModel deleteContent(long id);
    ResultModel updateItem(TbContent content);

}
