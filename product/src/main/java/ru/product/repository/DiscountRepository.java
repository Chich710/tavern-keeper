package ru.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.product.entity.DiscountEntity;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DiscountRepository extends JpaRepository<DiscountEntity, Long> {

    @Query("""
            SELECT de FROM DiscountEntity de
            WHERE de.active = true AND de.deletedAt IS NULL
              AND LOWER(de.conditionType) = 'date_range'
              AND de.startDate <= :endDate AND de.endDate >= :startDate
            """)
    List<DiscountEntity> findDateRangeDiscounts(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    @Query("""
            SELECT de FROM DiscountEntity de
            WHERE de.active = true AND de.deletedAt IS NULL
              AND LOWER(de.conditionType) = 'age_under'
            """)
    List<DiscountEntity> getAgeDiscounts();

    @Query("""
            SELECT de FROM DiscountEntity de
            WHERE de.active = true AND de.deletedAt IS NULL
              AND LOWER(de.conditionType) = 'weekday'
            """)
    List<DiscountEntity> findWeekdayDiscounts();

    @Query("""
            SELECT de FROM DiscountEntity de
            WHERE de.active = true AND de.deletedAt IS NULL
              AND LOWER(de.conditionType) = 'repeat_booking'
            """)
    List<DiscountEntity> findRepeatBookingDiscounts();
}
