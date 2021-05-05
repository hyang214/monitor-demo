package com.github.hyang214.demo.monitor.config;

import com.github.hyang214.demo.monitor.intercepor.TimeCostHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * title:
 *
 * @author Hao YANG
 * @since 2021.05.05
 */
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {

    private TimeCostHandlerInterceptor interceptor;

    public WebAppConfigurer(TimeCostHandlerInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 可添加多个
        registry.addInterceptor(interceptor).addPathPatterns("/**");
    }

}
