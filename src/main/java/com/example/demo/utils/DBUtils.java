package com.example.demo.utils;

import com.example.demo.db.Db;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 〈数据库工具类〉
 *
 * @author jinbiao
 * @create 2019.06.14
 */
public class DBUtils {

    private Connection connection;

    @Autowired
    private Db db;

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(db.getUrl(), db.getUsername(), db.getPassword());
    }

}
