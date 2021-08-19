package top.sakura.crm.web;

import java.util.HashMap;
import java.util.Map;

/**
 * @author leoLW
 * @Description
 * @create 2021-07-29 20:32
 */
public class Result {
    public static final Map SUCCESS = new HashMap(){{
       put("success",true);
       put("msg","操作成功");
    }};
}
