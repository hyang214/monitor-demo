package com.github.hyang214.demo.monitor.metrics;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Counter;

/**
 * title:
 *
 * @author Hao YANG
 * @since 2021.05.04
 */
public class CallCounter implements ICounter {

    private Counter counter;

    public CallCounter(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void registry() {
        counter.register(CollectorRegistry.defaultRegistry);
    }

    @Override
    public void inc(String... labelValues) {
        counter.labels(labelValues).inc();
    }

    @Override
    public void inc(double amt, String... labelValues) {
        counter.labels(labelValues).inc(amt);
    }

    @Override
    public double get(String... labelValues) {
        return counter.labels(labelValues).get();
    }

}
