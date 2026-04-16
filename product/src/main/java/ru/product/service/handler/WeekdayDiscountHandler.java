package ru.product.service.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.product.dto.request.DiscountRequestDto;
import ru.product.entity.DiscountEntity;
import ru.product.repository.DiscountRepository;

import java.time.DayOfWeek;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class WeekdayDiscountHandler implements DiscountHandler {
    private final DiscountRepository discountRepository;

    @Override
    public List<DiscountEntity> getApplicable(DiscountRequestDto request) {
        Long nights = ChronoUnit.DAYS.between(request.getCheckIn(), request.getCheckOut());
        if (nights != 1) {
            return Collections.emptyList();
        }

        DayOfWeek bookingDay = request.getCheckIn().getDayOfWeek();

        return discountRepository.findWeekdayDiscounts()
                .stream()
                .filter(d -> d.getConditionValue() != null
                        && bookingDay == DayOfWeek.valueOf(d.getConditionValue().toUpperCase()))
                .toList();
    }
}
