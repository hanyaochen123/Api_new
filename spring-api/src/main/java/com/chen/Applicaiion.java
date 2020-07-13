package com.chen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication

public class Applicaiion {

    public static void main(String[] args) {
        SpringApplication.run(Applicaiion.class,args);
    }
}
