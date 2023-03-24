package com.rohit.blog.backendBlogApp.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
    private String categoryTitle;
    private String categoryDescription;
    @OneToMany(mappedBy = "category")
    private List<Post> posts = new ArrayList<>();
}
