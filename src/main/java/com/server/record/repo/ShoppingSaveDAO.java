package com.server.record.repo;

import com.server.record.domain.ShoppingSave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShoppingSaveDAO extends JpaRepository<ShoppingSave,Integer> {

    @Query(value = "select * from shopping_save where user_code = :code",nativeQuery = true)
    List<ShoppingSave> AllViewShoppingSave(@Param("code")String code);

}
