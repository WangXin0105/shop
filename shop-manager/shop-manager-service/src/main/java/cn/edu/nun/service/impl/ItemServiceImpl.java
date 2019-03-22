package cn.edu.nun.service.impl;

import cn.edu.nun.common.pojo.DataModel;
import cn.edu.nun.mapper.TbItemMapper;
import cn.edu.nun.pojo.TbItem;
import cn.edu.nun.pojo.TbItemExample;
import cn.edu.nun.service.ItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;

    @Override
    public TbItem getItemById(long itemId) {
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(itemId);
        List<TbItem> list = itemMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public DataModel getItemList(int page, int rows) {
        DataModel dataModel = new DataModel();
        PageHelper.startPage(page, rows);
        TbItemExample tbItemExample = new TbItemExample();
        List<TbItem> tbItems = itemMapper.selectByExample(tbItemExample);
        PageInfo pageInfo = new PageInfo(tbItems);
        dataModel.setTotal(pageInfo.getTotal());
        dataModel.setRows(tbItems);
        return dataModel;
    }

}
