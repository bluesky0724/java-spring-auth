package com.example.demo.data;

import org.bson.types.ObjectId;

import java.util.Date;

// Todo entity validation
public class Comment {
    ObjectId user;

    String text;

    String name;

    String avatar;

    Date date;
}
