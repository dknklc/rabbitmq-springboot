package com.dekankilic.rabbitmq.consumer;

import com.dekankilic.rabbitmq.config.RabbitMQConfig;
import com.dekankilic.rabbitmq.entity.OrderResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Consumer {

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void consume(OrderResponseDto orderResponseDto){
        System.out.println("The message consumed from the queue is " + orderResponseDto);
    }
}
