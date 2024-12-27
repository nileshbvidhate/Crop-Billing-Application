package org.nilesh.model;
import java.sql.Date;

public class Data {

    private int customerId;
    private String customerName;
    private String customerAddress;
    private String customerContact;
    private String customerCrop;
    private int cropWeight;
    private int cropHighestRate;
    private int payment;
    private Date date;

    // Constructor
    public Data(int customerId, String customerName, String customerAddress, String customerContact, 
                String customerCrop, int cropWeight, int cropHighestRate, int payment, Date date) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerContact = customerContact;
        this.customerCrop = customerCrop;
        this.cropWeight = cropWeight;
        this.cropHighestRate = cropHighestRate;
        this.payment = payment;
        this.date = date;
    }

    // Getters and Setters
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(String customerContact) {
        this.customerContact = customerContact;
    }

    public String getCustomerCrop() {
        return customerCrop;
    }

    public void setCustomerCrop(String customerCrop) {
        this.customerCrop = customerCrop;
    }

    public int getCropWeight() {
        return cropWeight;
    }

    public void setCropWeight(int cropWeight) {
        this.cropWeight = cropWeight;
    }

    public int getCropHighestRate() {
        return cropHighestRate;
    }

    public void setCropHighestRate(int cropHighestRate) {
        this.cropHighestRate = cropHighestRate;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Customer ID         = " + customerId +
               "\nCustomer Name       = " + customerName +
               "\nCustomer Address    = " + customerAddress +
               "\nCustomer Contact    = " + customerContact +
               "\nCustomer Crop       = " + customerCrop +
               "\nCrop Weight         = " + cropWeight +
               "\nCrop Highest Rate   = " + cropHighestRate +
               "\nPayment             = " + payment +
               "\nDate                = " + date;
    }

}
