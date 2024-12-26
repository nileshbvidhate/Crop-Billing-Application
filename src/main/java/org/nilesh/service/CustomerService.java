package org.nilesh.service;

import org.nilesh.model.Customer;
import org.nilesh.repository.CustomerRepository;
import org.nilesh.repository.CustomerRepositoryImpl;

import java.util.List;

public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService() {
        this.customerRepository = new CustomerRepositoryImpl();
    }

    // Add a new customer
    public int addCustomer(Customer customer) {
        return customerRepository.addCustomer(customer);
    }

    // Get a customer by their ID
    public Customer getCustomerById(int customerId) {
        return customerRepository.getCustomerById(customerId);
    }

    // Get a customer by their name
    public Customer getCustomerByName(String customerName) {
        return customerRepository.getCustomerByName(customerName);
    }

    // Get all customers
    public List<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }

    // Delete a customer by their ID
    public void deleteCustomer(int customerId) {
        customerRepository.deleteCustomer(customerId);
    }

    // Update customer details
    public void updateCustomer(Customer customer) {
        customerRepository.updateCustomer(customer);
    }
}
