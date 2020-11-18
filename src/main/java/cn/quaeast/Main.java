package cn.quaeast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException {
        String JDBC_URL = "jdbc:mysql://localhost:3306/testDB";
        String JDBC_USER = "root";
        String JDBC_PASSWORD = "123456@Abcdefg";
        Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        conn.close();
    }
}
