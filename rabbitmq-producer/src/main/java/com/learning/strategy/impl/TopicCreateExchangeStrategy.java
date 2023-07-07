package com.learning.strategy.impl;

import com.learning.config.property.ModuleProperties;
import com.learning.strategy.ICreateExchangeStrategy;
import org.springframework.amqp.core.AbstractExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.stereotype.Service;

/**
 * @Author Fwt
 * @Date 2023/7/7 15:30
 */
@Service
public class TopicCreateExchangeStrategy implements ICreateExchangeStrategy {
    @Override
    public AbstractExchange create(ModuleProperties.Exchange exchange) {
        return new TopicExchange(exchange.getName(), true, false);
    }
}
