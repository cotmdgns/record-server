package com.server.record.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@Builder @Entity
public class Product {

    @Id
    @Column(name="product_code")
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
    private String productQuantity;

//    @ManyToOne
//    @JoinColumn(name="product_img")
//    private ProductImg productImg;
    @Column(name="product_img")
    private int productImg;


}
