package com.app.library.infrastructure.gateway.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.app.library.application.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

public class FileManager {
    @Value("${aws.bucket.name}")
    private String bucketName;
    private final AmazonS3 s3Client;

    public FileManager(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    public String uploadImage(MultipartFile multipartFile) {
        String imgName = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();
        try {
            java.io.File file = this.convertMultipartToFile(multipartFile);
            s3Client.putObject(bucketName, imgName, file);
            file.delete();
            return s3Client.getUrl(bucketName, imgName).toString();
        } catch (Exception e) {
            throw new ApplicationException("Not able to store image on S3: " + e.getMessage());
        }
        //return "";
    }

    public void deleteImage(String image_url) {
        try {
            s3Client.deleteObject(new DeleteObjectRequest(bucketName, image_url.substring(image_url.lastIndexOf("/") + 1)));
        } catch (Exception e) {}
    }

    private java.io.File convertMultipartToFile(MultipartFile multipartFile) throws IOException {
        java.io.File convert = new java.io.File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convert);
        fos.write(multipartFile.getBytes());
        fos.close();
        return convert;
    }
}
