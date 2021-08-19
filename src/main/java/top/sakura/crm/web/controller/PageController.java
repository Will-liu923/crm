package top.sakura.crm.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import top.sakura.crm.pojo.User;
import top.sakura.crm.service.UserService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author leoLW
 * @Description
 * @create 2021-07-30 20:21
 */
@Controller
public class PageController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private UserService userService;

    //所有请求需要判断用户是否登录，否则跳转到登录界面
    @RequestMapping("/")
    public String root(@CookieValue(value = "JAss01a", required = false) String username, //获取存入到Cookie的值,false表示没有Cookie也不会报错
                       @CookieValue(value = "ddDDid1", required = false) String password) {
        if (StringUtils.isNoneBlank(username,password)) { //如果都不为空
            String ip = request.getRemoteAddr();
            User user = userService.getUserForAutoLogin(username,password,ip);

            if (user != null) {
                request.getSession().setAttribute("loginUser",user);
                return "redirect:/workbench/index.html";
            }
        }

        return "redirect:/login.html";
    }
}
