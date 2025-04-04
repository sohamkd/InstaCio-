package com.InstaCio.services;

import com.InstaCio.dtos.CommentDto;


public interface CommentService {

    public CommentDto createComment(CommentDto commentDto, Integer postId, Integer userId) ;

    public CommentDto findCommentById(Integer commentId) ;
    public CommentDto likeComment(Integer commentId,Integer userId);

}
