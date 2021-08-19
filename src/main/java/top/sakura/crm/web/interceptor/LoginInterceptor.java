package top.sakura.crm.web.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author leoLW
 * @Description
 * @create 2021-07-30 20:36
 */
public class LoginInterceptor implements HandlerInterceptor {

    //权限控制，如果用户没有登录，无法访问其他页面
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("loginUser");
        if (user == null) {
            response.sendRedirect("/login.html");
            return false;
        }
        return true;
    }
}
