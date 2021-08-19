package top.sakura.crm.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.sakura.crm.exception.LoginException;
import top.sakura.crm.mapper.UserMapper;
import top.sakura.crm.pojo.User;
import top.sakura.crm.service.UserService;
import top.sakura.crm.util.DateTimeUtils;
import top.sakura.crm.util.MD5Util;

import java.text.ParseException;
import java.util.List;

/**
 * @author leoLW
 * @Description
 * @create 2021-07-29 19:45
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUser(String username, String password, String ip) {

        //对密码进行MD5加密
        password = MD5Util.getMD5(password);

        //MyBatis操作数据库，查询用户
        User user = userMapper.getUser(username, password);

        //如果用户为空，说明用户名或者密码错误
        if (user == null) {
            throw new LoginException("用户名或密码错误");
        }

        //判断是否过期-为空则永不失效
        String expireTime = user.getExpireTime();
        //使用Spring提供的工具类，见pom.xml
        if (StringUtils.isNotBlank(expireTime)) {
            long nowTime = System.currentTimeMillis();
            try {
                long expire = DateTimeUtils.SDF_19.parse(expireTime).getTime();
                if (nowTime > expire) {
                    throw new LoginException("账号已过期");
                }
            } catch (ParseException e) {
                //出现异常也作为账号过期处理
                e.printStackTrace();
                throw new LoginException("账号已过期");
            }
        }

        //判断账号是否被锁定-不为1就是被锁定
        String lockStatus = user.getLockStatus();
        if (!"1".equals(lockStatus)) {
            throw new LoginException("账号被锁定");
        }

        //判断IP是否被限制访问，为空不限制，ip之间使用逗号隔开
        String allowIps = user.getAllowIps();
        if (StringUtils.isNotBlank(allowIps)) {
            /*
             * 127.0.0.123 若使用String的index或者contains方法则会出现漏洞
             * 127.0.0.1 因此可以将字符串转换为数组处理，逗号切割
             * */
            String[] ips = allowIps.split(",");
            //使用spring提供的工具类将数组转换成集合
            List list = CollectionUtils.arrayToList(ips);
            if (!list.contains(ip)) {
                throw new LoginException("IP被限制访问");
            }
        }
        return user;
    }


    //免登录校验，若不符合数据库则说明账号出现异常，直接返回null即可
    @Override
    public User getUserForAutoLogin(String username, String password, String ip) {
        //MyBatis操作数据库，查询用户
        User user = userMapper.getUser(username, password);

        //如果用户为空，说明用户名或者密码错误
        if (user == null) {
            return null;
        }

        //判断是否过期-为空则永不失效
        String expireTime = user.getExpireTime();
        //使用Spring提供的工具类，见pom.xml
        if (StringUtils.isNotBlank(expireTime)) {
            long nowTime = System.currentTimeMillis();
            try {
                long expire = DateTimeUtils.SDF_19.parse(expireTime).getTime();
                if (nowTime > expire) {
                    return null;
                }
            } catch (ParseException e) {
                //出现异常也作为账号过期处理
                e.printStackTrace();
                return null;
            }
        }

        //判断账号是否被锁定-不为1就是被锁定
        String lockStatus = user.getLockStatus();
        if (!"1".equals(lockStatus)) {
            return null;
        }

        //判断IP是否被限制访问，为空不限制，ip之间使用逗号隔开
        String allowIps = user.getAllowIps();
        if (StringUtils.isNotBlank(allowIps)) {
            /*
             * 127.0.0.123 若使用String的index或者contains方法则会出现漏洞
             * 127.0.0.1 因此可以将字符串转换为数组处理，逗号切割
             * */
            String[] ips = allowIps.split(",");
            //使用spring提供的工具类将数组转换成集合
            List list = CollectionUtils.arrayToList(ips);
            if (!list.contains(ip)) {
                return null;
            }
        }
        return user;
    }

    @Override
    public List getAll() {
        return userMapper.getAll();
    }
}
