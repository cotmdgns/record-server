package com.server.record.controller;

import com.server.record.domain.UserTable;
import com.server.record.service.UserTableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/userTable/*")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class UserTableController {

    @Autowired
    private UserTableService service;


    //회원가입로직
    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody UserTable vo) {
        // 랜덤값 주기
        int num = (int)(Math.random()*4);
        // 회원가입 해당 유저 전용 이미지 만들기
        Path directoryPath = Paths.get("\\\\192.168.10.51\\record\\userFolder\\" + vo.getUserId() + "\\");
        Path directoryProfile = Paths.get("\\\\192.168.10.51\\record\\userFolder\\" + vo.getUserId() + "\\userProfile");
        try{
            // 이미지가 없다면 디폴트값 넣기
            if(vo.getUserImg() == null){
                vo.setUserImg("http://192.168.10.51:8084/ImageDefault/img-profile-default-"+ num + ".png");
            }
            log.info("path : " + directoryPath);
            log.info("path : " + directoryProfile);
            Files.createDirectories(directoryPath);
            Files.createDirectories(directoryProfile);

            service.signup(vo);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch(Exception e){
            log.info(e+"");
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserTable vo){
        UserTable member = service.login(vo.getUserId(),vo.getUserPwd());
        if(member.getUserPwd().equals(vo.getUserPwd())){
            log.info("member : 비밀번호 맞음");
            return ResponseEntity.status(HttpStatus.OK).body(member);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    // 아이디 체크
    @GetMapping("/idCheck/{id}")
    public ResponseEntity idCheck(@PathVariable String id){
        try{
            UserTable member = service.idCheck(id);
            //있을 경우
            return ResponseEntity.status(HttpStatus.OK).body(member);
        }catch (Exception exception){
            // 없을 경우
            return ResponseEntity.ok().build();
        }
    }

    // 아이디 삭제하기
    @DeleteMapping("/userDelete")
    public ResponseEntity userDelete(@RequestBody UserTable vo){
        log.info("" + vo);
        return ResponseEntity.ok().build();
    }

}

