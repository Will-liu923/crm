package top.sakura.crm.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.sakura.crm.pojo.Dept;
import top.sakura.crm.pojo.Page;
import top.sakura.crm.service.DeptService;
import top.sakura.crm.web.Result;

import java.util.Map;

/**
 * @author leoLW
 * @Description
 * @create 2021-08-02 19:55
 */
@RestController
@RequestMapping("dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @PostMapping("save.do")
    public Map save(Dept dept) {
        deptService.save(dept);
        return Result.SUCCESS;
    }

    //分页查询
    @GetMapping("page.json")
    public Page getPage(Page page) {
        return deptService.getPage(page);
    }

    @GetMapping("get.do")
    public Dept getById(String id) {
        return deptService.getById(id);
    }

    @DeleteMapping("delete.do")
    public Map delete(String[] ids) {
        deptService.delete(ids);
        return Result.SUCCESS;
    }

    @PutMapping("update.do")
    public Map update(Dept dept) {
        deptService.update(dept);
        return Result.SUCCESS;
    }
}
