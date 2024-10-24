package com.server.record.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserOrderDTO {

    private int orderCode;
    private Product product;
    private int usedProductCode;
    private String userOrderCreated;
    private UserTable userTable;
    private int orderStateCode;
}
