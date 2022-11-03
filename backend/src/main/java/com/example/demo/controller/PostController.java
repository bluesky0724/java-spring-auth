package com.example.demo.controller;

import com.example.demo.data.Comment;
import com.example.demo.data.Like;
import com.example.demo.data.Post;
import com.example.demo.dto.CreateCommentDto;
import com.example.demo.dto.CreatePostDto;
import com.example.demo.dto.response.MessageResponse;
import com.example.demo.exception.EntityNotFoundException;
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
import java.util.ArrayList;
import java.util.Iterator;
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

    @PostMapping("/addComment")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> addComment(@RequestBody CreateCommentDto commentDto, @RequestHeader(HttpHeaders.AUTHORIZATION) String headerAuth) {
        try {
            if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
                String authToken = headerAuth.substring(7, headerAuth.length());
                String userName = jwtUtils.getUserNameFromJwtToken(authToken);
                Post post = postService.findById(commentDto.postId).orElseThrow(EntityNotFoundException::new);
                String commentId = (new ObjectId()).toString();
                Comment comment = new Comment(userName, commentDto.text, commentId);
                post.comments.add(comment);
                return ResponseEntity.ok( postService.save(post));
            }
            return ResponseEntity.badRequest().body(new MessageResponse("invalid credential"));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse("Cannot find Post"));
        }
    }

    @DeleteMapping("/deleteComment/{postId}/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable String postId, @PathVariable String commentId, @RequestHeader(HttpHeaders.AUTHORIZATION) String headerAuth) {
        String userName = jwtUtils.getUserNameFromJwtToken(headerAuth.substring(7, headerAuth.length()));
        Post post = postService.findById(postId).orElseThrow(EntityNotFoundException::new);
        Iterator<Comment> itr = post.comments.iterator();
        while(itr.hasNext()){
            Comment comment = itr.next();
            if(commentId.equals(comment.id ))
                if(userName.equals(comment.userName)) return ResponseEntity.badRequest().body(new MessageResponse("Cannot remove other's comment"));
                itr.remove();
        }

        return ResponseEntity.ok(postService.save(post));
    }
}
