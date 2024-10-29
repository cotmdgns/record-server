package com.server.record.repo;

import com.server.record.domain.OneOneInquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OneOneInquiryDAO extends JpaRepository<OneOneInquiry,Integer> {

    @Query(value="select * from one_one_inquiry",nativeQuery = true)
    List<OneOneInquiry> AllViewOneOneInquiry();
}
