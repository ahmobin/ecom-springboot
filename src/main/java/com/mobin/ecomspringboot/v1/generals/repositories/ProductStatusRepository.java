package com.mobin.ecomspringboot.v1.generals.repositories;

import com.mobin.ecomspringboot.v1.generals.entities.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductStatusRepository extends JpaRepository<ProductStatus, UUID> {
    boolean existsByStatus(String status);
    Optional<ProductStatus> findByStatus(String status);
}
