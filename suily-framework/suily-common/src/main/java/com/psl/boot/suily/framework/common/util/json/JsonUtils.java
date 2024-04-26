/*
 * Copyright (c) 2024. Suily. All rights reserved..
 */

package com.psl.boot.suily.framework.common.util.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

/**
 * @Author: pengsulong@foxmail.com
 * @Date: 2024/4/21
 * @Description JSON 工具类
 * Json工具类太多了，自己写一个归一一下
 */
public class JsonUtils {
    // jackson
    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 将对象转换为JSON字符串
     *
     * @param obj 对象
     * @return JSON字符串
     */
    @SneakyThrows
    public static String toJsonString(Object obj) {
        return objectMapper.writeValueAsString(obj);
    }
}
