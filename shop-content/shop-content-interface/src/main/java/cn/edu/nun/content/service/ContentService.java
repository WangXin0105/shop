package cn.edu.nun.content.service;

import cn.edu.nun.common.utils.ResultModel;
import cn.edu.nun.pojo.TbContent;

import java.util.List;

public interface ContentService {

    ResultModel addContent(TbContent content);
    List<TbContent> getContentListByCid(long cid);

}
