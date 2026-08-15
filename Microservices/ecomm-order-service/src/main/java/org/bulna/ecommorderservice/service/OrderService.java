package org.bulna.ecommorderservice.service;

import org.bulna.ecommorderservice.client.InventoryClient;
import org.bulna.ecommorderservice.dto.Inventory;
import org.bulna.ecommorderservice.exception.MyCustomRuntimeException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {
    private final InventoryClient inventoryClient;
    private final RestTemplate restTemplate;
    private final RestClient restClient;
    public OrderService(InventoryClient inventoryClient, RestTemplate restTemplate, RestClient restClient) {
        this.inventoryClient = inventoryClient;
        this.restTemplate = restTemplate;
        this.restClient = restClient;
    }

    public String placeOrder(Long productId){
        //TODO call inventory service to check the stock

        //RestTemplate
        /*String response = restTemplate.getForObject(
                "http://localhost:8081/inventory/"+productId,
                String.class
        );*/

        /*ResponseEntity<Inventory> entity = restClient.get()
                .uri("http://localhost:8081/inventory/{productId}",productId)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,((request, response) -> {
                    throw new MyCustomRuntimeException(response.getStatusCode(), response.getHeaders());
                }))
                .toEntity(Inventory.class);*/

        //Simple RestClient Get
        /*ResponseEntity<Inventory> entity = restClient.get()
                .uri("http://localhost:8081/inventory/{productId}",productId)
                .retrieve()
                .toEntity(Inventory.class);*/

        Inventory inventory = inventoryClient.getInventory(productId);
        int quantity = inventory.getQuantity();
        updateInventory(inventory);
        return quantity > 0 ?
                "Order Placed" :
                "Product out of stock";
    }

    private void updateInventory(Inventory inventory) {
        inventory.setQuantity(inventory.getQuantity() - 1);
        /*restClient.post()
                .uri("http://localhost:8081/inventory")
                .body(inventory)
                .retrieve()
                .toBodilessEntity();*/
        inventoryClient.updateInventory(inventory);
    }
}
