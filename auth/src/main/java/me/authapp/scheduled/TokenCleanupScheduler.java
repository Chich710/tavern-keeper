package me.authapp.scheduled;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.authapp.entity.UserEntity;
import me.authapp.repository.UserRepository;
import me.authapp.util.TokenUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class TokenCleanupScheduler {

    private final UserRepository userRepository;

    @Scheduled(cron = "${token-cleanup}")
    @Transactional
    public void cleanupExpiredTokens() {
        List<UserEntity> users = userRepository.findAllWithToken();

        for (UserEntity user : users) {
            try {
                LocalDate expirationDate = TokenUtil.extractExpirationDate(user.getToken());
                if (expirationDate.isBefore(LocalDate.now())) {
                    user.setToken(null);
                }
            } catch (Exception e) {
                log.warn("Skipping user id={}", user.getId());
            }
        }
    }
}
