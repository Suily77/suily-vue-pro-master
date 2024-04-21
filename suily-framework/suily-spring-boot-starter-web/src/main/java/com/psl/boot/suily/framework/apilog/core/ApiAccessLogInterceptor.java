/*
 * Copyright (c) 2024. Suily. All rights reserved..
 */

package com.psl.boot.suily.framework.apilog.core;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONString;
import com.psl.boot.suily.framework.common.util.servlet.ServletUtils;
import com.psl.boot.suily.framework.common.util.spring.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.util.StopWatch;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Author: pengsulong@foxmail.com
 * @Date: 2024/4/20
 * @Description API 访问日志拦截器 Interceptor
 * 目的：在非 prod 环境时，打印 request 和 response 两条日志到日志文件（控制台）中。
 * 需要配置在spring容器中，否则不会拦截任何请求。
 */
@Slf4j
public class ApiAccessLogInterceptor implements HandlerInterceptor {
    public static final String HANDLER_METHOD = "HANDLER_METHOD";
    public static final String API_ACCESS_LOG_INTERCEPTOR_STOP_WATCH = "ApiAccessLogInterceptor.StopWatch";


    /**
     * 拦截器方法
     * 拦截请求之前的操作
     *
     * @param request  请求
     * @param response 响应
     * @param handler  处理器
     * @return true
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // HttpServletRequest转化成json
        String requestJson = ServletUtils.getParamMap(request).toString();
        log.info("request: {}", requestJson.toString());
        /**
         * 这段Java代码的功能是在处理Web请求的过程中，对处理器对象（handler）进行类型检查和封装
         * 并将其作为属性存储到当前HTTP请求（request）中
         * 以便后续处理流程或中间件可以访问和利用这些信息。具体实现步骤如下：
         * 记录 HandlerMethod，提供给 ApiAccessLogFilter 使用
         */
        HandlerMethod handlerMethod = handler instanceof HandlerMethod ? (HandlerMethod) handler : null;
        if (handlerMethod != null) {
            request.setAttribute(HANDLER_METHOD, handlerMethod);
        }
        // 只打印request的日志，且只打印非prod的日志
//        if (!"prod".equals(System.getProperty("spring.profiles.active"))) {
//            log.info("response: {}", response);
//        }

        if (!SpringUtils.isProd()) {
            // 获取请求集
            Map<String, String> queryString = ServletUtils.getParamMap(request);
            // 获取请求体, 非json请求为null
            String requestBody = ServletUtils.isJsonRequest(request) ? ServletUtils.getBody(request) : null;
            if (CollUtil.isEmpty(queryString) && StrUtil.isEmpty(requestBody)) {
                log.info("[preHandle][开始请求URL({}) 无参数]", request.getRequestURI());
            } else {
                // 打印请求参数，为空就打印queryString,不为空就打印requestBody
                log.info("[preHandle][开始请求URL({}) 参数({})]", request.getRequestURI(), StrUtil.nullToDefault(requestBody, queryString.toString()));
            }

            // 计时
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            request.setAttribute(API_ACCESS_LOG_INTERCEPTOR_STOP_WATCH, stopWatch);
        }
        return true;
    }

    /**
     * 拦截器方法
     * 拦截请求之后的操作,打印URI请求完成时间
     *
     * @param request  请求
     * @param response 响应
     * @param handler  处理器
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // 打印返回的response
        if (!SpringUtils.isProd()) {
            StopWatch stopWatch =(StopWatch) request.getAttribute(API_ACCESS_LOG_INTERCEPTOR_STOP_WATCH);
            stopWatch.stop();
            log.info("[afterCompleteion][完成请求 URL({}) 耗时({})ms]",request.getRequestURI(),stopWatch);
        }
    }
}
