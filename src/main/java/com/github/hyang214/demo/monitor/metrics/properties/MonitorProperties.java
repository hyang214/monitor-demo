package com.github.hyang214.demo.monitor.metrics.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * title:
 *
 * @author Hao YANG
 * @since 2021.05.04
 */
@Data
@Component
@ConfigurationProperties(prefix = "prometheus")
public class MonitorProperties {

    private String namespace;

    private String subSystem;

}
