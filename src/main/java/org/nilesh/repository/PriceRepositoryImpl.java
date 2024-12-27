package org.nilesh.repository;

import org.nilesh.model.Price;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PriceRepositoryImpl extends DBConnection implements PriceRepository {

    public DBConnection db;

    public PriceRepositoryImpl() {
        db = DBConnection.getInstance();
        connection = db.getConnection();
    }

    // Add a new price record
    @Override
    public void addPrice(Price price) {
        // Query to check if the price already exists for the given customer, crop, and quality
        String checkSql = "SELECT COUNT(*) FROM Price WHERE customer_id = ? AND crop_id = ? AND quality_id = ?";
        
        // Query to insert the price if it doesn't exist
        String insertSql = "INSERT INTO Price (customer_id, crop_id, quality_id, rate) VALUES (?, ?, ?, ?)";
        
        // Query to update the price if it exists
        String updateSql = "UPDATE Price SET rate = ? WHERE customer_id = ? AND crop_id = ? AND quality_id = ?";
        
        try (PreparedStatement checkStmt = connection.prepareStatement(checkSql)) {
            // Set the parameters for the check query
            checkStmt.setInt(1, price.getCustomerId());
            checkStmt.setInt(2, price.getCropId());
            checkStmt.setInt(3, price.getQualityId());
            
            // Execute the check query
            ResultSet rs = checkStmt.executeQuery();
            
            if (rs.next()) {
                int count = rs.getInt(1);
                
                // If the price already exists
                if (count > 0) {
                    System.out.println("Price already exists for this combination of customer, crop, and quality.");
                    System.out.println("Do you want to update the price? (yes/no)");
                    
                    try (Scanner scanner = new Scanner(System.in)) {
						String response = scanner.nextLine();
						
						if ("yes".equalsIgnoreCase(response)) {
						    try (PreparedStatement updateStmt = connection.prepareStatement(updateSql)) {
						        // Set the parameters for the update query
						        updateStmt.setInt(1, price.getRate());  // New rate to be updated
						        updateStmt.setInt(2, price.getCustomerId());
						        updateStmt.setInt(3, price.getCropId());
						        updateStmt.setInt(4, price.getQualityId());
						        
						        // Execute the update query
						        updateStmt.executeUpdate();
						        System.out.println("Price updated successfully.");
						    }
						} else {
						    System.out.println("Price update canceled.");
						}
					}
                } else {
                    // If the price doesn't exist, insert a new price
                    try (PreparedStatement insertStmt = connection.prepareStatement(insertSql)) {
                        // Set the parameters for the insert query
                        insertStmt.setInt(1, price.getCustomerId());
                        insertStmt.setInt(2, price.getCropId());
                        insertStmt.setInt(3, price.getQualityId());
                        insertStmt.setInt(4, price.getRate());
                        
                        // Execute the insert query
                        insertStmt.executeUpdate();
                        System.out.println("Price added successfully.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Get all price records for a specific customer and crop
    @Override
    public List<Price> getPricesById(int customerId, int cropId) {
        List<Price> prices = new ArrayList<>();
        String sql = "SELECT * FROM Price WHERE customer_id = ? AND crop_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            stmt.setInt(2, cropId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    prices.add(new Price(
                        rs.getInt("customer_id"),
                        rs.getInt("crop_id"),
                        rs.getInt("quality_id"),
                        rs.getInt("rate")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prices;
    }

    // Update price record
    @Override
    public void updatePrice(Price price) {
        // Update query that targets the specific price record based on customer_id, crop_id, and quality_id
        String sql = "UPDATE Price SET rate = ? WHERE customer_id = ? AND crop_id = ? AND quality_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // Set the parameters for the update query
            stmt.setInt(1, price.getRate());         // Set new rate
            stmt.setInt(2, price.getCustomerId());   // Set customer_id
            stmt.setInt(3, price.getCropId());       // Set crop_id
            stmt.setInt(4, price.getQualityId());    // Set quality_id

            // Execute the update
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Price updated successfully.");
            } else {
                System.out.println("No records found to update.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Delete price record by customer and crop ID
    @Override
    public boolean deletePrice(int customerId, int cropId) {
        String sql = "DELETE FROM Price WHERE customer_id = ? AND crop_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            stmt.setInt(2, cropId);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
