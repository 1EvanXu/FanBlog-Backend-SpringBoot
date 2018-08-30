package com.evan.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = "com.evan.blog.repository")
@ComponentScan(basePackages = {"com.evan.blog.*"})
@ServletComponentScan
public class BlogApplication {
//        extends SpringBootServletInitializer {
//
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(BlogApplication.class);
//    }

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }
}
