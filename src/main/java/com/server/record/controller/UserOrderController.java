package com.server.record.controller;

import com.server.record.service.UserOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
//        log.info(""+service.userOrderList(id));
        return ResponseEntity.ok().body(service.userOrderList(id));
    };

}
