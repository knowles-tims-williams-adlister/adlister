package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListAdsDao implements Ads {
    private List<Ad> ads;

    public List<Ad> all() {
        if (ads == null) {
            ads = generateAds();
        }
        return ads;
    }

    public Long insert(Ad ad) {
        // make sure we have ads
        if (ads == null) {
            ads = generateAds();
        }
        // we'll assign an "id" here based on the size of the ads list
        // really the dao would handle this
        ad.setId((long) ads.size());
        ads.add(ad);
        return ad.getId();
    }

    private List<Ad> generateAds() {
        List<Ad> ads = new ArrayList<>();
        ads.add(new Ad(
            1,
            1,
            "playstation for sale",
            "This is a slightly used playstation"
        ));
        ads.add(new Ad(
            2,
            1,
            "Super Nintendo",
            "Get your game on with this old-school classic!"
        ));
        ads.add(new Ad(
            3,
            2,
            "Junior Java Developer Position",
            "Minimum 7 years of experience required. You will be working in the scripting language for Java, JavaScript"
        ));
        ads.add(new Ad(
            4,
            2,
            "JavaScript Developer needed",
            "Must have strong Java skills"
        ));
        return ads;
    }

    public Ad getById(long id) {
        // UpdateAdServlet: Find and return the Ad with the given ID
        return ads.stream()
                .filter(ad -> ad.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // DeleteAds
    public boolean delete(long id) {
        Iterator<Ad> iterator = ads.iterator();
        while (iterator.hasNext()) {
            Ad ad = iterator.next();
            if (ad.getId() == id) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public void update(Ad ad) {
        // Find and update the ad with the given ID in the list
        for (int i = 0; i < ads.size(); i++) {
            Ad currentAd = ads.get(i);
            if (currentAd.getId() == ad.getId()) {
                ads.set(i, ad);
                break;
            }
        }
    }
}
