package org.nilesh.repository;

import org.nilesh.model.Weight;
import java.util.List;

public interface WeightRepository {

    // Add a new weight record
    void addWeight(Weight weight);

    // Get all weight records for a specific customer
    List<Weight> getWeightsById(int customerId,int cropId);

    // Update weight record
    void updateWeight(Weight weight);
    
    // Delete weight record by customer and crop ID
    boolean deleteWeight(int customerId, int cropId);
}
