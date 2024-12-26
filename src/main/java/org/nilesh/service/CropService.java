package org.nilesh.service;

import org.nilesh.model.Crop;
import org.nilesh.repository.CropRepository;
import org.nilesh.repository.CropRepositoryImpl;

import java.util.List;

public class CropService {

    private final CropRepository cropRepository;

    // Constructor to initialize the repository
    public CropService() {
        this.cropRepository = new CropRepositoryImpl(); // Using the repository implementation
    }

    // Method to add a new crop
    public int addCrop(Crop crop) {
        if (crop == null) {
            throw new IllegalArgumentException("Crop cannot be null");
        }
        return cropRepository.addCrop(crop);
    }

    // Method to get a crop by its crop_id and customer_id
    public Crop getCropById(int cropId, int customerId) {
        if (cropId <= 0 || customerId <= 0) {
            throw new IllegalArgumentException("Invalid crop ID or customer ID");
        }
        return cropRepository.getCropById(cropId, customerId);
    }

    // Method to get all crops for a given customer ID
    public List<Crop> getAllCropsByCustomerId(int customerId) {
        if (customerId <= 0) {
            throw new IllegalArgumentException("Invalid customer ID");
        }
        return cropRepository.getAllCropsByCustomerId(customerId);
    }

    // Method to delete a crop by crop_id and customer_id
    public boolean deleteCrop(int cropId, int customerId) {
        if (cropId <= 0 || customerId <= 0) {
            throw new IllegalArgumentException("Invalid crop ID or customer ID");
        }
        return cropRepository.deleteCrop(cropId, customerId);
    }

    // Method to update crop details
    public void updateCrop(Crop crop) {
        if (crop == null || crop.getCropId() <= 0 || crop.getCustomerId() <= 0) {
            throw new IllegalArgumentException("Invalid crop data");
        }
        cropRepository.updateCrop(crop);
    }
}
