package com.server.record.repo;

import com.server.record.domain.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserOrderDAO extends JpaRepository<UserOrder,Integer> {

    @Query(value="select * from user_order " +
            "join user_table using(user_code) " +
            "join product using (product_code) " +
            "where user_id = :id",nativeQuery = true)
    List<UserOrder> userOrderList(@Param("id") String id);
}
