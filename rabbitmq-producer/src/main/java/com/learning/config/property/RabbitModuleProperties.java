package com.learning.config.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @Author Fwt
 * @Date 2023/7/7 13:49
 */
@Data
@Configuration
@ConfigurationProperties(prefix="spring.rabbitmq")
public class RabbitModuleProperties {
    List<ModuleProperties> modules;
}
