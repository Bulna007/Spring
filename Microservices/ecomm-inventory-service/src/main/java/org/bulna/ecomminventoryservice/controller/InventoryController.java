package org.bulna.ecomminventoryservice.controller;

import org.bulna.ecomminventoryservice.model.Inventory;
import org.bulna.ecomminventoryservice.service.InventoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("{productId}")
    public Inventory checkInventory(@PathVariable Long productId) throws InterruptedException {
        //Thread.sleep(15000);
        System.out.println("Checking Inventory for ProductId: "+productId);
        return inventoryService.checkStock(productId);
    }

    @PostMapping
    public String addProduct(@RequestBody Inventory inventory){
        return inventoryService.addProduct(inventory);
    }

    @PutMapping
    public String updateProduct(@RequestBody Inventory inventory){
        return inventoryService.updateProduct(inventory);
    }
}
