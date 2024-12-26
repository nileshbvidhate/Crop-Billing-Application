package org.nilesh.repository;

import org.nilesh.model.Customer;
//import org.nilesh.repository.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImpl extends DBConnection implements CustomerRepository {

	public DBConnection db;

	public CustomerRepositoryImpl() {
		db = DBConnection.getInstance();
		connection = db.getConnection();
	}

	@Override
	public int addCustomer(Customer customer) {
		String query = "INSERT INTO Customer (customer_name, customer_address, customer_contact, customer_crop, `date`) VALUES (?, ?, ?, ?, ?)";
		int ID = -1;

		try (PreparedStatement stmt = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, customer.getName());
			stmt.setString(2, customer.getAddress());
			stmt.setString(3, customer.getContact());
			stmt.setString(4, customer.getCrop());
			stmt.setDate(5, customer.getDate());

			int affectedRows = stmt.executeUpdate();

			// Get the generated ID
			if (affectedRows > 0) {
				try (ResultSet rs = stmt.getGeneratedKeys()) {
					if (rs.next()) {
						ID = rs.getInt(1); // Get the auto-generated key
						customer.setId(ID);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ID;
	}

	@Override
	public Customer getCustomerById(int customerId) {
		String query = "SELECT * FROM Customer WHERE customer_id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, customerId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Customer(rs.getInt("customer_id"), rs.getString("customer_name"),
						rs.getString("customer_address"), rs.getString("customer_contact"),
						rs.getString("customer_crop"), rs.getDate("date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Customer getCustomerByName(String customerName) {
		String query = "SELECT * FROM Customer WHERE customer_name = ?";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, customerName);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Customer(rs.getInt("customer_id"), rs.getString("customer_name"),
						rs.getString("customer_address"), rs.getString("customer_contact"),
						rs.getString("customer_crop"), rs.getDate("date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> customers = new ArrayList<>();
		String query = "SELECT * FROM Customer";
		try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				customers.add(new Customer(rs.getInt("customer_id"), rs.getString("customer_name"),
						rs.getString("customer_address"), rs.getString("customer_contact"),
						rs.getString("customer_crop"), rs.getDate("date")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customers;
	}

	@Override
	public void deleteCustomer(int customerId) {
		String query = "DELETE FROM Customer WHERE customer_id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, customerId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateCustomer(Customer customer) {
		String query = "UPDATE Customer SET customer_name = ?, customer_address = ?, customer_contact = ?, customer_crop = ? WHERE customer_id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, customer.getName());
			stmt.setString(2, customer.getAddress());
			stmt.setString(3, customer.getContact());
			stmt.setString(4, customer.getCrop());
			stmt.setInt(5, customer.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
