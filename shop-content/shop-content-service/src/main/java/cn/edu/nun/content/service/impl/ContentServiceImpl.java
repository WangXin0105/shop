package cn.edu.nun.content.service.impl;

import cn.edu.nun.common.jedis.JedisClient;
import cn.edu.nun.common.pojo.DataModel;
import cn.edu.nun.common.utils.JsonUtils;
import cn.edu.nun.common.utils.ResultModel;
import cn.edu.nun.content.service.ContentService;
import cn.edu.nun.mapper.TbContentMapper;
import cn.edu.nun.pojo.TbContent;
import cn.edu.nun.pojo.TbContentExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper contentMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${CONTENT_LIST}")
    private String CONTENT_LIST;

    @Override
    public ResultModel addContent(TbContent content) {
        content.setCreated(new Date());
        content.setUpdated(new Date());
        contentMapper.insert(content);

        //缓存同步,删除缓存中对应的数据。
        jedisClient.hdel(CONTENT_LIST, content.getCategoryId().toString());
        return ResultModel.ok();
    }

    @Override
    public List<TbContent> getContentListByCid(long cid) {
        try {
            String json = jedisClient.hget(CONTENT_LIST, cid + "");
            if (StringUtils.isNotBlank(json)) {
                List<TbContent> list = JsonUtils.jsonToList(json, TbContent.class);
                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(cid);
        List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);

        try {
            jedisClient.hset(CONTENT_LIST, cid + "", JsonUtils.objectToJson(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public DataModel getContentList(int page, int rows) {
        DataModel dataModel = new DataModel();
        PageHelper.startPage(page, rows);
        TbContentExample example = new TbContentExample();
        List<TbContent> list = contentMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(list);
        dataModel.setTotal(pageInfo.getTotal());
        dataModel.setRows(list);
        return dataModel;
    }

    @Override
    public ResultModel deleteContent(long id) {
        TbContent content = contentMapper.selectByPrimaryKey(id);
        contentMapper.deleteByPrimaryKey(id);
        //缓存同步,删除缓存中对应的数据。
        jedisClient.hdel(CONTENT_LIST, content.getCategoryId().toString());
        return ResultModel.ok();
    }

    @Override
    public ResultModel updateItem(TbContent content) {
        TbContent tbContent = contentMapper.selectByPrimaryKey(content.getId());
        BeanUtils.copyProperties(content,tbContent);
        contentMapper.updateByPrimaryKeySelective(tbContent);
        //缓存同步,删除缓存中对应的数据。
        jedisClient.hdel(CONTENT_LIST, content.getCategoryId().toString());
        return ResultModel.ok();
    }

}
