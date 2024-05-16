package com.shanzhu.subject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author shanzhu
 * @description 刷题微服务启动类
 * @create 2024/4/28
 */
@SpringBootApplication
@ComponentScan("com.shanzhu")
@MapperScan("com.shanzhu.**.mapper")
public class SubjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(SubjectApplication.class, args);
    }
}
