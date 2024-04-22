/*
 * Copyright (c) 2024. Suily. All rights reserved..
 */

package com.psl.boot.suily.framework.web.config;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

/**
 * @Author: pengsulong@foxmail.com
 * @Date: 2024/4/22
 * @Description
 */
@Data
@Validated
public class WebProperties {
    @Data
    public static class Api{

        private String prefix;
        private String controller;
    }

}
