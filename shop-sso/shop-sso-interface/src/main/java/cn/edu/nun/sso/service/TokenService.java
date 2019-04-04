package cn.edu.nun.sso.service;

import cn.edu.nun.common.utils.ResultModel;

public interface TokenService {
    ResultModel getUserByToken(String token);
}
