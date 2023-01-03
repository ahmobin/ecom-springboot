package com.mobin.ecomspringboot.inventory.repositories;

import com.mobin.ecomspringboot.inventory.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BrandRepository extends JpaRepository<Brand, UUID> {
    boolean existsByName(String name);
    Brand findByName(String name);
}
