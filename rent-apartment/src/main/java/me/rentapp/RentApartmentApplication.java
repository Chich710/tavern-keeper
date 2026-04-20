package me.rentapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class RentApartmentApplication {
    public static void main(String[] args) {
        SpringApplication.run(RentApartmentApplication.class, args);
    }
}
