package com.rohit.blog.backendBlogApp.Service.impl;

import com.rohit.blog.backendBlogApp.Service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile image) throws IOException {
        String fileName = image.getOriginalFilename();
        String randomName = UUID.randomUUID().toString();
        String newFileName = randomName.concat(fileName.substring(fileName.lastIndexOf(".")));
        String filePath = path+File.separator+newFileName;
        File f = new File(path);
        if(!f.exists()){
            f.mkdirs();
        }
        Files.copy(image.getInputStream(), Paths.get(filePath));
        return newFileName;
    }

    @Override
    public InputStream getImage(String path, String fileName) throws FileNotFoundException {
        String fullPath = path+File.separator+fileName;
        InputStream inputStream = new FileInputStream(fullPath);
        return inputStream;
    }
}
