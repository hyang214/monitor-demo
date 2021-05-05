package com.github.hyang214.demo.monitor.metrics;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.PushGateway;

import java.io.IOException;
import java.util.List;

/**
 * title:
 *
 * @author Hao YANG
 * @since 2021.05.04
 */
public class PushExporter {

    private PushGateway pushGateway;

    public PushExporter(PushGateway pushGateway, List<IMetrics> metrics) {
        this.pushGateway = pushGateway;
        for (IMetrics im: metrics) {
            im.registry();
        }
        run();
    }

    private void run() {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    pushGateway.pushAdd(CollectorRegistry.defaultRegistry, "demo");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setName("prometheus-push-thread");
        thread.setDaemon(true);
        thread.start();
    }


}
