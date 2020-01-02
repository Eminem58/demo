package com.example.demo.excel.poi;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * []
 *
 * @author 金彪
 * @version 1.0
 * @date 2019/12/29
 */
@Repository
public class ExcelDao {
    public List<User> getUserList(){
        String sql = "select * from user";
        return null;
    }
}
