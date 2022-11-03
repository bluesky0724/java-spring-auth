package com.example.demo.dto;

import org.bson.types.ObjectId;

import javax.validation.constraints.NotBlank;

public class CreateCommentDto {
    @NotBlank
    public String postId;

    public String text;

}
