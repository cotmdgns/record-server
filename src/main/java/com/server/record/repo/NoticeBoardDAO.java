package com.server.record.repo;

import com.server.record.domain.NoticeBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoticeBoardDAO extends JpaRepository<NoticeBoard,Integer> {

    @Query(value = "select * from notice_board", nativeQuery = true)
    List<NoticeBoard> viewNoticeBoardAll();


}
