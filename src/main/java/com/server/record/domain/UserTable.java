package com.server.record.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data @Builder @Entity
@AllArgsConstructor @NoArgsConstructor
public class UserTable {

    @Id
    @Column(name="user_code")
    private int userCode;

    @Column(name="user_id")
    private String userId;

    @Column(name="user_pwd")
    private String userPwd;

    @Column(name="user_name")
    private String userName;

    @Column(name="user_gender")
    private String userGender;

    @Column(name="user_phone")
    private String userPhone;

    @Column(name="user_birthdaydata")
    private String userBirthdayData;

    @Column(name="user_created")
    private LocalDate userCreated;

//    @Column(name="user_address")
//    private String userAddress;

    @Column(name="user_email")
    private String userEmail;

    @Column(name="user_img")
    private String userImg;
}
