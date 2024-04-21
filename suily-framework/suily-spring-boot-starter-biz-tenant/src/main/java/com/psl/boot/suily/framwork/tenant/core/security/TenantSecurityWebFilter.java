/*
 * Copyright (c) 2024. Suily. All rights reserved..
 */

package com.psl.boot.suily.framwork.tenant.core.security;

import cn.hutool.core.collection.CollUtil;
import com.psl.boot.suily.framework.common.exception.enums.GlobalErrorCodeConstants;
import com.psl.boot.suily.framework.common.pojo.CommonResult;
import com.psl.boot.suily.framework.common.util.servlet.ServletUtils;
import com.psl.boot.suily.framework.web.core.filter.ApiRequestFilter;

import com.psl.boot.suily.framwork.tenant.config.TenantProperties;
import com.psl.boot.suily.framwork.tenant.core.context.TenantContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * @Author: pengsulong@foxmail.com
 * @Date: 2024/4/21
 * @Description 多租户 Security Web 过滤器
 * 获取TenantContextHolder的tenantID
 * 1. 如果是登陆的用户，校验是否有权限访问该租户，避免越权问题。
 * 2. 如果请求未带租户的编号，检查是否是忽略的 URL，否则也不允许访问。
 * 3. 校验租户是合法，例如说被禁用、到期
 */
@Slf4j
public class TenantSecurityWebFilter extends ApiRequestFilter {
    private TenantProperties tenantProperties;
    private AntPathMatcher pathMatcher;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Long tenantId = TenantContextHolder.getTenantId();
        // 1.登录的用户，校验是否有权限访问该租户，避免越权问题

        // 3.如果非允许忽略租户的 URL，则校验租户是否合法
        if (!isIgnoreTenantUrl(request)) {
            // 1.如果未传递租户编号，则直接返回 400
            if (tenantId == null) {
                log.error("[doFilterInterbal][URL ({}/{}) 未传递租户编号]", request.getRequestURI(), request.getMethod());
                ServletUtils.writeJSON(response, CommonResult.error(GlobalErrorCodeConstants.BAD_REQUEST.getCode(),
                        "请求的租户标识未传递，请进行排查"));
                // 提前结束
                return;
            }
            // 2.校验租户是合法，例如说被禁用、到期
            try {
                // 校验租户是合法
                //tenantFrameworkService.validTenant(tenantId);
            }catch (Throwable ex){
                log.error("[doFilterInterbal][URL ({}/{}) 校验租户失败，校验租户是合法，例如说被禁用、到期]", request.getRequestURI(), request.getMethod(), ex);
                ServletUtils.writeJSON(response, CommonResult.error(GlobalErrorCodeConstants.BAD_REQUEST.getCode(),"到期"));
                // 异常提前结束
                return;
            }
        }else {
            if(tenantId == null){
                // 设置为忽略租户
                TenantContextHolder.setIgnore(true);
            }
        }
        // 继续执行过滤器
        filterChain.doFilter(request, response);
    }

    /**
     * 是否忽略租户的 URL
     *
     * @param request 请求
     * @return 是否忽略租户的 URL
     */
    private boolean isIgnoreTenantUrl(HttpServletRequest request) {
        Set<String> ignoreUrls = tenantProperties.getIgnoreUrls();
        String requestURI = request.getRequestURI();
        // 快速匹配，保证性能，不执行下一步
        if (CollUtil.contains(ignoreUrls, requestURI)) {
            return true;
        }
        // 逐个Ant路径匹配
        return ignoreUrls.stream().anyMatch(url -> {
            return pathMatcher.match(url, requestURI);
        });
    }
}
