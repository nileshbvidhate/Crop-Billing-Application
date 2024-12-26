package org.nilesh.model;

public class PaymentReceipt {
    private int receiptId;
    private int customerId;
    private int cropId;
    private int qualityId;
    private int qualityWeight;
    private int rate;
    private int qualityAmount;
    private int porteringCharge;
    private int measuringCharge;
    private int vehicleRent;
    private int otherCharge;
    private int totalCharge;
    private int finalPayableAmount;

    // Constructor
    public PaymentReceipt(int receiptId, int customerId, int cropId, int qualityId, int qualityWeight, int rate, int qualityAmount, int porteringCharge, int measuringCharge, int vehicleRent, int otherCharge, int totalCharge, int finalPayableAmount) {
        this.receiptId = receiptId;
        this.customerId = customerId;
        this.cropId = cropId;
        this.qualityId = qualityId;
        this.qualityWeight = qualityWeight;
        this.rate = rate;
        this.qualityAmount = qualityAmount;
        this.porteringCharge = porteringCharge;
        this.measuringCharge = measuringCharge;
        this.vehicleRent = vehicleRent;
        this.otherCharge = otherCharge;
        this.totalCharge = totalCharge;
        this.finalPayableAmount = finalPayableAmount;
    }

    // Getters and Setters
    public int getReceiptId() { return receiptId; }
    public void setReceiptId(int receiptId) { this.receiptId = receiptId; }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public int getCropId() { return cropId; }
    public void setCropId(int cropId) { this.cropId = cropId; }

    public int getQualityId() { return qualityId; }
    public void setQualityId(int qualityId) { this.qualityId = qualityId; }

    public int getQualityWeight() { return qualityWeight; }
    public void setQualityWeight(int qualityWeight) { this.qualityWeight = qualityWeight; }

    public int getRate() { return rate; }
    public void setRate(int rate) { this.rate = rate; }

    public int getQualityAmount() { return qualityAmount; }
    public void setQualityAmount(int qualityAmount) { this.qualityAmount = qualityAmount; }

    public int getPorteringCharge() { return porteringCharge; }
    public void setPorteringCharge(int porteringCharge) { this.porteringCharge = porteringCharge; }

    public int getMeasuringCharge() { return measuringCharge; }
    public void setMeasuringCharge(int measuringCharge) { this.measuringCharge = measuringCharge; }

    public int getVehicleRent() { return vehicleRent; }
    public void setVehicleRent(int vehicleRent) { this.vehicleRent = vehicleRent; }

    public int getOtherCharge() { return otherCharge; }
    public void setOtherCharge(int otherCharge) { this.otherCharge = otherCharge; }

    public int getTotalCharge() { return totalCharge; }
    public void setTotalCharge(int totalCharge) { this.totalCharge = totalCharge; }

    public int getFinalPayableAmount() { return finalPayableAmount; }
    public void setFinalPayableAmount(int finalPayableAmount) { this.finalPayableAmount = finalPayableAmount; }
}
