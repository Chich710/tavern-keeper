package me.rentapp.service;

import me.rentapp.dto.ApartCreateRequestDto;
import me.rentapp.dto.ApartResponseDto;
import java.util.List;

public interface ApartService {
    ApartResponseDto create(ApartCreateRequestDto payload);
    List<ApartResponseDto> getList();
}
