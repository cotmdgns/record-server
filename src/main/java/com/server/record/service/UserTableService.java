package com.server.record.service;

import com.server.record.domain.UserTable;
import com.server.record.domain.UserTableDTO;
import com.server.record.repo.UserTableDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTableService {

    @Autowired
    private UserTableDAO dao;

    public void signup(UserTable vo){
        dao.save(vo);
    }

    public void checkId(UserTable vo){
        dao.findAll();
    }

}
