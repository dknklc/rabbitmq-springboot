package com.dekankilic.rabbitmq.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderResponseDto {
    private OrderCreateDto order;
    private String orderStatus;
    private String message;
}
