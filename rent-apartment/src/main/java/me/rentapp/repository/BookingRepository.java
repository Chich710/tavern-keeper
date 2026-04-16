package me.rentapp.repository;

import me.rentapp.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long> {

    @Query("SELECT COUNT(b) FROM BookingEntity b WHERE b.userId = :userId " +
            "AND b.checkIn > :today AND b.deletedAt IS NULL")
    Long countActiveBookings(@Param("userId") Long userId, @Param("today") LocalDate today);
}
