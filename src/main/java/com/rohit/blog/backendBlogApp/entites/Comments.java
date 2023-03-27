package com.rohit.blog.backendBlogApp.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;
    private String commentContent;
    @ManyToOne
    private User user;
    @ManyToOne
    private Post post;
}
