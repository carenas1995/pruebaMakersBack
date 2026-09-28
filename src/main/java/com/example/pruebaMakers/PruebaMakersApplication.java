package com.example.pruebaMakers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PruebaMakersApplication
{
    public static void main(String[] args) {
        SpringApplication.run(PruebaMakersApplication.class, args);
    }
}
