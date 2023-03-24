package com.rohit.blog.backendBlogApp.Service.impl;

import com.rohit.blog.backendBlogApp.Service.CategoryService;
import com.rohit.blog.backendBlogApp.entites.Category;
import com.rohit.blog.backendBlogApp.exceptions.ResourceNotFoundException;
import com.rohit.blog.backendBlogApp.payloads.CategoryDto;
import com.rohit.blog.backendBlogApp.repos.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryImpl implements CategoryService {
    private final ModelMapper modelMapper;
    private final CategoryRepo categoryRepo;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category cat = this.modelMapper.map(categoryDto,Category.class);
        Category savedCat = this.categoryRepo.save(cat);
        return this.modelMapper.map(savedCat,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer id) {
        Category cat = this.categoryRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Category","Category",id));
        cat.setCategoryTitle(categoryDto.getCategoryTitle());
        cat.setCategoryDescription(categoryDto.getCategoryDescription());
        Category savedCategory = this.categoryRepo.save(cat);
        return this.modelMapper.map(savedCategory,CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer id) {
        Category cat = this.categoryRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",id));
        this.categoryRepo.delete(cat);
    }

    @Override
    public CategoryDto getCategory(Integer id) {
        Category cat = this.categoryRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",id));
        return this.modelMapper.map(cat,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
       List<Category> allCategory = this.categoryRepo.findAll();
       List<CategoryDto> categoryDto = allCategory.stream().map(category -> this.modelMapper.map(category,CategoryDto.class)).collect(Collectors.toList());
       return categoryDto;
    }
}
