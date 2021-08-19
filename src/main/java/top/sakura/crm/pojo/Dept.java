package top.sakura.crm.pojo;

import top.sakura.crm.util.UUIDUtil;

/**
 * @author leoLW
 * @Description 部门信息
 * @create 2021-08-02 17:02
 */
public class Dept {

    private String id;
    private String no;
    private String name;
    private String manager;
    private String description;
    private String phone;

    @Override
    public String toString() {
        return "Dept{" +
                "id='" + id + '\'' +
                ", no='" + no + '\'' +
                ", name='" + name + '\'' +
                ", manager='" + manager + '\'' +
                ", description='" + description + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public String getId() {
        //部门id唯一标识
        if (id == null) {
            id = UUIDUtil.getUUID();
        }
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Dept(String id, String no, String name, String manager, String description, String phone) {
        this.id = id;
        this.no = no;
        this.name = name;
        this.manager = manager;
        this.description = description;
        this.phone = phone;
    }

    public Dept() {
    }
}
