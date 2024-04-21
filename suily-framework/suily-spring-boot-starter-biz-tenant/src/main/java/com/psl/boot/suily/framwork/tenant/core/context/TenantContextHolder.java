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
    private static final ThreadLocal<Boolean> IGNORE = new ThreadLocal<>();

    /**
     * 设置当前租户的编号
     *
     * @param tenantId 租户编号
     */
    public static void setTenantId(Long tenantId) {
        TENANT_ID.set(tenantId);
    }

    /**
     * 设置是否忽略租户
     *
     * @param isIgnore 是否忽略租户
     */
    public static void setIgnore(Boolean isIgnore){
        IGNORE.set(isIgnore);
    }

    /**
     * 获取当前租户的编号
     *
     * @return 租户编号
     */
    public static Long getTenantId() {
        return TENANT_ID.get();
    }

    /**
     * 获取是否忽略租户
     *
     * @return 是否忽略租户
     */
    public static Boolean isIgnore() {
        if (IGNORE.get() == null) {
            return Boolean.FALSE;// 防止拆箱
        }
        /**
         * public T get(),T类型转化一下，防止get的是其他类型？为了更好知道是true还是false，增加代码阅读性
         * IGNORE变量的类型应该是Boolean，而不是boolean，因为使用了equals方法进行比较
         * 避免空指针判断
         */
        return Boolean.TRUE.equals(IGNORE.get());
    }

    /**
     * 清空ThreadLocal，避免内存泄漏
     */
    public static void clear() {
        TENANT_ID.remove();
        IGNORE.remove();
    }
}
