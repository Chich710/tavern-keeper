package me.authapp.util;

import java.time.LocalDate;
import java.util.UUID;

public class TokenUtil {

    private static final String SEPARATOR = "\\|";
    private static final int EXPIRATION_DATE_INDEX = 2;

    public static String generateGuestToken(String role) {
        UUID randomUUuid = UUID.randomUUID();
        String finalUniqueToken = randomUUuid + "|" + role + "|" + LocalDate.now().plusDays(1);

        return finalUniqueToken;
    }

    public static LocalDate extractExpirationDate(String token) {
        String[] parts = token.split(SEPARATOR);
        if (parts.length <= EXPIRATION_DATE_INDEX) {
            throw new IllegalArgumentException("Wrong token format: " + token);
        }

        return LocalDate.parse(parts[EXPIRATION_DATE_INDEX]);
    }
}
