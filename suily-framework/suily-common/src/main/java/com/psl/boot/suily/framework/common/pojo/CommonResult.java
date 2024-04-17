/*
 * Copyright (c) 2024. Suily. All rights reserved..
 */

package com.psl.boot.suily.framework.common.pojo;

import lombok.Data;

/**
 * @Author: pengsulong@foxmail.com
 * @Date: 2024/4/17
 * @Description 返回结果实体类
 *  @code: 200   400  500
 *  @data: < T >: 返回的数据类型
 *  @msg:  String
 */
@Data
public class CommonResult<T> {
    /**
     * 错误码
     * @see
     */
    private Integer code;

    /**
     * 返回的信息
     */
    private String msg;

    /**
     * 返回的数据
     */
    private T data;

    /**
     * 成功返回
     * @param data 返回的数据
     * @return 返回结果类
     */
    public static <T> CommonResult<T> success(T data) {
        CommonResult<T> result = new CommonResult<>();
        result.setCode(200);
        result.setData(data);
        result.setMsg("Status is successful");
        return result;
    }

    /**
     * 错误返回
     * @param code 错误码，错误有很多种
     * @param msg 错误信息
     * @return 返回结果类
     */
    public static <T> CommonResult<T> error(Integer code, String msg) {
        CommonResult<T> result = new CommonResult<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
