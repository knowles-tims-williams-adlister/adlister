package com.codeup.adlister.dao;

import com.codeup.adlister.models.Category;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLCategoriesDao implements Categories {
    // Other methods
    private static Connection connection = null;

    public MySQLCategoriesDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    config.getUrl(),
                    config.getUser(),
                    config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }

    @Override
    public List<Category> getCategoriesByAdId(long adId) {
        List<Category> categories = new ArrayList<>();
        PreparedStatement stmt = null;

        try {
            String query = "SELECT c.id, c.name " +
                    "FROM categories c " +
                    "JOIN ad_categories ac ON c.id = ac.category_id " +
                    "WHERE ac.ad_id = ?";
            stmt = connection.prepareStatement(query);
            stmt.setLong(1, adId);
            ResultSet rs = stmt.executeQuery();


            return createCategoriesFromResults(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Category results found", e);
            // Handle the exception, log it, or throw a custom exception as needed.
        }
    }


    @Override
    public List<Category> all() {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM categories");
            ResultSet rs = stmt.executeQuery();
            return createCategoriesFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }

    private List<Category> createCategoriesFromResults(ResultSet rs) throws SQLException {
        List<Category> categories = new ArrayList<>();

        while (rs.next()){
          categories.add(extractCategories(rs));
        }
        return categories;
    }

    private Category extractCategories(ResultSet rs) throws SQLException {
        return new Category(rs.getLong("id"), rs.getString("name"));
    }
}

