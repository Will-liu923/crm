package top.sakura.crm.mapper;

import org.apache.ibatis.annotations.Param;
import top.sakura.crm.pojo.User;

import java.util.List;


public interface UserMapper {
    User getUser(@Param("loginAct") String username,
                 @Param("loginPwd") String password);

    List getAll();
}
