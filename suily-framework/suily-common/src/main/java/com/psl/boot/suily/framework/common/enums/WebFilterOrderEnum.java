/*
 * Copyright (c) 2024. Suily. All rights reserved..
 */

package com.psl.boot.suily.framework.common.enums;

/**
 * @Author: pengsulong@foxmail.com
 * @Date: 2024/4/21
 * @Description Web过滤器注册顺序的枚举类，保证过滤器符合预期
 * 每个starter都有自己的过滤器，统一管理
 */
public interface WebFilterOrderEnum {
    int TENANT_CONTEXT_FILTER = - 104; // 需要保证在 ApiAccessLogFilter 前面

    int TENANT_SECURITY_FILTER = -99; // 需要保证在 Spring Security 过滤器后面
}
