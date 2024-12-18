package com.server.record.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamicInsert
public class ShoppingSaveDTO {

    private int shoppingCode;
    private int productCode;
    private int userCode;
    private int productCount; // 갯수

    private String productImg;
    private Product product;


}
