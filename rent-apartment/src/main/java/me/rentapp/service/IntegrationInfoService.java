package me.rentapp.service;

import lombok.RequiredArgsConstructor;
import me.rentapp.entity.IntegrationInfoEntity;
import me.rentapp.repository.IntegrationInfoRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class IntegrationInfoService {

    private static final String OPEN_CAGE_CODE = "open_cage";

    private final IntegrationInfoRepository integrationInfoRepository;

    @Cacheable(value = "integrationInfo", key = "'openCageUrl'")
    public String getOpenCageUrl() {
        return decode(loadByCode(OPEN_CAGE_CODE).getLink());
    }

    @Cacheable(value = "integrationInfo", key = "'openCageKey'")
    public String getOpenCageApiKey() {
        return decode(loadByCode(OPEN_CAGE_CODE).getApiKey());
    }

    private IntegrationInfoEntity loadByCode(String code) {
        return integrationInfoRepository.findByCode(code)
                .orElseThrow(() -> new IllegalStateException("integration_info row not found for code: " + code));
    }

    private String decode(String value) {
        return new String(Base64.getDecoder().decode(value), StandardCharsets.UTF_8);
    }
}
