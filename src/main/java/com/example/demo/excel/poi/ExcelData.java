package com.example.demo.excel.poi;

import java.io.Serializable;
import java.util.List;

/**
 * []
 *
 * @author 金彪
 * @version 1.0
 * @date 2019/12/29
 */
public class ExcelData implements Serializable {

    // 表头
    private List<String> titles;

    // 数据
    private List<List<Object>> rows;

    // 页签名称
    private String name;

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public List<List<Object>> getRows() {
        return rows;
    }

    public void setRows(List<List<Object>> rows) {
        this.rows = rows;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}