package com.rohit.blog.backendBlogApp.Service.impl;

import com.rohit.blog.backendBlogApp.Service.PostService;
import com.rohit.blog.backendBlogApp.entites.Category;
import com.rohit.blog.backendBlogApp.entites.Post;
import com.rohit.blog.backendBlogApp.entites.User;
import com.rohit.blog.backendBlogApp.exceptions.ResourceNotFoundException;
import com.rohit.blog.backendBlogApp.payloads.CategoryDto;
import com.rohit.blog.backendBlogApp.payloads.PostDto;
import com.rohit.blog.backendBlogApp.repos.CategoryRepo;
import com.rohit.blog.backendBlogApp.repos.PostRepo;
import com.rohit.blog.backendBlogApp.repos.UserRepo;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostImpl implements PostService {
    private final ModelMapper modelMapper;
    private final CategoryRepo categoryRepo;
    private final UserRepo userRepo;
    private final PostRepo postRepo;
    @Override
    public PostDto createPost(PostDto postDto, Integer categoryId, Integer userId) {
        Post post = this.modelMapper.map(postDto,Post.class);
        User user = this.userRepo.findById(userId).
                orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category","id",categoryId));
        post.setCategory(category);
        post.setUser(user);
        post.setPostAddedDate(new Date());
        post.setPostImageName("default.png");
        Post savedPost = this.postRepo.save(post);
        return this.modelMapper.map(savedPost,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto,Integer postId) {
        Post post = this.postRepo.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post","Id",postId));
        post.setPostTitle(postDto.getPostTitle());
        post.setPostContent(postDto.getPostContent());
        post.setPostImageName(postDto.getPostImageName());
        Post savedPostDto = this.postRepo.save(post);
        return this.modelMapper.map(savedPostDto,PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
       Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Id",postId));
       this.postRepo.delete(post);
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = this.postRepo.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));
        return this.modelMapper.map(post,PostDto.class);
    }

    @Override
    public List<PostDto> getAllPost() {
        List<Post> posts = this.postRepo.findAll();
        List<PostDto> postDto = posts.stream().map((post -> this.modelMapper.map(post,PostDto.class))).collect(Collectors.toList());
        return postDto;
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","id",userId));
        List<Post> posts = this.postRepo.findByUser(user);
        List<PostDto> postDto = posts.stream().map((post -> this.modelMapper.map(post,PostDto.class))).collect(Collectors.toList());
        return postDto;
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category","Id",categoryId));
        List<Post> posts = this.postRepo.findByCategory(category);
       List<PostDto> postsDto = posts.stream().map((post -> this.modelMapper.map(post,PostDto.class))).collect(Collectors.toList());
       return postsDto;
    }

    @Override
    public List<PostDto> getPostByKeyword(String keyword) {
        return null;
    }
}
