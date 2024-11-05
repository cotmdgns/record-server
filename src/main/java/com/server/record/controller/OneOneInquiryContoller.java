package com.server.record.controller;

import com.server.record.domain.OneOneInquiry;
import com.server.record.domain.OneOneInquiryComment;
import com.server.record.domain.OneOneInquiryDTO;
import com.server.record.domain.UserTable;
import com.server.record.service.OneOneInquiryService;
import com.server.record.service.UserTableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/oneOneInquiry/*")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class OneOneInquiryContoller {
    @Autowired
    private OneOneInquiryService service;

    @Autowired
    private UserTableService userTableService;

    // 모든 문의 보여주기
    @GetMapping("AllViewOneOneInquiry")
    public ResponseEntity AllViewOneOneInquiry() {
        List<OneOneInquiry> AllViewInquiry = service.AllViewOneOneInquiry();
        return ResponseEntity.ok().body(AllViewInquiry);
    }
    // 디테일하나보기
    @GetMapping("DetailOneOneView/{code}")
    public ResponseEntity DetailOneOneView(@PathVariable int code) {
//        log.info("" + code);
//        log.info("" +service.DetailOneOneView(code));
        return ResponseEntity.ok().body(service.DetailOneOneView(code));
    }

    // 1:1 문의 만들기
    @PostMapping("createOneOneInquiry")
    public ResponseEntity createOneOneInquiry(OneOneInquiryDTO dto) {
        UserTable user = userTableService.UserIdCheck(dto.getUserCode());
        OneOneInquiry oneOneInquiry = OneOneInquiry.builder()
                .userTable(user)
                .oneOneInquiryH1(dto.getOneOneInquiryH1())
                .oneOneInquiryText(dto.getOneOneInquiryText())
                .oneOneInquiryFile(dto.getOneOneInquiryFile().getOriginalFilename())
                .build();

                service.createOneOneInquiry(oneOneInquiry);

        return ResponseEntity.status(HttpStatus.OK).build();
    }


    //댓글 작성하기
    @PostMapping("CreateComment")
    public ResponseEntity CreateComment(@RequestBody OneOneInquiryComment comment) {
        LocalDateTime localDateTime = LocalDateTime.now();
        comment.setOneOneInquiryCommentCreated(localDateTime);
        service.createComment(comment);
        return ResponseEntity.ok().build();
    }

    // 해당 페이지 댓글 보이기
    @GetMapping("ViewComment/{code}")
    public ResponseEntity ViewComment(@PathVariable  int code) {
        return ResponseEntity.ok().body(service.viewComment(code));
    }




}
