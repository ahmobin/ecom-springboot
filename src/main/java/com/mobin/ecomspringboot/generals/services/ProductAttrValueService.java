package com.mobin.ecomspringboot.generals.services;

import com.mobin.ecomspringboot.exceptions.DuplicateDataException;
import com.mobin.ecomspringboot.generals.entities.ProductAttribute;
import com.mobin.ecomspringboot.generals.entities.ProductAttributeValue;
import com.mobin.ecomspringboot.generals.models.requests.ProductAttrValueRequest;
import com.mobin.ecomspringboot.generals.repositories.ProductAttrValueRepository;
import com.mobin.ecomspringboot.generals.repositories.ProductAttributeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductAttrValueService {

    private final ProductAttrValueRepository productAttrValueRepo;
    private final ProductAttributeRepository productAttrRepo;

    public List<ProductAttributeValue> productAttrValues(UUID productAttrId){
        return productAttrValueRepo.findAllByProductAttributeId(productAttrId);
    }

    public ProductAttributeValue store(UUID productAttrId, ProductAttrValueRequest productAttrValueRequest){

        if(productAttrValueRepo.findByProductAttributeIdAndValue(productAttrId, productAttrValueRequest.getValue()).isPresent())
            throw new DuplicateDataException("Product Attribute Value Already Exist On The Attribute");

        ProductAttributeValue productAttributeValue = new ProductAttributeValue();
        productAttributeValue.setProductAttribute(getProductAttribute(productAttrId));
        productAttributeValue.setValue(productAttrValueRequest.getValue());
        productAttributeValue.setCode(productAttrValueRequest.getCode());
        return productAttrValueRepo.save(productAttributeValue);
    }


    public ProductAttributeValue show(UUID productAttrId, UUID attrValueId){
        if (productAttrValueRepo.findByProductAttributeIdAndId(productAttrId, attrValueId).isPresent())
            return productAttrValueRepo.findByProductAttributeIdAndId(productAttrId, attrValueId).get();
        else throw new EntityNotFoundException("Product Attribute Value Not Found");
    }

    public ProductAttributeValue update(UUID productAttrId, UUID attrValueId, ProductAttrValueRequest productAttrValueRequest){
        if (productAttrValueRepo.findByProductAttributeIdAndId(productAttrId, attrValueId).isEmpty())
            throw new EntityNotFoundException("Product Attribute Value Not Found");

        validateProductAttrValue(productAttrId, attrValueId, productAttrValueRequest.getValue());

        ProductAttributeValue productAttributeValue = new ProductAttributeValue();
        productAttributeValue.setProductAttribute(getProductAttribute(productAttrId));
        productAttributeValue.setValue(productAttrValueRequest.getValue());
        productAttributeValue.setCode(productAttrValueRequest.getCode());
        productAttributeValue.setId(attrValueId);
        return productAttrValueRepo.save(productAttributeValue);
    }

    public void delete(UUID productAttrId, UUID attrValueId){
        if (productAttrValueRepo.findByProductAttributeIdAndId(productAttrId, attrValueId).isEmpty())
            throw new EntityNotFoundException("Product Attribute Value Not Found");
        productAttrValueRepo.deleteById(attrValueId);
    }


    private ProductAttribute getProductAttribute(UUID id){
        if(productAttrRepo.findById(id).isPresent())
            return productAttrRepo.findById(id).get();
        else throw new EntityNotFoundException("Product Attribute Not Found");
    }

    private void validateProductAttrValue(UUID productAttrId, UUID attrValueId, String value){
        if (productAttrValueRepo.existsByValueAndProductAttributeId(value, productAttrId) &&
                !productAttrValueRepo.findByProductAttributeIdAndValue(productAttrId,value).get().getId().equals(attrValueId))
            throw new DuplicateDataException("Sub category name already exist");
    }
}
