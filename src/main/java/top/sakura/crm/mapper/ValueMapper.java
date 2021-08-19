package top.sakura.crm.mapper;

import top.sakura.crm.pojo.Value;

import java.util.List;

public interface ValueMapper {
    void save(Value value);

    List getAll();

    void update(Value value);

    void delete(String[] ids);

    Value getById(String id);
}
