package com.InstaCio.services.impl;

import com.InstaCio.dtos.CommentDto;
import com.InstaCio.dtos.PostDto;
import com.InstaCio.dtos.UserDto;
import com.InstaCio.entities.Comment;
import com.InstaCio.entities.Post;
import com.InstaCio.entities.User;
import com.InstaCio.exceptions.ResourceNotFoundException;
import com.InstaCio.repository.CommentRepository;
import com.InstaCio.repository.PostRepository;
import com.InstaCio.services.CommentService;
import com.InstaCio.services.PostService;
import com.InstaCio.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId, Integer userId) {

        UserDto userById = userService.findUserById(userId);
        PostDto postById = postService.findPostById(postId);

        Comment comment = modelMapper.map(commentDto, Comment.class);
        User user = modelMapper.map(userById, User.class);
        Post post = modelMapper.map(postById, Post.class);

        comment.setUser(user);
        comment.setCreatedAt(LocalDateTime.now());
        Comment savedComment = commentRepository.save(comment);

        post.getComments().add(savedComment);
        postRepository.save(post);

        CommentDto commentDto1 = modelMapper.map(savedComment, CommentDto.class);

        return commentDto1;
    }

    @Override
    public CommentDto findCommentById(Integer commentId) {

        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment not found with given id"));
        CommentDto commentDto = modelMapper.map(comment, CommentDto.class);
        return commentDto;
    }

    @Override
    public CommentDto likeComment(Integer commentId, Integer userId) {

        UserDto userById = userService.findUserById(userId);
        User user = modelMapper.map(userById, User.class);

        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment not found with given id"));

        if(!comment.getLiked().contains(user)) {
            comment.getLiked().add(user);
        }
        else comment.getLiked().remove(user);

        Comment savedComment = commentRepository.save(comment);

        return modelMapper.map(savedComment,CommentDto.class);
    }
}
