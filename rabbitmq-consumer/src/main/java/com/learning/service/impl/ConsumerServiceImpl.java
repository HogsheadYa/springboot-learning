package com.learning.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @Author Fwt
 * @Date 2023/7/7 9:57
 */
@Service
@Slf4j
@RabbitListener(queues = {"direct-queue1"})
public class ConsumerServiceImpl {


    @RabbitHandler
    public void process(String message) {
        log.info(message);
    }
}
