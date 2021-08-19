package top.sakura.crm.service;

import top.sakura.crm.pojo.Type;

import java.util.List;

/**
 * @author leoLW
 * @Description
 * @create 2021-07-30 20:58
 */
public interface TypeService {
    void save(Type type);

    List getAll();

    void update(Type type);

    void delete(String[] codes);

    Type getByCode(String code);
}
