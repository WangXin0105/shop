package cn.edu.nun.service.impl;

import cn.edu.nun.common.pojo.DataModel;
import cn.edu.nun.common.utils.IDUtils;
import cn.edu.nun.common.utils.ResultModel;
import cn.edu.nun.mapper.TbItemDescMapper;
import cn.edu.nun.mapper.TbItemMapper;
import cn.edu.nun.pojo.TbItem;
import cn.edu.nun.pojo.TbItemDesc;
import cn.edu.nun.pojo.TbItemExample;
import cn.edu.nun.service.ItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;

    @Autowired
    private TbItemDescMapper itemDescMapper;

    @Override
    public ResultModel getItemById(long itemId) {
        return ResultModel.ok(itemMapper.selectByPrimaryKey(itemId));
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

    @Override
    public ResultModel addItem(TbItem item, String desc) {
        Long id = IDUtils.getItemId();
        item.setId(id);
        item.setStatus((byte) 1);
        Date date = new Date();
        item.setCreated(date);
        item.setUpdated(date);
        itemMapper.insert(item);
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemId(id);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(date);
        itemDesc.setUpdated(date);
        itemDescMapper.insert(itemDesc);
        return ResultModel.ok(id);
    }

    @Override
    public ResultModel getItemDescById(long itemId) {
        return ResultModel.ok(itemDescMapper.selectByPrimaryKey(itemId));
    }

    @Override
    public ResultModel updateItem(TbItem item, String desc) {
        TbItem tbItem = itemMapper.selectByPrimaryKey(item.getId());
        TbItemDesc tbItemDesc = itemDescMapper.selectByPrimaryKey(item.getId());
        BeanUtils.copyProperties(item,tbItem);
        tbItemDesc.setItemDesc(desc);
        itemMapper.updateByPrimaryKeySelective(tbItem);
        itemDescMapper.updateByPrimaryKeySelective(tbItemDesc);
        return ResultModel.ok();
    }

    @Override
    public ResultModel deleteItem(long ids) {
        itemMapper.deleteByPrimaryKey(ids);
        itemDescMapper.deleteByPrimaryKey(ids);
        return ResultModel.ok();
    }
}
