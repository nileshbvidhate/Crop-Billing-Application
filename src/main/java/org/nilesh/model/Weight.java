package org.nilesh.model;

public class Weight {
    private int customerId;
    private int cropId;
    private int qualityId;
    private int weight;

    // Constructor
    public Weight(int customerId, int cropId, int qualityId, int weight) {
        this.customerId = customerId;
        this.cropId = cropId;
        this.qualityId = qualityId;
        this.weight = weight;
    }

    // Getters and Setters
    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public int getCropId() { return cropId; }
    public void setCropId(int cropId) { this.cropId = cropId; }

    public int getQualityId() { return qualityId; }
    public void setQualityId(int qualityId) { this.qualityId = qualityId; }

    public int getWeight() { return weight; }
    public void setWeight(int weight) { this.weight = weight; }
}