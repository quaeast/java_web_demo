package cn.quaeast.config;

import cn.quaeast.MyApplication;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


@Configuration
@ComponentScan(basePackages = "cn.quaeast")
public class MyApplicationContextConfiguration {

    @Bean
    public DataSource dataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("123456@Abcdefg");
        dataSource.setURL("jdbc:mysql://localhost:3306/testDB");
        return dataSource;
    }

    // use @ComponentScan replaced this block of code
    // by default @ComponentScan scans @Component from the itself as the root of the tree
    // but we could ues basePackageClasses to change that root.
    // @ComponentScan(basePackages = "cn.quaeast") or
    // @ComponentScan(basePackageClasses = MyApplication.class)
//    @Bean
//    public UserDao userDao() { // (3)
//        return new UserDao(dataSource());
//    }


}
