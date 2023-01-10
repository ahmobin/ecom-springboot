package com.mobin.ecomspringboot.globals.utilities.seeders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobin.ecomspringboot.globals.enumerates.Currency;
import com.mobin.ecomspringboot.globals.enumerates.ProductStatus;
import com.mobin.ecomspringboot.globals.enumerates.ProductStock;
import com.mobin.ecomspringboot.v1.generals.entities.Attribute;
import com.mobin.ecomspringboot.v1.generals.entities.AttributeValue;
import com.mobin.ecomspringboot.v1.generals.entities.StockStatus;
import com.mobin.ecomspringboot.v1.generals.repositories.AttributeRepository;
import com.mobin.ecomspringboot.v1.generals.repositories.AttributeValueRepository;
import com.mobin.ecomspringboot.v1.generals.repositories.UnitRepository;
import com.mobin.ecomspringboot.v1.inventory.entity.Product;
import com.mobin.ecomspringboot.v1.inventory.entity.ProductImage;
import com.mobin.ecomspringboot.v1.inventory.entity.ProductVariation;
import com.mobin.ecomspringboot.v1.inventory.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ProductSeeder {
    private final BrandRepository brandRepo;
    private final CategoryRepository cateRepo;
    private final SubCategoryRepository subCateRepo;
    private final UnitRepository unitRepo;
    private final ProductRepository prodRepo;
    private final AttributeRepository attrRepo;
    private final AttributeValueRepository attrValueRepo;
    private final ProductImageRepository prodImgRepo;
    private final ProductVariationRepository variationRepo;

    public void simpleProductCreate(){
        Product simpleProduct = new Product();
        simpleProduct.setCategory(cateRepo.findByName("Category Name 1"));
        simpleProduct.setSubCategory(subCateRepo.findByName("Sub Category Name 1"));
        simpleProduct.setBrand(brandRepo.findByName("Brand One"));
        simpleProduct.setCurrency(Currency.BDT.toString());
        simpleProduct.setUnit(unitRepo.findByName("Piece").orElseThrow());
        simpleProduct.setName("Simple Product");
        simpleProduct.setSlug("simple-product");
        simpleProduct.setPurchasePrice(521.00);
        simpleProduct.setRegularPrice(535.00);
        simpleProduct.setQuantity(50);
        simpleProduct.setProductStatus(ProductStatus.ACTIVE.toString());
        simpleProduct.setStockStatus(ProductStock.IN_STOCK.toString());
        simpleProduct.setThumb_image("http://www.onlylondon.properties/application/modules/themes/views/default/assets/images/image-placeholder.png");
        attachImages(prodRepo.save(simpleProduct));
    }

    public void advanceProductCreate() throws JsonProcessingException {
        Product advanceProduct = new Product();
        advanceProduct.setCategory(cateRepo.findByName("Category Name 1"));
        advanceProduct.setSubCategory(subCateRepo.findByName("Sub Category Name 1"));
        advanceProduct.setBrand(brandRepo.findByName("Brand One"));
        advanceProduct.setCurrency(Currency.BDT.toString());
        advanceProduct.setUnit(unitRepo.findByName("Piece").orElseThrow());
        advanceProduct.setName("Advance Product");
        advanceProduct.setSlug("advance-product");
        advanceProduct.setProductStatus(ProductStatus.ACTIVE.toString());
        advanceProduct.setStockStatus(ProductStock.IN_STOCK.toString());
        advanceProduct.setFeatured(true);
        advanceProduct.setAdvanced(true);
        advanceProduct.setThumb_image("http://www.onlylondon.properties/application/modules/themes/views/default/assets/images/image-placeholder.png");
        Product save = prodRepo.save(advanceProduct);

        Attribute attribute1 = attrRepo.findByName("Color").orElseThrow();
        Attribute attribute2 = attrRepo.findByName("Size").orElseThrow();
        AttributeValue attrValue1 = attrValueRepo.findByAttributeAndValue(attribute1, "Black").orElseThrow();
        AttributeValue attrValue2 = attrValueRepo.findByAttributeAndValue(attribute2, "L").orElseThrow();
        AttributeValue attrValue3 = attrValueRepo.findByAttributeAndValue(attribute2, "M").orElseThrow();

        ProductVariation variation1 = new ProductVariation();
        variation1.setProduct(save);
        variation1.setPurchasePrice(520.00);
        variation1.setRegularPrice(540.00);
        variation1.setDiscountPrice(530.00);
        variation1.setQuantity(15);
        //color => black
        Map<String, String> Var1attr1 = new HashMap<>();
        Var1attr1.put(attribute1.getName(),attrValue1.getValue());
        //size => L
        Map<String, String> Var1attr2 = new HashMap<>();
        Var1attr2.put(attribute2.getName(),attrValue2.getValue());
        //marge two attributes into string
        variation1.setAttributes(Var1attr1 +","+ Var1attr2);
        variationRepo.save(variation1);


        ProductVariation variation2 = new ProductVariation();
        variation2.setProduct(save);
        variation2.setPurchasePrice(520.00);
        variation2.setRegularPrice(540.00);
        variation2.setDiscountPrice(530.00);
        variation2.setQuantity(12);

        Map<Attribute, AttributeValue> Var2attr1 = new HashMap<>();
        //color => black
        Var2attr1.put(attribute1,attrValue1);
        //size => L
        Map<Attribute, AttributeValue> Var2attr2 = new HashMap<>();
        Var2attr2.put(attribute2,attrValue3);
        //marge two attributes into string
        String json3 = new ObjectMapper().writeValueAsString(Var2attr1);
        String json4 = new ObjectMapper().writeValueAsString(Var2attr2);

        String variationTwo = json3 +","+ json4;
        variation2.setAttributes(variationTwo);
        variationRepo.save(variation2);

        attachImages(save);


    }


    private void attachImages(Product product){
        List<ProductImage> images = new ArrayList<>();
        ProductImage img1 = new ProductImage();
        img1.setImage("http://www.onlylondon.properties/application/modules/themes/views/default/assets/images/image-placeholder.png");
        img1.setProduct(product);

        ProductImage img2 = new ProductImage();
        img2.setImage("http://www.onlylondon.properties/application/modules/themes/views/default/assets/images/image-placeholder.png");
        img2.setProduct(product);

        ProductImage img3 = new ProductImage();
        img3.setImage("http://www.onlylondon.properties/application/modules/themes/views/default/assets/images/image-placeholder.png");
        img3.setProduct(product);

        images.add(img1);
        images.add(img2);
        images.add(img3);

        prodImgRepo.saveAll(images);
    }
}
