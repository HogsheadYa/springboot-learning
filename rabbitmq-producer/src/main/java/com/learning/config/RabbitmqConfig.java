package com.learning.config;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.StopWatch;
import cn.hutool.extra.spring.SpringUtil;
import com.learning.config.property.ModuleProperties;
import com.learning.config.property.RabbitModuleProperties;
import com.learning.enums.RabbitExchangeTypeEnum;
import com.learning.strategy.ICreateExchangeStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author Fwt
 * @Date 2023/7/7 10:15
 */
@Slf4j
@Configuration
public class RabbitmqConfig implements SmartInitializingSingleton {
    @Resource
    private RabbitModuleProperties rabbitModuleProperties;
    @Resource
    private AmqpAdmin amqpAdmin;

    /**
     * 单例实例化后
     */
    @Override
    public void afterSingletonsInstantiated() {
        StopWatch stopWatch = StopWatch.create("MQ");
        stopWatch.start();
        stopWatch.stop();
        List<ModuleProperties> modules = rabbitModuleProperties.getModules();
        if (CollUtil.isEmpty(modules)) {
            log.warn("未配置MQ");
            return;
        }
        for (ModuleProperties module : modules) {
            try {
                if (module.isEnabled()) {
                    genQueue(module);
                    Exchange exchange = genQueueExchange(module);
                    if (exchange == null) {
                        throw new RuntimeException("交换机配置失败");
                    }
                    queueBindExchange(exchange, module);
                }
            } catch (Exception e) {
                log.error("初始化失败", e);
            }
        }
        log.info("初始化MQ配置成功耗时: {}ms", stopWatch.getTotal(TimeUnit.MILLISECONDS));
    }

    private Exchange genQueueExchange(ModuleProperties module) {
        try {
            RabbitExchangeTypeEnum rabbitExchangeTypeEnum = RabbitExchangeTypeEnum.valueOf(module.getType());
            ICreateExchangeStrategy createExchangeStrategy = SpringUtil.getBean(rabbitExchangeTypeEnum.getExchangeBean());
            return createExchangeStrategy.create(module.getExchange());
        } catch (Exception e) {
            log.error("交换机配置失败", e);
            return null;
        }
    }

    private void queueBindExchange(Exchange exchange, ModuleProperties module) {
        amqpAdmin.declareExchange(exchange);
        List<ModuleProperties.Queue> queues = module.getQueues();
        for (ModuleProperties.Queue queue : queues) {
            Binding binding = new Binding(queue.getName(),
                    Binding.DestinationType.QUEUE,
                    module.getExchange().getName(),
                    queue.getRoutingKey(),
                    null);
            amqpAdmin.declareQueue(queue.getQueue());
            amqpAdmin.declareBinding(binding);
        }
    }

    private void genQueue(ModuleProperties module) {
        List<ModuleProperties.Queue> queues = module.getQueues();
        for (ModuleProperties.Queue queue : queues) {
            queue.setQueue(new Queue(queue.getName(), queue.isDurable(), queue.isExclusive(), queue.isAutoDelete()));
        }
    }

}
