package com.github.hyang214.demo.monitor.metrics;

/**
 * title:
 *
 * @author Hao YANG
 * @since 2021.05.05
 */
public interface IHistogram extends IMetrics {

    /**
     * observe
     *
     * @param amt    amt
     * @param labelValues labelValues
     */
    void observe(double amt, String... labelValues);

    /**
     * get labelValues
     *
     * @param labelValues labelValues
     * @return get
     */
    double[] get(String... labelValues);

}
