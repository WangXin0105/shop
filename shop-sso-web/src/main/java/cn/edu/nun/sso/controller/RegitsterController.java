package cn.edu.nun.sso.controller;

import cn.edu.nun.common.utils.ResultModel;
import cn.edu.nun.pojo.TbUser;
import cn.edu.nun.sso.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RegitsterController {

    @Autowired
    private RegisterService registerService;

    @RequestMapping("/page/register")
    public String showRegister() {
        return "register";
    }

    @RequestMapping("/user/check/{param}/{type}")
    @ResponseBody
    public ResultModel checkData(@PathVariable String param, @PathVariable Integer type) {
        ResultModel result = registerService.checkData(param,type);
        return result;
    }

    @RequestMapping(value="/user/register", method=RequestMethod.POST)
    @ResponseBody
    public ResultModel register(TbUser user){
        ResultModel result = registerService.register(user);
        return result;
    }
}
