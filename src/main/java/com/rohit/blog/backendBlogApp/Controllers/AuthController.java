package com.rohit.blog.backendBlogApp.Controllers;

import com.rohit.blog.backendBlogApp.entites.Role;
import com.rohit.blog.backendBlogApp.entites.User;
import com.rohit.blog.backendBlogApp.exceptions.ResourceNotFoundException;
import com.rohit.blog.backendBlogApp.payloads.ApiResponse;
import com.rohit.blog.backendBlogApp.payloads.JwtAuthRequest;
import com.rohit.blog.backendBlogApp.payloads.JwtAuthResponse;
import com.rohit.blog.backendBlogApp.repos.RoleRepo;
import com.rohit.blog.backendBlogApp.repos.UserRepo;
import com.rohit.blog.backendBlogApp.security.CustomUserDetailService;
import com.rohit.blog.backendBlogApp.security.JwtTokenHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final JwtTokenHelper jwtTokenHelper;
    private final CustomUserDetailService customUserDetailService;
    private final AuthenticationManager authenticationManager;
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request){
        this.authenticate(request.getUsername(),request.getPassword());
        UserDetails userDetails = this.customUserDetailService.loadUserByUsername(request.getUsername());
        String token = this.jwtTokenHelper.generateToken(userDetails);
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setToken(token);
        return new ResponseEntity<>(jwtAuthResponse,HttpStatus.OK);
    }

    private void authenticate(String username, String password) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username,password);
        this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/giveAdmin/{userId}")
    public ResponseEntity<ApiResponse> grantAdminAccessToOther(@PathVariable Integer userId){
        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        Set<Role> roleSet = user.getRoles();
        Role role = this.roleRepo.findById(1).orElseThrow(()->new ResourceNotFoundException("Role","id",1));
        roleSet.add(role);
        user.setRoles(roleSet);
        this.userRepo.save(user);
        return new ResponseEntity<>(new ApiResponse("Success",true),HttpStatus.OK);
    }
}
