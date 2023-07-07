package com.learning.strategy;

import com.learning.config.property.ModuleProperties;
import org.springframework.amqp.core.AbstractExchange;
import org.springframework.stereotype.Service;

/**
 * @Author Fwt
 * @Date 2023/7/7 14:26
 */
@Service
public interface ICreateExchangeStrategy {

    /**
     * 创建交换机
     * @return
     */
    AbstractExchange create(ModuleProperties.Exchange exchange);
}
