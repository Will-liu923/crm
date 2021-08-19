package top.sakura.crm.service;

import top.sakura.crm.pojo.Dept;
import top.sakura.crm.pojo.Page;

import java.io.Serializable;
import java.util.List;

public interface DeptService {

    List getAll();
    Page getPage(Page page);

    void save(Dept dept);

    Dept getById(Serializable id);

    void update(Dept dept);

    void delete(Serializable... ids);
}
