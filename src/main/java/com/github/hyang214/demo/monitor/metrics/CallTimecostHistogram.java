package com.github.hyang214.demo.monitor.metrics;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Histogram;

/**
 * title:
 *
 * @author Hao YANG
 * @since 2021.05.05
 */
public class CallTimecostHistogram implements IHistogram {

    private Histogram histogram;

    public CallTimecostHistogram(Histogram histogram) {
        this.histogram = histogram;
    }

    @Override
    public void observe(double amt, String... labelValues) {
        histogram.labels(labelValues).observe(amt);
    }

    @Override
    public double[] get(String... labelValues) {
        return histogram.labels(labelValues).get().buckets;
    }

    @Override
    public void registry() {
        histogram.register(CollectorRegistry.defaultRegistry);
    }
}
