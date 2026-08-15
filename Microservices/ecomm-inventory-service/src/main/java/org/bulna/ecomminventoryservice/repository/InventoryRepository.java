package org.bulna.ecomminventoryservice.repository;

import org.bulna.ecomminventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
