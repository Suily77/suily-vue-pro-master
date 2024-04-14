/*
 * Copyright (c) 2024. Suily. All rights reserved..
 */

package com.psl.boot.suily.framework.banner.core;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

/**
 * @Author: pengsulong@foxmail.com
 * @Date: 2024/4/15
 * @Description
 */

public class BannerApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("============System开始启动====================");
    }
}
