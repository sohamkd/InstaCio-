package com.InstaCio.services.impl;

import com.InstaCio.dtos.PostDto;
import com.InstaCio.dtos.UserDto;
import com.InstaCio.entities.Post;
import com.InstaCio.entities.User;
import com.InstaCio.exceptions.ResourceNotFoundException;
import com.InstaCio.repository.PostRepository;
import com.InstaCio.repository.UserRepository;
import com.InstaCio.services.PostService;
import com.InstaCio.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public PostDto createNewPost(PostDto postDto, Integer userId) {

        /*UserDto userById = userService.findUserById(userId);
        User user = modelMapper.map(userById, User.class);
        */

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found with given id"));

        Post post = modelMapper.map(postDto, Post.class);
        post.setUser(user);
        post.setCreatedAt(LocalDateTime.now());
        Post createdPost = postRepository.save(post);
        return modelMapper.map(createdPost,PostDto.class);
    }

    @Override
    public void deletePost(Integer postId, Integer userId) {
        PostDto postById = findPostById(postId);
        UserDto userById = userService.findUserById(userId);

        Post post = modelMapper.map(postById, Post.class);
        User user = modelMapper.map(userById, User.class);
        if(post.getUser().getId()!=user.getId())
        {
            throw new RuntimeException("You can not delete another user's post");
        }

        postRepository.delete(post);
    }

    @Override
    public List<PostDto> findPostByUserId(Integer userId) {

        List<Post> post = postRepository.findPostByUserId(userId);
        List<PostDto> post1 = post.stream().map(pst -> new ModelMapper().map(pst, PostDto.class)).collect(Collectors.toList());
        return post1;
    }

    @Override
    public PostDto findPostById(Integer postId) {

        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found with given id"));
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> findAllPost() {
        List<Post> all = postRepository.findAll();
        List<PostDto> collect = all.stream().map(allPosts -> new ModelMapper().map(allPosts, PostDto.class)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public PostDto savedPost(Integer postId, Integer userId) {
        PostDto postById = findPostById(postId);
        UserDto userById = userService.findUserById(userId);

        Post post = modelMapper.map(postById, Post.class);
        User user = modelMapper.map(userById, User.class);

        if(user.getSavedPost().contains(post))
        {
            user.getSavedPost().remove(post);
        }
        else {
            user.getSavedPost().add(post);
        }

        User user1 = userRepository.save(user);
        return postById;
    }

    @Override
    public PostDto likePost(Integer postId, Integer userId) {

        PostDto postById = findPostById(postId);
        UserDto userById = userService.findUserById(userId);

        Post post = modelMapper.map(postById, Post.class);
        User user = modelMapper.map(userById, User.class);

        if(post.getLiked().contains(user))
        {
            post.getLiked().remove(user);
        }
        else {
            post.getLiked().add(user);
        }
        Post savedPost = postRepository.save(post);
        return modelMapper.map(savedPost, PostDto.class);
    }
}
