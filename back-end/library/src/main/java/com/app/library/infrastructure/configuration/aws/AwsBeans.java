package com.app.library.infrastructure.configuration.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.app.library.application.gateways.aws.FileManagerGateway;
import com.app.library.infrastructure.gateway.aws.FileManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsBeans {

    @Bean
    public FileManagerGateway fileManager(AmazonS3 amazonS3) {
        return new FileManager(amazonS3);
    }
}
