package com.example.gym.utils;

import org.mindrot.jbcrypt.BCrypt;

public class Password {
    public static String hashPassword(String password_plaintext) {
        String salt = BCrypt.gensalt(12);
        String hashed_password = BCrypt.hashpw(password_plaintext, salt);

        return(hashed_password);
    }
}
