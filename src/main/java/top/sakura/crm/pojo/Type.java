package top.sakura.crm.pojo;

/**
 * @author leoLW
 * @Description
 * @create 2021-07-30 20:46
 */
public class Type {
    private String code;
    private String name;
    private String description;

    @Override
    public String toString() {
        return "Type{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Type() {
    }

    public Type(String code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }
}
