package me.rentapp.repository;

import me.rentapp.entity.ApartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApartRepository extends JpaRepository<ApartEntity, Long> {
    @Query("SELECT a FROM ApartEntity a LEFT JOIN a.addressApart")
    List<ApartEntity> getList();
}
