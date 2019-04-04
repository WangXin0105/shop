package cn.edu.nun.sso.service;

import cn.edu.nun.common.utils.ResultModel;
import cn.edu.nun.pojo.TbUser;

public interface RegisterService {
    ResultModel checkData(String param, int type);
    ResultModel register(TbUser user);
}
