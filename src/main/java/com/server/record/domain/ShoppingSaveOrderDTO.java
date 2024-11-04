package com.server.record.domain;

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
public class ShoppingSaveOrderDTO {

    private int shoppingOrderCode;
    private Product product;
    // 이미지 하나 들어가기
    private String productImg;




}
