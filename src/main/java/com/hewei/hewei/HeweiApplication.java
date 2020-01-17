package com.hewei.hewei;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.hewei.hewei.*.mapper")
public class HeweiApplication {
    public static void main(String[] args) {
        SpringApplication.run(HeweiApplication.class, args);
    }

}
