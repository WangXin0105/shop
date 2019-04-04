package cn.edu.nun.sso.service;

import cn.edu.nun.common.utils.ResultModel;

public interface LoginService {
    ResultModel userLogin(String username, String password);
}
