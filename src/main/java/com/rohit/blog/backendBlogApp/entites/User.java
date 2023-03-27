package com.rohit.blog.backendBlogApp.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String email;
    private String password;
    private String about;
    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private  List<Comments> comments = new ArrayList<>();
}
