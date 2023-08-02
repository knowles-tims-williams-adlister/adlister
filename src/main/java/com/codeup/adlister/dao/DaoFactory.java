package com.codeup.adlister.dao;

import java.sql.Connection;

public class DaoFactory {
    private static Ads adsDao;
    private static Users usersDao;
    private static Config config = new Config();

    private static Categories categoriesDAO;

    public static Ads getAdsDao() {
        if (adsDao == null) {
            adsDao = new MySQLAdsDao(config);
        }
        return adsDao;
    }

    public static Users getUsersDao() {
        if (usersDao == null) {
            usersDao = new MySQLUsersDao(config);
        }
        return usersDao;
    }

    public static Categories getCategoriesDAO() {
        if (categoriesDAO == null) {
            categoriesDAO = new MySQLCategoriesDao(config);
        }
        return categoriesDAO;

    }
}
