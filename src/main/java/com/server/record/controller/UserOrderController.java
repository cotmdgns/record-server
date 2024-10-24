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

    @GetMapping("userId/{id}")
    public ResponseEntity userId(@PathVariable String id){
        // 잘나오는거 확인 완료.
        List<UserOrderDTO> dtoList = new ArrayList<>();
        UserOrderDTO dto = new UserOrderDTO();
        for(UserOrder userOrder : service.userOrderList(id)){
            // 왜 이렇게 짰냐? 생성날짜 뒤에 짜르기 위해 만들었음
//            dto.setOrderCode(userOrder.getOrderCode());
//            dto.setProduct(userOrder.getProduct());
//            dto.setUsedProductCode(userOrder.getUsedProductCode());
//            dto.setUserOrderCreated(String.valueOf(userOrder.getUserOrderCreated()).substring(0,10));
//            dto.setUserTable(userOrder.getUserTable());
//            dto.setOrderStateCode(userOrder.getOrderStateCode());

            dtoList.add(UserOrderDTO.builder()
                    .orderCode(userOrder.getOrderCode())
                    .product(userOrder.getProduct())
                    .usedProductCode(userOrder.getUsedProductCode())
                    .userOrderCreated(String.valueOf(userOrder.getUserOrderCreated()).substring(0,10))
                    .userTable(userOrder.getUserTable())
                    .orderStateCode(userOrder.getOrderStateCode())
                    .build());
        }
        log.info("gd  :  " + dtoList);
        return ResponseEntity.ok().body(dtoList);
    };

}

