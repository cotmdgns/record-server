package com.server.record.service;

import com.server.record.domain.OneOneInquiry;
import com.server.record.domain.OneOneInquiryComment;
import com.server.record.repo.OneOneInquiryCommentDAO;
import com.server.record.repo.OneOneInquiryDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class OneOneInquiryService {

    @Autowired
    private OneOneInquiryDAO dao;

    @Autowired
    private OneOneInquiryCommentDAO commentDao;

    // DB에서 값을 가져와서 클라이언트에 보내주기 ( 전체 )
    public List<OneOneInquiry> AllViewOneOneInquiry(){
        return dao.AllViewOneOneInquiry();
    }

    // 디테일 하나보기
    public Optional<OneOneInquiry> DetailOneOneView(int code){
        return dao.findById(code);
    }

    // 컨트롤에서 보낸 값으로 1:1 문의 만들기
    public void createOneOneInquiry(OneOneInquiry oneOneInquiry) {
        dao.save(oneOneInquiry);
    }


    // 게시판 댓글 만들기
    public void createComment(OneOneInquiryComment comment){
        log.info(comment+"");
        commentDao.save(comment);
    }

    //해당 게시판 댓글 가져오기
    public OneOneInquiryComment viewComment(int code) {
        return commentDao.viewComment(code);
    }

}
