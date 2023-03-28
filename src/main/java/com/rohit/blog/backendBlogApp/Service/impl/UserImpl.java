package com.rohit.blog.backendBlogApp.Service.impl;

import com.rohit.blog.backendBlogApp.Service.RoleService;
import com.rohit.blog.backendBlogApp.Service.UserService;
import com.rohit.blog.backendBlogApp.entites.Role;
import com.rohit.blog.backendBlogApp.entites.User;
import com.rohit.blog.backendBlogApp.exceptions.ResourceNotFoundException;
import com.rohit.blog.backendBlogApp.payloads.UserDTO;
import com.rohit.blog.backendBlogApp.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor

public class UserImpl implements UserService {

    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Override
    public UserDTO createUser(UserDTO user) {
        User Entityuser = this.dtoToUser(user);
        Entityuser.setPassword(passwordEncoder.encode(Entityuser.getPassword()));
        Set<Role> roleSet = new HashSet<>();
        Role role = this.roleService.getRoleById(2);
        roleSet.add(role);
        Entityuser.setRoles(roleSet);
        User savedUser = this.userRepo.save(Entityuser);
        return this.UserToDto(savedUser);
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


        return this.UserToDto(savedUser);
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
        Set<Role> roleSet = new HashSet<>();
        EntityUser.setRoles(roleSet);
        this.userRepo.delete(EntityUser);
    }
    private User dtoToUser(UserDTO user){
        User EntityUser = this.modelMapper.map(user,User.class);
        return EntityUser;
    }
    private UserDTO UserToDto(User user){
        UserDTO userDTO = this.modelMapper.map(user,UserDTO.class);
        return userDTO;
    }
}
