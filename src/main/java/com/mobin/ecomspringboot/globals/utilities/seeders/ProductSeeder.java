package com.mobin.ecomspringboot.globals.utilities.seeders;

import com.mobin.ecomspringboot.globals.enumerates.Currency;
import com.mobin.ecomspringboot.globals.enumerates.ProductStatus;
import com.mobin.ecomspringboot.globals.enumerates.ProductStock;
import com.mobin.ecomspringboot.v1.generals.repositories.*;
import com.mobin.ecomspringboot.v1.inventory.entity.Product;
import com.mobin.ecomspringboot.v1.inventory.entity.ProductImage;
import com.mobin.ecomspringboot.v1.inventory.entity.ProductVariation;
import com.mobin.ecomspringboot.v1.inventory.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductSeeder {
    private final ProductRepository productRepo;
    private final ProductImageRepository productImgRepo;
    private final ProductVariationRepository productVarRepo;
    private final ProductStatusRepository productStatusRepo;
    private final StockStatusRepository stockStatusRepo;
    private final CurrencyRepository currencyRepo;
    private final UnitRepository unitRepo;
    private final CategoryRepository categoryRepo;
    private final SubCategoryRepository subCategoryRepo;
    private final BrandRepository brandRepo;
    private final ProductAttributeRepository productAttributeRepo;
    private final ProductAttrValueRepository productAttrValueRepo;

    public void simpleProduct(){
        Product product = new Product();
        product.setAdvanced(false);
        product.setFeatured(false);
        product.setProductStatus(ProductStatus.ACTIVE);
        product.setStockStatus(ProductStock.IN_STOCK);
        product.setCurrency(Currency.BDT);
        product.setUnit(unitRepo.findByName("Piece").get());
        product.setCategory(categoryRepo.findByName("Category Name 1"));
        product.setSubCategory(subCategoryRepo.findByName("Sub Category Name 1"));
        product.setBrand(brandRepo.findByName("Brand One"));
        product.setPurchasePrice(1500.00);
        product.setRegularPrice(1700.00);
        product.setName("Simple Product");
        product.setSlug("simple-product");
        product.setQuantity(50);
        product.setThumb_image("http://www.onlylondon.properties/application/modules/themes/views/default/assets/images/image-placeholder.png");
        productRepo.save(product);
    }


    public void advancedProduct(){
        Product product = new Product();
        product.setAdvanced(true);
        product.setFeatured(true);
        product.setProductStatus(ProductStatus.ACTIVE);
        product.setStockStatus(ProductStock.IN_STOCK);
        product.setCurrency(Currency.BDT);
        product.setUnit(unitRepo.findByName("Piece").get());
        product.setCategory(categoryRepo.findByName("Category Name 1"));
        product.setSubCategory(subCategoryRepo.findByName("Sub Category Name 1"));
        product.setBrand(brandRepo.findByName("Brand One"));
        product.setName("Advance Product");
        product.setSlug("advance-product");
        product.setThumb_image("http://www.onlylondon.properties/application/modules/themes/views/default/assets/images/image-placeholder.png");
        Product saveProduct = productRepo.save(product);

        ProductVariation productVariation = new ProductVariation();
        productVariation.setProduct(saveProduct);
        productVariation.setProductAttribute(productAttributeRepo.findByName("Color"));
        productVariation.setProductAttributeValue(productAttrValueRepo.findByValue("Green").get());
        productVariation.setProductAttribute(productAttributeRepo.findByName("Size"));
        productVariation.setProductAttributeValue(productAttrValueRepo.findByValue("Long").get());
        productVariation.setQuantity(50);
        productVariation.setPurchasePrice(4050.0);
        productVariation.setRegularPrice(4550.0);
        productVariation.setDiscountPrice(4250.0);

        productVarRepo.save(productVariation);

        ProductVariation productVariation2 = new ProductVariation();
        productVariation2.setProduct(saveProduct);
        productVariation2.setProductAttribute(productAttributeRepo.findByName("Color"));
        productVariation2.setProductAttributeValue(productAttrValueRepo.findByValue("Green").get());
        productVariation2.setProductAttribute(productAttributeRepo.findByName("Size"));
        productVariation2.setProductAttributeValue(productAttrValueRepo.findByValue("Medium").get());
        productVariation2.setQuantity(50);
        productVariation2.setPurchasePrice(4050.0);
        productVariation2.setRegularPrice(4550.0);
        productVariation2.setDiscountPrice(4250.0);

        productVarRepo.save(productVariation2);

        ProductImage productImage1 = new ProductImage();
        productImage1.setProduct(product);
        productImage1.setImage("http://www.onlylondon.properties/application/modules/themes/views/default/assets/images/image-placeholder.png");
        productImgRepo.save(productImage1);

        ProductImage productImage2 = new ProductImage();
        productImage2.setProduct(product);
        productImage2.setImage("http://www.onlylondon.properties/application/modules/themes/views/default/assets/images/image-placeholder.png");
        productImgRepo.save(productImage2);
    }
}
