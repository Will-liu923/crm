package top.sakura.crm.mapper;

import org.apache.ibatis.annotations.Param;
import top.sakura.crm.pojo.Dept;

import java.io.Serializable;
import java.util.List;

public interface DeptMapper {
    int count();

    List getLimit(@Param("start") Integer start,
                  @Param("length") Integer length);

    List getAll();

    void save(Dept dept);

    Dept getById(Serializable id);

    void update(Dept dept);

    void delete(Serializable... ids);
}
