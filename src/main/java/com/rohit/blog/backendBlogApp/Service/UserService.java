package com.rohit.blog.backendBlogApp.Service;

import com.rohit.blog.backendBlogApp.entites.User;
import com.rohit.blog.backendBlogApp.payloads.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO user);
    UserDTO updateUser(UserDTO user,Integer userId);
    UserDTO getUserById(Integer userId);
    List<UserDTO> getAllUser();
    void deleteUser(Integer userId);

}
