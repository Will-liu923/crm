package top.sakura.crm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.sakura.crm.mapper.DeptMapper;
import top.sakura.crm.pojo.Dept;
import top.sakura.crm.pojo.Page;
import top.sakura.crm.service.DeptService;

import java.io.Serializable;
import java.util.List;

/**
 * @author leoLW
 * @Description
 * @create 2021-08-02 17:08
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    /*currentPage         = 1;        //当前页码
    rowsPerPage         = 10;       //每页记录数
    maxRowsPerPage      = 100;      //每页最大展示记录数
    visiblePageLinks    = 10;       //显示几个卡片
    totalPages;                     //总页数
    totalRows;                      //总记录数
    date;                           //分页展示的数据，使用limit查询*/
    //分页查询并返回
    @Override
    public Page getPage(Page page) {
        Integer totalRows = deptMapper.count();
        page.setTotalRows(totalRows);
        Integer totalPages = (page.getTotalRows() - 1) / page.getRowsPerPage() + 1;
        page.setTotalPages(totalPages);
        List limit = deptMapper.getLimit((page.getCurrentPage() - 1) * page.getRowsPerPage(),
                page.getRowsPerPage());

        page.setData(limit);
        return page;
    }

    @Override
    public List getAll() {
        return deptMapper.getAll();
    }

    @Override
    public void save(Dept dept) {
        deptMapper.save(dept);
    }

    @Override
    public Dept getById(Serializable id) {
        return deptMapper.getById(id);
    }

    @Override
    public void update(Dept dept) {
        deptMapper.update(dept);
    }

    @Override
    public void delete(Serializable... ids) {
        deptMapper.delete(ids);
    }
}
