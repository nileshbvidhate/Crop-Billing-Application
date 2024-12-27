package org.nilesh.service;

import org.nilesh.model.Price;
import org.nilesh.repository.PriceRepository;
import org.nilesh.repository.PriceRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

public class PriceService {
    private PriceRepository priceRepository;

    public PriceService() {
        this.priceRepository = new PriceRepositoryImpl();
    }

    // Add Price Record
    public void addPrice(Price price) throws SQLException {
        priceRepository.addPrice(price);
    }

    // Get Price Records by Customer and Crop ID
    public List<Price> getPricesById(int customerId, int cropId) throws SQLException {
        return priceRepository.getPricesById(customerId, cropId);
    }

    // Update Price Record
    public void updatePrice(Price price) throws SQLException {
        priceRepository.updatePrice(price);
    }

}
