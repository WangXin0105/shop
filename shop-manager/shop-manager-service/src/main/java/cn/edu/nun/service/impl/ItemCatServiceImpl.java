package cn.edu.nun.service.impl;

import cn.edu.nun.common.pojo.TreeDataModel;
import cn.edu.nun.mapper.TbItemCatMapper;
import cn.edu.nun.pojo.TbItemCat;
import cn.edu.nun.pojo.TbItemCatExample;
import cn.edu.nun.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper itemCatMapper;

    @Override
    public List<TreeDataModel> getItemCatList(Long parentId) {
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> itemCats = itemCatMapper.selectByExample(example);
        List<TreeDataModel> dataModels = new ArrayList<>();
        for (TbItemCat itemCat : itemCats){
            TreeDataModel dataModel = new TreeDataModel();
            dataModel.setId(itemCat.getId());
            dataModel.setText(itemCat.getName());
            dataModel.setState(itemCat.getIsParent()?"closed":"open");
            dataModels.add(dataModel);
        }
        return dataModels;
    }

}
