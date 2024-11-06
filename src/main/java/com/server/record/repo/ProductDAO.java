package com.server.record.repo;

import com.server.record.domain.Product;
import com.server.record.domain.ProductDTO;
import com.server.record.domain.ProductLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductDAO extends JpaRepository<Product,Integer> {

    /*전체 LP판  (메인 화면)*/
    @Query(value="SELECT * FROM product WHERE product_type = 'LP' LIMIT 10", nativeQuery = true)
    List<Product> MainViewLp();

    /*전체 레코드  (메인 화면)*/
    @Query(value="select * from product where product_type = '레코드' LIMIT 10",nativeQuery = true)
    List<Product> MainViewRecode();

    ////////////////////////////
    // 페이지 들어가서 보여주기 (LP)
    @Query(value="select * from product where product_type = 'LP' limit :page ,16",nativeQuery = true)
    List<Product> AllPagingViewLp(@Param("page")int page);
    // 총 LP정부 갯수 (페이징에 들어감)
    @Query(value="select count(*) from product where product_type = 'LP'",nativeQuery = true)
    int AllViewLp();

    // 페이지 들어가서 보여주기 (LP)
    @Query(value="select * from product where product_type = '레코드'",nativeQuery = true)
    List<Product> AllViewRecode();

    // 생성할떄 받아온 이름으로 코드찾기
    @Query(value="select * from product where product_name = :product_name",nativeQuery = true)
    Optional<Product> serverCheckProductName(@Param("product_name") String productName);

    // 디테일 페이지에서 url 코드를 받아서 서버에서 찾기
    @Query(value="select * from product where product_code = :code",nativeQuery = true)
    Product DetailInformation(@Param("code")int code);

    // 상품코드로 추천하기 코드 count 로 가져오기
    @Query(value = "select count(product_code) from product join product_like using (product_code) where product_code = :code",nativeQuery = true)
    int productLike(@Param("code")int code);

    // 유저코드로 추천하기 체크여부
    @Query(value = "select * from product_like where user_code = :userCode and product_code = :productCode",nativeQuery = true)
    ProductLike productLikeCheck(@Param("userCode")int userCode,@Param("productCode")int productCode);

    // 장바구니에서 사용할 정보들
    @Query(value="select * from product where product_code = :code",nativeQuery = true)
    Product ShoppingProductView(@Param("code")int code);


}



