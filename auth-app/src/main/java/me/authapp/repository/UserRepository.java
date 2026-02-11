package me.authapp.repository;

import me.authapp.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("""
            SELECT COUNT(u) > 0
            FROM UserEntity u
            WHERE u.login = :login
            """)
    boolean existsByLogin(String login);
}
