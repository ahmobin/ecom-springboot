package com.mobin.ecomspringboot.v1.inventory.repositories;

import com.mobin.ecomspringboot.v1.inventory.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, UUID> {
}
