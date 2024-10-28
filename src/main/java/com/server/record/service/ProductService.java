package com.server.record.service;


import com.server.record.domain.ProductImg;
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
    // 1. LP 전체 보여주기
    public List<Product> AllViewLp(){
        return dao.AllViewLp();
    }
    // 2. 코드로 이미지 가져오기 ( 2.2 디테일 페이지에서도 재활용 가능함 )
    public List<ProductImg> AllViewLpImg(int productCode){return daoimg.productImg(productCode);}
    //////////////////

    //////////////////
    // 페이지에서 보여줄거
    // LP 전체 보여주기
    public List<Product> AllViewRecode(){
        return dao.AllViewRecode();
    }
    //////////////////

    // 디테일 페이지 들어가면 해당 정보 위한 단계
    // 1. 코드를 가져와서 해당정보 가져오기
    public Product detailInformation(int code){
        Product product =  dao.DetailInformation(code);
        return product;
    }







    // 제품 컬럼 생성하기
    public int CreateLpProduct(Product product){
        // 제품 생성하기
        log.info("1 : "+ product);
        Product pro = dao.save(product);
        log.info("2 : "+ pro);
        // 생성하면서 제품이름으로 코드 가져오기
        return ProductCode(pro.getProductName());
    }
    // 컨트롤에서 다시 코드와 이미지 가져와서 만들어주기 (서버)
    public void CreateImpProduct(ProductImg productImg){
        daoimg.save(productImg);
    }

    // 제품 만들어주면 code를 안주기때문에 직접 클라이언트에서 받아온 name으로 서버에가서 찾은다음 직접 코드 가져오기 ( 잘못설정해서 코드가 안오는거였음 )
    public int ProductCode(String productCode){
        Optional<Product> pd = dao.serverCheckProductName(productCode);
        return pd.get().getProductCode();
    }


}
