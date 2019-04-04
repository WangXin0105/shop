package cn.edu.nun.sso.controller;

import cn.edu.nun.common.utils.CookieUtils;
import cn.edu.nun.common.utils.ResultModel;
import cn.edu.nun.sso.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Value("${TOKEN_KEY}")
    private String TOKEN_KEY;

    @RequestMapping("/page/login")
    public String showLogin() {
        return "login";
    }

    @RequestMapping(value="/user/login", method=RequestMethod.POST)
    @ResponseBody
    public ResultModel login(String username, String password,
                          HttpServletRequest request, HttpServletResponse response) {
        ResultModel result = loginService.userLogin(username, password);
        if(result.getStatus() == 200) {
            String token = result.getData().toString();
            //如果登录成功需要把token写入cookie
            CookieUtils.setCookie(request, response, TOKEN_KEY, token);
        }
        return result;
    }
}
