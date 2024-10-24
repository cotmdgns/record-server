package com.server.record.controller;

import com.server.record.domain.Product;
import com.server.record.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
