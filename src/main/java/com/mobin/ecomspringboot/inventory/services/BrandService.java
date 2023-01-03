package com.mobin.ecomspringboot.inventory.services;

import com.mobin.ecomspringboot.exceptions.DuplicateDataException;
import com.mobin.ecomspringboot.globals.helpers.FileUploadHandler;
import com.mobin.ecomspringboot.globals.helpers.Slugable;
import com.mobin.ecomspringboot.inventory.entity.Brand;
import com.mobin.ecomspringboot.inventory.repositories.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepository brandRepo;
    private final FileUploadHandler fileUploadHandler;

    public List<Brand> brandList(){
        return brandRepo.findAll();
    }

    public Brand create(MultipartFile file, String name) throws IOException {
        checkBrandIsExist(name);
        Brand brand = new Brand();
        brand.setName(name);
        brand.setSlug(Slugable.toSlug(name));

        if(!file.isEmpty()){
            brand.setImage(fileUploadHandler.fileUpload(file));
        }

        return brandRepo.save(brand);
    }

    public Brand show(UUID id){
        brandNotFound(id);
        return brandRepo.findById(id).get();
    }


    public Brand update(MultipartFile file, String name, UUID id) throws IOException{
        brandNotFound(id);
        brandUpdateValidation(name, id);

        Brand brand = new Brand();
        brand.setId(id);
        brand.setName(name);
        brand.setSlug(Slugable.toSlug(name));
        if(!file.isEmpty()){
            brand.setImage(fileUploadHandler.fileUpload(file));
        }else{
            brand.setImage(brandRepo.findById(id).get().getImage());
        }

        return brandRepo.save(brand);
    }

    public void delete(UUID id){
        brandNotFound(id);
        brandRepo.deleteById(id);
    }


    private void checkBrandIsExist(String name){
        if(brandRepo.existsByName(name)) throw new EntityNotFoundException("Brand name Already Exist");
    }

    private void brandNotFound(UUID id){
        if(!brandRepo.existsById(id)) throw new EntityNotFoundException("Brand Not Found");
    }

    private void brandUpdateValidation(String name, UUID id){
        if (brandRepo.existsByName(name) && !brandRepo.findByName(name).getId().equals(id))
            throw new DuplicateDataException("Brand name already exist");
    }
}
