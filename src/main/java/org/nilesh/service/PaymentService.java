package org.nilesh.service;

import org.nilesh.model.PaymentReceipt;
import org.nilesh.repository.PaymentReceiptRepository;
import org.nilesh.repository.PaymentReceiptRepositoryImpl;
import java.util.List;

public class PaymentService {

    private final PaymentReceiptRepository receiptRepository;

    // Constructor initializes the repository implementation
    public PaymentService() {
        this.receiptRepository = new PaymentReceiptRepositoryImpl();
    }

    // Add a new payment receipt
    public void generatePaymentReceipt(PaymentReceipt receipt) {
        if (receipt != null) {
            receiptRepository.updateReceiptByCustomerIdAndCropId(receipt.getCustomerId(),receipt.getCropId(),receipt);
            System.out.println("Payment receipt successfully recorded.");
        } else {
            System.out.println("Invalid payment receipt data.");
        }
    }

    // Fetch a single payment receipt by ID
    public List<PaymentReceipt> getPaymentReceiptByCustomerNameAndCropName(String customerName, String cropName) {
        List<PaymentReceipt> receipts = receiptRepository.getPaymentReceiptByCustomerNameAndCropName(customerName, cropName);
        if (receipts.isEmpty()) {
            System.out.println("No receipt found for ID: " + customerName);
        } else {
            System.out.println("Receipt found");
        }
        return receipts;
    }
    
    public boolean deletePaymentReceipt(String customerName, String cropName) {
        boolean isDeleted = receiptRepository.deletePaymentReceipt(customerName, cropName);
        if (isDeleted) {
            System.out.println("Payment receipt deleted successfully for Customer : " + customerName);
        } else {
            System.out.println("No receipt found for Customer: " + customerName + " and Crop : " + cropName);
        }
        return isDeleted;
    }


} 
