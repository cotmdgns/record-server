package com.server.record.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor
@NoArgsConstructor @Entity @Builder
public class ProductImg {

    @Id
    @Column(name="product_img_code")
    private int productImgCode;

    @Column(name="product_code")
    private int productCode;

    @Column(name="product_img_address")
    private String productImgAddress;
}
