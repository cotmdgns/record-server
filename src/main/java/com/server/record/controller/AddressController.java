package com.server.record.controller;


import com.server.record.domain.Address;
import com.server.record.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/address/*")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class AddressController {

    @Autowired
    private AddressService service;


    // 유저의 주소 보여주기 ( 모달창에서 )
    @GetMapping("allAddress/{userCode}")
    public ResponseEntity allAddress(@PathVariable int userCode){
        return ResponseEntity.ok().body(service.allAddress(userCode));
    }
    // 결제페이지에서 하나만 보여주기
    @PostMapping("viewAddress")
    public ResponseEntity viewAddress(@RequestBody Address address){
        return ResponseEntity.ok().body(service.viewAddress(address));
    }

    // 주소 정보 가져와서 만들기
    @PostMapping("createAddress")
    public ResponseEntity createAddress(@RequestBody Address address){
        List<Address> list = service.allAddress(address.getUserCode());
        if(list.isEmpty()){
            address.setAddressUserStartCode(1);
            service.createAddress(address);
        }else{
            service.createAddress(address);
        }
        return ResponseEntity.ok().body(service.allAddress(address.getUserCode()));
    }

    // 주소 정보 삭제하기
    @DeleteMapping("deleteAddress/{userCode}/{addressCode}")
    public ResponseEntity deleteAddress(@PathVariable("userCode") int userCode,@PathVariable("addressCode") int addressCode){
        service.deleteAddress(addressCode);
        return ResponseEntity.ok().body(service.allAddress(userCode));
    }

    // 주소 정보 변경하기 ( 눌렀을때 스타트 코드가 1로 바뀌게끔 만들기 )
    @PutMapping("putAddress")
    public ResponseEntity putAddress(@RequestBody Address address){
        List<Address> list = service.allAddress(address.getUserCode());
        for(Address adds : list){
            adds.setAddressUserStartCode(0);
            service.putAddress(adds);
        }
        address.setAddressUserStartCode(1);
        service.putAddress(address);

        return ResponseEntity.ok().body(service.allAddress(address.getUserCode()));
    }



}
