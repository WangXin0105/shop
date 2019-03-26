package cn.edu.nun.content.service.impl;

import cn.edu.nun.common.utils.ResultModel;
import cn.edu.nun.content.service.ContentService;
import cn.edu.nun.mapper.TbContentMapper;
import cn.edu.nun.pojo.TbContent;
import cn.edu.nun.pojo.TbContentExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper contentMapper;

    @Override
    public ResultModel addContent(TbContent content) {
        content.setCreated(new Date());
        content.setUpdated(new Date());
        contentMapper.insert(content);
        return ResultModel.ok();
    }

    @Override
    public List<TbContent> getContentListByCid(long cid) {
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(cid);
        List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
        return list;
    }

}
