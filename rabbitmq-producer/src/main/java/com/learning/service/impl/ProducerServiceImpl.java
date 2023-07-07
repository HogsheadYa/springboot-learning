package com.learning.service.impl;

import com.learning.service.IProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @Author Fwt
 * @Date 2023/7/7 10:14
 */
@Service
@RequiredArgsConstructor
public class ProducerServiceImpl implements IProducerService {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void send(Object message) {
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend("direct-exchange", "direct-routing-key1", message, correlationData);
    }
}
