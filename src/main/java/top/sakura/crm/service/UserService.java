package top.sakura.crm.service;

import top.sakura.crm.pojo.User;

import java.util.List;

public interface UserService {
    User getUser(String username, String password, String ip);

    User getUserForAutoLogin(String username, String password, String ip);

    List getAll();
}
