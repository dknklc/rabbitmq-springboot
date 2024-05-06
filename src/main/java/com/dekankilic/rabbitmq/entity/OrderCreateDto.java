package com.dekankilic.rabbitmq.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderCreateDto {

    private Long id;
    private String name;
    private BigDecimal price;
    private int quantity;

}
