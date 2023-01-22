package com.mobin.ecomspringboot.validators;

import com.mobin.ecomspringboot.exceptions.DuplicateDataException;
import com.mobin.ecomspringboot.exceptions.UnwantedRequestException;
import com.mobin.ecomspringboot.globals.helpers.Slugable;
import com.mobin.ecomspringboot.v1.inventory.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProductValidation {
    private final ProductRepository productRepo;


    public void productValidation(String name, Double purchasePrice, Double regularPrice, Double discountPrice, Integer quantity){
        
        if(hasProductName(name)){
            throw new DuplicateDataException("Product name already taken");
        }

        if(hasProductSlug(name)){
            throw new DuplicateDataException("Product name already taken");
        }

        if (purchasePrice != null && !purchasePriceLseRegularPrice(purchasePrice, regularPrice)){
            throw new UnwantedRequestException("Regular price cannot be greater than purchase price");
        }

        if (discountPrice != null && !regularPriceLseDiscountPrice(regularPrice, discountPrice)){
            throw new UnwantedRequestException("Discount price cannot be greater than regular price");
        }

        if (!isQuantityGtZero(quantity)){
            throw new UnwantedRequestException("Quantity cannot be zero");
        }

    }



    private boolean hasProductName(String name){
        return productRepo.existsByName(name);
    }

    private boolean hasProductSlug(String name){
        return productRepo.existsBySlug(Slugable.toSlug(name));
    }

    private boolean purchasePriceLseRegularPrice(Double purchasePrice, Double regularPrice){
        return purchasePrice <= regularPrice;
    }

    private boolean regularPriceLseDiscountPrice(Double regularPrice, Double discountPrice){
        return discountPrice <= regularPrice;
    }

    private boolean isQuantityGtZero(Integer quantity){
        return quantity > 0;
    }
}
