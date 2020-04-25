package com.bootzufang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.bootzufang.mapper")
@SpringBootApplication
public class BootzufangApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootzufangApplication.class, args);
    }

}
