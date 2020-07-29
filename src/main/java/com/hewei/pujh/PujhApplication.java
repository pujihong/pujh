package com.hewei.pujh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hewei.pujh.**.mapper")
public class PujhApplication {
    public static void main(String[] args) {
        SpringApplication.run(PujhApplication.class, args);
    }
}
