package org.nilesh.repository;

import org.nilesh.model.Crop;
import java.util.List;

public interface CropRepository {
    int addCrop(Crop crop);
    void updateCrop(Crop crop);
	boolean deleteCrop(int cropId, int customerId);
	List<Crop> getAllCropsByCustomerId(int customerId);
	Crop getCropById(int cropId, int customerId);
}