package com.mobin.ecomspringboot.globals.helpers;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.impl.IOFileUploadException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.UUID;

@Component
public class FileUploadHandler {

//    public final String UPLOAD_DIRECTORY = "/home/mobin/Projects/java/ecom-spring-boot/src/main/resources/static/uploads";
    public final String DYNAMIC_UPLOAD_DIRECTORY = new ClassPathResource("static/uploads").getFile().getAbsolutePath();

    public FileUploadHandler() throws IOException {
    }

    public String fileUpload(MultipartFile multipartFile) throws IOException {

        if(multipartFile.getSize() >= 1048576){
            throw new FileUploadException("File limit exceed");
        }

        String uploadFileName = multipartFile.getOriginalFilename();
        int index = uploadFileName.lastIndexOf(".");
        String extension = uploadFileName.substring(index + 1);

        if(!isAllowTheExtension(extension)){
            throw new FileUploadException("Extension Not Allowed");
        }

        String fileName = UUID.randomUUID()+"."+extension;

        Files.copy(multipartFile.getInputStream(), Paths.get(DYNAMIC_UPLOAD_DIRECTORY + File.separator + fileName), StandardCopyOption.REPLACE_EXISTING);
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/uploads/").path(fileName).toUriString();

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

    private boolean isAllowTheExtension(String inputStr) {
        String[] allowedExtensions = new String[]{"jpg","png","jpeg"};
        return Arrays.stream(allowedExtensions).anyMatch(inputStr::contains);
    }
}
