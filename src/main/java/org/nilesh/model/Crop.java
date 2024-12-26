package org.nilesh.model;

public class Crop {
    private int cropId;
    private int customerId;
    private String cropName;
    private int firstQualityBags;
    private int secondQualityBags;
    private int thirdQualityBags;
    private int fourthQualityBags;
    private int fifthQualityBags;

    public Crop(int cropId, int customerId, String cropName, int firstQualityBags, int secondQualityBags, int thirdQualityBags, int fourthQualityBags, int fifthQualityBags) {
        this.cropId = cropId;
        this.customerId = customerId;
        this.cropName = cropName;
        this.firstQualityBags = firstQualityBags;
        this.secondQualityBags = secondQualityBags;
        this.thirdQualityBags = thirdQualityBags;
        this.fourthQualityBags = fourthQualityBags;
        this.fifthQualityBags = fifthQualityBags;
    }

    public int getCropId() {
        return cropId;
    }

    public void setCropId(int cropId) {
        this.cropId = cropId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public int getFirstQualityBags() {
        return firstQualityBags;
    }

    public void setFirstQualityBags(int firstQualityBags) {
        this.firstQualityBags = firstQualityBags;
    }

    public int getSecondQualityBags() {
        return secondQualityBags;
    }

    public void setSecondQualityBags(int secondQualityBags) {
        this.secondQualityBags = secondQualityBags;
    }

    public int getThirdQualityBags() {
        return thirdQualityBags;
    }

    public void setThirdQualityBags(int thirdQualityBags) {
        this.thirdQualityBags = thirdQualityBags;
    }

    public int getFourthQualityBags() {
        return fourthQualityBags;
    }

    public void setFourthQualityBags(int fourthQualityBags) {
        this.fourthQualityBags = fourthQualityBags;
    }

    public int getFifthQualityBags() {
        return fifthQualityBags;
    }

    public void setFifthQualityBags(int fifthQualityBags) {
        this.fifthQualityBags = fifthQualityBags;
    }

    @Override
    public String toString() {
        return "Crop ID: " + cropId +
                "\nCustomer ID: " + customerId +
                "\nCrop Name: " + cropName +
                "\nFirst Quality Bags: " + firstQualityBags +
                "\nSecond Quality Bags: " + secondQualityBags +
                "\nThird Quality Bags: " + thirdQualityBags +
                "\nFourth Quality Bags: " + fourthQualityBags +
                "\nFifth Quality Bags: " + fifthQualityBags;
    }
}