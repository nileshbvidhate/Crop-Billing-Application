package org.nilesh.repository;

import org.nilesh.model.Customer;
import java.util.List;

public interface CustomerRepository {

	int addCustomer(Customer customer);
	Customer getCustomerById(int customerId);
	Customer getCustomerByName(String customerName);
	List<Customer> getAllCustomers();
	void deleteCustomer(int customerId);
	void updateCustomer(Customer customer);
}
