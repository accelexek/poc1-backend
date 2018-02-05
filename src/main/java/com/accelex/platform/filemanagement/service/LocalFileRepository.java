package com.accelex.platform.filemanagement.service;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@ConditionalOnProperty(name = FileRepository.PROP_NAME_LOCAL_TESTING, havingValue = "true")
public class LocalFileRepository implements FileRepository{


    @Override
    public void save(InputStream inputStream, String fileName) {

    }
}
