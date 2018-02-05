package com.accelex.platform.filemanagement.rest;

import com.accelex.platform.filemanagement.service.FileRepository;
import com.accelex.platform.filemanagement.service.LocalFileRepository;
import com.amazonaws.RequestClientOptions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.AbstractMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping(path = "/api/files/upload/v1")
public class UploadResource {

    @Autowired
    FileRepository fileRepository;

    public UploadResource() {
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> upload(HttpServletRequest request) {
        try {
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            if (!isMultipart) {
                return new ResponseEntity<String>("uploading file requires a multipart request", HttpStatus.BAD_REQUEST);
            } else {

                AbstractMultipartHttpServletRequest multipartRequest = (AbstractMultipartHttpServletRequest) request;
                MultipartFile file = multipartRequest.getFile("file");
                String originalFilename = file.getOriginalFilename();
                try (BufferedInputStream inputStream = new BufferedInputStream(file.getInputStream(), RequestClientOptions.DEFAULT_STREAM_BUFFER_SIZE)) {
                    saveToS3(inputStream, originalFilename);
                }
                return new ResponseEntity<String>("aaa uploading file requires a multipart request", HttpStatus.OK);

            }
        } catch (IOException e) {
            return new ResponseEntity<String>("bbb uploading file requires a multipart request", HttpStatus.BAD_REQUEST);
        }
    }

    private void saveToS3(InputStream inputStream, String filename) {
    }
}
