package com.example.demo.data;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

// Todo entity validation
public class Comment {
    @Id
    public String id;
    public String userName;

    public String text;

    public Date date;

    public Comment(String userName, String text, String id ){
        this.id = id;
        this.userName = userName;
        this.text = text;
        this.date = new Date();
    }
}
