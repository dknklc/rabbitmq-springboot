package com.dekankilic.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig { // We need to create exchanges, queues and bindings here.

    public static final String EXCHANGE = "rabbitmq_exchange";
    public static final String QUEUE = "rabbitmq_queue";
    public static final String ROUTING_KEY = "rabbitmq_routing_key";

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    public Queue queue(){
        return new Queue(QUEUE);
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange directExchange){
        return BindingBuilder.bind(queue).to(directExchange).with(ROUTING_KEY);
    }

    // We have created this bean because SimpleMessageConverter(default message converter) only supports String and byte[]. However, we want to send OrderResponseDto which is JSON.
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    // The converted that we have created above needs to be in RabbitTemplate, so we need to use the messageConverter inside the Rabbit Template.
    @Bean
    public AmqpTemplate getTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
