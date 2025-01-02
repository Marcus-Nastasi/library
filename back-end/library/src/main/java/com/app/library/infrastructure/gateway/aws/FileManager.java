package com.app.library.infrastructure.gateway.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.app.library.application.exception.ApplicationException;
import com.app.library.application.gateways.aws.FileManagerGateway;
import org.springframework.beans.factory.annotation.Value;

import java.io.ByteArrayInputStream;
import java.util.UUID;

public class FileManager implements FileManagerGateway {

    @Value("${aws.bucket.name}")
    private String bucketName;
    private final AmazonS3 s3Client;

    public FileManager(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    @Override
    public String upload(byte[] fileData, String fileName) {
        try {
            String uniqueFileName = UUID.randomUUID() + "-" + fileName;
            s3Client.putObject(bucketName, uniqueFileName, new ByteArrayInputStream(fileData), null);
            return s3Client.getUrl(bucketName, uniqueFileName).toString();
        } catch (Exception e) {
            throw new ApplicationException("Failed to upload file to S3 > " + e.getMessage());
        }
    }

    @Override
    public void delete(String fileUrl) {
        try {
            String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
            s3Client.deleteObject(new DeleteObjectRequest(bucketName, fileName));
        } catch (Exception e) {
            throw new ApplicationException("Failed to delete file from S3 > " + e.getMessage());
        }
    }
}
