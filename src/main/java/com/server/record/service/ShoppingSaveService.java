package com.server.record.service;

import com.server.record.domain.ShoppingSave;
import com.server.record.domain.ShoppingSaveOrder;
import com.server.record.repo.ShoppingSaveDAO;
import com.server.record.repo.ShoppingSaveOrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingSaveService {

    @Autowired
    private ShoppingSaveDAO dao;
    @Autowired
    private ShoppingSaveOrderDAO daoOrder;

    // 장바구니 생성하기
    public void createShoppingSave(ShoppingSave shoppingSave) {
        dao.save(shoppingSave);
    };
    // 장바구니 삭제하기
    public void DeleteProductSave(int shoppingCode){
        dao.deleteById(shoppingCode);
    }

    // 장바구니 보여주기
    public List<ShoppingSave> AllViewShoppingSave(int userCode){
        return dao.AllViewShoppingSave(userCode);
    };

    // 페이지 들어오면 유저가 장바구니 추가한거 확인하기
    public ShoppingSave userMemberSaveCheck (ShoppingSave save){
        return dao.userMemberSaveCheck(save.getProductCode(), save.getUserCode());
    }

    ////////
    // 바로 결제할때 생성되는 컬럼
    public void createShoppingSaveOrder(ShoppingSaveOrder shoppingSaveOrder){daoOrder.save(shoppingSaveOrder);}
    // 결제하고 해당 정보 결제페이지에서 보여줘야함
    public ShoppingSaveOrder viewCreateSaveOrder(int userCode){
        return daoOrder.viewSaveOrder(userCode);
    }
    ////////
}
