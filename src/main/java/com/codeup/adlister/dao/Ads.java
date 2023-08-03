package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;

import java.util.List;

public interface Ads {
    //retrieves an ad by its id
    Ad getById(long id);

    //delete an existing add
    void delete(long id);

    //update an existing ad with new data.
    void update(Ad ad);

    // get a list of all the ads
    List<Ad> all();
    // insert a new ad and return the new ad's id
    Long insert(Ad ad);

    // Search ads by title
    List<Ad> searchByTitle(String keyword);
}