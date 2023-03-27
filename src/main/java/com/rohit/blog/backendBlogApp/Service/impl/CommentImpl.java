package com.rohit.blog.backendBlogApp.Service.impl;

import com.rohit.blog.backendBlogApp.Service.CommentService;
import com.rohit.blog.backendBlogApp.entites.Comments;
import com.rohit.blog.backendBlogApp.entites.Post;
import com.rohit.blog.backendBlogApp.entites.User;
import com.rohit.blog.backendBlogApp.exceptions.ResourceNotFoundException;
import com.rohit.blog.backendBlogApp.payloads.CommentDto;
import com.rohit.blog.backendBlogApp.payloads.PostDto;
import com.rohit.blog.backendBlogApp.repos.CommentRepo;
import com.rohit.blog.backendBlogApp.repos.PostRepo;
import com.rohit.blog.backendBlogApp.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentImpl implements CommentService {
    private final ModelMapper modelMapper;
    private final UserRepo userRepo;
    private final PostRepo postRepo;
    private final CommentRepo commentRepo;
    @Override
    public CommentDto createComment(CommentDto commentDto, Integer userId, Integer postId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        Post post = this.postRepo.findById(postId).
                orElseThrow(()->new ResourceNotFoundException("Post","Id",postId));
        Comments comments = this.modelMapper.map(commentDto,Comments.class);
        comments.setUser(user);
        comments.setPost(post);
        Comments savedComment = this.commentRepo.save(comments);
         return this.modelMapper.map(savedComment,CommentDto.class);
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto) {
        Comments comments = this.modelMapper.map(commentDto,Comments.class);
        Comments savedComment = this.commentRepo.save(comments);
        return this.modelMapper.map(savedComment,CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comments comments = this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","Id",commentId));
        this.commentRepo.delete(comments);
    }

    @Override
    public CommentDto findCommentById(Integer commentId) {
        Comments comment = this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","id",commentId));
        return this.modelMapper.map(comment,CommentDto.class);
    }

    @Override
    public List<CommentDto> findCommentByPostId(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Id",postId));
        List<Comments> comments = this.commentRepo.findByPost(post);
        return comments.stream().map((comment -> this.modelMapper.map(comment,CommentDto.class))).collect(Collectors.toList());
    }
}
