package com.mobin.ecomspringboot.v1.inventory.models.request;

import com.mobin.ecomspringboot.globals.enumerates.Currency;
import com.mobin.ecomspringboot.globals.enumerates.ProductStatus;
import com.mobin.ecomspringboot.globals.enumerates.ProductStock;
import com.mobin.ecomspringboot.v1.inventory.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ProductRequest {
    private UUID categoryId;
    private UUID subCategoryId;
    private UUID brandId;
    private Currency currency;
    private UUID unitId;
    private String name;
    private Double purchasePrice;
    private Double regularPrice;
    private Double discountPrice;
    private Integer quantity;
    private ProductStatus productStatus;
    private ProductStock stockStatus;
    private boolean isFeatured;
    private boolean isAdvanced;
    private MultipartFile thumbImage;


}
