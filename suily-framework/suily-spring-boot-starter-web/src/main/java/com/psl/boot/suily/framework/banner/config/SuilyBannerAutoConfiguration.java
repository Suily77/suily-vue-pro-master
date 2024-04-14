/*
 * Copyright (c) 2024. Suily. All rights reserved..
 */

package com.psl.boot.suily.framework.banner.config;

import com.psl.boot.suily.framework.banner.core.BannerApplicationRunner;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @Author: pengsulong@foxmail.com
 * @Date: 2024/4/15
 * @Description
 */
//@AutoConfiguration
public class SuilyBannerAutoConfiguration {
    @Bean
    public BannerApplicationRunner bannerApplicationRunner() {
        return new BannerApplicationRunner();
    }
}
