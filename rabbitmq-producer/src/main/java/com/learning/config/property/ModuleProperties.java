package com.learning.config.property;

import lombok.Data;

import java.util.List;

/**
 * @Author Fwt
 * @Date 2023/7/7 13:42
 */
@Data
public class ModuleProperties {

    /**
     * 是否启用
     */
    private boolean enabled;
    /**
     * 模式
     */
    private String type;

    /**
     * 交换机
     */
    private Exchange exchange;

    @Data
    public static class Exchange {
        /**
         * 交换机名称
         */
        private String name;
    }

    /**
     * 队列信息
     */
    private List<Queue> queues;

    @Data
    public static class Queue {
        /**
         * 队列名称
         */
        private String name;

        /**
         * 路由key
         */
        private String routingKey;

        /**
         * 是否持久化
         */
        private boolean durable; // 默认true持久化，重启消息不会丢失

        /**
         * 是否具有排他性
         */
        private boolean exclusive; // 默认false，可多个消费者消费同一个队列

        /**
         * 当消费者均断开连接，是否自动删除队列
         */
        private boolean autoDelete; // 默认false,不自动删除，避免消费者断开队列丢弃消息

        /**
         * 队列
         */
        private org.springframework.amqp.core.Queue queue;
    }


}
