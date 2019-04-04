package cn.edu.nun.sso.service.impl;

import cn.edu.nun.common.jedis.JedisClient;
import cn.edu.nun.common.utils.JsonUtils;
import cn.edu.nun.common.utils.ResultModel;
import cn.edu.nun.mapper.TbUserMapper;
import cn.edu.nun.pojo.TbUser;
import cn.edu.nun.pojo.TbUserExample;
import cn.edu.nun.sso.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.UUID;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private TbUserMapper userMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${SESSION_EXPIRE}")
    private Integer SESSION_EXPIRE;

    @Override
    public ResultModel userLogin(String username, String password) {
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<TbUser> list = userMapper.selectByExample(example);
        if (list == null || list.size() == 0) {
            return ResultModel.build(400, "用户名或密码错误");
        }
        TbUser user = list.get(0);
        if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
            return ResultModel.build(400, "用户名或密码错误");
        }
        String token = UUID.randomUUID().toString();
        user.setPassword(null);

        jedisClient.set("SESSION:" + token, JsonUtils.objectToJson(user));
        jedisClient.expire("SESSION:" + token, SESSION_EXPIRE);
        return ResultModel.ok(token);
    }

}
