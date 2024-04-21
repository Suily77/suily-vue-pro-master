/*
 * Copyright (c) 2024. Suily. All rights reserved..
 */

package com.psl.boot.suily.framework.common.exception;

import lombok.Data;

/**
 * @Author: pengsulong@foxmail.com
 * @Date: 2024/4/21
 * @Description
 */
@Data
public class ErrorCode {
    /**
     * 错误码
     */
    private final Integer code;
    /**
     * 错误提示
     */
    private final String msg;
    public ErrorCode(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
