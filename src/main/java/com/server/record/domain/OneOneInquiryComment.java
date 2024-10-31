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
public class OneOneInquiryComment {

    @Id
    @Column(name="one_one_inquiry_comment_comment")
    private int oneOneInquiryCommentComment;

    @Column(name="one_one_inquiry_code")
    private int oneOneInquiryCode;

    @ManyToOne
    @JoinColumn(name="user_code")
    private UserTable userTable;

    @Column(name="one_one_inquiry_comment_text")
    private String oneOneInquiryCommentText;

    @Column(name="one_one_inquiry_comment_created")
    private LocalDateTime oneOneInquiryCommentCreated;

}
