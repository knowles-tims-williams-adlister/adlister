package com.codeup.adlister.dao;

import com.codeup.adlister.models.User;

import java.util.List;

public interface Users {
    User findByUsername(String username);
    Long insert(User user);
    void updatePassword(User user);
    void updateEmail(User user);
    void updateUsername(String username, User user);
}
