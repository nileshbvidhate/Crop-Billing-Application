package org.nilesh.model;

public class Price {
    private int customerId;
    private int cropId;
    private int qualityId;
    private int rate;

    // Constructor
    public Price(int customerId, int cropId, int qualityId, int rate) {
        this.customerId = customerId;
        this.cropId = cropId;
        this.qualityId = qualityId;
        this.rate = rate;
    }

    // Getters and Setters
    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public int getCropId() { return cropId; }
    public void setCropId(int cropId) { this.cropId = cropId; }

    public int getQualityId() { return qualityId; }
    public void setQualityId(int qualityId) { this.qualityId = qualityId; }

    public int getRate() { return rate; }
    public void setRate(int rate) { this.rate = rate; }
}