/*
 * Copyright (c) 2024. Suily. All rights reserved..
 */

package com.psl.boot.suily.framework.web.core.util;

import cn.hutool.core.util.NumberUtil;
import com.psl.boot.suily.framework.web.config.WebProperties;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: pengsulong@foxmail.com
 * @Date: 2024/4/21
 * @Description 专属于Web的工具类，用于tenant的获取
 */
public class WebFrameworkUtils {
    public static final String TENANT_ID = "tenant-id";

    /**
     * 获得租户编号
     * 考虑到其它 framework 组件也会使用到租户编号，所以不得不放在 WebFrameworkUtils 统一提供
     *
     * @param request 请求
     * @return 租户编号
     */
    public static Long getTenantId(HttpServletRequest request) {
        String tenantId = request.getHeader(TENANT_ID);
        Long tenantID = NumberUtil.isNumber(tenantId) ? Long.valueOf(tenantId) : null;
        return tenantID;
    }

    /**
     * WebFrameworkUtils类，提供web配置属性的工具方法。
     * 需要传入WebProperties实例以初始化全局属性。
     */
    private static WebProperties properties;

    /**
     * WebFrameworkUtils构造函数。
     * 用于初始化WebFrameworkUtils类的全局WebProperties属性。
     *
     * @param webProperties 传入的WebProperties实例，用于存储web配置属性。
     */
    public WebFrameworkUtils(WebProperties webProperties){
        WebFrameworkUtils.properties=webProperties;
    }
}
