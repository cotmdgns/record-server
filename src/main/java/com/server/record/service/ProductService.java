package com.server.record.service;


import com.server.record.domain.ProductImg;
import com.server.record.domain.ProductLike;
import com.server.record.repo.ProductImgDAO;
import org.springframework.beans.factory.annotation.Autowired;

import com.server.record.domain.Product;
import com.server.record.domain.ProductDTO;
import com.server.record.repo.ProductDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductService {

    @Autowired
    private ProductDAO dao;

    @Autowired
    private ProductImgDAO daoimg;

//    @Autowired
//    private JPAQueryFactory


    // 메인페이지 12개 보여주기
    public List<Product> MainLP(){
        return dao.MainViewLp();
    }
    public List<Product> MainRecode(){
        return dao.MainViewRecode();
    }
    //////////////////
    // 페이지에서 보여줄거
    // 1. LP 전체 보여주기 (페이징)
    public List<Product> AllPagingViewLp(int page){
        return dao.AllPagingViewLp(page);
    }
    // Lp 페이지
    public int AllViewLp(){
        return dao.AllViewLp();
    }
    //////////////////
    // 페이지에서 보여줄거
    // 1. 레코드 전체 보여주기 (페이징)
    public List<Product> AllPagingViewRecord(int page){
        return dao.AllPagingViewRecord(page);
    }
    // 레코드 페이지
    public int AllViewRecode(){
        return dao.AllViewRecord();
    }
    //////////////////

    //코드로 이미지 가져오기 ( 2.2 디테일 페이지에서도 재활용 가능함 )
    public List<ProductImg> AllViewImg(int productCode){
        return daoimg.productImg(productCode);
    }

    // 결제했을떄 업데이트
    public void productUpData(Product product){
        dao.save(product);
    }


    // 디테일 페이지 들어가면 해당 정보 위한 단계
    // 1. 코드를 가져와서 해당정보 가져오기
    public Product detailInformation(int code){
        Product product = dao.DetailInformation(code);
        return product;
    }
    // 2. 코드로 해당 페이지 추천하기 count로 가져오기
    public int productLike(int code){
        return dao.productLike(code);
    }
    // 3. 코드로 추천하기 체크여부 확인하기
    public ProductLike productLikeCheck(ProductLike productLike){
        return dao.productLikeCheck(productLike.getUserCode(),productLike.getProductCode());
    }





    // 제품 컬럼 생성하기
    public Product CreateLpProduct(Product product){
        // 제품 생성하기
//        log.info("1 : "+ product);
        Product pro = dao.save(product);
//        log.info("2 : "+ pro);
        return pro;
    }
    // 컨트롤에서 다시 코드와 이미지 가져와서 만들어주기 (서버)
    public void CreateImpProduct(ProductImg productImg){
        daoimg.save(productImg);
    }





}
