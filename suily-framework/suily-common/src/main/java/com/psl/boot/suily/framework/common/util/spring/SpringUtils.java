/*
 * Copyright (c) 2024. Suily. All rights reserved..
 */

package com.psl.boot.suily.framework.common.util.spring;

import cn.hutool.extra.spring.SpringUtil;

import java.util.Objects;

/**
 * @Author: pengsulong@foxmail.com
 * @Date: 2024/4/20
 * @Description Spring工具类拓展
 */
public class SpringUtils extends SpringUtil {
    /**
     * 是否为生产环境
     *
     * @return 是否为生产环境
     */
    public static boolean isProd() {
        String activeProfile = getActiveProfile();
        return Objects.equals("prod", activeProfile);
    }
}
