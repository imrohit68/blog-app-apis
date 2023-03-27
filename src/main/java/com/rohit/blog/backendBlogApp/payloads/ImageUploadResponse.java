package com.rohit.blog.backendBlogApp.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ImageUploadResponse {
    private PostDto postDto;
    private String imageName;
    private Boolean status;
}
