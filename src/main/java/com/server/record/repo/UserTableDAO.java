package com.server.record.repo;

import com.server.record.domain.UserTable;
import com.server.record.domain.UserTableDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTableDAO extends JpaRepository<UserTable, Integer> {
}
