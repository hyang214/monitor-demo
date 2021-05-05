package com.github.hyang214.demo.monitor.metrics;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Gauge;

/**
 * title:
 *
 * @author Hao YANG
 * @since 2021.05.05
 */
public class ResourceGauge implements IGauge {

    private Gauge gauge;

    public ResourceGauge(Gauge gauge) {
        this.gauge = gauge;
    }

    @Override
    public void inc(String... labelValues) {
        gauge.labels(labelValues).inc();
    }

    @Override
    public void inc(double amt, String... labelValues) {
        gauge.labels(labelValues).inc(amt);
    }

    @Override
    public void dec(String... labelValues) {
        gauge.labels(labelValues).dec();
    }

    @Override
    public void dec(double amt, String... labelValues) {
        gauge.labels(labelValues).dec(amt);
    }

    @Override
    public void set(double val, String... labelValues) {
        gauge.labels(labelValues).set(val);
    }

    @Override
    public double get(String... labelValues) {
        return gauge.labels(labelValues).get();
    }

    @Override
    public void registry() {
        gauge.register(CollectorRegistry.defaultRegistry);
    }

}
