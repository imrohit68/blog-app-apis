package com.rohit.blog.backendBlogApp.Service;

import com.rohit.blog.backendBlogApp.entites.Post;
import com.rohit.blog.backendBlogApp.payloads.PageResponse;
import com.rohit.blog.backendBlogApp.payloads.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto,Integer categoryId,Integer userId);
    PostDto updatePost(PostDto postDto,Integer postId);
    void deletePost(Integer postId);
    PostDto getPostById(Integer postId);
    PageResponse getAllPost(Integer pageNo, Integer pageSize,String sortBy,String sortDirection);
    List<PostDto> getPostByUser(Integer userId);
    List<PostDto> getPostByCategory(Integer categoryId);
    List<PostDto> getPostByKeyword(String keyword);
}
