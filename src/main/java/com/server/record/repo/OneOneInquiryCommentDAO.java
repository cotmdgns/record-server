package com.server.record.repo;

import com.server.record.domain.OneOneInquiry;
import com.server.record.domain.OneOneInquiryComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OneOneInquiryCommentDAO extends JpaRepository<OneOneInquiryComment,Integer> {



}
