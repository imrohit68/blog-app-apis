package com.rohit.blog.backendBlogApp;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
public class BackendBlogAppApplication{

	public static void main(String[] args) {
		SpringApplication.run(BackendBlogAppApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper (){
		return new ModelMapper();
	}

}
