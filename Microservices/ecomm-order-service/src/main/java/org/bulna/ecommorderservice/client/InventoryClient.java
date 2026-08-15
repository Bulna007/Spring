package org.bulna.ecommorderservice.client;

import org.bulna.ecommorderservice.client.config.FeignConfig;
import org.bulna.ecommorderservice.client.config.InventoryFeignClientConfig;
import org.bulna.ecommorderservice.dto.Inventory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ecomm-inventory-service",
            configuration = InventoryFeignClientConfig.class)
public interface InventoryClient {
    @GetMapping("/inventory/{productId}")
    Inventory getInventory(@PathVariable Long productId);
    @PostMapping("/inventory")
    String updateInventory(@RequestBody Inventory inventory);
}
