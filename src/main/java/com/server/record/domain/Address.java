package com.server.record.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@DynamicInsert
public class Address {

    @Id
    @Column(name ="address_code")
    private int addressCode;

    @Column(name ="user_code")
    private int userCode;

    @Column
    private String zonecode;

    @Column(name ="road_address")
    private String roadAddress;

    @Column(name ="jibunAddress")
    private String jibunAddress;

    @Column(name ="address_detail")
    private String addressDetail;

    @Column(name ="address_phone")
    private String addressPhone;

    @Column(name ="address_user_state")
    private int addressUserState;

    @Column(name ="address_user_start_code")
    private int addressUserStartCode;

}
