/*
 * Copyright (c) 2024. Suily. All rights reserved..
 */

package com.psl.boot.suily.framework.web.core.filter;

import cn.hutool.core.util.StrUtil;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: pengsulong@foxmail.com
 * @Date: 2024/4/21
 * @Description 过滤 /admin-api、/app-api 等 API 请求的过滤器
 * 继承 OncePerRequestFilter 它的主要作用是确保每个请求只会被过滤一次
 */
public abstract class ApiRequestFilter extends OncePerRequestFilter {
    /**
     * 判断是否需要过滤
     *
     * @param request
     * @return
     * @throws ServletException
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        // 每次都拦截API请求的地址，“app-api""admin-api"请求将进入 doFilterInternal 方法进行正常的认证处理
        boolean isFilterApi = StrUtil.startWithAny(request.getRequestURI(), "app-api", "admin-api");
        return !isFilterApi;
    }
    /**
     * 过滤器拦截请求
     *
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    }
}
