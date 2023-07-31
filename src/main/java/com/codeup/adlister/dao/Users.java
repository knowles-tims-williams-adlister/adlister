package com.codeup.adlister.dao;

import com.codeup.adlister.models.User;

import java.util.List;

public interface Users {
    User findByUsername(String username);
    Long insert(User user);
    void updatePassword(User user, String password);
    void updateEmail(User user, String email);
    void updateUsername(User user, String username);
    void deleteUser(User user);
}
