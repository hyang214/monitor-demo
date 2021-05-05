package com.github.hyang214.prometheus;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.exporter.PushGateway;

/**
 * title:
 *
 * @author Hao YANG
 * @since 2021.05.04
 */
public class PushGatewayTest {

    public static void main(String[] args) {
        try{
            String url = "127.0.0.1:9091";
            CollectorRegistry registry = CollectorRegistry.defaultRegistry;
            Gauge guage = Gauge.build("my_custom_metric", "This is my custom metric.").create();
            guage.set(23.12);
            guage.register(registry);
            PushGateway pg = new PushGateway(url);
            pg.pushAdd(registry, "my_job2");


            guage.set(1111.23);
            pg.pushAdd(registry, "my_job2");

            Counter counter = Counter.build()
                    .namespace("demo")
                    .subsystem("monitor")
                    .name("my_custom_metric_counter")
                    .help("counter test")
                    .labelNames("requestURI", "cost")
                    .create();

            counter.labels("/test/hello", "27").inc();
            counter.labels("/test/hello", "59").inc();
            counter.labels("/test/hello", "124").inc();
            counter.register(registry);
            pg.pushAdd(registry, "my_job2");

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
