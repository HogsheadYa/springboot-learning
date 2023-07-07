package com.learning.config;

import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @Author Fwt
 * @Date 2023/7/7 14:34
 */
@Configuration
public class RabbitTemplateConfig implements RabbitTemplate.ConfirmCallback , RabbitTemplate.ReturnsCallback{
    @Resource
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init(){
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnsCallback(this);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        System.out.println(correlationData);
        System.out.println(b);
        System.out.println(s);
    }

    @Override
    public void returnedMessage(ReturnedMessage returnedMessage) {
        System.out.println(returnedMessage.getMessage());
    }
}
