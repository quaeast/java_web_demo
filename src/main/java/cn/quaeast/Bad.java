package cn.quaeast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Bad {
    public static void main(String[] args) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/aaa", "root", "");
        Statement st = conn.createStatement();
        conn.close();
    }
}