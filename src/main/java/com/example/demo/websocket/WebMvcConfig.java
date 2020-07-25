package com.example.demo.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * []
 *
 * @author 金彪
 * @version 1.0
 * @date 2020/5/9
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 为各个页面提供路径映射
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/client").setViewName("client");
        registry.addViewController("/index").setViewName("index");
    }
}