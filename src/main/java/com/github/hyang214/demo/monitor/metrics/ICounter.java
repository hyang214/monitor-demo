package com.github.hyang214.demo.monitor.metrics;

/**
 * title:
 *
 * @author Hao YANG
 * @since 2021.05.04
 */
public interface ICounter extends IMetrics {

    /**
     * inc label values
     *
     * @param labelValues label
     */
    void inc(String... labelValues);

    /**
     * inc
     *
     * @param amt         amt
     * @param labelValues labelValues
     */
    void inc(double amt, String... labelValues);

    /**
     * get
     *
     * @param labelValues lab
     * @return double
     */
    double get(String... labelValues);

}
