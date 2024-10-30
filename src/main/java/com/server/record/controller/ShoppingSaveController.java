package com.server.record.controller;

import com.server.record.domain.ShoppingSave;
import com.server.record.service.ShoppingSaveService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // 유저 찜목록 보여주기
    @GetMapping("/allViewShoppingSave/{userCode}")
    public ResponseEntity AllViewShoppingSave(@PathVariable String userCode){
        return ResponseEntity.ok().body(service.AllViewShoppingSave(userCode));
    }


}
