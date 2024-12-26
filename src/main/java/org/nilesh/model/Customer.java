package org.nilesh.model;

import java.sql.Date;

public class Customer {
    private int id;
    private String name;
    private String address;
    private String contact;
    private String crop;
    private Date date;

    // Constructor for creating a new customer (without ID)
    public Customer(int id, String name, String address, String contact, String crop, Date date) {
        this.id = id;
    	this.name = name;
        this.address = address;
        this.contact = contact;
        this.crop = crop;
        this.date = date;
    }

	// Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCrop() {
        return crop;
    }

    public void setCrop(String crop) {
        this.crop = crop;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return
                "ID      = " + this.id +
                "\nName    = " + name +
                "\nAddress = " + address + 
                "\nContact = " + contact + 
                "\nCrop    = " + crop + 
                "\nDate    = " + date 
                ;
    }
}
