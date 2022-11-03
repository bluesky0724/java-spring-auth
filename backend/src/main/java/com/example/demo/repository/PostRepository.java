package com.example.demo.repository;

import com.example.demo.data.Comment;
import com.example.demo.data.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PostRepository extends MongoRepository<Post, String> {
}
