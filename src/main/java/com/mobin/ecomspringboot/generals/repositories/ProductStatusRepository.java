package com.mobin.ecomspringboot.generals.repositories;

import com.mobin.ecomspringboot.generals.entities.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductStatusRepository extends JpaRepository<ProductStatus, UUID> {
    boolean existsByStatus(String status);
}
