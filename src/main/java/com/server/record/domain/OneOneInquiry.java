package com.server.record.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@DynamicInsert
public class OneOneInquiry {

    @Id
    @Column(name="one_one_inquiry_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int oneOneInquiryCode;

    @Column(name="user_code")
    private int userCode;

    @Column(name="one_one_inquiry_h1")
    private String oneOneInquiryH1;

    @Column(name="one_one_inquiry_text")
    private String oneOneInquiryText;

    @Column(name="one_one_inquiry_file")
    private String oneOneInquiryFile;

    @Column(name="one_one_inquiry_created")
    private LocalDateTime oneOneInquiryCreated;

    @Column(name="one_one_inquiry_state")
    private int oneOneInquiryState;
}
