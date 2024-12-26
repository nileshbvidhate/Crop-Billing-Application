package org.nilesh.repository;

import org.nilesh.model.Weight;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WeightRepositoryImpl extends DBConnection implements WeightRepository {

	public DBConnection db;

	public WeightRepositoryImpl() {
		db = DBConnection.getInstance();
		connection = db.getConnection();
	}
    // Add a new weight record
    @Override
    public void addWeight(Weight weight) {
        String sql = "INSERT INTO Weight (customer_id, crop_id, quality_id, weight) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, weight.getCustomerId());
            stmt.setInt(2, weight.getCropId());
            stmt.setInt(3, weight.getQualityId());
            stmt.setInt(4, weight.getWeight());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Get all weight records for a specific customer
    @Override
    public List<Weight> getWeightsById(int customerId,int cropId) {
        List<Weight> weights = new ArrayList<>();
        String sql = "SELECT * FROM Weight WHERE crop_id = ?";  // Corrected to crop_id
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, cropId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    weights.add(new Weight(
                        rs.getInt("customer_id"),
                        rs.getInt("crop_id"),
                        rs.getInt("quality_id"),
                        rs.getInt("weight")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return weights;
    }


    // Update weight record
    @Override
    public void updateWeight(Weight weight) {
        String sql = "UPDATE Weight SET quality_id = ?, weight = ? WHERE customer_id = ? AND crop_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, weight.getQualityId());
            stmt.setInt(2, weight.getWeight());
            stmt.setInt(3, weight.getCustomerId());
            stmt.setInt(4, weight.getCropId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete weight record by customer and crop ID
    @Override
    public boolean deleteWeight(int customerId, int cropId) {
        String sql = "DELETE FROM Weight WHERE customer_id = ? AND crop_id = ?";
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
