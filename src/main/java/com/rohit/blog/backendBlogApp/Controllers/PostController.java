package com.rohit.blog.backendBlogApp.Controllers;

import com.rohit.blog.backendBlogApp.Service.PostService;
import com.rohit.blog.backendBlogApp.entites.Post;
import com.rohit.blog.backendBlogApp.payloads.ApiResponse;
import com.rohit.blog.backendBlogApp.payloads.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/")
public class PostController {
    private final PostService postService;

    @PostMapping("/userId/{userId}/categoryId/{categoryId}/post")
    public ResponseEntity<PostDto> createPost (@RequestBody PostDto postDto, @PathVariable Integer userId,@PathVariable Integer categoryId){
        PostDto createPost = this.postService.createPost(postDto,categoryId,userId);
        return new ResponseEntity<>(createPost, HttpStatus.CREATED);
    }
    @GetMapping("/categoryId/{categoryId}/post")
    public ResponseEntity<List<PostDto>> getPostByCategory (@PathVariable Integer categoryId){
        List<PostDto> posts = this.postService.getPostByCategory(categoryId);
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }
    @GetMapping("/userId/{userId}/post")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
        List<PostDto> postDto = this.postService.getPostByUser(userId);
        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }
    @GetMapping("/post/{postId}")
    public ResponseEntity<PostDto> getPostByPostId(@PathVariable Integer postId){
        PostDto post = this.postService.getPostById(postId);
        return new ResponseEntity<>(post,HttpStatus.OK);
    }
    @GetMapping("/post")
    public  ResponseEntity<List<PostDto>> getAllPost(){
        List<PostDto> postDto = this.postService.getAllPost();
        return ResponseEntity.ok(postDto);
    }
    @DeleteMapping("/post/{userId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer userId){
        this.postService.deletePost(userId);
        return new ResponseEntity<>(new ApiResponse("Success",true),HttpStatus.OK);
    }
    @PutMapping("/post/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId){
        PostDto updatedPostdto = this.postService.updatePost(postDto,postId);
        return new ResponseEntity<>(updatedPostdto,HttpStatus.OK);
    }

}
