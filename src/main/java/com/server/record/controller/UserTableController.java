package com.server.record.controller;

import com.server.record.domain.UserTable;
import com.server.record.domain.UserTableDTO;
import com.server.record.service.UserTableService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

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

    @PutMapping("/upDataController")
    public ResponseEntity upDataController(@RequestBody UserTableDTO vo) {
        UserTable member = service.UserIdCheck(vo.getUserCode());
        // 변경전 비밀번호,  서버에있는 비밀번호가 같내
        if(vo.getOldUserPwd().equals(member.getUserPwd())) {
            member.setUserPwd(vo.getUserPwd());
            service.userUpData(member);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("비밀번호 틀림");
        }
    }

    // 아이디 삭제하기
    @DeleteMapping("/userDelete/{code}")
    public void userDelete(@PathVariable int code){
        // 삭제하기전에 해당 정보 가져오기
        UserTable member = deleteIdCheck(code);
        File directory = new File("\\\\192.168.10.51\\record\\userFolder\\" + member.getUserId() + "\\");
        
        if (deleteDirectory(directory)) {
            log.info("폴더와 그 안의 모든 파일 및 폴더를 삭제했습니다.");
        } else {
            log.info("삭제 실패");
        }
        service.userDelete(code);
    }
    // 폴더 재귀법으로 삭제
    public static boolean deleteDirectory(File directory) {
        //안에 파일이 존재하냐
        if (directory.exists()) {
            // 존재하면 배열로 파일 객체 생성하고
            File[] files = directory.listFiles();
            // 널이 아닐때
            if (files != null) {
                // 상향돈 for 문으로
                for (File file : files) {
                    // 디렉토리인지 판단하고
                    if (file.isDirectory()) {
                        // 삭제하기
                        deleteDirectory(file); // 재귀 호출
                    }
                    // 만약 없다면 실패
                    if (!file.delete()) {
                        log.info("파일 삭제 실패: " + file.getAbsolutePath());
                    }
                }
            }
        }
        // 마지막으로 폴더 자체 삭제
        return directory.delete();
    }
    // 삭제할때 아이디 가져오기
    public UserTable deleteIdCheck(int code) {
        return service.UserIdCheck(code);
    }
}

