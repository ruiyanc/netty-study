package com.yanrui.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @date 2020/08/18 23:12:38
 * @author 许睿
 */
@SpringBootApplication
// 扫描mybatis mapper包路径
@MapperScan(basePackages="com.yanrui.demo.mapper")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
