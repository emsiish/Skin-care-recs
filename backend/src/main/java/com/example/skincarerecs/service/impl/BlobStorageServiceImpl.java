package com.example.skincarerecs.service.impl;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobClientBuilder;
import com.azure.storage.common.StorageSharedKeyCredential;
import com.example.skincarerecs.service.BlobStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class BlobStorageServiceImpl implements BlobStorageService {
    @Value("${spring.cloud.azure.storage.blob.container-name}")
    private String containerName;

    @Value("${spring.cloud.azure.storage.blob.endpoint}")
    private String endpoint;

    @Value("${spring.cloud.azure.storage.account}")
    private String accountName;

    @Value("${spring.cloud.azure.storage.account-key}")
    private String accountKey;

    @Override
    public String uploadFile(MultipartFile file, String blobName) throws IOException {
        BlobClient blobClient = new BlobClientBuilder()
                .endpoint(endpoint)
                .credential(new StorageSharedKeyCredential(accountName, accountKey))
                .containerName(containerName)
                .blobName(blobName)
                .buildClient();

        blobClient.upload(file.getInputStream(), file.getSize(), true);
        return blobClient.getBlobUrl();
    }
}
