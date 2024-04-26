/*
 * Copyright (c) 2024. Suily. All rights reserved..
 */

package com.psl.boot.suily.framework.web.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Author: pengsulong@foxmail.com
 * @Date: 2024/4/22
 * @Description
 */
@ConfigurationProperties(prefix = "suily.web")
@Data
@Validated
public class WebProperties {

    @NotNull(message = "App-Api不能为空")
    private Api Appapi = new Api("/app-api","**.controller.app.**");

    @NotNull(message = "Admin-Api不能为空")
    private Api adminApi = new Api("/admin-api","**.controller.admin.**");

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Valid // 验证开启
    public static class Api{
        /**
         * api前缀,实现所有Controller提供的RESTful Api 的统一前缀
         *
         * 目的：为了避免Swagger、Actuator意外通过Ngnix暴露给外部，带来安全线问题
         * 这样，nginx只需要配置转发：/api/* -> /api/的所以接口即可
         * @see SuilyWebAutoConfiguration #
         *
         */
        @NotEmpty(message = "Api前缀不能为空")
        private String prefix;
        @NotEmpty(message = "Controller所在包不能为空")
        private String controller;
    }

}
