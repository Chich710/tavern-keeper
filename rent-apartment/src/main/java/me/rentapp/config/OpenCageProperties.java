package me.rentapp.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("opencage")
public class OpenCageProperties {
    private String url;
    private String apiKey;
}
