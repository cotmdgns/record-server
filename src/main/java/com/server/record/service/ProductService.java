package com.server.record.service;

import com.server.record.domain.Product;
import com.server.record.repo.ProductDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ProductService {

    @Autowired
    private ProductDAO dao;

    // 메인페이지 12개 보여주기
    public List<Product> MainLP(){
        return dao.MainViewLp();
    }
    public List<Product> MainRecode(){
        return dao.MainViewRecode();
    }
    // 페이지에서 보여줄거
    public List<Product> AllViewLp(){
        return dao.AllViewLp();
    }
    public List<Product> AllViewRecode(){
        return dao.AllViewRecode();
    }

}
