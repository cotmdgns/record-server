package com.server.record.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
public class NoticeBoard {
    @Id
    @Column(name="noticeBoard_code")
    private int noticeBoardCode;

//    @Column(name="userCode")
//    private UserTable userTable;

    @Column(name="noticeBoard_h1")
    private String noticeBoardH1;

    @Column(name="noticeBoard_text")
    private String noticeBoardText;

    @Column(name="noticeBoard_created")
    private LocalDateTime noticeBoardCreated;

}
