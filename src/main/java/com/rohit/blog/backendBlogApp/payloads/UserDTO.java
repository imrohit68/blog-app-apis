package com.rohit.blog.backendBlogApp.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class UserDTO {
    private int id;
    @NotEmpty
    private String name;
    @Email
    private String email;
    @NotEmpty
    @Size(min = 7,message = "Password must be of atleast 7 characters.")
    private String password;
    @NotEmpty
    @Size(min = 30,message = "About must be of atleast 30 characters.")
    private String about;
}
