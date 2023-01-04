package com.mobin.ecomspringboot.generals.repositories;

import com.mobin.ecomspringboot.generals.entities.ProductAttributeValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductAttrValueRepository extends JpaRepository<ProductAttributeValue, UUID> {
    List<ProductAttributeValue> findAllByProductAttributeId(UUID id);

    Optional<ProductAttributeValue> findByProductAttributeIdAndId(UUID productAttrId, UUID id);

    Optional<ProductAttributeValue> findByProductAttributeIdAndValue(UUID productAttrId, String value);

    boolean existsByValueAndProductAttributeId(String value, UUID productAttributeId);
}
