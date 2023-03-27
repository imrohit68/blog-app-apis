package com.rohit.blog.backendBlogApp.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    private String postTitle;
    @Column(length = 10000)
    private String postContent;
    private String postImageName;
    private Date postAddedDate;
    @ManyToOne
    private Category category;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "post")
    private List<Comments> comments = new ArrayList<>();

}
