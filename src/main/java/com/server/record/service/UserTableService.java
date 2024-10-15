package com.server.record.service;

import ch.qos.logback.core.net.QueueFactory;
import com.server.record.domain.UserTable;
import com.server.record.domain.UserTableDTO;
import com.server.record.repo.UserTableDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserTableService {

    @Autowired
    private UserTableDAO dao;

    // 회원가입
    public void signup(UserTable vo){
        dao.save(vo);
    }
    
    // 로그인 체크
    public UserTable login(String id){
        UserTable member = dao.CheckId(id).get();
        return member;
    }



}
