package com.server.record.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@DynamicInsert
public class ShoppingSaveOrder {

    @Id
    @Column(name="shopping_order_code")
    private int shoppingOrderCode;

    @Column(name="product_code")
    private int productCode;

    @Column(name="user_code")
    private int userCode;

}
