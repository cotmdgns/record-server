package com.server.record.repo;

import com.server.record.domain.ShoppingSaveOrder;
import com.server.record.domain.ShoppingSaveOrderDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ShoppingSaveOrderDAO extends JpaRepository<ShoppingSaveOrder,Integer> {

    @Query(value = "select * from shopping_save_order join product using (product_code) where user_code = :code",nativeQuery = true)
    ShoppingSaveOrder viewSaveOrder(@Param("code")int code);


    /// 삭제하기
    @Modifying
    @Query(value = "DELETE FROM shopping_save_order where product_code = :productCode and user_code = :userCode",nativeQuery = true)
    void deleteCreateSaveOrder(@Param("productCode")int productCode,@Param("userCode")int userCode);


}
