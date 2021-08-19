package top.sakura.crm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.sakura.crm.mapper.ValueMapper;
import top.sakura.crm.pojo.Value;
import top.sakura.crm.service.ValueService;

import java.util.List;

/**
 * @author leoLW
 * @Description
 * @create 2021-08-02 8:25
 */
@Service
public class ValueServiceImpl implements ValueService {

    @Autowired
    private ValueMapper valueMapper;

    @Override
    public void save(Value value) {
        valueMapper.save(value);
    }

    @Override
    public List getAll() {
        return valueMapper.getAll();
    }

    @Override
    public void update(Value value) {
        valueMapper.update(value);
    }

    @Override
    public void delete(String[] ids) {
        valueMapper.delete(ids);
    }

    @Override
    public Value getById(String id) {
        return valueMapper.getById(id);
    }
}
