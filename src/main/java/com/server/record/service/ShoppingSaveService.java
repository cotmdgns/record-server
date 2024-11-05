package com.server.record.service;

import com.server.record.domain.*;
import com.server.record.repo.ProductDAO;
import com.server.record.repo.ShoppingSaveDAO;
import com.server.record.repo.ShoppingSaveOrderDAO;
import com.server.record.repo.UserOrderDAO;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class ShoppingSaveService {

    @Autowired
    private ShoppingSaveDAO dao;
    @Autowired
    private ShoppingSaveOrderDAO daoOrder;
    @Autowired
    private UserOrderDAO daoUserOrder;
    @Autowired
    private ProductDAO productDAO;
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

    // 장바구니에서 사용할 정보들
    public Product ShoppingProductView(int code) {
        return productDAO.ShoppingProductView(code);
    }

    //////// ( 바로 결제페이지 들어갈때 상황)
    // 바로 결제할때 생성되는 컬럼 ( 끝 )
    public void createShoppingSaveOrder(ShoppingSaveOrder shoppingSaveOrder){daoOrder.save(shoppingSaveOrder);}
    // 결제하고 해당 정보 결제페이지에서 보여줘야함 ( 끝 )
    public ShoppingSaveOrder viewCreateSaveOrder(int userCode){
        return daoOrder.viewSaveOrder(userCode);
    }
    // 페이지 나가면 자동으로 삭제하기 ( 끝 )
    @Transactional
    public void deleteCreateSaveOrder(ShoppingSaveOrder shoppingSaveOrder){
        daoOrder.deleteCreateSaveOrder(shoppingSaveOrder.getProductCode(),shoppingSaveOrder.getUserCode());
    }
    // 결제하기 버튼 눌렀을떄 생성
    @Transactional
    public void createProductOrder(int product, int userCode){
        daoUserOrder.createProductOrder(product,userCode);
    }
    ////////

    //////// ( 장바구니에서 결제페이지 들어갈때 상황)
    // 장바구니 -> 결제페이지 갔을때 보여줄상황
    public List<ShoppingSave> viewSaveproduct(int userCode) {
        return dao.AllViewShoppingSave(userCode);
    };

    @Transactional
    public void deleteCreateSave(int shoppingCode){
        dao.deleteById(shoppingCode);
    }
    ////////
}
