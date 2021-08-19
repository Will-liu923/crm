package top.sakura.crm.pojo;

import top.sakura.crm.util.UUIDUtil;

/**
 * @author leoLW
 * @Description
 * @create 2021-07-31 17:30
 */
public class Value {
    private String id;
    private String value;
    private String text;
    private String orderNo;
    private String typeCode;

    public Value() {
    }

    public Value(String id, String value, String text, String orderNo, String typeCode) {
        this.id = id;
        this.value = value;
        this.text = text;
        this.orderNo = orderNo;
        this.typeCode = typeCode;
    }

    @Override
    public String toString() {
        return "Value{" +
                "id='" + id + '\'' +
                ", value='" + value + '\'' +
                ", text='" + text + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", typeCode='" + typeCode + '\'' +
                '}';
    }

    public String getId() {
        if (id == null) {
            //生成主键，唯一标识
            id = UUIDUtil.getUUID();
        }
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }
}
