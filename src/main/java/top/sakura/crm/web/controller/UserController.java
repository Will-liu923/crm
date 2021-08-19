package top.sakura.crm.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.sakura.crm.pojo.User;
import top.sakura.crm.service.UserService;
import top.sakura.crm.util.MD5Util;
import top.sakura.crm.web.Result;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author leoLW
 * @Description
 * @create 2021-07-29 20:27
 */
@RestController //相当于@Controller + @ResponseBody
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest request;

    @PostMapping("login.do")
    public Map login(String username, String password, Boolean autoLogin, HttpServletResponse response) {
        String ip = request.getRemoteAddr();
        /*
        Controller只做正常情况的响应，其他问题一律交给业务层处理
        */
        User user = userService.getUser(username, password, ip);
        request.getSession().setAttribute("loginUser",user);

        //免登录，将账户存储到Cookie中
        if (autoLogin) {
            Cookie cookie1 = new Cookie("JAss01a", username);
            cookie1.setMaxAge(3600 * 24 * 10);
            cookie1.setPath("/");

            //密码加密存储
            Cookie cookie2 = new Cookie("ddDDid1", MD5Util.getMD5(password));
            cookie2.setMaxAge(3600 * 24 * 10);
            cookie2.setPath("/");

            response.addCookie(cookie1);
            response.addCookie(cookie2);
        }

        return Result.SUCCESS;
    }

    @GetMapping("list.json")
    public List getAll() {
        return userService.getAll();
    }
}
