package com.example.demo.excel.poi;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * []
 *
 * @author 金彪
 * @version 1.0
 * @date 2019/12/29
 */

@Service
public class ExcelService {
    /**
     * 网评结果中的导出
     * @author lemon
     * @since 2019/11/1 0001
     */
    public ExcelData getOutputExcelProjectData() {

        ExcelDao excelDao = new ExcelDao();

        //Excel实体类
        ExcelData excelData = new ExcelData();

        //找到表中需要添加的数据
        List<User> list = excelDao.getUserList();

        //设置sheet名称
        excelData.setName("用户信息");

        //设置表头字段
        List<String> titles = new ArrayList<String>();
        titles.add("Id");
        titles.add("账号");
        titles.add("密码");
        excelData.setTitles(titles);

        //把expertUserList转换为List<List<Object>> rows
        List<List<Object>> rows = new ArrayList<List<Object>>();

        //加入数据
        for (User user : list) {
            List<Object> row = new ArrayList<Object>();
            row.add(user.getPassWord());
            row.add(user.getUserName());
            row.add(user.getPassWord());
            rows.add(row);
        }

        //设置要输出的记录
        excelData.setRows(rows);
        return excelData;
    }
}