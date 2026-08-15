package org.bulna.ecomminventoryservice.service;

import org.bulna.ecomminventoryservice.model.Inventory;
import org.bulna.ecomminventoryservice.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    public Inventory checkStock(Long productId){
        Optional<Inventory> inventory = inventoryRepository.findById(productId);
        return inventory.get();
    }

    public String addProduct(Inventory inventory){
        inventoryRepository.save(inventory);
        return "Product Added";
    }

    public String updateProduct(Inventory inventory){
        return "";
    }
}
