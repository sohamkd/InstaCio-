package com.InstaCio.controllers;

import com.InstaCio.dtos.ApiResponseMsg;
import com.InstaCio.dtos.PostDto;
import com.InstaCio.dtos.UserDto;
import com.InstaCio.entities.User;
import com.InstaCio.services.PostService;
import com.InstaCio.services.UserService;
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

    @Autowired
    public UserService userService;

    @PostMapping
    public ResponseEntity<PostDto> creatPost(@RequestHeader("Authorization") String jwt,@RequestBody PostDto postDto)
    {
        UserDto userByJwt = userService.findUserByJwt(jwt);
        PostDto newPost = postService.createNewPost(postDto,userByJwt.getId());
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponseMsg> deletePost(@PathVariable int postId,@RequestHeader("Authorization") String jwt)
    {
        UserDto userByJwt = userService.findUserByJwt(jwt);
        postService.deletePost(postId,userByJwt.getId());
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

    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> savedPostByUser(@PathVariable int postId,@RequestHeader("Authorization") String jwt)
    {
        UserDto userByJwt = userService.findUserByJwt(jwt);
        PostDto postDto = postService.savedPost(postId, userByJwt.getId());
        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }

    @PutMapping("/like/{postId}")
    public ResponseEntity<PostDto> likePostByUser(@PathVariable int postId,@RequestHeader("Authorization") String jwt)
    {
        UserDto userByJwt = userService.findUserByJwt(jwt);
        PostDto postDto = postService.likePost(postId, userByJwt.getId());
        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }
}
