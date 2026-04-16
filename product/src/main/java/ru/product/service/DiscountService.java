package ru.product.service;

import ru.product.dto.request.DiscountRequestDto;
import ru.product.dto.response.DiscountResponseDto;

import java.util.List;

public interface DiscountService {
    List<DiscountResponseDto> getDiscounts(DiscountRequestDto request);
}
