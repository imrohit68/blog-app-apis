package com.rohit.blog.backendBlogApp.payloads;

import com.rohit.blog.backendBlogApp.entites.Category;
import com.rohit.blog.backendBlogApp.entites.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor

public class PostDto {
    private Integer postId;
    private String postTitle;
    private String postContent;
    private String postImageName;
    private Date postAddedDate;
    private CategoryDto category;
    private UserDTO user;
}
