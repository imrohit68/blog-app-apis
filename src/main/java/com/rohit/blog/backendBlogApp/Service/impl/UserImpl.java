package com.rohit.blog.backendBlogApp.Service.impl;

import com.rohit.blog.backendBlogApp.Service.UserService;
import com.rohit.blog.backendBlogApp.entites.User;
import com.rohit.blog.backendBlogApp.exceptions.ResourceNotFoundException;
import com.rohit.blog.backendBlogApp.payloads.UserDTO;
import com.rohit.blog.backendBlogApp.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor

public class UserImpl implements UserService {
    private final UserRepo userRepo;

    @Override
    public UserDTO createUser(UserDTO user) {
        User Entityuser = this.dtoToUser(user);
        User savedUser = this.userRepo.save(Entityuser);
        return this.UserToDto(Entityuser);
    }

    @Override
    public UserDTO updateUser(UserDTO user, Integer userId) {
        User EntityUser = this.userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User","id",userId));

        EntityUser.setName(user.getName());
        EntityUser.setEmail(user.getEmail());
        EntityUser.setPassword(user.getPassword());
        EntityUser.setAbout(user.getAbout());

        User savedUser = this.userRepo.save(EntityUser);


        return this.UserToDto(EntityUser);
    }

    @Override
    public UserDTO getUserById(Integer userId) {
        User EntityUser = this.userRepo
                .findById(userId).orElseThrow(()->
                        new ResourceNotFoundException("User","id",userId));
        return UserToDto(EntityUser);
    }

    @Override
    public List<UserDTO> getAllUser() {
        List<User> EntityAllUsers = this.userRepo.findAll();
        List<UserDTO> userDto = EntityAllUsers.stream().map(user -> this.UserToDto(user)).collect(Collectors.toList());
        return userDto;
    }

    @Override
    public void deleteUser(Integer userId) {
        User EntityUser = this.userRepo
                .findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
        this.userRepo.delete(EntityUser);
    }
    private User dtoToUser(UserDTO user){
        User EntityUser = new User();
        EntityUser.setId(user.getId());
        EntityUser.setName(user.getName());
        EntityUser.setEmail(user.getEmail());
        EntityUser.setPassword(user.getPassword());
        EntityUser.setAbout(user.getAbout());

        return EntityUser;
    }
    private UserDTO UserToDto(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setAbout(user.getAbout());

        return userDTO;
    }
}
