package com.server.record.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {


    private int productCode;
    private String productType;
    private String productName;
    private String productPrice;
    private String productExplanation;
    private int productQuantity;
    private MultipartFile[] productImg;

}
