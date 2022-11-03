package com.example.demo.data;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document
public class Post {
    @Id
    public String _id;

    public String userName;

    public String text;

    public List<Like> likes;

    public List<Dislike> dislikes;

    public ArrayList<Comment> comments;
    public Date date;
    public Post(String userName, String text) {
        this.userName = userName;
        this.text = text;
        this.date = new Date();
        this.likes = new ArrayList<Like>();
        this.dislikes = new ArrayList<Dislike>();
        this.comments = new ArrayList<Comment>();
    }

}
