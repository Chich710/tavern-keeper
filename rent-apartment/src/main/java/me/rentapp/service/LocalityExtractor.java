package me.rentapp.service;

import me.rentapp.dto.OpenCageComponents;
import me.rentapp.dto.OpenCageResponse;
import me.rentapp.dto.OpenCageResult;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Component
public class LocalityExtractor {

    private static final List<Function<OpenCageComponents, String>> FALLBACK_CHAIN = List.of(
            OpenCageComponents::getCity,
            OpenCageComponents::getTown,
            OpenCageComponents::getVillage,
            OpenCageComponents::getHamlet,
            OpenCageComponents::getMunicipality,
            OpenCageComponents::getSuburb,
            OpenCageComponents::getCounty
    );

    public Optional<String> extract(OpenCageResponse response) {
        if (response == null || response.getResults() == null || response.getResults().isEmpty()) {
            return Optional.empty();
        }
        OpenCageResult first = response.getResults().get(0);
        if (first == null || first.getComponents() == null) {
            return Optional.empty();
        }
        OpenCageComponents components = first.getComponents();
        return FALLBACK_CHAIN.stream()
                .map(getter -> getter.apply(components))
                .filter(value -> value != null && !value.isBlank())
                .findFirst();
    }
}
