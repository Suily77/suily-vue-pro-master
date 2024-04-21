/*
 * Copyright (c) 2024. Suily. All rights reserved..
 */

package com.psl.boot.suily.framework.web.core.util;

import cn.hutool.core.util.NumberUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: pengsulong@foxmail.com
 * @Date: 2024/4/21
 * @Description 专属于Web的工具类，用于tenant的获取
 */
public class WebFrameworkUtils {

    public static final String TENANT_ID = "tenant-id";

    public static Long getTenantId(HttpServletRequest request) {
        String tenantId = request.getHeader(TENANT_ID);
        Long tenantID = NumberUtil.isNumber(tenantId) ? Long.valueOf(tenantId) : null;
        return tenantID;
    }
}
