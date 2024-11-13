package com.server.record.controller;

import com.server.record.domain.UserTable;
import com.server.record.domain.UserTableDTO;
import com.server.record.service.UserTableService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/userTable/*")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class UserTableController {

    @Autowired
    private UserTableService service;

    private String url = "\\\\192.168.10.51\\record\\userFolder\\";

    //회원가입로직
    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody UserTable vo) {
        // 랜덤값 주기
        int num = (int)(Math.random()*4);
        // 회원가입 해당 유저 전용 이미지 만들기
        Path directoryPath = Paths.get(url + vo.getUserId() + "\\");
        Path directoryProfile = Paths.get(url + vo.getUserId() + "\\userProfile");

        try{

            Files.createDirectories(directoryPath);
            Files.createDirectories(directoryProfile);

            // 이미지가 없다면 디폴트값 넣기
            if(vo.getUserImg() == null){
//                File copyFile = new File(url + vo.getUserId() + File.separator + "userProfile" + File.separator + fileName);
//                dto.getUserImg().transferTo(copyFile);
                File file = new File("\\\\192.168.10.51\\record\\ImageDefault\\img-profile-default-"+ num + ".png");
                File newFile = new File(url + vo.getUserId() + "\\userProfile\\img-profile-default-"+ num + ".png");
                FileUtils.copyFile(file, newFile);

                vo.setUserImg("img-profile-default-"+ num + ".png");
            }

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
        UserTable member = service.idCheck(id);
        return ResponseEntity.status(HttpStatus.OK).body(member);
    }

    // 수정하기
    @PutMapping("/upDataController")
    public ResponseEntity upDataController(@RequestBody UserTableDTO vo) {
        UserTable member = service.UserIdCheck(vo.getUserCode());

        if(vo.getOldUserPwd() != null && vo.getUserPwd() != null ){
            if(vo.getOldUserPwd().equals(vo.getUserPwd())){
                return ResponseEntity.ok().body("1");
            } else if(vo.getOldUserPwd().equals(member.getUserPwd())) {
                member.setUserPwd(vo.getUserPwd());
                service.userUpData(member);
                return ResponseEntity.ok().body("2");
            } else {
                return ResponseEntity.ok().body("3");
            }
        }else if(vo.getUserEmail() != null){
            member.setUserEmail(vo.getUserEmail());
            service.userUpData(member);
            return ResponseEntity.ok().body(member.getUserEmail());
        }
        return ResponseEntity.ok().build();
    }

    // 이미지 수정하면서 만약에 폴더가 생성이 안됬을경우를 위한 보험
    @PutMapping("/upDataImgController")
    public ResponseEntity userImgUpDatePut(UserTableDTO dto) throws IOException {

        // 코드로 서버에서 해당 유저 정보 다 가져오기
        UserTable vo = service.UserIdCheck(dto.getUserCode());
        // 가져온 이미지 이름을 중복되지않게 랜덤 이름 넣어지기
        String fileName = fileUpload(dto.getUserImg());

        // 가져온 파일 이름으로
        File copyFile = new File(url + vo.getUserId() + File.separator + "userProfile" + File.separator + fileName);
        dto.getUserImg().transferTo(copyFile);

        // 안에 존재하냐
        if(!vo.getUserImg().isEmpty()){
            // 값이 널이 아니지?
            if(vo.getUserImg()!=null){
                // 그럼 이 주소로 해서
                File imgDelete = new File(url + vo.getUserId() + File.separator + "userProfile" + File.separator + vo.getUserImg());
                // 지울게
                imgDelete.delete();
            }
        }
        // 파일 주소넣기
        vo.setUserImg(fileName);
        service.userUpData(vo);

        return ResponseEntity.ok().build();
    }

    // 가져온 파일을 UUID 로 랜덤 이름값 같이 붙여서 만들어주기
    public String fileUpload(MultipartFile file) throws IOException{
        UUID uuid = UUID.randomUUID();
        // 멀티파일로 가져온 이름 오리지널로 바꿔주면서 저장하기
        return uuid.toString()+"_"+file.getOriginalFilename();
    }




    // 아이디 삭제하기
    @DeleteMapping("/userDelete/{code}")
    public void userDelete(@PathVariable int code){
        // 삭제하기전에 해당 정보 가져오기
        UserTable member = deleteIdCheck(code);
        File directory = new File(url + member.getUserId() + "\\");
        
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
                // 향상된 for 문으로
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

