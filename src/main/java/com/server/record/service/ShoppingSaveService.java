package com.server.record.service;

import com.server.record.domain.ShoppingSave;
import com.server.record.repo.ShoppingSaveDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingSaveService {

    @Autowired
    private ShoppingSaveDAO dao;


    // 찜하기 생성하기
    public void createShoppingSave(ShoppingSave shoppingSave) {
        dao.save(shoppingSave);
    };

    // 찜하기 보여주기
    public List<ShoppingSave> AllViewShoppingSave(int userCode){
        return dao.AllViewShoppingSave(userCode);
    };

    // 페이지 들어오면 유저가 장바구니 추가한거 확인하기
    public ShoppingSave userMemberSaveCheck (ShoppingSave save){
        return dao.userMemberSaveCheck(save.getProductCode(), save.getUserCode());
    }


}
