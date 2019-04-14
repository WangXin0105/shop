package cn.edu.nun.order.interceptor;

import cn.edu.nun.cart.service.CartService;
import cn.edu.nun.common.utils.CookieUtils;
import cn.edu.nun.common.utils.JsonUtils;
import cn.edu.nun.common.utils.ResultModel;
import cn.edu.nun.pojo.TbItem;
import cn.edu.nun.pojo.TbUser;
import cn.edu.nun.sso.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Value("${SSO_URL}")
    private String SSO_URL;

    @Autowired
    private TokenService tokenService;
    @Autowired
    private CartService cartService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = CookieUtils.getCookieValue(request, "token");
        if (StringUtils.isBlank(token)) {
            response.sendRedirect(SSO_URL + "/page/login?redirect=" + request.getRequestURL());
            return false;
        }
        ResultModel result = tokenService.getUserByToken(token);
        if (result.getStatus() != 200) {
            response.sendRedirect(SSO_URL + "/page/login?redirect=" + request.getRequestURL());
            return false;
        }
        TbUser user = (TbUser) result.getData();
        request.setAttribute("user", user);
        String jsonCartList = CookieUtils.getCookieValue(request, "cart", true);
        if (StringUtils.isNoneBlank(jsonCartList)) {
            cartService.mergeCart(user.getId(), JsonUtils.jsonToList(jsonCartList, TbItem.class));
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {

    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {

    }



}

