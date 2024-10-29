package com.server.record.service;

import com.server.record.domain.OneOneInquiry;
import com.server.record.repo.OneOneInquiryCommentDAO;
import com.server.record.repo.OneOneInquiryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OneOneInquiryService {

    @Autowired
    private OneOneInquiryDAO dao;

    // DB에서 값을 가져와서 클라이언트에 보내주기 ( 전체 )
    public List<OneOneInquiry> AllViewOneOneInquiry(){
        return dao.AllViewOneOneInquiry();
    }
    
    // 컨트롤에서 보낸 값으로 1:1 문의 만들기
    public void createOneOneInquiry(OneOneInquiry oneOneInquiry) {
        dao.save(oneOneInquiry);
    }



}
