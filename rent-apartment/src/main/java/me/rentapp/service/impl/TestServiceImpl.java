package me.rentapp.service.impl;

import lombok.RequiredArgsConstructor;
import me.rentapp.service.TestService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {
    private final RestTemplate restTemplate;

    @Override
    public String test() {
        return restTemplate.exchange(
                prepareUrl(),
                HttpMethod.GET,
                new HttpEntity<>(null, null),
                String.class
        ).getBody();
    }

    private String prepareUrl() {
        return "http://localhost:8085/products/test";
    }

}
