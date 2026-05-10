package me.rentapp.client;

import lombok.RequiredArgsConstructor;
import me.rentapp.config.OpenCageProperties;
import me.rentapp.dto.OpenCageResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class OpenCageClient {
    private final RestTemplate restTemplate;
    private final OpenCageProperties properties;

    public OpenCageResponse geocode(Double latitude, Double longitude) {
        String url = properties.getUrl().formatted(
                latitude,
                longitude,
                properties.getApiKey()
        );

        return restTemplate.getForObject(url, OpenCageResponse.class);
    }
}
