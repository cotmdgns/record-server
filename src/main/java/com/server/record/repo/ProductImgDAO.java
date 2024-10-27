package com.server.record.repo;

import com.server.record.domain.ProductImg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImgDAO extends JpaRepository<ProductImg,Integer> {
}
