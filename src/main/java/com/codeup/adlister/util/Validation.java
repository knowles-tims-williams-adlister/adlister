package com.codeup.adlister.util;

import com.codeup.adlister.models.User;

public class Validation {
    public static boolean isValidUsername(String username) {

        if (username == null || username.trim().isEmpty() || username.length() > 30) {
            return false;
        }
        return true;
    }
}


