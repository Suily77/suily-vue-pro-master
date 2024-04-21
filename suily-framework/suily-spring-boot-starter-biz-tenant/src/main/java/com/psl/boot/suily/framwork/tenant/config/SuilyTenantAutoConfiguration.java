/*
 * Copyright (c) 2024. Suily. All rights reserved..
 */

package com.psl.boot.suily.framwork.tenant.config;

import com.psl.boot.suily.framework.common.enums.WebFilterOrderEnum;
import com.psl.boot.suily.framwork.tenant.core.security.TenantSecurityWebFilter;
import com.psl.boot.suily.framwork.tenant.core.web.TenantContextWebFilter;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * @Author: pengsulong@foxmail.com
 * @Date: 2024/4/21
 * @Description 对多个类的自动配置进行整合
 */
@AutoConfiguration
@EnableConfigurationProperties(TenantProperties.class)
// 允许使用 suily.tenant.enable=false 禁用多租户
@ConditionalOnProperty(prefix = "suily.tenant", value = "enabled", matchIfMissing = true)

public class SuilyTenantAutoConfiguration {

    // ==============WEB 过滤器====================
    @Bean
    public FilterRegistrationBean<TenantContextWebFilter> tenantContextWebFilter() {
        // 创建一个FilterRegistrationBean对象
        FilterRegistrationBean<TenantContextWebFilter> registrationBean = new FilterRegistrationBean<>();
        // 设置过滤器
        registrationBean.setFilter(new TenantContextWebFilter());
        // 设置过滤器的路径匹配
        registrationBean.addUrlPatterns("/*");
        // 设置过滤器的执行顺序
        registrationBean.setOrder(WebFilterOrderEnum.TENANT_CONTEXT_FILTER);
        // 返回注册的过滤器
        return registrationBean;
    }

    // ============SECURITY WEB 过滤器=================
    @Bean
    public FilterRegistrationBean<TenantSecurityWebFilter> tenantSecurityWebFilter() {
        // 创建一个FilterRegistrationBean对象
        FilterRegistrationBean<TenantSecurityWebFilter> registrationBean = new FilterRegistrationBean<>();
        // 设置过滤器
        registrationBean.setFilter(new TenantSecurityWebFilter());
        // 设置过滤器的路径匹配
        registrationBean.addUrlPatterns("/*");
        // 设置过滤器的执行顺序
        registrationBean.setOrder(WebFilterOrderEnum.TENANT_SECURITY_FILTER);
        // 返回注册的过滤器
        return registrationBean;
    }
}
