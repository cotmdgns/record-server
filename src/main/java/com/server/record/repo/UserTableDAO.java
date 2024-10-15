package com.server.record.repo;

import com.server.record.domain.UserTable;
import com.server.record.domain.UserTableDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserTableDAO extends JpaRepository<UserTable, Integer> {

    // 아이디 조회
    @Query(value = "SELECT * FROM user_table WHERE user_id = :id",nativeQuery = true)
    Optional<UserTable> CheckId(@Param("id") String id);
}
