package com.server.record.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserTableDTO {

    private int userCode;
    private String userId;
    private String userPwd; // - 새 비밀번호 번호
    private String userName;
    private String userGender;
    private String userEmail;
    private String userPhone;
    private String userBirthdayData;
    private MultipartFile userImg;
    private String oldUserPwd; // - 이전 비밀번호 번호


}
