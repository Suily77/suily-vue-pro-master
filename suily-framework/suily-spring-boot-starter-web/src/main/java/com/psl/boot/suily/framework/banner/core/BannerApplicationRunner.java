/*
 * Copyright (c) 2024. Suily. All rights reserved..
 */

package com.psl.boot.suily.framework.banner.core;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.stereotype.Component;

/**
 * @Author: pengsulong@foxmail.com
 * @Date: 2024/4/15
 * @Description
 */
// 没有自动配置装配启动，不会扫描这个注解，有的话就不需要，而且与@Bean效果类似，@Bean优先级高，
// 这里@Bean生效;@Configurable无效果，需要其他注解开启自动配置;
public class BannerApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("============System开始启动====================");
    }
}
