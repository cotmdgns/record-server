package com.server.record.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UserTableDTO {

    @Id
    @Column
    private String userId;

    @Column
    private String userPwd;

    @Column
    private String userName;

    @Column
    private String userGender;

    @Column
    private String userPhone;

    @Column
    private String userBirthdayData;

}
