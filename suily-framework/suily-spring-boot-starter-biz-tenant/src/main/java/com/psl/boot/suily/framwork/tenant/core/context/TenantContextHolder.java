/*
 * Copyright (c) 2024. Suily. All rights reserved..
 */

package com.psl.boot.suily.framwork.tenant.core.context;

/**
 * @Author: pengsulong@foxmail.com
 * @Date: 2024/4/21
 * @Description 多租户上下文 Holder
 * 该类用于管理租户上下文。它提供了一组静态方法来设置、获取和清除租户上下文
 * 用于在多租户系统中存储当前租户的相关信息
 * 可以在不同的代码模块中轻松地访问当前租户的信息，从而实现对租户数据的正确隔离和处理
 */
public class TenantContextHolder {
    /**
     * 当前租户的编号
     */
    private static final ThreadLocal<Long> TENANT_ID = new ThreadLocal<>();

    public static void setTenantId(Long tenantId) {
        TENANT_ID.set(tenantId);
    }

    public static Long getTenantId() {
        return TENANT_ID.get();
    }

    public static void clear() {
        TENANT_ID.remove();
    }
}
