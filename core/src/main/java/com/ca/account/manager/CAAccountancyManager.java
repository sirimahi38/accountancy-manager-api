package com.ca.account.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;


@Import(CoreConfig.class)
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class CAAccountancyManager {

    public static void main(String[] args) {
        SpringApplication.run(CAAccountancyManager.class, args);
    }

}
