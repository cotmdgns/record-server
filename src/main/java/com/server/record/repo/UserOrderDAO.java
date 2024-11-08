package com.server.record.repo;

import com.server.record.domain.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserOrderDAO extends JpaRepository<UserOrder,Integer> {

    // 결제한 상품 보여주기
    @Query(value="select * from user_order " +
            "join user_table using(user_code) " +
            "join product using (product_code) " +
            "where user_id = :id",nativeQuery = true)
    List<UserOrder> userOrderList(@Param("id") String id);

    // 생성하기
    @Modifying
    @Query(value ="INSERT INTO user_order (product_code, user_code,address_code) VALUES (:productCode,:userCode,:address_code)",nativeQuery = true)
    void createProductOrder(@Param("productCode")int productCode,@Param("userCode")int userCode,@Param("address_code")int addressCode);
    
    
}
