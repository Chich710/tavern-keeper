package ru.product.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.product.dto.request.DiscountRequestDto;
import ru.product.dto.response.DiscountResponseDto;
import ru.product.mapper.DiscountMapper;
import ru.product.service.DiscountService;
import ru.product.service.handler.DiscountHandler;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {
    private final List<DiscountHandler> discountHandlers;
    private final DiscountMapper discountMapper;

    @Override
    @Transactional(readOnly = true)
    public List<DiscountResponseDto> getDiscounts(DiscountRequestDto request) {
        return discountHandlers.stream()
                .flatMap(handler -> handler.getApplicable(request).stream())
                .map(discountMapper::toDiscountResponseDto)
                .toList();
    }
}
