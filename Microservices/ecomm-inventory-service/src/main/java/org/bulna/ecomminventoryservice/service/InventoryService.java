package org.bulna.ecomminventoryservice.service;

import org.bulna.ecomminventoryservice.model.Inventory;
import org.bulna.ecomminventoryservice.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    public Inventory checkStock(Long productId){
        return inventoryRepository.findById(productId)
                .orElseThrow(() -> productNotFound(productId));
    }

    @Transactional
    public boolean reserveOne(Long productId){
        if (inventoryRepository.reserveOne(productId) == 1) {
            return true;
        }
        // 0 rows updated: either the product doesn't exist (404) or it's out of stock (false -> 409)
        if (!inventoryRepository.existsById(productId)) {
            throw productNotFound(productId);
        }
        return false;
    }

    public String addProduct(Inventory inventory){
        inventoryRepository.save(inventory);
        return "Product Added";
    }

    public String updateProduct(Inventory inventory){
        return "";
    }

    private ResponseStatusException productNotFound(Long productId){
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Product " + productId + " not found");
    }
}
