package top.sakura.crm.service;

import top.sakura.crm.pojo.Value;

import java.util.List;

public interface ValueService {
    void save(Value value);

    List getAll();

    void update(Value value);

    void delete(String[] ids);

    Value getById(String id);
}
