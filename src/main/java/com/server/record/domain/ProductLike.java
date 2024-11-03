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
public class ProductLike {

    @Id
    @Column(name ="product_sub_like")
    private int productSubLike;

    @Column(name ="user_code")
    private int userCode;

    @Column(name ="product_code")
    private int productCode;

}
