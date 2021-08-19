package top.sakura.crm.mapper;

import org.apache.ibatis.annotations.Param;
import top.sakura.crm.pojo.Activity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface ActivityMapper {
    List getLimit(@Param("start") Integer start,
                  @Param("length") Integer length,
                  @Param("data") Map<String, String> condition);

    int count(@Param("data") Map<String, String> condition);

    void save(Activity activity);

    void update(Activity activity);

    void delete(Serializable... ids);

    Activity getById(Serializable id);

    List<Activity> getAll();

    void saveList(List<Activity> list);
}
