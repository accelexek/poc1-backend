package com.accelex.platform.filemanagement.service;

import org.springframework.stereotype.Service;

import java.io.InputStream;

public interface FileRepository {
    String PROP_NAME_LOCAL_TESTING = "localTesting";

    void save(InputStream inputStream, String fileName);
}
