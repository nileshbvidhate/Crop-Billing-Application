package org.nilesh.repository;

import org.nilesh.model.Crop;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CropRepositoryImpl extends DBConnection implements CropRepository {

	public CropRepositoryImpl() {
		connection = DBConnection.getInstance().getConnection();
	}

	@Override
	public int addCrop(Crop crop) {
		String query = "INSERT INTO Crop (crop_id, customer_id, crop_name, first_quality_bags, second_quality_bags, third_quality_bags, fourth_quality_bags, fifth_quality_bags) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		int cropId = crop.getCropId(); // Assuming the cropId is set externally or manually

		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, cropId); // Set the manually provided crop ID
			stmt.setInt(2, crop.getCustomerId());
			stmt.setString(3, crop.getCropName());
			stmt.setInt(4, crop.getFirstQualityBags());
			stmt.setInt(5, crop.getSecondQualityBags());
			stmt.setInt(6, crop.getThirdQualityBags());
			stmt.setInt(7, crop.getFourthQualityBags());
			stmt.setInt(8, crop.getFifthQualityBags());

			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected > 0) {
				// If insertion is successful, return the manually provided cropId
				return cropId;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1; // Return -1 if insertion fails
	}

	@Override
	public Crop getCropById(int cropId, int customerId) {
		String query = "SELECT * FROM Crop WHERE crop_id = ? AND customer_id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, cropId);
			stmt.setInt(2, customerId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Crop(rs.getInt("crop_id"), rs.getInt("customer_id"), rs.getString("crop_name"),
						rs.getInt("first_quality_bags"), rs.getInt("second_quality_bags"),
						rs.getInt("third_quality_bags"), rs.getInt("fourth_quality_bags"),
						rs.getInt("fifth_quality_bags"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Crop> getAllCropsByCustomerId(int customerId) {
		List<Crop> crops = new ArrayList<>();
		String query = "SELECT * FROM Crop WHERE customer_id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, customerId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				crops.add(new Crop(rs.getInt("crop_id"), rs.getInt("customer_id"), rs.getString("crop_name"),
						rs.getInt("first_quality_bags"), rs.getInt("second_quality_bags"),
						rs.getInt("third_quality_bags"), rs.getInt("fourth_quality_bags"),
						rs.getInt("fifth_quality_bags")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return crops;
	}

	@Override
	public boolean deleteCrop(int cropId, int customerId) {
		String query = "DELETE FROM Crop WHERE crop_id = ? AND customer_id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, cropId);
			stmt.setInt(2, customerId);
			int affected = stmt.executeUpdate();

			if (affected > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public void updateCrop(Crop crop) {
		String query = "UPDATE Crop SET crop_name = ?, first_quality_bags = ?, second_quality_bags = ?, third_quality_bags = ?, fourth_quality_bags = ?, fifth_quality_bags = ? WHERE crop_id = ? AND customer_id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, crop.getCropName());
			stmt.setInt(2, crop.getFirstQualityBags());
			stmt.setInt(3, crop.getSecondQualityBags());
			stmt.setInt(4, crop.getThirdQualityBags());
			stmt.setInt(5, crop.getFourthQualityBags());
			stmt.setInt(6, crop.getFifthQualityBags());
			stmt.setInt(7, crop.getCropId());
			stmt.setInt(8, crop.getCustomerId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
