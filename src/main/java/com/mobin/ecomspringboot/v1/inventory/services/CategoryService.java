package com.mobin.ecomspringboot.v1.inventory.services;

import com.mobin.ecomspringboot.exceptions.DuplicateDataException;
import com.mobin.ecomspringboot.globals.helpers.FileUploadHandler;
import com.mobin.ecomspringboot.globals.helpers.Slugable;
import com.mobin.ecomspringboot.v1.inventory.entity.Category;
import com.mobin.ecomspringboot.v1.inventory.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final FileUploadHandler fileUploadHandler;
    private final CategoryRepository categoryRepo;

    public List<Category> categoryList(){
        return categoryRepo.findAll();
    }


    public Category store(MultipartFile file, String name) throws IOException {
        categoryValidation(name);

        Category category = new Category();
        category.setName(name);
        category.setSlug(Slugable.toSlug(name));

        if(!file.isEmpty()){
            category.setImage(fileUploadHandler.fileUpload(file));
        }

        return categoryRepo.save(category);
    }

    public Category show(UUID id){
        if (categoryRepo.findById(id).isPresent())
            return categoryRepo.findById(id).get();
        else
            throw new EntityNotFoundException("Category Not Found");
    }

    public Category update(MultipartFile file, String name, UUID id) throws IOException {
        categoryUpdateValidation(name, id);
        Category category = new Category();
        category.setName(name);
        category.setSlug(Slugable.toSlug(name));
        if(!file.isEmpty()){
            category.setImage(fileUploadHandler.fileUpload(file));
        }
        category.setId(id);
        categoryRepo.save(category);

        return categoryRepo.findById(id).get();
    }

    public void destroy(UUID id){
        categoryRepo.deleteById(id);
    }

    private void categoryValidation(String name){
        if(categoryRepo.findByName(name) != null)  throw new DuplicateDataException("Category name already exist");
    }

    private void categoryUpdateValidation(String name, UUID id){
        if (categoryRepo.existsByName(name) && !categoryRepo.findByName(name).getId().equals(id))
            throw new DuplicateDataException("Category name already exist");
    }
}
