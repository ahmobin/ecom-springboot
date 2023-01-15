package com.mobin.ecomspringboot.v1.inventory.services;

import com.mobin.ecomspringboot.globals.enumerates.Currency;
import com.mobin.ecomspringboot.globals.enumerates.ProductStatus;
import com.mobin.ecomspringboot.globals.enumerates.ProductStock;
import com.mobin.ecomspringboot.globals.helpers.FileUploadHandler;
import com.mobin.ecomspringboot.globals.helpers.Slugable;
import com.mobin.ecomspringboot.v1.generals.entities.Unit;
import com.mobin.ecomspringboot.v1.generals.repositories.UnitRepository;
import com.mobin.ecomspringboot.v1.inventory.entity.*;
import com.mobin.ecomspringboot.v1.inventory.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final CategoryRepository categoryRepo;
    private final SubCategoryRepository subCategoryRepo;
    private final BrandRepository brandRepo;
    private final UnitRepository unitRepo;
    private final ProductRepository productRepo;
    private final ProductImageRepository productImgRepo;
    private final ProductVariationRepository productVarRepo;
    private final FileUploadHandler fileUploadHandler;

    public List<Product> products(){
        return productRepo.findAll();
    }

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
                        List<MultipartFile> moreImages
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
        product.setPurchasePrice(purchasePrice);
        product.setRegularPrice(regularPrice);
        product.setDiscountPrice(discountPrice);
        product.setQuantity(quantity);
        product.setFeatured(isFeature);
        product.setAdvanced(isAdvance);
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

}
