package com.server.record.repo;

import com.server.record.domain.Product;
import com.server.record.domain.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductDAO extends JpaRepository<Product,Integer> {

    /*전체 LP판  (메인 화면)*/
    @Query(value="SELECT * FROM product WHERE product_type = 'LP' LIMIT 12", nativeQuery = true)
    List<Product> MainViewLp();

    /*전체 레코드  (메인 화면)*/
    @Query(value="select * from product where product_type = '레코드' LIMIT 12",nativeQuery = true)
    List<Product> MainViewRecode();

    // 페이지 들어가서 보여주기 (LP)
    @Query(value="select * from product where product_type = 'LP'",nativeQuery = true)
    List<Product> AllViewLp();

    // 페이지 들어가서 보여주기 (LP)
    @Query(value="select * from product where product_type = '레코드'",nativeQuery = true)
    List<Product> AllViewRecode();


    // 생성할떄 받아온 이름으로 코드찾기
    @Query(value="select * from product where product_name = :product_name",nativeQuery = true)
    Optional<Product> serverCheckProductName(@Param("product_name") String productName);
}

