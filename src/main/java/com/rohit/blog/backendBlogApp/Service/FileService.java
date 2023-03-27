package com.rohit.blog.backendBlogApp.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface FileService {
    String uploadImage(String path, MultipartFile image)  throws IOException;
    InputStream getImage(String path,String fileName) throws FileNotFoundException;
}
