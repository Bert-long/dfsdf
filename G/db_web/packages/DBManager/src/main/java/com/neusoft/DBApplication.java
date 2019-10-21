package com.neusoft;

import com.neusoft.utils.IdWork;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DBApplication {
    public static void main(String[] args) {
        SpringApplication.run(DBApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public IdWork idwork() {
        return new IdWork();
    }
}
