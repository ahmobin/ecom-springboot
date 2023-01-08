package com.mobin.ecomspringboot.v1.inventory.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mobin.ecomspringboot.v1.generals.entities.ProductAttribute;
import com.mobin.ecomspringboot.v1.generals.entities.ProductAttributeValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity(name = "product_variations")
public class ProductVariation {
    @Id
    @GeneratedValue(generator = "UUID")
    @Type(type = "uuid-char")
    private UUID id;

    @ManyToOne
    @JsonIgnore
    private Product product;

    @ManyToOne
    private ProductAttribute productAttribute;

    @ManyToOne
    private ProductAttributeValue productAttributeValue;

    @Column(nullable = false)
    private Integer quantity;


    @Column(name = "purchase_price", precision = 12, scale = 2)
    private Double purchasePrice;

    @Column(name = "regular_price",nullable = false, precision = 12, scale = 2)
    private Double regularPrice;

    @Column(name = "discount_price", precision = 12, scale = 2)
    private Double discountPrice;

    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updatedAt;
}
