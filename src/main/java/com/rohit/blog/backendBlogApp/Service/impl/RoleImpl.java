package com.rohit.blog.backendBlogApp.Service.impl;

import com.rohit.blog.backendBlogApp.Service.RoleService;
import com.rohit.blog.backendBlogApp.entites.Post;
import com.rohit.blog.backendBlogApp.entites.Role;
import com.rohit.blog.backendBlogApp.exceptions.ResourceNotFoundException;
import com.rohit.blog.backendBlogApp.repos.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoleImpl implements RoleService {
    private final RoleRepo roleRepo;
    @Override
    public Role getRoleById(Integer id) {
        Role role = this.roleRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Role","id",id));
        return role;
    }
}
