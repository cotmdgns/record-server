package com.server.record.controller;

import com.server.record.domain.*;
import com.server.record.service.UserOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/*")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class UserOrderController {

    @Autowired
    private UserOrderService service;

    // 결제한 정보들 나오게 만들기 ( 여기는 이걸로 종결 땅땅 )
    @GetMapping("userId/{id}")
    public ResponseEntity userId(@PathVariable String id){
        // 잘나오는거 확인 완료.
        List<UserOrderDTO> dtoList = new ArrayList<>();

        for(UserOrder userOrder : service.userOrderList(id)){
            dtoList.add(UserOrderDTO.builder()
                    .orderCode(userOrder.getOrderCode())
                    .product(userOrder.getProduct())
                    .userOrderCreated(String.valueOf(userOrder.getUserOrderCreated()).substring(0,10))
                    .userTable(userOrder.getUserTable())
                    .orderStateCode(userOrder.getOrderStateCode())
                    .build());
        }
        return ResponseEntity.ok().body(dtoList);
    };

}

