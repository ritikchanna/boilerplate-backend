package com.ritik.boilerplatebackend.endpoints.user.pojo;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    protected @Column(nullable = false, unique = true)
    String username;
    protected @Column(nullable = false)
    String password;
    protected @Column(nullable = false)
    String firstName;
    protected String lastName;
    protected @ElementCollection()
    Set<Role> roles;
    protected @Column(nullable = false)
    Boolean isActive;
    protected @Column(nullable = false, unique = true)
    String email;
    public User(String username, String firstName, String lastName, String email, String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roles = new HashSet<>();
        roles.add(Role.GUEST);
        this.isActive = true;
        this.password = password;
    }
    public User() {

    }


    public enum Role {SUPERADMIN, ADMIN, MODERATOR, CREATOR, USER, GUEST}
}
