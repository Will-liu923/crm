package top.sakura.crm.pojo;

import java.util.List;
import java.util.Map;

/**
 * @author leoLW
 * @Description
 * @create 2021-08-02 16:53
 */
/*
* 分页查询，将分页插件所需的参数（检锁条件，分页相关的-当前页码，每页展示记录数）与页面展示的数据封装成一个类
* */
public class Page {

    private Integer currentPage         = 1;        //当前页码
    private Integer rowsPerPage         = 10;       //每页记录数
    private Integer maxRowsPerPage      = 100;      //每页最大展示记录数
    private Integer visiblePageLinks    = 10;       //显示几个卡片
    private Integer totalPages;                     //总页数
    private Integer totalRows;                      //总记录数
    private List data;                              //分页展示的数据，使用limit查询
    private Map<String, String> condition;          //分页展示时的检锁条件(可选：有的分页查询直接查询所有，有的按条件查询)

    public Page() {
    }

    public Page(Integer currentPage, Integer rowsPerPage, Integer maxRowsPerPage, Integer visiblePageLinks, Integer totalPages, Integer totalRows, List data, Map condition) {
        this.currentPage = currentPage;
        this.rowsPerPage = rowsPerPage;
        this.maxRowsPerPage = maxRowsPerPage;
        this.visiblePageLinks = visiblePageLinks;
        this.totalPages = totalPages;
        this.totalRows = totalRows;
        this.data = data;
        this.condition = condition;
    }

    public Map getCondition() {
        return condition;
    }

    public void setCondition(Map condition) {
        this.condition = condition;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getRowsPerPage() {
        return rowsPerPage;
    }

    public void setRowsPerPage(Integer rowsPerPage) {
        this.rowsPerPage = rowsPerPage;
    }

    public Integer getMaxRowsPerPage() {
        return maxRowsPerPage;
    }

    public void setMaxRowsPerPage(Integer maxRowsPerPage) {
        this.maxRowsPerPage = maxRowsPerPage;
    }

    public Integer getVisiblePageLinks() {
        return visiblePageLinks;
    }

    public void setVisiblePageLinks(Integer visiblePageLinks) {
        this.visiblePageLinks = visiblePageLinks;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Integer totalRows) {
        this.totalRows = totalRows;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Page{" +
                "currentPage=" + currentPage +
                ", rowsPerPage=" + rowsPerPage +
                ", maxRowsPerPage=" + maxRowsPerPage +
                ", visiblePageLinks=" + visiblePageLinks +
                ", totalPages=" + totalPages +
                ", totalRows=" + totalRows +
                ", data=" + data +
                ", condition=" + condition +
                '}';
    }
}
