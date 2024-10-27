package com.server.record.controller;

import com.server.record.domain.Product;
import com.server.record.domain.ProductDTO;
import com.server.record.domain.ProductImg;
import com.server.record.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/product/*")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class ProductController {

    @Autowired
    private ProductService service;


    // 이 두개는 메인페이지에서 12개정도만 보여는거
    @GetMapping("MainLP")
    public ResponseEntity MainLP(){
        return ResponseEntity.status(HttpStatus.OK).body(service.MainLP());
    }

    @GetMapping("MainRecord")
    public ResponseEntity MainRecord(){
        return ResponseEntity.status(HttpStatus.OK).body(service.MainRecode());
    }


    @GetMapping("AllViewLp")
    public ResponseEntity AllViewLp() {
        return ResponseEntity.status(HttpStatus.OK).body(service.AllViewLp());
    }
    @GetMapping("AllViewRecord")
    public ResponseEntity AllViewRecord() {
        return ResponseEntity.status(HttpStatus.OK).body(service.AllViewRecode());
    }

    @PostMapping("CreateLpRecordProduct")
    public ResponseEntity createProduct(ProductDTO dto){
        // 빌드로 넣어주고
        Product pro = Product.builder()
                .productType(dto.getProductType())
                .productName(dto.getProductName())
                .productPrice(dto.getProductPrice())
                .productExplanation(dto.getProductExplanation())
                .productQuantity(dto.getProductQuantity())
                .build();
        // 서버에 보내준다음 코드를받아
        int ProductCode = service.CreateLpProduct(pro);

        // 그코드를 이미지에 넣어주고 이미지가 2장이상미면 이미지 갯수만큼 반복문 돌려서 이미지 갯수만큼 컬럼만들어주기
        for(int i=0;i<dto.getProductImg().length;i++){
            ProductImg img = ProductImg.builder()
                    .productCode(ProductCode)
                    .productImgAddress(String.valueOf(dto.getProductImg()[i].getOriginalFilename()))
                    .build();
            service.CreateImpProduct(img);
        }
        return ResponseEntity.ok().build();
    }
}
