package com.learning.enums;

/**
 * @Author Fwt
 * @Date 2023/7/7 13:35
 */
public enum RabbitExchangeTypeEnum {

    /**
     * 直连交换机
     * <p>
     * 根据routing-key精准匹配队列(最常使用)
     */
    DIRECT("directCreateExchangeStrategy"),

    /**
     * 主题交换机
     * <p>
     * 根据routing-key模糊匹配队列，*匹配任意一个字符，#匹配0个或多个字符
     */
    TOPIC("topicCreateExchangeStrategy"),
    /**
     * 扇形交换机
     * <p>
     * 直接分发给所有绑定的队列，忽略routing-key,用于广播消息
     */
    FANOUT("fanoutCreateExchangeStrategy");

    private final String exchangeBean;

    RabbitExchangeTypeEnum(String exchangeBean){
        this.exchangeBean=exchangeBean;
    }

    public String getExchangeBean(){
        return exchangeBean;
    }
}
