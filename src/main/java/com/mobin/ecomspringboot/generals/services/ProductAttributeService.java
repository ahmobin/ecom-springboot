package com.mobin.ecomspringboot.generals.services;

import com.mobin.ecomspringboot.exceptions.DuplicateDataException;
import com.mobin.ecomspringboot.generals.entities.ProductAttribute;
import com.mobin.ecomspringboot.generals.models.requests.ProductAttributeRequest;
import com.mobin.ecomspringboot.generals.repositories.ProductAttributeRepository;
import com.mobin.ecomspringboot.globals.helpers.StringManageable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductAttributeService {

    private final ProductAttributeRepository productAttrRepo;

    public List<ProductAttribute> productAttributes(){
        return productAttrRepo.findAll();
    }


    public ProductAttribute store(ProductAttributeRequest productAttributeRequest){
        ProductAttribute productAttribute = new ProductAttribute();
        productAttribute.setName(StringManageable.capitalize(productAttributeRequest.getName()));
        return productAttrRepo.save(productAttribute);
    }

    public ProductAttribute show(UUID id){
        isProductAttrExist(id);
        return productAttrRepo.findById(id).get();
    }

    public ProductAttribute update(UUID id, ProductAttributeRequest productAttributeRequest){
        isProductAttrExist(id);
        productAttrUpdateValidate(id, productAttributeRequest.getName());

        ProductAttribute productAttribute = new ProductAttribute();
        productAttribute.setName(StringManageable.capitalize(productAttributeRequest.getName()));
        productAttribute.setId(id);
        return productAttrRepo.save(productAttribute);
    }


    public void delete(UUID id){
        isProductAttrExist(id);
        productAttrRepo.deleteById(id);
    }


    private void isProductAttrExist(UUID id){
        if(!productAttrRepo.existsById(id)) throw new EntityNotFoundException("Product Attribute Not Found");
    }

    private void productAttrUpdateValidate(UUID id, String name){
        if (productAttrRepo.existsByName(name) && !productAttrRepo.findByName(name).getId().equals(id))
            throw new DuplicateDataException("Product attribute already exist");
    }
}
