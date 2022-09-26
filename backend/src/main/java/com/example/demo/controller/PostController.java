package com.example.demo.controller;

import com.example.demo.data.Like;
import com.example.demo.data.Post;
import com.example.demo.dto.CreatePostDto;
import com.example.demo.dto.response.MessageResponse;
import com.example.demo.security.jwt.JwtUtils;
import com.example.demo.service.PostService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private JwtUtils jwtUtils;

//    @PostMapping("/create")
//    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
//    public Post createPost(@RequestBody CreatePostDto postDto, @RequestHeader(HttpHeaders.AUTHORIZATION) String headerAuth) {
//        String authToken;
//        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
//            authToken = headerAuth.substring(7, headerAuth.length());
//            String userName = jwtUtils.getUserNameFromJwtToken(authToken);
//            Post post = new Post(userName, postDto.text);
//
//            return postService.save(post);
//        }
//        return null;
//    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> createPost(@RequestBody CreatePostDto postDto, @RequestHeader(HttpHeaders.AUTHORIZATION) String headerAuth) {
        String authToken;
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            authToken = headerAuth.substring(7, headerAuth.length());
            String userName = jwtUtils.getUserNameFromJwtToken(authToken);
            Post post = new Post(userName, postDto.text);
            return ResponseEntity.ok(postService.save(post));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("invalid credential"));
    }


    @GetMapping("/all")
    public List<Post> findAll() {
        return postService.findAll();
    }


}
