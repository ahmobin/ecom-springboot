package com.mobin.ecomspringboot.v1.inventory.controller;

import com.mobin.ecomspringboot.globals.apis.v1.ApiEndpoints;
import com.mobin.ecomspringboot.globals.enumerates.Currency;
import com.mobin.ecomspringboot.globals.enumerates.ProductStatus;
import com.mobin.ecomspringboot.globals.enumerates.ProductStock;
import com.mobin.ecomspringboot.v1.inventory.entity.Product;
import com.mobin.ecomspringboot.v1.inventory.models.request.ProductRequest;
import com.mobin.ecomspringboot.v1.inventory.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
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
                                         @Valid @NotNull @RequestParam Integer quantity,
                                         @RequestParam boolean isFeature,
                                         @RequestParam boolean isAdvance,
                                         @Valid @NotNull @RequestParam MultipartFile thumbImage,
                                         @RequestParam List<MultipartFile> moreImages,
                                         @RequestParam List<String> attrName,
                                         @RequestParam List<String> attrValue,
                                         @RequestParam List<Double> advPurchasePrice,
                                         @RequestParam List<Double> advRegularPrice,
                                         @RequestParam List<Double> advDiscountPrice,
                                         @RequestParam List<Integer> advQuantity
    ) throws IOException {

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
}
