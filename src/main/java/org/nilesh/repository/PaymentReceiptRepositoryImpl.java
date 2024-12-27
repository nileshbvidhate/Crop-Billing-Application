package org.nilesh.repository;

import org.nilesh.model.CropManager;
import org.nilesh.model.PaymentReceipt;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentReceiptRepositoryImpl extends DBConnection implements PaymentReceiptRepository {

	public DBConnection db;

	public PaymentReceiptRepositoryImpl() {
		db = DBConnection.getInstance();
		connection = db.getConnection();
	}

	// Add a new payment receipt
	@Override
	public void generatePaymentReceipt(PaymentReceipt payRec) {
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {
	        connection.setAutoCommit(false);

	        String query = "SELECT w.quality_id, SUM(w.weight) AS total_weight, p.rate, " +
	                       "c.first_quality_bags, c.second_quality_bags, c.third_quality_bags, c.fourth_quality_bags, c.fifth_quality_bags " +
	                       "FROM Weight w " +
	                       "JOIN Price p ON w.crop_id = p.crop_id AND w.quality_id = p.quality_id " +
	                       "JOIN Crop c ON w.crop_id = c.crop_id AND w.customer_id = c.customer_id " +
	                       "WHERE w.customer_id = ? AND w.crop_id = ? GROUP BY w.quality_id, p.rate, c.first_quality_bags, c.second_quality_bags, c.third_quality_bags, c.fourth_quality_bags, c.fifth_quality_bags";

	        pstmt = connection.prepareStatement(query);
	        pstmt.setInt(1, payRec.getCustomerId());
	        pstmt.setInt(2, payRec.getCropId());
	        rs = pstmt.executeQuery();

	        while (rs.next()) {
	            int qualityId = rs.getInt("quality_id");
	            int weight = rs.getInt("total_weight");
	            int rate = rs.getInt("rate");
	            int bags = 0;

	            // Retrieve number of bags based on quality_id
	            switch (qualityId) {
	                case 1: bags = rs.getInt("first_quality_bags"); break;
	                case 2: bags = rs.getInt("second_quality_bags"); break;
	                case 3: bags = rs.getInt("third_quality_bags"); break;
	                case 4: bags = rs.getInt("fourth_quality_bags"); break;
	                case 5: bags = rs.getInt("fifth_quality_bags"); break;
	            }

	            int qualityAmount = (weight/100) * rate;

	            int totalCharge = (payRec.getPorteringCharge() + payRec.getMeasuringCharge() + payRec.getVehicleRent() +
	                              payRec.getOtherCharge()) * bags;

	            int finalAmount = qualityAmount - totalCharge;

	            String insertSQL = "INSERT INTO Payment_Receipt (customer_id, crop_id, quality_id, quality_weight, rate, " +
	                               "quality_ammount, portering_charge, measuring_charge, vehicle_rent, other_charge, " +
	                               "total_charge, final_payable_ammount) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	            pstmt = connection.prepareStatement(insertSQL);
	            pstmt.setInt(1, payRec.getCustomerId());
	            pstmt.setInt(2, payRec.getCropId());
	            pstmt.setInt(3, qualityId);
	            pstmt.setInt(4, weight);
	            pstmt.setInt(5, rate);
	            pstmt.setInt(6, qualityAmount);
	            pstmt.setInt(7, payRec.getPorteringCharge() * bags);
	            pstmt.setInt(8, payRec.getMeasuringCharge() * bags);
	            pstmt.setInt(9, payRec.getVehicleRent() * bags);
	            pstmt.setInt(10, payRec.getOtherCharge() * bags);
	            pstmt.setInt(11, totalCharge);
	            pstmt.setInt(12, finalAmount);

	            pstmt.executeUpdate();
	        }

	        connection.commit();
	        System.out.println("Payment receipt generated successfully");

	    } catch (SQLException e) {
	        try {
	            if (connection != null) connection.rollback();
	        } catch (SQLException rollbackEx) {
	            rollbackEx.printStackTrace();
	        }
	        System.out.println("Error adding payment receipt: " + e.getMessage());
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (pstmt != null) pstmt.close();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
	}
	
	public boolean deletePaymentReceipt(String customerName, String cropName) {
	    String getIdQuery = "SELECT customer_id FROM Customer WHERE customer_name = ?";
	    String checkReceiptQuery = "SELECT receipt_id FROM Payment_Receipt WHERE customer_id = ? AND crop_id = ?";
	    String deleteQuery = "DELETE FROM Payment_Receipt WHERE customer_id = ? AND crop_id = ?";

	    int cropId = CropManager.getCropIdByName(cropName);
	    boolean isDeleted = false;

	    try (PreparedStatement getIdStmt = connection.prepareStatement(getIdQuery)) {
	        getIdStmt.setString(1, customerName);
	        ResultSet rs = getIdStmt.executeQuery();
	        
	        while (rs.next()) {  
	            int customerId = rs.getInt("customer_id");

	            try (PreparedStatement checkStmt = connection.prepareStatement(checkReceiptQuery)) {
	                checkStmt.setInt(1, customerId);
	                checkStmt.setInt(2, cropId);
	                ResultSet receiptRs = checkStmt.executeQuery();

	                if (!receiptRs.next()) {
	                    System.out.println("No receipt found for Customer: " + customerName + " and Crop: " + cropName);
	                    continue;
	                }
	            }

	            // Delete the receipt
	            try (PreparedStatement deleteStmt = connection.prepareStatement(deleteQuery)) {
	                deleteStmt.setInt(1, customerId);
	                deleteStmt.setInt(2, cropId);
	                int rowsAffected = deleteStmt.executeUpdate();
	                
	                if (rowsAffected > 0) {
	                    System.out.println("Deleted receipt for Customer: " + customerName + " and Crop: " + cropName);
	                    isDeleted = true;
	                }
	            }
	        }
	    } catch (SQLException e) {
	        System.err.println("Error deleting payment receipt: " + e.getMessage());
	        e.printStackTrace();
	    }
	    return isDeleted;
	}





	public void updateReceiptByCustomerIdAndCropId(int customerId, int cropId, PaymentReceipt payRec) {
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {
	        // First, check if a receipt with the given customerId and cropId exists
	        String checkSQL = "SELECT * FROM Payment_Receipt WHERE customer_id = ? AND crop_id = ?";
	        pstmt = connection.prepareStatement(checkSQL);
	        pstmt.setInt(1, customerId);
	        pstmt.setInt(2, cropId);
	        rs = pstmt.executeQuery();

	        // If no record is found, generate a new payment receipt
	        if (!rs.next()) {
	            System.out.println("No receipt found for Customer ID: " + customerId + " and Crop ID: " + cropId);
	            generatePaymentReceipt(payRec);  // Call the function to generate a new receipt
	            return;
	        }

	        // Query to update the receipt details based on customerId and cropId
	        String updateSQL = "UPDATE Payment_Receipt SET " +
	                           "quality_weight = ?, rate = ?, quality_ammount = ?, " +
	                           "portering_charge = ?, measuring_charge = ?, vehicle_rent = ?, " +
	                           "other_charge = ?, total_charge = ?, final_payable_ammount = ? " +
	                           "WHERE customer_id = ? AND crop_id = ?";

	        pstmt = connection.prepareStatement(updateSQL);

	        // Set parameters for the update query
	        pstmt.setInt(1, payRec.getQualityWeight());
	        pstmt.setInt(2, payRec.getRate());
	        pstmt.setInt(3, payRec.getQualityAmount());
	        pstmt.setInt(4, payRec.getPorteringCharge());
	        pstmt.setInt(5, payRec.getMeasuringCharge());
	        pstmt.setInt(6, payRec.getVehicleRent());
	        pstmt.setInt(7, payRec.getOtherCharge());

	        // Calculate total charge and final payable amount
	        int totalCharge = (payRec.getPorteringCharge() + payRec.getMeasuringCharge() + payRec.getVehicleRent() + payRec.getOtherCharge());
	        pstmt.setInt(8, totalCharge);
	        int finalAmount = payRec.getQualityAmount() - totalCharge;
	        pstmt.setInt(9, finalAmount);

	        // Set the customer_id and crop_id for the WHERE clause
	        pstmt.setInt(10, customerId);
	        pstmt.setInt(11, cropId);

	        // Execute the update query
	        int rowsUpdated = pstmt.executeUpdate();

	        if (rowsUpdated > 0) {
	            System.out.println("Payment receipt updated successfully for Customer ID: " + customerId + " and Crop ID: " + cropId);
	        } else {
	            System.out.println("No receipt found for Customer ID: " + customerId + " and Crop ID: " + cropId);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Error updating payment receipt: " + e.getMessage());
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (pstmt != null) pstmt.close();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
	}



	// Get a payment receipt by receipt_id
	@Override
	public List<PaymentReceipt> getPaymentReceiptByCustomerNameAndCropName(String customerName, String cropName) {
	    List<PaymentReceipt> receipts = new ArrayList<>();
	    int cropId = CropManager.getCropIdByName(cropName);
	    String getIdQuery = "SELECT customer_id FROM Customer WHERE customer_name = ? LIMIT 1";
	    String sql = "SELECT * FROM Payment_Receipt WHERE customer_id = ? AND crop_id = ?";

	    try (PreparedStatement getIdStmt = connection.prepareStatement(getIdQuery)) {
	        getIdStmt.setString(1, customerName);
	        ResultSet rs = getIdStmt.executeQuery();
	        
	        if (rs.next()) {  // Fetch only one customer ID as it's a primary key
	            int customerId = rs.getInt("customer_id");
	            
	            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	                stmt.setInt(1, customerId);
	                stmt.setInt(2, cropId);

	                try (ResultSet resultSet = stmt.executeQuery()) {
	                    while (resultSet.next()) {
	                        PaymentReceipt receipt = new PaymentReceipt(
	                            resultSet.getInt("receipt_id"),
	                            resultSet.getInt("customer_id"),
	                            resultSet.getInt("crop_id"),
	                            resultSet.getInt("quality_id"),
	                            resultSet.getInt("quality_weight"),
	                            resultSet.getInt("rate"),
	                            resultSet.getInt("quality_ammount"),
	                            resultSet.getInt("portering_charge"),
	                            resultSet.getInt("measuring_charge"),
	                            resultSet.getInt("vehicle_rent"),
	                            resultSet.getInt("other_charge"),
	                            resultSet.getInt("total_charge"),
	                            resultSet.getInt("final_payable_ammount")
	                        );
	                        receipts.add(receipt);
	                    }
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return receipts;
	}

}
