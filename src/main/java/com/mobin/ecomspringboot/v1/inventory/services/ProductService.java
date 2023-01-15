package com.mobin.ecomspringboot.v1.inventory.services;

import com.mobin.ecomspringboot.globals.enumerates.Currency;
import com.mobin.ecomspringboot.globals.enumerates.ProductStatus;
import com.mobin.ecomspringboot.globals.enumerates.ProductStock;
import com.mobin.ecomspringboot.globals.helpers.FileUploadHandler;
import com.mobin.ecomspringboot.globals.helpers.Slugable;
import com.mobin.ecomspringboot.v1.generals.entities.Attribute;
import com.mobin.ecomspringboot.v1.generals.entities.AttributeValue;
import com.mobin.ecomspringboot.v1.generals.entities.Unit;
import com.mobin.ecomspringboot.v1.generals.repositories.AttributeRepository;
import com.mobin.ecomspringboot.v1.generals.repositories.AttributeValueRepository;
import com.mobin.ecomspringboot.v1.generals.repositories.UnitRepository;
import com.mobin.ecomspringboot.v1.inventory.entity.*;
import com.mobin.ecomspringboot.v1.inventory.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final CategoryRepository categoryRepo;
    private final SubCategoryRepository subCategoryRepo;
    private final BrandRepository brandRepo;
    private final UnitRepository unitRepo;
    private final ProductRepository productRepo;
    private final ProductImageRepository productImgRepo;
    private final AttributeRepository attrRepo;
    private final AttributeValueRepository attrValueRepo;
    private final ProductVariationRepository productVarRepo;
    private final FileUploadHandler fileUploadHandler;

    public List<Product> products(){
        return productRepo.findAll();
    }

    @Transactional
    public Product save(UUID categoryId,
                        UUID subCategoryId,
                        UUID brandId,
                        UUID unitId,
                        Currency currency,
                        ProductStatus productStatus,
                        ProductStock stockStatus,
                        String name,
                        Double purchasePrice,
                        Double regularPrice,
                        Double discountPrice,
                        Integer quantity,
                        boolean isFeature,
                        boolean isAdvance,
                        MultipartFile thumbImage,
                        List<MultipartFile> moreImages,
                        List<String> attrName,
                        List<String> attrValue,
                        List<Double> advPurchasePrice,
                        List<Double> advRegularPrice,
                        List<Double> advDiscountPrice,
                        List<Integer> advQuantity
    ) throws IOException {
        Product product = new Product();
        product.setCategory(checkCategory(categoryId));
        product.setSubCategory(checkSubCategory(subCategoryId));
        product.setBrand(checkBrand(brandId));
        product.setUnit(checkUnit(unitId));
        product.setCurrency(currency.name());
        product.setProductStatus(productStatus.name());
        product.setStockStatus(stockStatus.name());
        product.setName(name);
        product.setSlug(Slugable.toSlug(name));
        product.setAdvanced(isAdvance);
        if(!isAdvance){
            product.setPurchasePrice(purchasePrice);
            product.setRegularPrice(regularPrice);
            product.setDiscountPrice(discountPrice);
            product.setQuantity(quantity);
        }

        product.setFeatured(isFeature);
        product.setThumbImage(fileUploadHandler.fileUpload(thumbImage));
        Product storeProduct = productRepo.save(product);

        List<ProductImage> productImageList = new ArrayList<>();

        for (int i = 0; i < moreImages.size(); i++) {
            String imageUri = fileUploadHandler.fileUpload(moreImages.get(i));
            ProductImage productImage = new ProductImage();
            productImage.setImage(imageUri);
            productImage.setProduct(storeProduct);
            productImgRepo.save(productImage);
            productImageList.add(productImage);
        }
        storeProduct.setProductImages(productImageList);


        if(isAdvance && attrName.size() == attrValue.size()){

            if(attrName.size() < 2){
                throw new RuntimeException("An attribute name should have an attribute value");
            }

            int minSize = Math.min(attrName.size(), attrValue.size());
            minSize = Math.min(minSize, advPurchasePrice.size());
            minSize = Math.min(minSize, advRegularPrice.size());
            minSize = Math.min(minSize, advDiscountPrice.size());
            minSize = Math.min(minSize, advQuantity.size());
            for (int i = 0; i < minSize; i++) {
                ProductVariation variation = new ProductVariation();

                Map<String, String> Var1attr1 = new HashMap<>();
                Var1attr1.put(attrName.get(i),attrValue.get(i));
                Map<String, String> Var1attr2 = new HashMap<>();
                Var1attr2.put(attrName.get(i+1),attrValue.get(i+1));
                variation.setAttributes(Var1attr1 +","+ Var1attr2);


                variation.setProduct(storeProduct);
                variation.setPurchasePrice(advPurchasePrice.get(i));
                variation.setRegularPrice(advRegularPrice.get(i));
                variation.setDiscountPrice(advDiscountPrice.get(i));
                variation.setQuantity(advQuantity.get(i));
                productVarRepo.save(variation);
            }

//            for (int i = 0; i < attrName.size(); i++) {
//                getAttributeValue(attrName.get(i), attrValue.get(i));
//
//                ProductVariation variation = new ProductVariation();
//
//                Map<String, String> Var1attr1 = new HashMap<>();
//                Var1attr1.put(attrName.get(i),attrValue.get(i));
//                Map<String, String> Var1attr2 = new HashMap<>();
//                Var1attr2.put(attrName.get(i+1),attrValue.get(i+1));
//                //marge two attributes into string
//                variation.setAttributes(Var1attr1 +","+ Var1attr2);
//                variation.setProduct(storeProduct);
//                variation.setPurchasePrice(advPurchasePrice.get(i));
//                variation.setRegularPrice(advRegularPrice.get(i));
//                variation.setDiscountPrice(advDiscountPrice.get(i));
//                variation.setQuantity(advQuantity.get(i));
//
//                productVarRepo.save(variation);
//            }
        }else{
            throw new RuntimeException("Attribute Name and value size mismatch");
        }


        return storeProduct;
    }


    public Product show(UUID id){
        return productRepo.findById(id).orElseThrow(()->{throw new EntityNotFoundException("Product Not Found");});
    }



    private Category checkCategory(UUID id){
        return categoryRepo.findById(id).orElseThrow(()->{throw new EntityNotFoundException("Category Not Found");
        });
    }

    private SubCategory checkSubCategory(UUID id){
        return subCategoryRepo.findById(id).orElseThrow(()->{throw new EntityNotFoundException("Sub Category Not Found");
        });
    }

    private Brand checkBrand(UUID id){
        return brandRepo.findById(id).orElseThrow(()->{throw new EntityNotFoundException("Brand Not Found");
        });
    }

    private Unit checkUnit(UUID id){
        return unitRepo.findById(id).orElseThrow(()->{throw new EntityNotFoundException("Unit Not Found");
        });
    }


    private Attribute getAttribute(String name){
        return attrRepo.findByName(name).orElseThrow(()->{throw new EntityNotFoundException("Attribute Not Found");});
    }

    private AttributeValue getAttributeValue(String name, String value){
        return attrValueRepo.findByAttributeAndValue(getAttribute(name), value).orElseThrow(()->{throw new EntityNotFoundException("Attribute Value Not Found");});
    }
}
