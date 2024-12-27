package org.nilesh.repository;

import org.nilesh.model.Price;
import java.util.List;

public interface PriceRepository {

    // Add a new price record
    void addPrice(Price price);

    // Get all price records for a specific customer and crop
    List<Price> getPricesById(int customerId, int cropId);

    // Update price record
    void updatePrice(Price price);

    // Delete price record by customer and crop ID
    boolean deletePrice(int customerId, int cropId);
}
