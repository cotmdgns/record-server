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

@Slf4j
@RestController
@RequestMapping("/api/*")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class UserTableController {

    @Autowired
    private UserTableService service;


    //회원가입로직
    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody UserTable vo) {
        /*
        * 1. 회원가입 로직 성공하고
        * 2. 성공하고나서 해당 회원전용 이미지 폴더 만들어주기
        * */
//        String folderPath = "\\\\192.168.10.23\\userFolder\\" + vo.getUserId() + "\\";
//        File folder = new File(folderPath);
//
//        String folderPath2 = "\\\\192.168.10.23\\userFolder\\";
//        File folder2 = new File(folderPath2);
//
//        log.info("폴더2 : " +folder2);
//        log.info("폴더존재  : " +folder.exists());
//        log.info("폴더존재2  : " +folder2.exists());
//        if(!folder.exists()){
//            if(folder.mkdir()){
//                log.info("생성됨 ㅇㅇ");
//            }else{
//                log.info("생성됨 ㄴㄴ");
//            }
//        }


        try{

            Path directoryPath = Paths.get("\\\\\\\\192.168.10.23\\\\userFolder\\\\" + vo.getUserId() + "\\");
            log.info("path : " + directoryPath);
            Files.createDirectories(directoryPath);

//            service.signup(vo);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch(Exception e){
            log.info(e+"");
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserTable vo){
        UserTable member = service.login(vo.getUserId(),vo.getUserPwd());
        if(member.getUserPwd().equals(vo.getUserPwd())){
            log.info("member : 비밀번호 맞음");
            return ResponseEntity.status(HttpStatus.OK).body(member);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

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

}

