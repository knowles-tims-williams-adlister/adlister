package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;

import java.util.List;

public interface Ads {
    Ad getById(long id);

    boolean delete(long id); // MySQLAdsDao: Add the delete method with boolean return type

    // get a list of all the ads
    List<Ad> all();
    // insert a new ad and return the new ad's id
    Long insert(Ad ad);
    void update(Ad ad);

}