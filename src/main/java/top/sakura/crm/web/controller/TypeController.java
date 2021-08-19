package top.sakura.crm.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.sakura.crm.pojo.Type;
import top.sakura.crm.service.TypeService;
import top.sakura.crm.web.Result;

import java.util.List;
import java.util.Map;

/**
 * @author leoLW
 * @Description
 * @create 2021-07-30 20:51
 */
@RestController
@RequestMapping("type")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @PostMapping("save.do")
    public Map save(Type type) {
        typeService.save(type);
        return Result.SUCCESS;
    }

    @GetMapping("list.json")
    public List getList() {
        return typeService.getAll();
    }

    @PutMapping("update.do")
    public Map update(Type type) {
        typeService.update(type);
        return Result.SUCCESS;
    }

    @DeleteMapping("delete.do")
    public Map delete(String[] codes) {
        typeService.delete(codes);
        return Result.SUCCESS;
    }

    @GetMapping("get.do")
    public Type getByCode(String code) {
        return typeService.getByCode(code);
    }
}
