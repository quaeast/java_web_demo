package cn.quaeast;

import com.sun.jndi.ldap.sasl.LdapSasl;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
//@Data
@Getter
@Setter
@ToString
public class UserDao {
//    @Autowired
    private DataSource dataSource;

    public UserDao(@Autowired DataSource dataSource){
        this.dataSource = dataSource;
    }
    public User findUserById(Long id){
        return null;
    }
}
