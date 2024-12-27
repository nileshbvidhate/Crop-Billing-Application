package org.nilesh.repository;

import org.nilesh.model.PaymentReceipt;
import java.util.List;

public interface PaymentReceiptRepository {

    // Method to add a new payment receipt
    void generatePaymentReceipt(PaymentReceipt receipt);
    
    void updateReceiptByCustomerIdAndCropId(int customerId, int cropId, PaymentReceipt payRec);
    public boolean deletePaymentReceipt(String customerName, String cropName);
    // Method to get a payment receipt by receipt_id

	List<PaymentReceipt> getPaymentReceiptByCustomerNameAndCropName(String customerName, String cropName);

}
