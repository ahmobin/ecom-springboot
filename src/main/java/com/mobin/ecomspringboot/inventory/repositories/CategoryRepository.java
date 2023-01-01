package com.mobin.ecomspringboot.inventory.repositories;

import com.mobin.ecomspringboot.inventory.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
