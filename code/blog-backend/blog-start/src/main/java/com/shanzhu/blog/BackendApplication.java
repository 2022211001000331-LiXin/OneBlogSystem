package com.shanzhu.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * SpringBoot 启动类
 * 项目启动入口（点击右键 选择 "run BackendApplication" 启动项目）
 * <p>
 * 什么是SpringBoot？（https://www.php.cn/faq/498384.html）
 * */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan({
        "com.shanzhu.blog.system.mapper",
        "com.shanzhu.blog.cms.mapper",
        "com.shanzhu.blog.quartz.mapper",
        "com.shanzhu.blog.generator.mapper",
})
public class BackendApplication {

    public static void main(String[] args) {

        //SpringBoot 执行启动
        SpringApplication.run(BackendApplication.class, args);
        System.out.printf("================项目后端启动成功===================");

    }
}
