package me.rentapp.repository;

import me.rentapp.entity.ApartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApartRepository extends JpaRepository<ApartEntity, Integer> {
    @Query("SELECT a FROM ApartEntity a LEFT JOIN a.addressApart addr " +
            "WHERE :city IS NULL OR LOWER(addr.city) = LOWER(:city)")
    List<ApartEntity> getList(@Param("city") String city);
}
