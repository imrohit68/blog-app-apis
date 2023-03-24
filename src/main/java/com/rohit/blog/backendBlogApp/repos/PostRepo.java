package com.rohit.blog.backendBlogApp.repos;

import com.rohit.blog.backendBlogApp.entites.Category;
import com.rohit.blog.backendBlogApp.entites.Post;
import com.rohit.blog.backendBlogApp.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
}

