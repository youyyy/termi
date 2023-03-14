package com.ue.termi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class TermiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TermiApplication.class, args);
    }

}
