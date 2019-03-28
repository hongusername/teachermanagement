package com.management.cn.chen.controller.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BaseDao {

    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://112.125.89.159:3306/management?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
    String username = "root";
    String password = "root";


    private Connection conn;
    private PreparedStatement ps;

    public void getConn() {
        try {
            Class.forName(driver); //classLoader,加载对应驱动
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void upd() {
        getConn();
        String sql = "UPDATE classes SET STATUS=1";
        try {
            ps = (PreparedStatement) conn.prepareStatement(sql);
            int i = ps.executeUpdate();
            System.out.println("resutl: " + i);
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
