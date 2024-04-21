/*
 * Copyright (c) 2024. Suily. All rights reserved..
 */

package com.psl.boot.suily.framework.common.exception.enums;

import com.psl.boot.suily.framework.common.exception.ErrorCode;

/**
 * @Author: pengsulong@foxmail.com
 * @Date: 2024/4/21
 * @Description 全局错误码枚举
 * 0-999 系统异常编码保留
 * 使用接口来定义枚举类
 * 一般情况下，使用 HTTP 响应状态码 https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Status
 * 虽然说，HTTP 响应状态码作为业务使用表达能力偏弱，但是使用在系统层面还是非常不错的
 */
public interface GlobalErrorCodeConstants {
    ErrorCode SUCCESS = new ErrorCode(200, "成功");

    // ========== 客户端错误段 ==========
    ErrorCode BAD_REQUEST = new ErrorCode(400, "请求参数不正确");
    ErrorCode UNAUTHORIZED = new ErrorCode(401, "账号未登录");
    ErrorCode FORBIDDEN = new ErrorCode(403, "没有该操作权限");
    ErrorCode NOT_FOUND = new ErrorCode(404, "请求未找到");
    ErrorCode METHOD_NOT_ALLOWED = new ErrorCode(405, "请求方法不正确");
    ErrorCode LOCKED = new ErrorCode(423, "请求失败，请稍后重试");// 并发请求，不允许
    ErrorCode TOO_MANY_REQUESTS = new ErrorCode(429, "请求过于频繁,请稍后重试");

    // ========== 服务端错误段 ==========
    ErrorCode INTERNAL_SERVER_ERROR = new ErrorCode(500, "系统错误");
    ErrorCode NOT_IMPLEMENTED = new ErrorCode(501, "功能未实现/未开启");
    ErrorCode ERROR_CONFIGURATION = new ErrorCode(502, "错误的配置项");
    ErrorCode SERVICE_UNAVAILABLE = new ErrorCode(503, "服务不可用");

    // ========== 自定义错误段 ==========
    ErrorCode REPEATED_REQUESTS = new ErrorCode(900, "重复请求，请稍后重试"); // 重复请求
    ErrorCode DEMO_DENY = new ErrorCode(901, "演示模式，禁止写操作");
    ErrorCode UNKNOWN = new ErrorCode(999, "未知错误");

    // ========== 认证错误段 ==========
    ErrorCode UNPROCESSABLE_ENTITY = new ErrorCode(422, "请求参数格式正确，但是不支持的参数值");
    ErrorCode TOO_LONG = new ErrorCode(413, "请求实体过大");
    ErrorCode TOO_LONG_TEXT = new ErrorCode(414, "请求实体过大");
    ErrorCode TOO_LONG_URI = new ErrorCode(414, "请求URI过长");
    ErrorCode TOO_LONG_HEADER = new ErrorCode(431, "请求头过大");
    ErrorCode TOO_MANY_HEADERS = new ErrorCode(432, "请求头过多");
    ErrorCode TOO_MANY_HEADERS_VALUE = new ErrorCode(433, "请求头值过多");
    ErrorCode TOO_MANY_HEADERS_NAME = new ErrorCode(434, "请求头名称过多");
    ErrorCode TOO_MANY_HEADERS_CONTENT_LENGTH = new ErrorCode(435, "请求头Content-Length过多");
    ErrorCode TOO_MANY_HEADERS_CONTENT_TYPE = new ErrorCode(436, "请求头Content-Type过多");
    ErrorCode TOO_MANY_HEADERS_CONTENT_ENCODING = new ErrorCode(437, "请求头Content-Encoding过多");
    ErrorCode TOO_MANY_HEADERS_CONTENT_LANGUAGE = new ErrorCode(438, "请求头Content-Language过多");
}
