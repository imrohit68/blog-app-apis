package com.rohit.blog.backendBlogApp.Controllers;

import com.rohit.blog.backendBlogApp.Service.FileService;
import com.rohit.blog.backendBlogApp.Service.PostService;
import com.rohit.blog.backendBlogApp.config.Constants;
import com.rohit.blog.backendBlogApp.payloads.ApiResponse;
import com.rohit.blog.backendBlogApp.payloads.ImageUploadResponse;
import com.rohit.blog.backendBlogApp.payloads.PageResponse;
import com.rohit.blog.backendBlogApp.payloads.PostDto;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/")
public class PostController {
    private final PostService postService;
    private final FileService fileService;
    @Value("${project.image}")
    private String path;

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
    public  ResponseEntity<PageResponse> getAllPost(
            @RequestParam(value = "pageNumber",defaultValue = Constants.pageNumber,required = false) Integer pageNo,
            @RequestParam(value = "pageSize",defaultValue = Constants.pageSize,required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = Constants.sortBy,required = false)String sortBy,
            @RequestParam(value = "sortDirection",defaultValue = Constants.sortDirection,required = false)String sortDirection){
        PageResponse pageResponse = this.postService.getAllPost(pageNo, pageSize,sortBy,sortDirection);
        return ResponseEntity.ok(pageResponse);
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
    @GetMapping("/keyword/{keyword}/post")
    public ResponseEntity<List<PostDto>> postByKeyword(@PathVariable String keyword){
        List<PostDto> postDto = this.postService.getPostByKeyword(keyword);
        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }
    @PostMapping("post/imageUpload/{postId}")
    public ResponseEntity<ImageUploadResponse> uploadProfileImage(@RequestParam(value = "image")MultipartFile image,@PathVariable Integer postId) throws IOException {
        PostDto postDto = this.postService.getPostById(postId);
        String fileName = this.fileService.uploadImage(path,image);
        postDto.setPostImageName(fileName);
        PostDto postDto1 = this.postService.updatePost(postDto,postId);
        return new ResponseEntity<>(new ImageUploadResponse(postDto1,fileName,true),HttpStatus.OK);
    }
    @GetMapping(value = "/post/getImage/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void getImage(@PathVariable String imageName, HttpServletResponse response) throws IOException {
        InputStream resource = this.fileService.getImage(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }
}
