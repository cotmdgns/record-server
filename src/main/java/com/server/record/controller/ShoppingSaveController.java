package com.server.record.controller;

import com.server.record.domain.ShoppingSave;
import com.server.record.service.ShoppingSaveService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/shoppingSave/*")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class ShoppingSaveController {

    @Autowired
    private ShoppingSaveService service;

    // 찜하기 생성하기
    @PostMapping("createShoppingSave")
    public ResponseEntity create(@RequestBody ShoppingSave shoppingSave){
        service.createShoppingSave(shoppingSave);
        return ResponseEntity.ok().build();
    };

    // 삭제하기
    @DeleteMapping("deleteShoppingSave")
    public ResponseEntity deleteShoppingSave (@RequestParam(name="userCode") int userCode,@RequestParam(name="productCode") int productCode){
        log.info("유저 코드 : "+ userCode);
        log.info("상품 코드 : "+ productCode);
        return ResponseEntity.ok().build();
    }

    // 유저 찜목록 보여주기
    @GetMapping("/allViewShoppingSave/{userCode}")
    public ResponseEntity AllViewShoppingSave(@PathVariable int userCode){
        return ResponseEntity.ok().body(service.AllViewShoppingSave(userCode));
    }




}
