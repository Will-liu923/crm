package top.sakura.crm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.sakura.crm.mapper.ActivityMapper;
import top.sakura.crm.pojo.Activity;
import top.sakura.crm.pojo.Page;
import top.sakura.crm.service.ActivityService;

import java.io.Serializable;
import java.util.List;

/**
 * @author leoLW
 * @Description
 * @create 2021-08-03 20:07
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public Page getPage(Page page) {
        //获取符合检锁条件的总记录数
        int count = activityMapper.count(page.getCondition());
        page.setTotalRows(count);
        //计算总页数
        int pages = (page.getTotalRows() - 1) / page.getRowsPerPage() + 1;
        page.setTotalPages(pages);
        //获取符合检锁条件的数据
        List limit = activityMapper.getLimit((page.getCurrentPage() - 1) * page.getRowsPerPage(), page.getRowsPerPage(), page.getCondition());
        page.setData(limit);
        return page;
    }

    @Override
    public void save(Activity activity) {
        activityMapper.save(activity);
    }

    @Override
    public void update(Activity activity) {
        activityMapper.update(activity);
    }

    @Override
    public void delete(Serializable... ids) {
        activityMapper.delete(ids);
    }

    @Override
    public Activity getById(Serializable id) {
        return activityMapper.getById(id);
    }

    @Override
    public List<Activity> getAll() {
        return activityMapper.getAll();
    }

    @Override
    public void importExcel(List<Activity> list) {
        activityMapper.saveList(list);
    }
}
