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
public class ShoppingSave {

    @Id
    @Column(name="shopping_code")
    private int shoppingCode;

    @Column(name="product_code")
    private int productCode;

    @Column(name="user_code")
    private int userCode;

    @Column(name="shopping_quantity")
    private int shoppingQuantity;

    @Column(name="address_code")
    private int addressCode;
}
