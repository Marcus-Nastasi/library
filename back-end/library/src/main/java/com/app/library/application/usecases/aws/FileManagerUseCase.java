package com.app.library.application.usecases.aws;

import com.app.library.application.gateways.aws.FileManagerGateway;

public class FileManagerUseCase {

    private final FileManagerGateway fileManagerGateway;

    public FileManagerUseCase(FileManagerGateway fileManagerGateway) {
        this.fileManagerGateway = fileManagerGateway;
    }

    public String upload(byte[] fileData, String fileName) {
        return fileManagerGateway.upload(fileData, fileName);
    }

    public void delete(String fileUrl) {
        fileManagerGateway.delete(fileUrl);
    }
}
