package top.sakura.crm.web.controller;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.sakura.crm.pojo.Activity;
import top.sakura.crm.pojo.Page;
import top.sakura.crm.pojo.User;
import top.sakura.crm.service.ActivityService;
import top.sakura.crm.util.DateTimeUtils;
import top.sakura.crm.web.Result;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author leoLW
 * @Description
 * @create 2021-08-03 20:50
 */
@RestController
@RequestMapping("act")
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    //分页展示 -- 这里的请求方式应该是get，但是使用了@RequestBody注解获取请求中的JSON数据
    //导致只能使用post请求-----因此对于get请求，则不能使用@RequestBody注解
    //只有post请求的时候才能使用@RequestBody这个注解
    //前端封装Map类型的数据格式为: data['name'] : value
    //get请求是没有请求体的，但是默写工具可以携带请求体，如postman
    /*@PostMapping("page.json")
    public Page getPage(@RequestBody Page page) {
        return activityService.getPage(page);
    }*/
    @GetMapping("page.json")
    public Page getPage(Page page) {
        return activityService.getPage(page);
    }

    @GetMapping("get.do")
    public Activity getById(String id) {
        return activityService.getById(id);
    }

    @PostMapping("save.do")
    public Map save(Activity activity, HttpSession session) {
        //创建人
        User createBy = (User) session.getAttribute("loginUser");
        //创建时间
        String createTime = DateTimeUtils.SDF_10.format(System.currentTimeMillis());
        activity.setCreateBy(createBy.getName());
        activity.setCreateTime(createTime);
        activityService.save(activity);
        return Result.SUCCESS;
    }

    @PutMapping("update.do")
    public Map update(Activity activity, HttpSession session) {
        //修改人 - session中获取，修改时间 - 当前时间
        User editBy = (User)session.getAttribute("loginUser");
        String createTime = DateTimeUtils.SDF_10.format(System.currentTimeMillis());
        activity.setEditBy(editBy.getName());
        activity.setEditTime(createTime);
        activityService.update(activity);
        return Result.SUCCESS;
    }

    @DeleteMapping("delete.do")
    public Map delete(String[] ids) {
        activityService.delete(ids);
        return Result.SUCCESS;
    }

    //导出为excel，类似文件下载 - 文件下载返回值使用void
    @GetMapping("export.do")
    public void export(HttpServletResponse response) throws IOException {
        /*//文件下载的案例
        //1.读取被下载的文件
        FileInputStream in = new FileInputStream("D:\\test\\test.avi");
        //设置文件输出格式及名称
        response.setHeader("Content-Disposition", "attachment;filename=test.avi");
        //获取输出流
        ServletOutputStream out = response.getOutputStream();
        //读
        byte[] bytes = new byte[4096];
        int len;
        while((len = in.read(bytes)) != -1) {
            out.write(bytes,0,len);
        }
        out.close();
        in.close();*/
        //1.使用POI创建EXCEL
        HSSFWorkbook excel = new HSSFWorkbook();
        //2.创建一个excel-sheet
        HSSFSheet sheet = excel.createSheet();
        //3.创建行，第一行(0)为标题行
        int rowIndex = 0;
        HSSFRow row = sheet.createRow(rowIndex++);
        //4.在第一行创建列-创建标题
        int cellIndex = 0;
        row.createCell(cellIndex++).setCellValue("名称"); //第一行第1列
        row.createCell(cellIndex++).setCellValue("所有者");  //第一行第2列
        row.createCell(cellIndex++).setCellValue("开始时间"); //第一行第3列
        row.createCell(cellIndex++).setCellValue("结束时间"); //第一行第4列

        //5.查询需要导出的数据
        List<Activity> list = activityService.getAll();

        //6.根据查询结果生成数据
        for (Activity activity : list) {
            //每写入一条数据之前都需要创建一个新的行
            row = sheet.createRow(rowIndex++);
            //每一行生成数据都是从第一列开始
            cellIndex = 0;
            row.createCell(cellIndex++).setCellValue(activity.getName());
            row.createCell(cellIndex++).setCellValue(activity.getOwner());
            row.createCell(cellIndex++).setCellValue(activity.getStartDate());
            row.createCell(cellIndex++).setCellValue(activity.getEndDate());
        }

        //7.设置输出格式
        response.setHeader("Content-Disposition", "attachment;filename=activity.xls");
        excel.write(response.getOutputStream());
        excel.close();
    }

    //导入EXCEL-文件上传
    @PostMapping("import.do")
    public Map importExcel(MultipartFile upFile, HttpSession session) throws IOException {
        //获取创建人-创建时间
        String createBy = ((User) session.getAttribute("loginUser")).getName();
        String createTime = DateTimeUtils.SDF_10.format(System.currentTimeMillis());
        //创建List集合，从excel读取的数据存入到集合中，统一存储到数据库中
        List<Activity> list = new ArrayList<>();

        //读取提交的excel文件
        HSSFWorkbook excel = new HSSFWorkbook(upFile.getInputStream());
        //读取sheet - 按照索引读取，如果指定sheet的name也可以根据name获取sheet
        HSSFSheet sheet = excel.getSheetAt(0);
        //读取每一行，从第一行开始读
        Row row;
        int rowIndex = 0;
        while ((row = sheet.getRow(rowIndex++)) != null) {
            //每次读取读从第一列开始读取
            int cellIndex = 0;
            String name = row.getCell(cellIndex++).getStringCellValue();
            String owner = row.getCell(cellIndex++).getStringCellValue();
            String startDate = row.getCell(cellIndex++).getStringCellValue();
            String endDate = row.getCell(cellIndex++).getStringCellValue();
            //将数据封装成pojo，统一存储到集合中，统一存储到数据库中
            Activity activity = new Activity();
            activity.setName(name);
            activity.setOwner(owner);
            activity.setStartDate(startDate);
            activity.setEndDate(endDate);
            activity.setCreateBy(createBy);
            activity.setCreateTime(createTime);
            list.add(activity);
        }

        //将数据存储到数据库中
        activityService.importExcel(list);
        return Result.SUCCESS;
    }
}
