package cn.edu.nun.sso.service.impl;

import cn.edu.nun.common.jedis.JedisClient;
import cn.edu.nun.common.utils.JsonUtils;
import cn.edu.nun.common.utils.ResultModel;
import cn.edu.nun.pojo.TbUser;
import cn.edu.nun.sso.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private JedisClient jedisClient;

    @Value("${SESSION_EXPIRE}")
    private Integer SESSION_EXPIRE;

    @Override
    public ResultModel getUserByToken(String token) {
        //根据token到redis中取用户信息
        String json = jedisClient.get("SESSION:" + token);

        if (StringUtils.isBlank(json)) {
            return ResultModel.build(201, "用户登录已经过期");
        }
        jedisClient.expire("SESSION:" + token, SESSION_EXPIRE);
        //返回结果，E3Result其中包含TbUser对象
        TbUser user = JsonUtils.jsonToPojo(json, TbUser.class);
        return ResultModel.ok(user);
    }

}

