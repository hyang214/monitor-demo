package com.github.hyang214.demo.monitor.config;

import com.github.hyang214.demo.monitor.metrics.*;
import com.github.hyang214.demo.monitor.metrics.properties.MonitorProperties;
import io.prometheus.client.*;
import io.prometheus.client.exporter.PushGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.export.prometheus.PrometheusProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * title:
 *
 * @author Hao YANG
 * @since 2021.05.04
 */
@Configuration
public class MonitorConfig {

    @Value(value = "${management.metrics.export.prometheus.pushgateway.baseUrl}")
    private String pushGatewayUrl;

    @Bean
    public ICounter callCounter(MonitorProperties monitorProperties) {
        Counter counter = Counter.build()
                .namespace(monitorProperties.getNamespace())
                .subsystem(monitorProperties.getSubSystem())
                .name("call_counter")
                .labelNames("requestURI")
                .help("url call counter")
                .create();
        return new CallCounter(counter);
    }

    @Bean
    public ISummary callTimecostSummary(MonitorProperties monitorProperties) {
        Summary summary = Summary.build()
                .namespace(monitorProperties.getNamespace())
                .subsystem(monitorProperties.getSubSystem())
                .name("call_time_cost")
                .labelNames("requestURI")
                .help("url call time cost")
                .quantile(0.5, 0.01)
                .quantile(0.8, 0.01)
                .quantile(0.9, 0.01)
                .quantile(0.99, 0.001)
                .create();
        return new CallTimecostSummary(summary);
    }

    @Bean
    public IGauge resourceGauge(MonitorProperties monitorProperties) {
        Gauge gauge = Gauge.build()
                .namespace(monitorProperties.getNamespace())
                .subsystem(monitorProperties.getSubSystem())
                .name("call_time_cost_h")
                .labelNames("requestURI")
                .help("available resource size")
                .create();
        return new ResourceGauge(gauge);
    }

    @Bean
    public IHistogram callTimecostHistogram(MonitorProperties monitorProperties) {
        Histogram histogram = Histogram.build()
                .namespace(monitorProperties.getNamespace())
                .subsystem(monitorProperties.getSubSystem())
                .name("resource_available")
                .labelNames("resource_name")
                .help("url call time cost in histogram")
                .buckets(1, 5, 10, 20, 50, 100, 200, 500, 1000, 2000, 3000, 5000)
                .create();
        return new CallTimecostHistogram(histogram);
    }

    @Bean
    public PushGateway pushGateway() {
        return new PushGateway(pushGatewayUrl);
    }

    @Bean
    public PushExporter pushExporter(PushGateway pushGateway, List<IMetrics> metrics) {
        return new PushExporter(pushGateway, metrics);
    }

}
