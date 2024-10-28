package com.server.record.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@Data @NoArgsConstructor @AllArgsConstructor
@Builder @Entity
@DynamicInsert
public class Product {

    @Id
    @Column(name="product_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productCode;

    @Column(name="product_type")
    private String productType;

    @Column(name="product_name")
    private String productName;

    @Column(name="product_price")
    private String productPrice;

    @Column(name="product_explanation")
    private String productExplanation;

    @Column(name="product_quantity")
    private int productQuantity;

    @Column(name="product_longtext")
    private String productLongtext;

}
