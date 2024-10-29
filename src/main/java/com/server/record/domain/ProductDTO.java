package com.server.record.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    private String productLongtext;
    
    // 클라이언트에서 받을때 사용
    private MultipartFile[] productImg;
    // 디테일 페이지 들어갈때 사용
    private List<ProductImg> productImgAll;
    // 상품페이지에서 하나만 보여줄때 사용
    private String productImgOne;

}
