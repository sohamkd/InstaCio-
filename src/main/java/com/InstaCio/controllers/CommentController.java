package com.InstaCio.controllers;

import com.InstaCio.dtos.CommentDto;
import com.InstaCio.dtos.UserDto;
import com.InstaCio.entities.Comment;
import com.InstaCio.entities.User;
import com.InstaCio.services.CommentService;
import com.InstaCio.services.UserService;
import org.aspectj.asm.IModelFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @PostMapping("/{postId}")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable int postId, @RequestHeader("Authorization")String jwt)
    {
        UserDto userByJwt = userService.findUserByJwt(jwt);
        CommentDto createdComment = commentService.createComment(commentDto, postId, userByJwt.getId());

        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }


    @PutMapping("/like/{commentId}")
    public ResponseEntity<CommentDto> likeCommentHandler(@PathVariable Integer commentId, @RequestHeader("Authorization")String jwt) {
        System.out.println("----------- like comment id ---------- ");
        UserDto user = userService.findUserByJwt(jwt);
        CommentDto commentDto = commentService.likeComment(commentId, user.getId());
        return new ResponseEntity<CommentDto>(commentDto,HttpStatus.OK);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentDto> findCommentById(@PathVariable int commentId)
    {
        CommentDto commentById = commentService.findCommentById(commentId);
        return new ResponseEntity<>(commentById,HttpStatus.OK);
    }

}
