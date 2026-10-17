package org.bulna.ecomminventoryservice.repository;

import org.bulna.ecomminventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    // Single conditional UPDATE: the check and the decrement happen atomically in the database,
    // so two concurrent orders can never both take the last unit. Returns rows updated (0 or 1).
    @Modifying
    @Query("update Inventory i set i.quantity = i.quantity - 1 where i.productId = :productId and i.quantity > 0")
    int reserveOne(@Param("productId") Long productId);
}
