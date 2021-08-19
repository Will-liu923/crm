package top.sakura.crm.web.advice;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.sakura.crm.exception.LoginException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author leoLW
 * @Description
 * @create 2021-07-29 20:33
 */
@ControllerAdvice
public class MyExceptionHandler {

    //登录异常
    @ExceptionHandler(LoginException.class)
    @ResponseBody
    public Map loginException(Exception e) {
        return new HashMap(){{
            put("success",false);
            put("msg",e.getMessage());
        }};
    }

    //其他异常，暂做处理
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Map exception(Exception e) {
        e.printStackTrace();
        return new HashMap(){{
            put("success",false);
            put("msg",e.getMessage());
        }};
    }
}
