package com.mobin.ecomspringboot.v1.inventory.entity;

import com.mobin.ecomspringboot.globals.enumerates.Currency;
import com.mobin.ecomspringboot.globals.enumerates.ProductStatus;
import com.mobin.ecomspringboot.globals.enumerates.ProductStock;
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
    private Category category;

    @ManyToOne
    private SubCategory subCategory;

    @ManyToOne
    private Brand brand;

    @Column(nullable = false)
    private String currency;

    @ManyToOne
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

    @OneToMany(mappedBy = "product")
    private List<ProductImage> productImages;

    @Column(name = "product_status", nullable = false)
    private String productStatus;

    @Column(name = "stock_status", nullable = false)
    private String stockStatus;

    @Column(name = "is_featured", nullable = false)
    private boolean isFeatured = false;

    @Column(name = "is_advanced", nullable = false)
    private boolean isAdvanced = false;

    @Column(length = 255, nullable = false, name = "thumb_image")
    private String thumbImage;

    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updatedAt;
}
