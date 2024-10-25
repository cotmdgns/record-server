package com.server.record.controller;

import com.server.record.domain.Product;
import com.server.record.domain.ProductDTO;
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
        log.info(""+ dto);
        return ResponseEntity.ok().build();
        // 화면에 뿌려지잖아
        // 1000개 넘어
        // 이 1000개 에서 방금 내가 만든 상품을 가져와서
        // 이미지 코드에 넣고 다시 인설트해야지
        // 1000
    }
}
