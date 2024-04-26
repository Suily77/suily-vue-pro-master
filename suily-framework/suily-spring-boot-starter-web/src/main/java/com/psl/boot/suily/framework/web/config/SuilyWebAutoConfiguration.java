/*
 * Copyright (c) 2024. Suily. All rights reserved..
 */

package com.psl.boot.suily.framework.web.config;

import com.psl.boot.suily.framework.web.core.util.WebFrameworkUtils;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @Author: pengsulong@foxmail.com
 * @Date: 2024/4/23
 * @Description
 */
@AutoConfiguration
@EnableConfigurationProperties(WebProperties.class)
public class SuilyWebAutoConfiguration implements WebMvcConfigurer {

    @Resource
    WebProperties webProperties;
    /**
     * 1.配置路径匹配,给予controller的请求前缀，添加app-api前缀,或者admin-api前缀
     *
     * @param configurer
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurePathMatch(configurer, webProperties.getAppapi());
        configurePathMatch(configurer,webProperties.getAdminApi());
    }
    private void configurePathMatch(PathMatchConfigurer configurer,WebProperties.Api api) {

        /**
         * 创建一个Ant路径匹配器实例。
         * <p>此行代码初始化了一个AntPathMatcher对象，用于后续的路径匹配操作。
         * AntPathMatcher是Spring框架中的一种路径匹配工具，它支持类似于Apache Ant的路径模式。
         * 这里使用"."作为路径分隔符配置，意味着在匹配路径时将使用系统默认的路径分隔符。
         */
        AntPathMatcher antPathMatcher = new AntPathMatcher(".");
        /**
         * 添加路径前缀。
         * <p>此行代码添加了路径前缀，即在匹配路径时，将添加指定的前缀。
         * 例如，如果前缀为"/api"，则匹配路径"/api/users"将成功。
         * 条件包括：类必须带有@RestController注解，并且其包名必须与API配置中的控制器路径匹配。
         *
         * @param api.getPrefix() 前缀
         * @param clazz->clazz.isAnnotationPresent(RestController.class)
         *              判断是否是RestController注解
         * @param antPathMatcher.match(api.getController(),clazz.getPackage().getName())
         */
        configurer.addPathPrefix(api.getPrefix(),clazz->clazz.isAnnotationPresent(RestController.class)
        && antPathMatcher.match(api.getController(),clazz.getPackage().getName()));
    }

    /**
     * 2.将WebProperties注入WebFrameworkUtils；之后可以随便使用WebProperties
     * @see EnableConfigurationProperties(WebProperties)
     * @param webProperties
     * @return WebFrameworkUtils
     */
    @Bean
    public WebFrameworkUtils webFrameworkUtils(WebProperties webProperties) {
        return new WebFrameworkUtils(webProperties);
    }
    //============注入其他Bean==================

}
