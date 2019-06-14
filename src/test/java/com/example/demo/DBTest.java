package com.example.demo;

import com.example.demo.db.DB;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 〈测试连接数据库〉
 *
 * @author jinbiao
 * @create 2019.06.13
 */
public class DBTest extends DemoApplicationTests {

    @Autowired
    private DB db;

    @Test
    public void connectMysqlTest() throws SQLException {
        Connection connection = DriverManager.getConnection(db.getUrl(), db.getUsername(), db.getPassword());
        System.out.println(connection);
        String sql = "insert into userinfo(user_id,user_name,password) values(10010,'Em','123')";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        System.out.println(preparedStatement);
        int res=preparedStatement.executeUpdate();
        System.out.println(res);
    }

}
