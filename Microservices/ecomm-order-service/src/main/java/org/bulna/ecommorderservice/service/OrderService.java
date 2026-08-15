package org.bulna.ecommorderservice.service;

import org.bulna.ecommorderservice.client.InventoryClient;
import org.bulna.ecommorderservice.dto.Inventory;
import org.bulna.ecommorderservice.exception.MyCustomRuntimeException;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Service
public class OrderService {
    private final InventoryClient inventoryClient;
    private final RestTemplate restTemplate;
    private final RestClient restClient;
    private final DiscoveryClient discoveryClient;
    public OrderService(InventoryClient inventoryClient, RestTemplate restTemplate, RestClient restClient, DiscoveryClient discoveryClient) {
        this.inventoryClient = inventoryClient;
        this.restTemplate = restTemplate;
        this.restClient = restClient;
        this.discoveryClient = discoveryClient;
    }

    public String placeOrder(Long productId){
        //TODO call inventory service to check the stock
        /* for RestTemplate we have to write below code and also the loadbalancer code to get the uri and loadbalanced it but FeignClient will automatically do all this
        List<ServiceInstance> instances = discoveryClient.getInstances("ecomm-inventory-service");
        ServiceInstance serviceInstance = instances.get(0);
        URI uri = serviceInstance.getUri();*/
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
