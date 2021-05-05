package com.github.hyang214.demo.monitor.intercepor;

import com.github.hyang214.demo.monitor.metrics.ICounter;
import com.github.hyang214.demo.monitor.metrics.IHistogram;
import com.github.hyang214.demo.monitor.metrics.ISummary;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * title:
 *
 * @author Hao YANG
 * @since 2021.05.03
 */
@Component
public class TimeCostHandlerInterceptor implements HandlerInterceptor {

    private ICounter counter;

    private ISummary summary;

    private IHistogram histogram;

    private static ThreadLocal<Long> START_TIME_CACHE = new ThreadLocal<>();

    public TimeCostHandlerInterceptor(ICounter counter, ISummary summary, IHistogram histogram) {
        this.counter = counter;
        this.summary = summary;
        this.histogram = histogram;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        START_TIME_CACHE.set(System.currentTimeMillis());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        Long startTime = START_TIME_CACHE.get();
        Long endTime = System.currentTimeMillis();
        Long timeCost = endTime - startTime;
        counter.inc("/test/hello");
        summary.observe(timeCost,"/test/hello");
        histogram.observe(timeCost, "/test/hello");
    }

}
