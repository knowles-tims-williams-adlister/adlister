package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLAdsDao implements Ads {
    private static Connection connection = null;

    public MySQLAdsDao(Config config) {
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
    public Ad getById(long id) {
        // Retrieves an ad from the database by ID
        // Similar to the `all` method, but with a WHERE clause to filter by ad ID
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM ads WHERE id = ?");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractAd(rs);
            } else {
                return null; // Return null if the ad with the given ID is not found
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving the ad by ID.", e);
        }
    }

    @Override
    public void delete(long id) {
        // Deletes an ad from the database by ID
        // Uses a DELETE query with a WHERE clause to delete the ad with the specified ID
        try {
            String deleteQuery = "DELETE FROM ads WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(deleteQuery);
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting the ad.", e);
        }
    }
    public void update(Ad ad) {
        try {
            String updateQuery = "UPDATE ads SET user_id = ?, title = ?, description = ? WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(updateQuery);
            stmt.setLong(1, ad.getUserId());
            stmt.setString(2, ad.getTitle());
            stmt.setString(3, ad.getDescription());
            stmt.setLong(4, ad.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating the ad.", e);
        }
    }

    @Override
    public List<Ad> all() {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM ads");
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }

    @Override
    public Long insert(Ad ad) {
        try {
            String insertQuery = "INSERT INTO ads(user_id, title, description) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, ad.getUserId());
            stmt.setString(2, ad.getTitle());
            stmt.setString(3, ad.getDescription());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new ad.", e);
        }
    }

//    Search Functionality
    public List<Ad> all(String keyword, String category) {
        PreparedStatement stmt = null;
        try {
            String query = "SELECT * FROM ads WHERE title LIKE ? AND category = ?";
            stmt = connection.prepareStatement(query);
            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, category);

            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving ads with search.", e);
        }
    }

//    Sort Functionality
    public List<Ad> all(String keyword, String category, String sortOrder) {
        PreparedStatement stmt = null;
        try {
            String query = "SELECT * FROM ads WHERE title LIKE ? AND category = ? ORDER BY " + "CASE ? " + "WHEN 'asc' THEN title " + "WHEN 'desc' " +
                    "THEN title END";
            stmt = connection.prepareStatement(query);
            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, category);
            stmt.setString(3, sortOrder);

            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving ads with search and sort.", e);
        }
    }
    private Ad extractAd(ResultSet rs) throws SQLException {
        return new Ad(
                rs.getLong("id"),
                rs.getLong("user_id"),
                rs.getString("title"),
                rs.getString("description")
        );
    }

    private List<Ad> createAdsFromResults(ResultSet rs) throws SQLException {
        List<Ad> ads = new ArrayList<>();
        while (rs.next()) {
            ads.add(extractAd(rs));
        }
        return ads;
    }
}