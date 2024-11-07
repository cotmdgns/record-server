package com.server.record.repo;

import com.server.record.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressDAO extends JpaRepository<Address,Integer> {

    @Query(value = "select * from address where user_code = :code",nativeQuery = true)
    List<Address> allAddress(@Param("code")int code);

}
