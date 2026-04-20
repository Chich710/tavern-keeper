package me.rentapp.repository;

import me.rentapp.entity.IntegrationInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IntegrationInfoRepository extends JpaRepository<IntegrationInfoEntity, Integer> {
    Optional<IntegrationInfoEntity> findByCode(String code);
}
