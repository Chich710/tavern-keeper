package me.rentapp;

import me.rentapp.config.OpenCageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
@EnableConfigurationProperties(OpenCageProperties.class)
public class RentApartmentApplication {
    public static void main(String[] args) {
        SpringApplication.run(RentApartmentApplication.class, args);
    }
}
