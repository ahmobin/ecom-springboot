package com.mobin.ecomspringboot.inventory.repositories;

import com.mobin.ecomspringboot.inventory.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, UUID> {
}
