package com.server.record.service;

import com.server.record.domain.UserTable;
import com.server.record.repo.UserTableDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserTableService {

    @Autowired
    private UserTableDAO dao;

    // 회원가입
    public void signup(UserTable vo){
        dao.save(vo);
    }
    // 로그인 후 아이디 체크
    public UserTable login(String id, String pwd){
        UserTable member = dao.login(id,pwd).get();
        return member;
    }
    
    // 회원가입할떄 로그인 체크
    public UserTable idCheck(String id){
        UserTable member = dao.CheckId(id).get();
        return member;
    }

    // 아이디 삭제하기
    public void userDelete(int userCode){
        dao.deleteById(userCode);
    }

    public void userUpData(UserTable vo){
        dao.save(vo);
    }

    // 유저 코드로 아이디 체크하기
    public UserTable UserIdCheck(int code){
        UserTable member = dao.deleteIdCheck(code);
        return member;
    }

}
