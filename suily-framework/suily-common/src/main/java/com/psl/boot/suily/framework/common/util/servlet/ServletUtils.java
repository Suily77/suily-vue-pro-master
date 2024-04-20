/*
 * Copyright (c) 2024. Suily. All rights reserved..
 */

package com.psl.boot.suily.framework.common.util.servlet;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;

import cn.hutool.http.server.HttpServerRequest;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author: pengsulong@foxmail.com
 * @Date: 2024/4/20
 * @Description
 */
public class ServletUtils extends ServletUtil {
    /**
     * 获取请求参数
     * @param request
     * @return 封装成Map集合
     */
    public static Map<String, String> getParamMap(HttpServletRequest request){
        return ServletUtil.getParamMap(request);
    }

    /**
     * 判断是否是json请求
     * @param request
     * @return true/false
     */
    public static boolean isJsonRequest(HttpServletRequest request){
        return StrUtil.containsIgnoreCase(request.getContentType(), MediaType.APPLICATION_JSON_VALUE);
    }
}
