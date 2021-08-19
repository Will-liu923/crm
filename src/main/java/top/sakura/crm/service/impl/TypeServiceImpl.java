package top.sakura.crm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import top.sakura.crm.mapper.TypeMapper;
import top.sakura.crm.pojo.Type;
import top.sakura.crm.service.TypeService;

import java.util.List;

/**
 * @author leoLW
 * @Description
 * @create 2021-07-30 20:59
 */
@Repository
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeMapper typeMapper;

    @Override
    public void save(Type type) {
        typeMapper.save(type);
    }

    @Override
    public List getAll() {
        return typeMapper.getAll();
    }

    @Override
    public void update(Type type) {
        typeMapper.update(type);
    }

    @Override
    public void delete(String[] codes) {
        typeMapper.delete(codes);
    }

    @Override
    public Type getByCode(String code) {
        return typeMapper.getByCode(code);
    }
}
