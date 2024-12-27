package org.nilesh.service;

import org.nilesh.model.Data;
import org.nilesh.repository.DataRepository;
import java.util.List;

public class DataService {

    private DataRepository dataRepository;

    public DataService() {
        this.dataRepository = new DataRepository();  // Initialize the repository
    }

    // Get all data records
    public List<Data> getAllData() {
        return dataRepository.getAllData();
    }
}
