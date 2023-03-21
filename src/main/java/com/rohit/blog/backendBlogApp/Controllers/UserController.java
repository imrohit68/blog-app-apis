package com.rohit.blog.backendBlogApp.Controllers;

import com.rohit.blog.backendBlogApp.Service.UserService;
import com.rohit.blog.backendBlogApp.Service.impl.UserImpl;
import com.rohit.blog.backendBlogApp.entites.User;
import com.rohit.blog.backendBlogApp.payloads.ApiResponse;
import com.rohit.blog.backendBlogApp.payloads.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping("/")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
        UserDTO createUserDto = this.userService.createUser(userDTO);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }
    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO ,@PathVariable Integer userId){
        UserDTO updatedUser = this.userService.updateUser(userDTO,userId);
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId){
    this.userService.deleteUser(userId);
    return  new ResponseEntity(new ApiResponse("User Deleted Successfully",true),HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUser());
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Integer userId){
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }
}
