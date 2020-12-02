package cn.quaeast;


import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        // 显式注册1，不必使用
        //DriverManager.registerDriver(new Driver());
        // 显式注册2，不必使用
        //Class.forName("com.mysql.cj.jdbc.Driver");
        String JDBC_URL = "jdbc:mysql://localhost:3306/testDB";
        String JDBC_USER = "root";
        String JDBC_PASSWORD = "123456@Abcde";
        System.out.println(DriverManager.getDrivers());
        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        try (connection) {
            connection.setAutoCommit(false);
            String sql = "select * from person;";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            connection.commit();
            while (resultSet.next()) {
                System.out.println("id:" + resultSet.getLong("id"));
            }
        } catch (SQLException e) {
            connection.rollback();
        }
    }
}
