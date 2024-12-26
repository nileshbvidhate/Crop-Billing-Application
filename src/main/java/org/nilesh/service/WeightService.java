package org.nilesh.service;

import org.nilesh.model.Weight;
import org.nilesh.repository.WeightRepository;
import org.nilesh.repository.WeightRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

public class WeightService {
    private WeightRepository weightRepository;

    public WeightService() {
        this.weightRepository = new WeightRepositoryImpl();
    }

    // Add Weight Record
    public void addWeight(Weight weight) throws SQLException {
        weightRepository.addWeight(weight);
    }

    // Get All Weight Records
    public List<Weight> getWeightsById(int customerId,int cropId) throws SQLException {
        return weightRepository.getWeightsById(customerId,cropId);
    }

    // Delete Weight Record
    public void removeWeight(int customerId, int cropId) throws SQLException {
        weightRepository.deleteWeight(customerId, cropId);
    }
}
