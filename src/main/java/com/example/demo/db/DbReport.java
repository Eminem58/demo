package com.example.demo.db;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.sql.*;

/**
 *  [数据库报表] 
 *  @author 金彪
 *  @date 2019年07月03日
 *  @version 1.0
 *  
 */
@Component
public class DbReport {

    @Autowired
    private Db db;

    /**
     * [读取clob]
     * @author 金彪
     * @date 2019年07月03日
     * @param
     * @return
     * @version 1.0
     */
    public void getClob() throws SQLException, IOException {
        Connection connection = db.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from report");
        if (resultSet.next()) {
            Blob blob = resultSet.getBlob("clob_attr");
            InputStream inputStream = blob.getBinaryStream();
            OutputStream outputStream =new FileOutputStream("file.txt");
            int len;
            byte[] b=new byte[1024];
            while((len=inputStream.read(b))!=-1){
                outputStream.write(b,0,len);
            }

            BufferedReader bf=new BufferedReader(new InputStreamReader(inputStream));
            String temp;
            StringBuffer sb=new StringBuffer();
            while((temp=bf.readLine())!=null){
                sb.append(temp).append("\n");
            }
            Object obj=JSON.parse(sb.toString());
            System.out.println(inputStream);
        }
    }
    /**
     * [插入clob]
     * @author 金彪
     * @date 2019年07月03日
     * @param
     * @return
     * @version 1.0
     */
    public void insertClob() throws SQLException, IOException {
        Connection connection = db.getConnection();
        Statement statement = connection.createStatement();
        FileInputStream in = new FileInputStream("C:\\Users\\Administrator\\Desktop\\客服\\接口\\a.txt");
        String sql = "insert into report(report_id,clob_attr) values(?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,1);
        preparedStatement.setBlob(2,in);
        preparedStatement.executeUpdate();
    }
}
