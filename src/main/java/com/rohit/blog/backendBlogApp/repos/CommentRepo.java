package com.rohit.blog.backendBlogApp.repos;

import com.rohit.blog.backendBlogApp.entites.Comments;
import com.rohit.blog.backendBlogApp.entites.Post;
import com.rohit.blog.backendBlogApp.payloads.CommentDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comments,Integer> {
    List<Comments> findByPost(Post post);
}
