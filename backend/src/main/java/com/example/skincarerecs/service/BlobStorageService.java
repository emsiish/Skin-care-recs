package com.example.skincarerecs.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface BlobStorageService {
    String uploadFile(MultipartFile file, String blobName) throws IOException;
}
