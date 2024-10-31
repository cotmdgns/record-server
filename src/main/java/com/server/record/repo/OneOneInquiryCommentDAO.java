package com.server.record.repo;

import com.server.record.domain.OneOneInquiry;
import com.server.record.domain.OneOneInquiryComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OneOneInquiryCommentDAO extends JpaRepository<OneOneInquiryComment,Integer> {

    @Query(value = "select * from one_one_inquiry_comment where one_one_inquiry_code = :code",nativeQuery = true)
    OneOneInquiryComment viewComment(@Param("code")int code);

}
