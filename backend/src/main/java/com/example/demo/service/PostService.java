package com.example.demo.service;

import com.example.demo.data.Like;
import com.example.demo.data.Post;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepo;

    public Post save(Post post) {
        return postRepo.save(post);
    }

    public List<Post> findAll() {
        return postRepo.findAll();
    }

}
