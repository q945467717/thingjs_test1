package com.zl.thingjs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@EntityScan("com.zl.model")
@ComponentScan(basePackages = {"com.zl.controller","com.zl.service","com.zl.configuration","com.zl.security"})
@MapperScan("com.zl.mapper")
@SpringBootApplication
public class ThingjsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ThingjsApplication.class, args);
    }


}
