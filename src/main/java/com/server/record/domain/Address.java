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

    @Column(name ="address_user_zip")
    private int addressUserZip;

    @Column(name ="address_user_address_detail")
    private int addressUserAddressDetail;

    @Column(name ="address_user_phone")
    private int addressUserPhone;

    @Column(name ="address_user_state")
    private int addressUserState;

    @Column(name ="address_user_details")
    private int addressUserDetails;

}
