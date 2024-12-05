package com.app.library.application.gateways.aws;

public interface FileManagerGateway {
    String upload(byte[] fileData, String fileName);
    void delete(String fileUrl);
}
