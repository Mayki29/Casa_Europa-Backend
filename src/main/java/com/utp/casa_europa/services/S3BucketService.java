package com.utp.casa_europa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;

import io.jsonwebtoken.io.IOException;

@Service
public class S3BucketService {
    
    @Autowired
    private AmazonS3 s3Client;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    @Value("${aws.region}")
    private String awsRegion;

    public String uploadFile(String fileName, MultipartFile file) throws IOException {
        try {
            s3Client.putObject(bucketName, fileName, file.getInputStream(), null);
            String fileUrl = String.format("https://%s.s3.%s.amazonaws.com/casa_europa/%s", bucketName, awsRegion, fileName);
            return fileUrl;
        } catch (Exception e) {
            throw new Error("Error uploading file to S3: " + e.getMessage());
        }

    }
}
