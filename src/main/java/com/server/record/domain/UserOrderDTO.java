package com.server.record.domain;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserOrderDTO {

    private int orderCode;
    private Product product;
    private ProductImg productImg;
    private LocalDateTime usedProductCode;
    private Address address;
    private String userOrderCreated;
    private UserTable userTable;
    private int orderStateCode;

}
