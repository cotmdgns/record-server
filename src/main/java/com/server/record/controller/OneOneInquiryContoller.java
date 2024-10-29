package com.server.record.controller;

import com.server.record.domain.OneOneInquiry;
import com.server.record.domain.OneOneInquiryDTO;
import com.server.record.service.OneOneInquiryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/oneOneInquiry/*")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class OneOneInquiryContoller {

    @Autowired
    private OneOneInquiryService service;

    // 모든 문의 보여주기
    @GetMapping("AllViewOneOneInquiry")
    public ResponseEntity AllViewOneOneInquiry() {
        List<OneOneInquiry> AllViewInquiry = service.AllViewOneOneInquiry();
        return ResponseEntity.ok().body(AllViewInquiry);
    }



    // 1:1 문의 만들기
    @PostMapping("createOneOneInquiry")
    public ResponseEntity createOneOneInquiry(OneOneInquiryDTO dto) {
        OneOneInquiry oneOneInquiry = OneOneInquiry.builder()
                .userCode(dto.getUserCode())
                .oneOneInquiryH1(dto.getOneOneInquiryH1())
                .oneOneInquiryText(dto.getOneOneInquiryText())
                .oneOneInquiryFile(dto.getOneOneInquiryFile().getOriginalFilename())
                .build();

                service.createOneOneInquiry(oneOneInquiry);

        return ResponseEntity.status(HttpStatus.OK).build();
    }





}
