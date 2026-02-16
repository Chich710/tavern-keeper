package me.authapp.repository;

import me.authapp.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    @Query("SELECT r FROM RoleEntity r WHERE r.roleCode = :roleCode")
    Optional<RoleEntity> getByRoleCode(@Param("role_code") String roleCode);
}
