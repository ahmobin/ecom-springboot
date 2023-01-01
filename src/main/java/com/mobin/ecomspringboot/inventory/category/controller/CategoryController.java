package com.mobin.ecomspringboot.inventory.category.controller;

import com.mobin.ecomspringboot.globals.apis.v1.ApiEndpoints;
import com.mobin.ecomspringboot.globals.helpers.FileUploadHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final FileUploadHandler fileUploadHandler;

    @PostMapping(ApiEndpoints.CATEGORIES_API)
    public String store(@RequestParam("file") MultipartFile file) throws IOException {
        return fileUploadHandler.fileUpload(file);
    }
}
