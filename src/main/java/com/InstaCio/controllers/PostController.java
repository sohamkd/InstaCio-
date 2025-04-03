package com.InstaCio.controllers;

import com.InstaCio.dtos.ApiResponseMsg;
import com.InstaCio.dtos.PostDto;
import com.InstaCio.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/posts/")
public class PostController {

    @Autowired
    public PostService postService;

    @PostMapping("/{userId}")
    public ResponseEntity<PostDto> creatPost(@RequestBody PostDto postDto, @PathVariable int userId)
    {
        PostDto newPost = postService.createNewPost(postDto, userId);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    @DeleteMapping("/{postId}/user/{userId}")
    public ResponseEntity<ApiResponseMsg> deletePost(@PathVariable int postId,@PathVariable int userId)
    {
        postService.deletePost(postId,userId);
        ApiResponseMsg postDeletedSuccessfully = ApiResponseMsg.builder().message("post deleted successfully").success(true).status(HttpStatus.OK).build();
        return new ResponseEntity<>(postDeletedSuccessfully,HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostDto>> findPostByUserId(@PathVariable int userId)
    {
        List<PostDto> postByUserId = postService.findPostByUserId(userId);
        return new ResponseEntity<>(postByUserId,HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> findPostById(@PathVariable int postId)
    {
        PostDto postById = postService.findPostById(postId);
        return new ResponseEntity<>(postById,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PostDto>> findAllPosts()
    {
        List<PostDto> allPost = postService.findAllPost();
        return new ResponseEntity<>(allPost,HttpStatus.OK);
    }

    @PutMapping("/{postId}/user/{userId}")
    public ResponseEntity<PostDto> savedPostByUser(@PathVariable int postId,@PathVariable int userId)
    {
        PostDto postDto = postService.savedPost(postId, userId);
        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }

    @PutMapping("/like/{postId}/user/{userId}")
    public ResponseEntity<PostDto> likePostByUser(@PathVariable int postId,@PathVariable int userId)
    {
        PostDto postDto = postService.likePost(postId, userId);
        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }
}
