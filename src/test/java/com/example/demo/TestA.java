package com.example.demo;

import com.example.demo.db.Db;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  [] 
 *  @author 金彪
 *  @date 2019年07月19日
 *  @version 1.0
 *  
 */
public class TestA<T> extends DemoApplicationTests {
    @Autowired
    Db db;

    private List list = new ArrayList<>();
    private List<T> list2;

    @Test
    public void batchInsert(){
        String driverClassName = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://10.145.208.245:12007/DEV_CRM_SRV?useSSL=false&characterEncoding=utf-8&rewriteBatchedStatements=true";
        String user = "dev_srv_app";
        String password = "HSNT&a1o";
        Connection conn = null;
        PreparedStatement pstm =null;
        ResultSet rt = null;
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now;
        try {
            Class.forName(driverClassName);
            conn = DriverManager.getConnection(url, user, password);
            String sql = "INSERT INTO complaint(complaint_id,complaint_num,create_date,create_staff) values(?,?,?,?)";
            pstm = conn.prepareStatement(sql);
            Long startTime = System.currentTimeMillis();
            conn.setAutoCommit(false);
            for (int i = 0; i < 2000000; i++) {
                now = new Date();
                pstm.setLong(1, 200000000000L+i);
                pstm.setString(2, "3-"+System.nanoTime());
                pstm.setString(3,sf.format(now));
                pstm.setLong(4,21108825L);
                pstm.addBatch();
                now = null;
            }
            pstm.executeBatch();
            conn.commit();
            Long endTime = System.currentTimeMillis();
            System.out.println("用时：" + (endTime - startTime));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally{
            try {
                if(pstm!=null){
                    pstm.close();
                }
                if(conn!=null){
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public <T> void syso() {
        System.out.println(list);
        System.out.println(list2);
        System.out.println(db);
        System.out.println(db.getUrl());
        System.out.println(StringUtils.uncapitalize("Ab2dDfg"));
        System.out.println("************");

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        Field[] declaredFields = this.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            //System.out.println(applicationContext.getBean(field.getName()));
            System.out.println(field.getName());
        }
        System.out.println("************");

        String url = "www";
        url = new Db().getUrl();
        System.out.println(url);

        System.out.println("************");
        HashMap map = new HashMap();
        map.put("a","1");
        map.put("b","2");
        System.out.println(map.toString());


        Pattern pattern2 = Pattern.compile("^-[1]|[0-7]");
        Matcher matcher = pattern2.matcher("0");
        Matcher matcher2 = pattern2.matcher("-1");
        Matcher matcher3 = pattern2.matcher("-2");
        Matcher matcher4 = pattern2.matcher("-a");
        System.out.println(matcher.matches());
        System.out.println(matcher2.matches());
        System.out.println(matcher3.matches());
        System.out.println(matcher4.matches());


        System.out.println("************");
        T a = null;
        String b = (String) a;
        T c = (T) b;
        System.out.println(a instanceof String);
        System.out.println(b instanceof String);

        System.out.println("***aa*********");
        System.out.println("1"+new StringBuffer().toString());
        System.out.println("2"+new StringBuffer().substring(0,0));
        //System.out.println("3"+new StringBuffer().substring(0,-1));



        System.out.println("************");
        long a2=22L;
        Long a3=null;
        //System.out.println(a2==a3);
        System.out.println("".split(","));
        System.out.println("".split(",").length);

        int m = 2;
        Long n = (long)m;
        System.out.println(m+"-"+n);
    }
}
