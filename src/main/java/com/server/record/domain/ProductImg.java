package com.server.record.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.web.multipart.MultipartFile;

@Data @AllArgsConstructor
@NoArgsConstructor @Entity @Builder
@DynamicInsert
public class ProductImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_img_code")
    private int productImgCode;

    @Column(name="product_code")
    private int productCode;

    @Column(name="product_img_address")
    private String productImgAddress;
}
