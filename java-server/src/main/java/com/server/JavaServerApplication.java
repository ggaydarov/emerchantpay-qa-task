package com.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.server")
public class JavaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(JavaServerApplication.class, args);
    }
}
