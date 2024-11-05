package com.server.record.repo;

import com.server.record.domain.ShoppingSave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShoppingSaveDAO extends JpaRepository<ShoppingSave,Integer> {
    
    // 장바구니에서 사용할건
    @Query(value = "select * from shopping_save where user_code = :code",nativeQuery = true)
    List<ShoppingSave> AllViewShoppingSave(@Param("code")int code);

    // 장바구니 체크여부
    @Query(value = "select * from shopping_save where product_code = :productCode and user_code = :userCode;",nativeQuery = true)
    ShoppingSave userMemberSaveCheck(@Param("productCode")int productCode,@Param("userCode")int userCode);

    @Modifying
    @Query(value = "DELETE FROM shopping_save_order where product_code = :productCode and user_code = :userCode",nativeQuery = true)
    void deleteCreateSave(@Param("productCode")int productCode,@Param("userCode")int userCode);

}
