package com.mobin.ecomspringboot.inventory.services;

import com.mobin.ecomspringboot.globals.helpers.FileUploadHandler;
import com.mobin.ecomspringboot.globals.helpers.Slugable;
import com.mobin.ecomspringboot.inventory.entity.Category;
import com.mobin.ecomspringboot.inventory.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final FileUploadHandler fileUploadHandler;
    private final CategoryRepository categoryRepo;

    public List<Category> categoryList(){
        return categoryRepo.findAll();
    }


    public Category store(MultipartFile file, String name) throws IOException {
        Category category = new Category();
        category.setName(name);
        category.setSlug(Slugable.toSlug(name));

        if(!file.isEmpty()){
            category.setImage(fileUploadHandler.fileUpload(file));
        }

        return categoryRepo.save(category);
    }
}
