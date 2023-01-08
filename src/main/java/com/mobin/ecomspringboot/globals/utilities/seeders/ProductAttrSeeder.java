package com.mobin.ecomspringboot.globals.utilities.seeders;

import com.mobin.ecomspringboot.v1.generals.entities.ProductAttribute;
import com.mobin.ecomspringboot.v1.generals.entities.ProductAttributeValue;
import com.mobin.ecomspringboot.v1.generals.repositories.ProductAttrValueRepository;
import com.mobin.ecomspringboot.v1.generals.repositories.ProductAttributeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductAttrSeeder {
    private final ProductAttributeRepository productAttributeRepo;
    private final ProductAttrValueRepository productAttrValueRepo;

    public void createAttr(){
        ProductAttribute one = new ProductAttribute();
        ProductAttribute two = new ProductAttribute();
        List<ProductAttribute> productAttributes = new ArrayList<>();
        one.setName("Color");
        two.setName("Size");
        productAttributes.add(one);
        productAttributes.add(two);
        productAttributeRepo.saveAll(productAttributes);

        ProductAttributeValue value1 = new ProductAttributeValue();
        ProductAttributeValue value2 = new ProductAttributeValue();
        ProductAttributeValue value3 = new ProductAttributeValue();
        ProductAttributeValue value4 = new ProductAttributeValue();
        ProductAttributeValue value5 = new ProductAttributeValue();
        ProductAttributeValue value6 = new ProductAttributeValue();
        List<ProductAttributeValue> productAttributeValues = new ArrayList<>();
        value1.setProductAttribute(one);
        value1.setValue("Green");
        value1.setCode("#00FF00");
        value2.setProductAttribute(one);
        value2.setValue("Black");
        value2.setCode("#000000");
        value3.setProductAttribute(one);
        value3.setValue("White");
        value3.setCode("#FFFFFF");
        value4.setProductAttribute(two);
        value4.setValue("Long");
        value4.setCode("L");
        value5.setProductAttribute(two);
        value5.setValue("Medium");
        value5.setCode("M");
        value6.setProductAttribute(two);
        value6.setValue("Exel");
        value6.setCode("XL");

        productAttributeValues.add(value1);
        productAttributeValues.add(value2);
        productAttributeValues.add(value3);
        productAttributeValues.add(value4);
        productAttributeValues.add(value5);
        productAttributeValues.add(value6);

        productAttrValueRepo.saveAll(productAttributeValues);
    }
}
