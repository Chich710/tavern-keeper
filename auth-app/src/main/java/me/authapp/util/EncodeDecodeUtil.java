package me.authapp.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class EncodeDecodeUtil {
    public static String encodePassword(String password) {
        Base64.Encoder encoder = Base64.getEncoder();

        return encoder.encodeToString(password.getBytes((StandardCharsets.UTF_8)));
    }

    public static String decodePassword(String password) {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decodedBytes = decoder.decode(password);

        return new String(decodedBytes);
    }
}
