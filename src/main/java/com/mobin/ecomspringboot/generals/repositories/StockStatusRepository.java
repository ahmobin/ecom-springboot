package com.mobin.ecomspringboot.generals.repositories;

import com.mobin.ecomspringboot.generals.entities.StockStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StockStatusRepository extends JpaRepository<StockStatus, UUID> {
    boolean existsByStatus(String status);
}
