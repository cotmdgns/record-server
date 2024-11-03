package com.server.record.repo;

import com.server.record.domain.ShoppingSaveOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ShoppingSaveOrderDAO extends JpaRepository<ShoppingSaveOrder,Integer> {

    @Query(value = "select * from shopping_save_order where user_code  = :userCode",nativeQuery = true)
    ShoppingSaveOrder viewSaveOrder(@Param("userCode")int userCode);

}
