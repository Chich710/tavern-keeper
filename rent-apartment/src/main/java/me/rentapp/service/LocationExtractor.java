package me.rentapp.service;

import me.rentapp.dto.OpenCageComponents;
import me.rentapp.dto.OpenCageResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Component
public class LocationExtractor {

    private static final List<Function<OpenCageComponents, String>> NORMALIZED_CITY = List.of(
            OpenCageComponents::getCity,
            OpenCageComponents::getTown,
            OpenCageComponents::getTownship,
            OpenCageComponents::getVillage,
            OpenCageComponents::getHamlet,
            OpenCageComponents::getMunicipality,
            OpenCageComponents::getSuburb,
            OpenCageComponents::getCounty,
            OpenCageComponents::getIsland
    );

    public Optional<String> extract(OpenCageResponse response) {
        if (response == null || response.getResults() == null || response.getResults().isEmpty()) {
            return Optional.empty();
        }
        OpenCageResponse.Result first = response.getResults().getFirst();
        if (first == null || first.getComponents() == null) {
            return Optional.empty();
        }
        OpenCageComponents components = first.getComponents();
        String normalizedCity = components.getNormalizedCity();
        if (normalizedCity != null && !normalizedCity.isBlank()) {
            return Optional.of(normalizedCity);
        }

        return NORMALIZED_CITY.stream()
                .map(it -> it.apply(components))
                .filter(value -> value != null && !value.isBlank())
                .findFirst();
    }
}
