package com.github.hyang214.demo.monitor.resources;

import com.github.hyang214.demo.monitor.metrics.IGauge;
import com.github.hyang214.demo.monitor.metrics.ResourceGauge;
import io.prometheus.client.Gauge;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * title:
 *
 * @author Hao YANG
 * @since 2021.05.05
 */
@Component
public class SomeResource {

    private static final String RESOURCE_NAME = "some_resource";

    private static final Integer MAX = 200;

    private AtomicInteger resources = new AtomicInteger(MAX);

    private IGauge resourceGauge;

    public SomeResource(IGauge resourceGauge) {
        this.resourceGauge = resourceGauge;
        resourceGauge.set(MAX, RESOURCE_NAME);
    }

    public void borrow() {
        resources.decrementAndGet();
        resourceGauge.dec(RESOURCE_NAME);
    }

    public void ret() {
        resources.incrementAndGet();
        resourceGauge.inc(RESOURCE_NAME);
    }

    public Integer available() {
        return resources.get();
    }

}
