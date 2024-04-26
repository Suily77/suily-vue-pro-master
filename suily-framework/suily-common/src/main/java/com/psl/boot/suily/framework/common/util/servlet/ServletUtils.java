/*
 * Copyright (c) 2024. Suily. All rights reserved..
 */

package com.psl.boot.suily.framework.common.util.servlet;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.psl.boot.suily.framework.common.util.json.JsonUtils;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Author: pengsulong@foxmail.com
 * @Date: 2024/4/20
 * @Description 客户端工具类
 */
public class ServletUtils extends ServletUtil {
    /**
     * 获取请求参数,可以删除
     *
     * @param request
     * @return 封装成Map集合
     */
    public static Map<String, String> getParamMap(HttpServletRequest request){
        return ServletUtil.getParamMap(request);
    }

    /**
     * 判断是否是json请求
     *
     * @param request
     * @return true/false
     */
    public static boolean isJsonRequest(HttpServletRequest request){
        return StrUtil.containsIgnoreCase(request.getContentType(), MediaType.APPLICATION_JSON_VALUE);
    }

    /**
     * 返回json数据
     *
     * @param response 响应体
     * @param result 对象，会序列化成JSON字符串 必须使用 APPLICATION_JSON_UTF8_VALUE，否则会乱码
     */
    public static void writeJSON(HttpServletResponse response, Object result){
        String context = JsonUtils.toJsonString(result);
        ServletUtils.write(response, context, MediaType.APPLICATION_JSON_UTF8_VALUE);
    }
}
