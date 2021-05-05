package com.github.hyang214.demo.monitor.metrics;

/**
 * title:
 *
 * @author Hao YANG
 * @since 2021.05.05
 */
public interface IGauge extends IMetrics {

    /**
     * inc
     *
     * @param labelValues labelValues
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
     * dec
     *
     * @param labelValues labelValues
     */
    void dec(String... labelValues);

    /**
     * dec
     *
     * @param amt         amt
     * @param labelValues labelValues
     */
    void dec(double amt, String... labelValues);

    /**
     * set
     *
     * @param val         val
     * @param labelValues labelValues
     */
    void set(double val, String... labelValues);

    /**
     * get
     *
     * @param labelValues labelValues
     * @return
     */
    double get(String... labelValues);

}
