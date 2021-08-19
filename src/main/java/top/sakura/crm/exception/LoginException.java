package top.sakura.crm.exception;

/**
 * @author leoLW
 * @Description 自定义异常，描述登录过程中可能出现的异常
 * @create 2021-07-29 19:58
 */

public class LoginException extends RuntimeException {
    public LoginException() {
    }

    public LoginException(String message) {
        super(message);
    }
}
