package com.rohit.blog.backendBlogApp.Controllers;

import com.rohit.blog.backendBlogApp.Service.CommentService;
import com.rohit.blog.backendBlogApp.Service.impl.CommentImpl;
import com.rohit.blog.backendBlogApp.entites.Comments;
import com.rohit.blog.backendBlogApp.payloads.ApiResponse;
import com.rohit.blog.backendBlogApp.payloads.CommentDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/comments")
public class CommentController {
    private final CommentService commentService;
    @PostMapping("/userId/{userId}/postId/{postId}/createComment")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,@PathVariable Integer userId,@PathVariable Integer postId){
        CommentDto savedPost = this.commentService.createComment(commentDto,userId,postId);
        return new ResponseEntity<>(savedPost, HttpStatus.OK);
    }
    @PutMapping("/commentId/{commentId}/updateComment")
    public ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto commentDto,@PathVariable Integer commentId){
        CommentDto comment = this.commentService.findCommentById(commentId);
        comment.setCommentContent(commentDto.getCommentContent());
        CommentDto savedComment = this.commentService.updateComment(comment);
        return new ResponseEntity<>(savedComment,HttpStatus.OK);
    }
    @DeleteMapping("/commentId/{commentId}/delete")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<>(new ApiResponse("Successfully Deleted",true),HttpStatus.OK);
    }
    @GetMapping("/postId/{postId}/getCommentByPost")
    public ResponseEntity<List<CommentDto>> getCommentByPost(@PathVariable Integer postId){
        List<CommentDto> comments = this.commentService.findCommentByPostId(postId);
        return new ResponseEntity<>(comments,HttpStatus.OK);
    }
}
