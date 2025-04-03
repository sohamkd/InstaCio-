package com.InstaCio.services;


import com.InstaCio.dtos.PostDto;
import com.InstaCio.entities.Post;

import java.util.List;

public interface PostService {

    PostDto createNewPost(PostDto postDto, Integer userId);

    //user have to delete its own post
    void deletePost(Integer postId,Integer userId) ;

    List<PostDto> findPostByUserId(Integer userId);

    PostDto findPostById(Integer postId);

    List<PostDto> findAllPost();

    PostDto savedPost(Integer postId,Integer userId) ;

    PostDto likePost(Integer postId,Integer userId);
}
