package cn.quaeast;

import javax.persistence.*;

@Entity
@Table(name="users")
public static class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    //Getters and setters are omitted for brevity
}