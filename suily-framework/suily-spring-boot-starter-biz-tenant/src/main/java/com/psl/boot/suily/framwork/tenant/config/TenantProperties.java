/*
 * Copyright (c) 2024. Suily. All rights reserved..
 */

package com.psl.boot.suily.framwork.tenant.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Collections;
import java.util.Set;

/**
 * @Author: pengsulong@foxmail.com
 * @Date: 2024/4/21
 * @Description 多租户的配置类
 * 配置多租户的属性，忽略URL等
 */
// 将前缀suily.tenant配置的属性赋值进来，例如suily.tenant.enable=true,TenantProperties.getEnable()是suily.tenant.enabled的值
@ConfigurationProperties(prefix = "suily.tenant")
@Data
public class TenantProperties {
    /**
     * 默认开启多租户
     */
    private static final Boolean ENABLE_DEFAULT = true;
    /**
     * 是否开启多租户
     */
    private Boolean enable = ENABLE_DEFAULT;

    /**
     * 忽略的URL
     * 需要忽略多租户的请求
     *
     * 默认情况下，每个请求需要带上 tenant-id 的请求头。
     * 但是，部分请求是无需带上的，例如说短信回调、支付回调等 Open API！
     */
    private Set<String> ignoreUrls = Collections.emptySet();
}
