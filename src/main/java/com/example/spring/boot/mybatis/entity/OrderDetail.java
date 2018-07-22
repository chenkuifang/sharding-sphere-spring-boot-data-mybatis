package com.example.spring.boot.mybatis.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public final class OrderDetail {

    private Long detailId;
    private Long orderId;
    private Long goodsId;
    private String goodsName;
    private BigDecimal price;
    private Integer count;
    private Integer status;
}
