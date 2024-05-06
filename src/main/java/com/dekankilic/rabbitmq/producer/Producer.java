package com.dekankilic.rabbitmq.producer;

import com.dekankilic.rabbitmq.config.RabbitMQConfig;
import com.dekankilic.rabbitmq.entity.OrderCreateDto;
import com.dekankilic.rabbitmq.entity.OrderResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class Producer {

    private final RabbitTemplate rabbitTemplate;

    @PostMapping
    public ResponseEntity<OrderResponseDto> placeOrder(@RequestBody OrderCreateDto order){
        OrderResponseDto orderResponseDto = new OrderResponseDto(order, "Order Placed", "Hi Producer, Your order is placed");
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, orderResponseDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderResponseDto);

    }
}
