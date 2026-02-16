package me.authapp.util;

import java.time.LocalDate;
import java.util.UUID;

public class TokenUtil {
    public static String generateGuestToken(String role) {
        UUID randomUUuid = UUID.randomUUID();
        String finalUniqueToken = randomUUuid + "|" + role + "|" + LocalDate.now().plusDays(1);

        return finalUniqueToken;
    }
}
