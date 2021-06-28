package com.linglouyi.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author linglouyi
 */
@SpringBootApplication
public class UserApplication {

    public static ConfigurableApplicationContext ac;

    public static void main(String[] args) {
        ac = SpringApplication.run(UserApplication.class,args);
    }
}
