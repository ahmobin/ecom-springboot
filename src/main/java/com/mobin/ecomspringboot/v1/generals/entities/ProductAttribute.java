package com.mobin.ecomspringboot.v1.generals.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mobin.ecomspringboot.v1.inventory.entity.ProductVariation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity(name = "product_attributes")
public class ProductAttribute {

    @Id
    @GeneratedValue(generator = "UUID")
    @Type(type = "uuid-char")
    private UUID id;

    @Column(nullable = false, unique = true, length = 128)
    private String name;

    @OneToMany(mappedBy = "productAttribute")
    @JsonIgnore
    private List<ProductAttributeValue> productAttributeValues;

    @OneToMany(mappedBy = "productAttribute")
    @JsonIgnore
    private List<ProductVariation> productVariations;

    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updatedAt;
}
