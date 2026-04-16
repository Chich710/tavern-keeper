package me.rentapp.client;

import lombok.RequiredArgsConstructor;
import me.rentapp.dto.DiscountDto;
import me.rentapp.dto.DiscountRequestDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductClient {
    private final RestTemplate restTemplate;

    private static final String BASE_URL = "http://localhost:8084/products";

    public List<DiscountDto> getDiscounts(DiscountRequestDto request) {
        return restTemplate.exchange(
                BASE_URL + "/discount",
                HttpMethod.POST,
                new HttpEntity<>(request),
                new ParameterizedTypeReference<List<DiscountDto>>() {}
        ).getBody();
    }
}
