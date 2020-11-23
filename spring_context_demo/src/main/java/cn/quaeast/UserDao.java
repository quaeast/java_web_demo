package cn.quaeast;

import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.lang.reflect.Method;

@Component
@ToString
public class UserDao {
    @Autowired
    private DataSource dataSource;

    public User findUserById(Long id) {
        Method[] methods = this.getClass().getMethods();
        for (Method m : methods) {
            System.out.println(m);
        }
        return null;
    }
}
