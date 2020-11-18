package cn.quaeast;


import java.sql.*;


public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // 显式注册1，不必使用
        //DriverManager.registerDriver(new Driver());
        // 显式注册2，不必使用
        //Class.forName("com.mysql.cj.jdbc.Driver");
        String JDBC_URL = "jdbc:mysql://localhost:3306/testDB";
        String JDBC_USER = "root";
        String JDBC_PASSWORD = "123456@Abcdefg";
        System.out.println(DriverManager.getDrivers());
        Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        String sql ="select * from person;";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            System.out.println("id:"+resultSet.getLong("id"));
        }

        conn.close();
    }
}
