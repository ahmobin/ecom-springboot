package com.mobin.ecomspringboot.v1.inventory.services;

import com.mobin.ecomspringboot.v1.inventory.entity.Product;
import com.mobin.ecomspringboot.v1.inventory.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepo;

    public List<Product> products(){
        return productRepo.findAll();
    }
}
