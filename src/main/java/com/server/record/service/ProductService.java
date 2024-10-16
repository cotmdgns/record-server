package com.server.record.service;

import com.server.record.domain.Product;
import com.server.record.repo.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductDAO dao;

    public List<Product> MainLP(){
        // 10개만 보여주기
        return dao.findAll();
    }

}
