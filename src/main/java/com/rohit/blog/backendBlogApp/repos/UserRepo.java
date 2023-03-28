package com.rohit.blog.backendBlogApp.repos;

import com.rohit.blog.backendBlogApp.entites.Comments;
import com.rohit.blog.backendBlogApp.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);
}

