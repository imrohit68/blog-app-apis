package com.rohit.blog.backendBlogApp.Service;

import com.rohit.blog.backendBlogApp.payloads.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto,Integer userId,Integer postId);
    CommentDto updateComment(CommentDto commentDto);
    void deleteComment(Integer commentId);
    CommentDto findCommentById(Integer commentId);
    List<CommentDto> findCommentByPostId(Integer postId);
}
