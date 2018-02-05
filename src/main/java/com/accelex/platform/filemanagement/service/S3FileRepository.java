package com.accelex.platform.filemanagement.service;

import com.accelex.platform.filemanagement.service.FileRepository;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@ConditionalOnProperty(name = FileRepository.PROP_NAME_LOCAL_TESTING, havingValue = "false", matchIfMissing = true)
public class S3FileRepository implements FileRepository{
    private AmazonS3 s3client = AmazonS3ClientBuilder.defaultClient();

    @Override
    public void save(InputStream inputStream, String fileName) {
        s3client.putObject("poc1bucket", fileName, inputStream, new ObjectMetadata());
    }
}
