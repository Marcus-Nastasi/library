package com.app.library.infrastructure.configuration.aws;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.app.library.application.gateways.aws.FileManagerGateway;
import com.app.library.application.usecases.aws.FileManagerUseCase;
import com.app.library.infrastructure.gateway.aws.FileManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsConfiguration {

    @Value("${aws.region}")
    private String awsRegion;
    @Value("${aws.accessKeyId}")
    private String accessKeyId;
    @Value("${aws.secretKey}")
    private String secretKey;

    @Bean
    public AmazonS3 createS3Instance() {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKeyId, secretKey);
        return AmazonS3ClientBuilder
            .standard()
            .withRegion(awsRegion)
            .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
            .build();
    }

    @Bean
    public FileManagerGateway fileManager(AmazonS3 amazonS3) {
        return new FileManager(amazonS3);
    }

    @Bean
    public FileManagerUseCase fileManagerUseCase(FileManagerGateway fileManagerGateway) {
        return new FileManagerUseCase(fileManagerGateway);
    }
}
