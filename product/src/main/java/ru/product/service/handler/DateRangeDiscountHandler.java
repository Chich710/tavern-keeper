package ru.product.service.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.product.dto.request.DiscountRequestDto;
import ru.product.entity.DiscountEntity;
import ru.product.repository.DiscountRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DateRangeDiscountHandler implements DiscountHandler {
    private final DiscountRepository discountRepository;

    @Override
    public List<DiscountEntity> getApplicable(DiscountRequestDto request) {
        return discountRepository.findDateRangeDiscounts(
                request.getCheckIn().minusDays(7),
                request.getCheckOut().plusDays(7)
        );
    }
}
