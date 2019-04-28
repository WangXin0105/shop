package cn.edu.nun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminLoginController {

    @RequestMapping("/admin/login")
    public String showLogin() {
        return "login";
    }

}
