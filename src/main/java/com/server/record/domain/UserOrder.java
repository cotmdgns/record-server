package com.server.record.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class UserOrder {

    @Id
    @Column(name="order_code")
    private int orderCode;

    // 상품 코드 조인키
    @ManyToOne
    @JoinColumn(name="product_code")
    private Product product;

    @Column(name="Used_product_code")
    private int usedProductCode;

    @Column(name="user_order_created")
    private LocalDateTime userOrderCreated;

    // 유저 테이블 조인키
    @ManyToOne
    @JoinColumn(name="user_code")
    private UserTable userTable;

    @Column(name="order_state_code")
    private int orderStateCode;

}
