package cn.edu.nun.sso.service.impl;

import cn.edu.nun.common.utils.ResultModel;
import cn.edu.nun.mapper.TbUserMapper;
import cn.edu.nun.pojo.TbUser;
import cn.edu.nun.pojo.TbUserExample;
import cn.edu.nun.sso.service.RegisterService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private TbUserMapper userMapper;

    @Override
    public ResultModel checkData(String param, int type) {
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        if (type == 1){
            criteria.andUsernameEqualTo(param);
        } else if (type == 2){
            criteria.andPhoneEqualTo(param);
        } else if (type == 3) {
            criteria.andEmailEqualTo(param);
        } else {
            return ResultModel.build(400,"数据类型错误");
        }
        List<TbUser> list = userMapper.selectByExample(example);
        if (list != null && list.size() > 0){
            return ResultModel.ok(false);
        }
        return ResultModel.ok(true);
    }

    @Override
    public ResultModel register(TbUser user) {
        if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())
                || StringUtils.isBlank(user.getPhone())) {
            return ResultModel.build(400, "用户数据不完整，注册失败");
        }
        ResultModel result = checkData(user.getUsername(), 1);
        if (!(boolean) result.getData()) {
            return ResultModel.build(400, "此用户名已经被占用");
        }
        result = checkData(user.getPhone(), 2);
        if (!(boolean)result.getData()) {
            return ResultModel.build(400, "手机号已经被占用");
        }
        user.setCreated(new Date());
        user.setUpdated(new Date());
        String md5Pass = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(md5Pass);
        userMapper.insert(user);
        return ResultModel.ok();
    }

}
