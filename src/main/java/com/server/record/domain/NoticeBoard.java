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
    @Column(name="notice_board_code")
    private int noticeBoardCode;

    @Column(name="user_code")
    private int userCode;

    @Column(name="notice_board_h1")
    private String noticeBoardH1;

    @Column(name="notice_board_text")
    private String noticeBoardText;

    @Column(name="notice_board_created")
    private LocalDateTime noticeBoardCreated;

}
