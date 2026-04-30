package me.rentapp.service.impl;

import lombok.RequiredArgsConstructor;
import me.rentapp.client.OpenCageClient;
import me.rentapp.dto.ApartCreateRequestDto;
import me.rentapp.dto.ApartResponseDto;
import me.rentapp.entity.ApartEntity;
import me.rentapp.mapper.ApartMapper;
import me.rentapp.repository.ApartRepository;
import me.rentapp.service.ApartService;
import me.rentapp.service.LocationExtractor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApartServiceImpl implements ApartService {
    private final ApartRepository apartRepository;
    private final ApartMapper apartMapper;
    private final OpenCageClient openCageClient;
    private final LocationExtractor locationExtractor;

    @Override
    @Transactional
    public ApartResponseDto create(ApartCreateRequestDto payload) {
        ApartEntity apart = apartMapper.toNewApartEntity(payload);
        ApartEntity savedApart = apartRepository.save(apart);

        return apartMapper.toApartResponseDto(savedApart, savedApart.getAddressApart());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ApartResponseDto> getList(Double latitude, Double longitude, String city) {
        String filterCity = resolveCity(latitude, longitude, city);

        return apartRepository.getList(filterCity).stream()
                .map(aprt -> apartMapper.toApartResponseDto(aprt, aprt.getAddressApart()))
                .toList();
    }

    private String resolveCity(Double latitude, Double longitude, String city) {
        if (city != null && !city.isBlank()) { return city; }

        return locationExtractor.extract(openCageClient.geocode(latitude, longitude)).orElse(null);
    }
}
