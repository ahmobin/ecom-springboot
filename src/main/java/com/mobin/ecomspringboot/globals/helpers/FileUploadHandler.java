package com.mobin.ecomspringboot.globals.helpers;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUploadHandler {

    public final String UPLOAD_DIRECTORY = "/home/mobin/Projects/java/ecom-spring-boot/src/main/resources/static/uploads";

    public void fileUpload(MultipartFile multipartFile) throws IOException {

        String outputStreamString = UPLOAD_DIRECTORY+ File.separator+multipartFile.getOriginalFilename();

        fileUploadMethodTwo(multipartFile, outputStreamString);


    }


    private void fileUploadMethodOne(MultipartFile multipartFile, String outputStreamString) throws IOException {
        InputStream inputStream = multipartFile.getInputStream();
        byte data[] = new byte[inputStream.available()];
        inputStream.read(data);

        FileOutputStream fileOutputStream = new FileOutputStream(outputStreamString);
        fileOutputStream.write(data);
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    private void fileUploadMethodTwo(MultipartFile multipartFile, String outputStreamString) throws IOException {
        Files.copy(multipartFile.getInputStream(), Paths.get(outputStreamString), StandardCopyOption.REPLACE_EXISTING);
    }
}
