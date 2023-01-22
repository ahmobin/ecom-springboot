package com.mobin.ecomspringboot.v1.inventory.controller;

import com.mobin.ecomspringboot.exceptions.UnwantedRequestException;
import com.mobin.ecomspringboot.globals.apis.v1.ApiEndpoints;
import com.mobin.ecomspringboot.globals.enumerates.Currency;
import com.mobin.ecomspringboot.globals.enumerates.ProductStatus;
import com.mobin.ecomspringboot.globals.enumerates.ProductStock;
import com.mobin.ecomspringboot.v1.inventory.entity.Product;
import com.mobin.ecomspringboot.v1.inventory.models.request.ProductRequest;
import com.mobin.ecomspringboot.v1.inventory.services.ProductService;
import com.mobin.ecomspringboot.validators.ProductValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Validated
public class ProductController {
    private final ProductService productService;
    private final ProductValidation productValidation;

    @GetMapping(ApiEndpoints.PRODUCTS_API)
    public ResponseEntity<List<Product>> index(){
        return ResponseEntity.of(Optional.of(productService.products()));
    }


    @PostMapping(ApiEndpoints.PRODUCTS_API)
    public ResponseEntity<Product> store(@Valid @NotNull @RequestParam UUID categoryId,
                                         @Valid @NotNull @RequestParam UUID subCategoryId,
                                         @Valid @NotNull @RequestParam UUID brandId,
                                         @Valid @NotNull @RequestParam UUID unitId,
                                         @Valid @NotNull @RequestParam Currency currency,
                                         @Valid @NotNull @RequestParam ProductStatus productStatus,
                                         @Valid @NotNull @RequestParam ProductStock stockStatus,
                                         @Valid @NotNull @RequestParam String name,
                                         @RequestParam Double purchasePrice,
                                         @RequestParam Double regularPrice,
                                         @RequestParam Double discountPrice,
                                         @RequestParam Integer quantity,
                                         @RequestParam boolean isFeature,
                                         @RequestParam boolean isAdvance,
                                         @Valid @NotNull @RequestParam MultipartFile thumbImage,
                                         @RequestParam @Nullable List<MultipartFile> moreImages,
                                         @RequestParam List<String> attrName,
                                         @RequestParam List<String> attrValue,
                                         @RequestParam List<Double> advPurchasePrice,
                                         @RequestParam List<Double> advRegularPrice,
                                         @RequestParam List<Double> advDiscountPrice,
                                         @RequestParam List<Integer> advQuantity
    ) throws IOException {

        if(thumbImage.isEmpty()){
            throw new UnwantedRequestException("Thumbnail image required");
        }

        int minSize = Math.min(attrName.size(), attrValue.size());
        minSize = Math.min(minSize, advPurchasePrice.size());
        minSize = Math.min(minSize, advRegularPrice.size());
        minSize = Math.min(minSize, advDiscountPrice.size());
        minSize = Math.min(minSize, advQuantity.size());

        if(isAdvance){
            for (int i = 0; i < minSize; i++) {
                productValidation.productValidation(name, advPurchasePrice.get(i), advRegularPrice.get(i), advDiscountPrice.get(i), advQuantity.get(i));
            }

        }else{
            productValidation.productValidation(name, purchasePrice, regularPrice, discountPrice, quantity);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(
                categoryId,
                subCategoryId,
                brandId,
                unitId,
                currency,
                productStatus,
                stockStatus,
                name,
                purchasePrice,
                regularPrice,
                discountPrice,
                quantity,
                isFeature,
                isAdvance,
                thumbImage,
                moreImages,
                attrName,
                attrValue,
                advPurchasePrice,
                advRegularPrice,
                advDiscountPrice,
                advQuantity
                ));
    }


    @GetMapping(ApiEndpoints.SINGLE_PRODUCTS_API)
    public ResponseEntity<Product> show(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(productService.show(id));
    }


    @PutMapping(ApiEndpoints.PRODUCTS_UPDATE_API)
    public ResponseEntity<Product> update(@Valid @NotNull @RequestParam UUID categoryId,
                                         @Valid @NotNull @RequestParam UUID subCategoryId,
                                         @Valid @NotNull @RequestParam UUID brandId,
                                         @Valid @NotNull @RequestParam UUID unitId,
                                         @Valid @NotNull @RequestParam Currency currency,
                                         @Valid @NotNull @RequestParam ProductStatus productStatus,
                                         @Valid @NotNull @RequestParam ProductStock stockStatus,
                                         @Valid @NotNull @RequestParam String name,
                                         @RequestParam Double purchasePrice,
                                         @RequestParam Double regularPrice,
                                         @RequestParam Double discountPrice,
                                         @RequestParam Integer quantity,
                                         @RequestParam boolean isFeature,
                                         @RequestParam boolean isAdvance,
                                         @Valid @NotNull @RequestParam MultipartFile thumbImage,
                                         @PathVariable UUID productId
    ) throws IOException {

        if(thumbImage.isEmpty()) {
            throw new UnwantedRequestException("Thumbnail image required");
        }

        if(!isAdvance){
            productValidation.productValidation(name, purchasePrice, regularPrice, discountPrice, quantity);
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(productService.update(
                categoryId,
                subCategoryId,
                brandId,
                unitId,
                currency,
                productStatus,
                stockStatus,
                name,
                purchasePrice,
                regularPrice,
                discountPrice,
                quantity,
                isFeature,
                isAdvance,
                thumbImage,
                productId
        ));
    }
}
