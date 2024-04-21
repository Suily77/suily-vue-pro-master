/*
 * Copyright (c) 2024. Suily. All rights reserved..
 */

package com.psl.boot.suily.framwork.tenant.core.web;

import com.psl.boot.suily.framework.web.core.util.WebFrameworkUtils;
import com.psl.boot.suily.framwork.tenant.core.context.TenantContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: pengsulong@foxmail.com
 * @Date: 2024/4/21
 * @Description 多租户 Context Web 过滤器
 * 将请求 Header 中的 tenant-id 解析出来
 * 添加到 {@link TenantContextHolder} 中
 * 这样后续的 DB 等操作，可以获得到租户编号。
 */
public class TenantContextWebFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Long tenantId = WebFrameworkUtils.getTenantId(request);
        //1. 设置租户ID
        if(tenantId != null){
            // 每个需要拦截的请求，进来时候设置租户ID
            TenantContextHolder.setTenantId(tenantId);
        }
        // 2. 执行过滤器
        try {
            filterChain.doFilter(request, response);
        }finally {
            // 3. 清空多租户上下文
            TenantContextHolder.clear();
        }
    }
}
