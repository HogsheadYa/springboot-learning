package com.learning.strategy.impl;

import com.learning.config.property.ModuleProperties;
import com.learning.strategy.ICreateExchangeStrategy;
import org.springframework.amqp.core.AbstractExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.stereotype.Service;

/**
 * @Author Fwt
 * @Date 2023/7/7 14:52
 */
@Service
public class FanoutCreateExchangeStrategy implements ICreateExchangeStrategy {
    @Override
    public AbstractExchange create(ModuleProperties.Exchange exchange) {
        return new FanoutExchange(exchange.getName(),true,false);
    }
}
