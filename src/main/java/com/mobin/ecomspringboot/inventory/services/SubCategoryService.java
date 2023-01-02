package com.mobin.ecomspringboot.inventory.services;

import com.mobin.ecomspringboot.exceptions.DuplicateDataException;
import com.mobin.ecomspringboot.globals.helpers.FileUploadHandler;
import com.mobin.ecomspringboot.globals.helpers.Slugable;
import com.mobin.ecomspringboot.inventory.entity.Category;
import com.mobin.ecomspringboot.inventory.entity.SubCategory;
import com.mobin.ecomspringboot.inventory.repositories.CategoryRepository;
import com.mobin.ecomspringboot.inventory.repositories.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubCategoryService {
    private final FileUploadHandler fileUploadHandler;
    private final SubCategoryRepository subCategoryRepo;
    private final CategoryRepository categoryRepo;

    public List<SubCategory> subCategoryList(UUID categoryId){
        checkCategoryIsExist(categoryId);
        return subCategoryRepo.findAllByCategoryId(categoryId);
    }


    public SubCategory store(UUID categoryId, MultipartFile file, String name) throws IOException {
        checkCategoryIsExist(categoryId);
        subCategoryValidation(name);
        return subCategoryRepo.save(query(categoryId, name,file));
    }

    public SubCategory show(UUID categoryId, UUID id){
        checkSubCategoryIsExist(categoryId, id);
        return subCategoryRepo.findById(id).get();

    }

    public SubCategory update(UUID categoryId, MultipartFile file, String name, UUID id) throws IOException {
        checkSubCategoryIsExist(categoryId, id);
        subCategoryUpdateValidation(name, id);

        SubCategory subCategory = query(categoryId, name,file);
        subCategory.setId(id);
        if(file.isEmpty()){
            subCategory.setImage(subCategoryRepo.findById(id).get().getImage());
        }
        subCategoryRepo.save(subCategory);

        return subCategoryRepo.findById(id).get();
    }

    public void destroy(UUID categoryId, UUID id){
        checkSubCategoryIsExist(categoryId, id);
        subCategoryRepo.deleteById(id);
    }


    private SubCategory query(UUID categoryId, String name, MultipartFile file) throws IOException {
        SubCategory subCategory = new SubCategory();
        subCategory.setCategory(categoryById(categoryId));
        subCategory.setName(name);
        subCategory.setSlug(Slugable.toSlug(name));
        if(!file.isEmpty()){
            subCategory.setImage(fileUploadHandler.fileUpload(file));
        }
        return subCategory;
    }

    private void checkCategoryIsExist(UUID categoryId){
        if(!categoryRepo.existsById(categoryId)) throw new EntityNotFoundException("Category Not Found");
    }

    private void checkSubCategoryIsExist(UUID categoryId, UUID id){
        if(subCategoryRepo.findByCategoryIdAndId(categoryId, id).isEmpty()) throw new EntityNotFoundException("Sub Category Not Found");
    }

    private Category categoryById(UUID categoryId){
        return categoryRepo.findById(categoryId).get();
    }

    private void subCategoryValidation(String name){
        if(subCategoryRepo.findByName(name) != null)  throw new DuplicateDataException("Sub category name already exist");
    }

    private void subCategoryUpdateValidation(String name, UUID id){
        if (subCategoryRepo.existsByName(name) && !subCategoryRepo.findByName(name).getId().equals(id))
            throw new DuplicateDataException("Sub category name already exist");
    }
}
