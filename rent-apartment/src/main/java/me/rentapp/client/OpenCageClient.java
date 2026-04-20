package me.rentapp.client;

import lombok.RequiredArgsConstructor;
import me.rentapp.dto.OpenCageResponse;
import me.rentapp.service.IntegrationInfoService;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class OpenCageClient {
    private final RestTemplate restTemplate;
    private final IntegrationInfoService integrationInfoService;

    public OpenCageResponse geocode(Double latitude, Double longitude) {
        String url = integrationInfoService.getOpenCageUrl().formatted(
                latitude,
                longitude,
                integrationInfoService.getOpenCageApiKey()
        );

        return restTemplate.getForObject(url, OpenCageResponse.class);
    }
}
