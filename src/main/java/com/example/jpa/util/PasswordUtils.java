package com.example.jpa.util;

import lombok.experimental.UtilityClass;
import org.springframework.security.crypto.bcrypt.BCrypt;

@UtilityClass
public class PasswordUtils {

    public static String encryptedPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean equalsPassword(String password, String encryptPassword) {
        try {
            return BCrypt.checkpw(password, encryptPassword);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
