package com.mobin.ecomspringboot.v1.inventory.repositories;

import com.mobin.ecomspringboot.v1.inventory.entity.ProductVariation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductVariationRepository extends JpaRepository<ProductVariation, UUID> {
}
