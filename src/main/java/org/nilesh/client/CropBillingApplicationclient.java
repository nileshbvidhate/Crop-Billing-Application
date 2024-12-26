package org.nilesh.client;

import java.util.List;
import java.util.Scanner;

import org.nilesh.model.Crop;
import org.nilesh.model.CropManager;
import org.nilesh.model.Customer;
import org.nilesh.model.Weight;
import org.nilesh.service.CropService;
import org.nilesh.service.CustomerService;
import org.nilesh.service.WeightService;

import java.sql.Date;

public class CropBillingApplicationclient {

	public static void main(String[] args) {

		System.out.println(
				"------------------------Welcome to Niles Crop Billing Application !!! ------------------------");
		Scanner sobj = new Scanner(System.in);
		int iToken = 0;

		do {
			System.out.println("--------------------Use---------------------");
			System.out.println("1 : Add Customer to Cart");
			System.out.println("2 : Add Crop to Cart");
			System.out.println("3 : Print Weight Receipt");
			System.out.println("4 : Print Payment Reciept");
			System.out.println("5 : Search Customer By ID");
			System.out.println("6 : Search Customer by Name");
			System.out.println("7 : Delete Customer from Cart");
			System.out.println("8 : Delete Crop from Customer");
			System.out.println("9 : Add Customer to Data");
			System.out.println("10 : See All Data");
			System.out.println("11 : See All Customer");
			System.out.println("12 : Update Customer Details");
			System.out.println("13 : Update Crop Details");
			System.out.println("14 : See all crops");
			System.out.println("15 : Add Crop Weights");
			System.out.println("16 : Add Crop Rate :");
			System.out.println("0 : Exit the Application...");
			System.out.println("--------------------------------------------");

			System.out.println("Enter the Token : ");
			iToken = sobj.nextInt();

			switch (iToken) {
			case 0: {
				sobj.close();
				break;
			}

			case 1: {
				System.out.println("Adding Customer to Cart...");

				sobj.nextLine();
				System.out.print("Enter Customer Name: ");
				String name = sobj.nextLine();
				System.out.print("Enter Customer Address: ");
				String address = sobj.nextLine();
				System.out.print("Enter Customer Contact: ");
				String contact = sobj.nextLine();
				System.out.print("Enter Customer Crop: ");
				String crop = sobj.nextLine();
				System.out.print("Enter Date (YYYY-MM-DD): ");
				String dateStr = sobj.nextLine();
				Date date = Date.valueOf(dateStr);

				Customer customer = new Customer(0, name, address, contact, crop, date);
				CustomerService customerservice = new CustomerService();
				int id = customerservice.addCustomer(customer);
				System.out.println("Customer ID is : " + id);
				System.out.println("Customer added successfully!");
				break;
			}

			case 2: {
				System.out.println("Adding Crop to Cart...");
				CropService cropService = new CropService();

				System.out.print("Enter Customer ID: ");
				int customerId = sobj.nextInt();
				sobj.nextLine();

				System.out.print("Enter Crop Name: ");
				String cropName = sobj.nextLine();

				int cropId = CropManager.getCropIdByName(cropName);

				System.out.print("Enter First Quality Bags: ");
				int firstQualityBags = sobj.nextInt();
				System.out.print("Enter Second Quality Bags: ");
				int secondQualityBags = sobj.nextInt();
				System.out.print("Enter Third Quality Bags: ");
				int thirdQualityBags = sobj.nextInt();
				System.out.print("Enter Fourth Quality Bags: ");
				int fourthQualityBags = sobj.nextInt();
				System.out.print("Enter Fifth Quality Bags: ");
				int fifthQualityBags = sobj.nextInt();

				Crop crop = new Crop(cropId, customerId, cropName, firstQualityBags, secondQualityBags,
						thirdQualityBags, fourthQualityBags, fifthQualityBags);
				cropId = cropService.addCrop(crop);
				System.out.println("Crop ID is : " + cropId);
				System.out.println("Crop added successfully!");
				break;
			}

			case 3: {
			    System.out.println("---- Print Weight Receipt ----");
			    try {
			        System.out.print("Enter Customer ID: ");
			        int customerId = sobj.nextInt();
			        
			        System.out.print("Enter Crop ID: ");
			        int cropId = sobj.nextInt();
			        
			        WeightService weightService = new WeightService();
			        List<Weight> weightList = weightService.getWeightsById(customerId, cropId);
			        
			        if (weightList.isEmpty()) {
			            System.out.println("No weight record found for the given Customer ID and Crop ID.");
			        } else {
			        	System.out.println("\n--- Weight Receipt for ---");
			        	System.out.println("Customer ID : " + customerId);
		                System.out.println("Crop ID     : " + cropId);
		                System.out.println("Quality         Weight(kg)");
			            for (Weight weight : weightList) {
			                
			                System.out.print(weight.getQualityId()+"                  ");
			                System.out.println(weight.getWeight());
			                
			            }
		                System.out.println("-------------------------------");
			        }
			    } catch (Exception e) {
			        System.out.println("Error printing weight receipt: " + e.getMessage());
			    }
			    break;
			}

			case 4: {
				System.out.println("Generating Payment Receipt...");
				break;
			}

			case 5: {
				System.out.println("Searching Customer by CustomerID...");
				System.out.println("Enter the Customer ID :");
				int id = sobj.nextInt();

				CustomerService customerservice = new CustomerService();
				Customer cus = customerservice.getCustomerById(id);
				if (cus != null) {
					cus.toString();
					System.out.println("------------------Customer Detail-----------------");
					System.out.println(cus);
					System.out.println("--------------------------------------------------");
				} else {
					System.out.println("Customer not found");
				}
				break;
			}

			case 6: {
				System.out.println("Searching Customer by Name...");
				System.out.println("Enter the Customer Name :");
				sobj.nextLine();
				String name = sobj.nextLine();

				CustomerService customerservice = new CustomerService();
				Customer cus = customerservice.getCustomerByName(name);
				if (cus != null) {
					cus.toString();
					System.out.println("------------------Customer Detail-----------------");
					System.out.println(cus);
					System.out.println("--------------------------------------------------");
				} else {
					System.out.println("Sorry... Customer not found...");
				}

				break;
			}

			case 7: {
				System.out.println("Delete Customer from Cart...");
				System.out.println("Enter the Customer ID :");
				int id = sobj.nextInt();

				CustomerService customerservice = new CustomerService();
				Customer cus = customerservice.getCustomerById(id);

				// Check if customer exists
				if (cus != null) {
					// Display customer details
					System.out.println("------------------Customer Detail-----------------");
					System.out.println(cus); // Automatically calls toString() method of Customer
					System.out.println("--------------------------------------------------");

					// Prompt to confirm deletion
					System.out.println("Are you sure you want to delete this customer? (yes/no):");
					String confirmation = sobj.next();

					if ("yes".equalsIgnoreCase(confirmation)) {
						customerservice.deleteCustomer(id); // Call the delete method to remove the customer
						System.out.println("Customer deleted successfully.");
					} else {
						System.out.println("Customer deletion canceled.");
					}
				} else {
					System.out.println("Customer not found with ID: " + id);
				}
				break;
			}

			case 8:{
				System.out.println("Delete Specific Crop from Cart...");
				CropService cropService = new CropService();

				System.out.print("Enter Customer ID to Delete Crop: ");
				int customerId = sobj.nextInt();

				System.out.print("Enter Crop ID to Delete: ");
				int cropId = sobj.nextInt();

				boolean b = cropService.deleteCrop(cropId, customerId);
				if (b) {
					System.out.println("Crop is deleted Successfully.");
				} else {
					System.out.println("Crop not found.");
				}

				break;
			}

			case 9: {
				System.out.println("Add Customer to Data...");
				break;
			}

			case 10: {
				System.out.println("See All Data...");
				break;
			}

			case 11: {
				System.out.println("See All Customers");

				CustomerService customerservice = new CustomerService();
				List<Customer> cus = customerservice.getAllCustomers();
				if (cus != null && !cus.isEmpty()) {
					System.out.println("------------ All Customers ------------");
					for (Customer customer : cus) {
						System.out.println(customer); // This will automatically call the toString() method of the
														// Customer class
						System.out.println("--------------------------------------");
					}

				} else {
					System.out.println("No customers found.");
				}

				break;
			}

			case 12: {
				System.out.println("Updating Customer Details...");
				System.out.println("Enter the Customer ID to Update:");
				int id = sobj.nextInt();

				sobj.nextLine();

				CustomerService customerservice = new CustomerService();
				Customer cus = customerservice.getCustomerById(id); // Fetch the customer by ID

				if (cus != null) {
					// Display current customer details
					System.out.println("------------------Current Customer Detail-----------------");
					System.out.println(cus); // This will call toString() of the Customer class
					System.out.println("---------------------------------------------------------");

					// Ask for updated details
					System.out.println("Enter the new Customer Name (or press Enter to keep current):");
					String name = sobj.nextLine();
					if (!name.isEmpty()) {
						cus.setName(name);
					}

					System.out.println("Enter the new Customer Address (or press Enter to keep current):");
					String address = sobj.nextLine();
					if (!address.isEmpty()) {
						cus.setAddress(address);
					}

					System.out.println("Enter the new Customer Contact (or press Enter to keep current):");
					String contact = sobj.nextLine();
					if (!contact.isEmpty()) {
						cus.setContact(contact);
					}

					System.out.println("Enter the new Customer Crop (or press Enter to keep current):");
					String crop = sobj.nextLine();
					if (!crop.isEmpty()) {
						cus.setCrop(crop);
					}

					// Call update method to save changes
					customerservice.updateCustomer(cus); // Update customer in database

				} else {
					System.out.println("No customer found with ID: " + id);
				}
				break;
			}
			
			case 13: {
				System.out.println("Updating Crop Details...");
				CropService cropService = new CropService();

				System.out.print("Enter Crop ID customer Id to Update: ");
				int cropId = sobj.nextInt();
				int customerId = sobj.nextInt();
				
				Crop crop = cropService.getCropById(cropId, customerId);

				if (crop != null) {
					System.out.println("------------------Current Crop Detail-----------------\n" + crop + "\n---------------------------------------------------------");
					sobj.nextLine();

					System.out.print("Enter new Crop Name (or press Enter to keep current): ");
					String cropName = sobj.nextLine();
					if (!cropName.isEmpty()) {
						crop.setCropName(cropName);
					}

					System.out.print("Enter new First Quality Bags: ");
					crop.setFirstQualityBags(sobj.nextInt());

					System.out.print("Enter new Second Quality Bags: ");
					crop.setSecondQualityBags(sobj.nextInt());

					cropService.updateCrop(crop);
					System.out.println("Crop updated successfully.");
				} else {
					System.out.println("Crop not found.");
				}
				break;
			}
			case 14: {
				System.out.println("See All Crops");
				CropService cropService = new CropService();
				System.out.println("Enter the customer ID : ");
				int id = sobj.nextInt();

				List<Crop> crops = cropService.getAllCropsByCustomerId(id);

				if (crops != null && !crops.isEmpty()) {
					System.out.println("------------ All Crops ------------");
					for (Crop crop : crops) {
						System.out.println(crop);
						System.out.println("----------------------------------");
					}
				} else {
					System.out.println("No crops found.");
				}
				break;
			}
			
			case 15:
			    System.out.println("---- Add Weight Record ----");
			    try {
			        System.out.print("Enter Customer ID: ");
			        int customerId = sobj.nextInt();
			        
			        System.out.print("Enter Crop ID: ");
			        int cropId = sobj.nextInt();
			        
			        System.out.print("Enter Quality ID: ");
			        int qualityId = sobj.nextInt();
			        
			        sobj.nextLine();  // Consume newline after nextInt()
			        String ch;
			        
			        WeightService weightService = new WeightService();  // Move outside the loop
			        
			        do {
			            System.out.print("Enter Weight (kg): ");
			            int weightValue = sobj.nextInt();
			            sobj.nextLine();  // Consume newline after nextInt()
			            
			            Weight weight = new Weight(customerId, cropId, qualityId, weightValue);
			            weightService.addWeight(weight);
			            
			            System.out.println("Weight record added successfully.");
			            
			            System.out.println("Do you want to add more weight? (y/n)");
			            ch = sobj.nextLine();
			            
			        } while (ch.equalsIgnoreCase("y"));  // Use equalsIgnoreCase to handle 'Y' or 'y'
			        
			    } catch (Exception e) {
			        System.out.println("Error adding weight record: " + e.getMessage());
			    }
			    break;

			}

		} while (iToken != 0);

		System.out.println(
				"---------------------Thank you for visiting Niles Crop Billing Application-------------------");

	}

}
