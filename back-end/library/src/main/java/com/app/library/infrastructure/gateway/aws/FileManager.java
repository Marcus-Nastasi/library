package com.app.library.infrastructure.gateway.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

public class FileManager {
    @Value("${aws.bucket.name}")
    private String bucketName;
    @Autowired
    private AmazonS3 s3Client;

    public String uploadImage(MultipartFile multipartFile) {
        String imgName = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();
        try {
            java.io.File file = this.convertMultipartToFile(multipartFile);
            s3Client.putObject(bucketName, imgName, file);
            file.delete();
            return s3Client
                    .getUrl(bucketName, imgName)
                    .toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    public boolean deleteImage(String image_url) {
        //S3Object object = s3Client.getObject(bucketName, image_url);
        s3Client.deleteObject(bucketName, image_url);
        return true;
    }

    private java.io.File convertMultipartToFile(MultipartFile multipartFile) throws IOException {
        java.io.File convert = new java.io.File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convert);
        fos.write(multipartFile.getBytes());
        fos.close();
        return convert;
    }
}
