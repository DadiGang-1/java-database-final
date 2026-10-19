package com.project.code.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.code.Model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
    List<Product> findAll();
    List<Product> findByCategory(String category);
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);
    Product findBySku(String sku);
    Product findByName(String name);
    @Query("SELECT i.product FROM Inventory i WHERE i.store.id = :storeId AND LOWER(i.product.name) LIKE LOWER(CONCAT('%', :pname, '%'))")
    List<Product> findByNameLike(Long storeId, String pname);

}
