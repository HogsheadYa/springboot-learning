package com.learning.strategy.impl;

import com.learning.config.property.ModuleProperties;
import com.learning.strategy.ICreateExchangeStrategy;
import org.springframework.amqp.core.AbstractExchange;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.stereotype.Service;

/**
 * @Author Fwt
 * @Date 2023/7/7 14:28
 */
@Service
public class DirectCreateExchangeStrategy implements ICreateExchangeStrategy {
    @Override
    public AbstractExchange create(ModuleProperties.Exchange exchange) {
        return new DirectExchange(exchange.getName(),true,false);
    }
}
