package com.server.record.controller;

import com.server.record.domain.UserTable;
import com.server.record.service.UserTableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/*")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class UserTableController {

    @Autowired
    private UserTableService service;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody UserTable vo) {
        service.signup(vo);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserTable vo){
        UserTable member = service.login(vo.getUserId());
        if(member.getUserPwd().equals(vo.getUserPwd())){
            log.info("member : 비밀번호 맞음");
            return ResponseEntity.status(HttpStatus.OK).body(member);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}

