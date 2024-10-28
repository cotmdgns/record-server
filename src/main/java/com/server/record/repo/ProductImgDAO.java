package com.server.record.repo;

import com.server.record.domain.ProductImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductImgDAO extends JpaRepository<ProductImg,Integer> {

    // 코드로 이미지 불러오
    @Query(value="select * from product_img " +
            "where product_code = :productCode ;",nativeQuery = true)
    List<ProductImg> productImg (@Param("productCode")int code);
}
