package com.mobin.ecomspringboot.v1.generals.repositories;

import com.mobin.ecomspringboot.v1.generals.entities.AttributeValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttributeValueRepository extends JpaRepository<AttributeValue, UUID> {
}
