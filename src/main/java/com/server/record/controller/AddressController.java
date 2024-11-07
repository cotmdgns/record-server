package com.server.record.controller;


import com.server.record.domain.Address;
import com.server.record.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/address/*")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class AddressController {

    @Autowired
    private AddressService service;

    // 유저의 주소 보여주기
    @GetMapping("allAddress/{userCode}")
    public ResponseEntity allAddress(@PathVariable int userCode){
        return ResponseEntity.ok().body(service.allAddress(userCode));
    }

    // 주소 정보 가져와서 만들기
    @PostMapping("createAddress")
    public ResponseEntity createAddress(@RequestBody Address address){
        service.createAddress(address);
        return ResponseEntity.ok().build();
    }

    // 주소 정보 수정하기
    @PutMapping("putAddress")
    public ResponseEntity putAddress(@RequestBody Address address){
        service.putAddress(address);
        return ResponseEntity.ok().build();
    }

    // 주소 정보 삭제하기
        @DeleteMapping("deleteAddress/{code}")
    public ResponseEntity deleteAddress(@PathVariable("name") int code){
        service.deleteAddress(code);
        return ResponseEntity.ok().build();
    }

}
