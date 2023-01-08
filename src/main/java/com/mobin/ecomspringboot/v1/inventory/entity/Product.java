package com.mobin.ecomspringboot.v1.inventory.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mobin.ecomspringboot.v1.generals.entities.Currency;
import com.mobin.ecomspringboot.v1.generals.entities.ProductStatus;
import com.mobin.ecomspringboot.v1.generals.entities.StockStatus;
import com.mobin.ecomspringboot.v1.generals.entities.Unit;
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
@Entity(name = "products")
public class Product {

    @Id
    @GeneratedValue(generator = "UUID")
    @Type(type = "uuid-char")
    private UUID id;

    @ManyToOne
    @JsonIgnore
    private Category category;

    @ManyToOne
    @JsonIgnore
    private SubCategory subCategory;

    @ManyToOne
    @JsonIgnore
    private Brand brand;

    @ManyToOne
    @JsonIgnore
    private Currency currency;


    @ManyToOne
    @JsonIgnore
    private Unit unit;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String slug;

    @Column(name = "purchase_price", precision = 14, scale = 2)
    private Double purchasePrice;

    @Column(name = "regular_price", precision = 14, scale = 2)
    private Double regularPrice;

    @Column(name = "discount_price", precision = 14, scale = 2)
    private Double discountPrice;

    private Integer quantity;


    @OneToMany(mappedBy = "product")
    private List<ProductVariation> productVariation;

    @ManyToOne
    @JsonIgnore
    private ProductStatus productStatus;

    @ManyToOne
    @JsonIgnore
    private StockStatus stockStatus;

    @Column(name = "is_featured", nullable = false)
    private boolean isFeatured = false;

    @Column(name = "is_advanced", nullable = false)
    private boolean isAdvanced = false;

    @Column(length = 255, nullable = false)
    private String thumb_image;

    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updatedAt;
}
