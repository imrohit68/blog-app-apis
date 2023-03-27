package com.rohit.blog.backendBlogApp.payloads;

import com.rohit.blog.backendBlogApp.entites.Post;
import com.rohit.blog.backendBlogApp.entites.User;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {

    private Integer commentId;
    private String commentContent;
    private UserDTO user;
    private PostDto post;
}
