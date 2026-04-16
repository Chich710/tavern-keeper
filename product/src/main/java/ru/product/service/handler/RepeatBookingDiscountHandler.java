package ru.product.service.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.product.dto.request.DiscountRequestDto;
import ru.product.entity.DiscountEntity;
import ru.product.repository.DiscountRepository;
import java.util.List;

import static java.util.Collections.emptyList;

@Component
@RequiredArgsConstructor
public class RepeatBookingDiscountHandler implements DiscountHandler {
    private final DiscountRepository discountRepository;

    @Override
    public List<DiscountEntity> getApplicable(DiscountRequestDto request) {
        if (request.getActiveBookingsCount() == null || request.getActiveBookingsCount() < 2) {
            return emptyList();
        }

        return discountRepository.findRepeatBookingDiscounts();
    }
}
