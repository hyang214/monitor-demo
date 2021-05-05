package com.github.hyang214.demo.monitor.metrics;

import io.prometheus.client.Collector;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Summary;

/**
 * title:
 *
 * @author Hao YANG
 * @since 2021.05.05
 */
public class CallTimecostSummary implements ISummary {

    private Summary summary;

    public CallTimecostSummary(Summary summary) {
        this.summary = summary;
    }

    @Override
    public void observe(double amt, String... labelValues) {
        summary.labels(labelValues).observe(amt);
    }

    @Override
    public void registry() {
        summary.register(CollectorRegistry.defaultRegistry);
    }

}
