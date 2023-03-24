package com.rohit.blog.backendBlogApp.repos;

import com.rohit.blog.backendBlogApp.entites.Category;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
