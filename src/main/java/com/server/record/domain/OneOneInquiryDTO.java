package com.server.record.domain;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class OneOneInquiryDTO {


    private int userCode;
    private String oneOneInquiryH1;
    private String oneOneInquiryText;
    private MultipartFile oneOneInquiryFile;

}
