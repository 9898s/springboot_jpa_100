package com.example.jpa.util;

import lombok.experimental.UtilityClass;
import org.springframework.security.crypto.bcrypt.BCrypt;

@UtilityClass
public class PasswordUtils {
    public static boolean equalsPassword(String password, String encryptPassword) {
        return BCrypt.checkpw(password, encryptPassword);
    }
}
