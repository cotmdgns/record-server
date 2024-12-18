package com.server.record.repo;

import com.server.record.domain.ShoppingSave;
import com.server.record.domain.ShoppingSaveOrder;
import com.server.record.domain.ShoppingSaveOrderDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShoppingSaveOrderDAO extends JpaRepository<ShoppingSaveOrder,Integer> {

    // 결제하기 바로 눌렀을때 보여주는 쿼리
    @Query(value = "select * from shopping_save_order join product using (product_code) where user_code = :code",nativeQuery = true)
    ShoppingSaveOrder viewSaveOrder(@Param("code")int code);

    // 삭제하기
    @Modifying
    @Query(value = "DELETE FROM shopping_save_order where product_code = :productCode and user_code = :userCode",nativeQuery = true)
    void deleteCreateSaveOrder(@Param("productCode")int productCode,@Param("userCode")int userCode);

    // 삭제하기 ( 장바구니 용도 )
    @Modifying
    @Query(value = "DELETE FROM shopping_save where product_code = :productCode and user_code = :userCode",nativeQuery = true)
    void deleteCreateSaveOrders(@Param("productCode")int productCode,@Param("userCode")int userCode);

    // 합계 금액 보여주기위한 쿼리
    @Query(value ="select product_price from shopping_save join product using (product_code) where user_code = :code;",nativeQuery = true)
    List<Integer> viewOrderPrice(@Param("code")int code);
}
