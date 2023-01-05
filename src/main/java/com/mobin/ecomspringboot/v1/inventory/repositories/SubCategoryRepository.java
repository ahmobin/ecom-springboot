package com.mobin.ecomspringboot.v1.inventory.repositories;

import com.mobin.ecomspringboot.v1.inventory.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, UUID> {
    List<SubCategory> findAllByCategoryId(UUID categoryId);

    SubCategory findByName(String name);
    Optional<SubCategory> findByCategoryIdAndId(UUID categoryId, UUID id);

    boolean existsByName(String name);
}
