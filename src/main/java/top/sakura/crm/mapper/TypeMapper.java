package top.sakura.crm.mapper;

import top.sakura.crm.pojo.Type;

import java.util.List;

public interface TypeMapper {
    void save(Type type);

    List getAll();

    void update(Type type);

    void delete(String[] codes);

    Type getByCode(String code);
}
