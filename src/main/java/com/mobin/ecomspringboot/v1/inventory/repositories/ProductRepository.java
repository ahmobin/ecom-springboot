package com.mobin.ecomspringboot.v1.inventory.repositories;

import com.mobin.ecomspringboot.v1.inventory.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    boolean existsByName(String name);
    boolean existsBySlug(String slug);
}
