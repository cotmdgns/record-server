package com.server.record.service;

import com.server.record.domain.NoticeBoard;
import com.server.record.repo.NoticeBoardDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeBoardService {

    @Autowired
    private NoticeBoardDAO dao;
    // 보여주기
    public List<NoticeBoard> viewAll(){
        return dao.viewNoticeBoardAll();
    }

    // 만들기
    public void createNoticeBoard(NoticeBoard noticeBoard){
        dao.save(noticeBoard);
    }

}
