package com.project.code.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.code.Model.Inventory;

import jakarta.transaction.Transactional;

@Repository 
public interface InventoryRepository extends JpaRepository<Inventory, Long>{
    @Query("SELECT i FROM inventory i WHERE i.product.id = :productId and i.store.id = :storeId")
    Inventory findByProductIdandStoreId(Long productId, Long storeId);

    List<Inventory> findByStore_Id(Long storeId);

    @Modifying 
    @Transactional 
    @Query("DELETE FROM inventory i WHERE i.product.id  =:productId")
    void deleteByProductId(Long productId);
}
