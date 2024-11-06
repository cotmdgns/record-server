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
@DynamicInsert
public class NoticeBoardDTO {

    private int noticeBoardCode;
    private UserTable userTable;
    private String noticeBoardH1;
    private String noticeBoardText;
    private LocalDateTime noticeBoardCreated;

}
