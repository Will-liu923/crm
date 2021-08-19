package top.sakura.crm.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.sakura.crm.pojo.Value;
import top.sakura.crm.service.ValueService;
import top.sakura.crm.web.Result;

import java.util.List;
import java.util.Map;

/**
 * @author leoLW
 * @Description
 * @create 2021-08-02 8:25
 */
@RestController
@RequestMapping("value")
public class ValueController {
    @Autowired
    private ValueService valueService;

    @GetMapping("list.json")
    public List getAll() {
        return valueService.getAll();
    }

    @PostMapping("save.do")
    public Map save(Value value) {
        valueService.save(value);
        return Result.SUCCESS;
    }

    @PutMapping("update.do")
    public Map update(Value value) {
        System.out.println(value);
        valueService.update(value);
        return Result.SUCCESS;
    }

    @DeleteMapping("delete.do")
    public Map delete(String[] ids) {
        valueService.delete(ids);
        return Result.SUCCESS;
    }

    @GetMapping("get.do")
    public Value getById(String id) {
        return valueService.getById(id);
    }
}
