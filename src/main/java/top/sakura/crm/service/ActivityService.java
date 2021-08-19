package top.sakura.crm.service;

import top.sakura.crm.pojo.Activity;
import top.sakura.crm.pojo.Page;

import java.io.Serializable;
import java.util.List;

/**
 * @author leoLW
 * @Description
 * @create 2021-08-03 20:05
 */
public interface ActivityService {

    Page getPage(Page page);

    void save(Activity activity);

    void update(Activity activity);

    void delete(Serializable... ids);

    Activity getById(Serializable id);

    List<Activity> getAll();

    void importExcel(List<Activity> list);
}
