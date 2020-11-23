package cn.quaeast;

import cn.quaeast.config.MyApplicationContextConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

public class MyApplication {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(MyApplicationContextConfiguration.class);
//        ApplicationContext ctx = new AnnotationConfigApplicationContext();
        UserDao userDao = ctx.getBean(UserDao.class);
        DataSource dataSource = ctx.getBean(DataSource.class);
        System.out.println( userDao);
    }
}
