package com.example.demo.data;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class User {
    @Id
    public String _id;

    // Todo: Unique, required
    public String userName;

    public String email;

    // Todo: required
    public String password;

    // Todo: default now.
    public Date createdAt;

    public User(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.createdAt = new Date();
    }

}
