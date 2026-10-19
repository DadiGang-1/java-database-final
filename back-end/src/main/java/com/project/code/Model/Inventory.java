package com.project.code.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Inventory {

   @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @ManyToOne()
   @JsonBackReference("inventory-product")
   @JoinColumn(name = "product_id", nullable = false)
   private Product product;

   @ManyToOne()
   @JsonBackReference("inventory-store")
   @JoinColumn(name = "store_id", nullable = false)
   private Store store;

   private Integer stockLevel;

   public Inventory() {}
   public Inventory(Product product, Store store, Integer stockLevel) {
      this.product = product;
      this.store = store;
      this.stockLevel = stockLevel;
   }

   public Long getId() {
       return id;
   }
   public Product getProduct() {
       return product;
   }
   public Store getStore() {
       return store;
   }
   public Integer getStockLevel() {
       return stockLevel;
   }

   public void setId(Long id) {
       this.id = id;
   }
   public void setProduct(Product product) {
       this.product = product;
   }
   public void setStore(Store store) {
       this.store = store;
   }
   public void setStockLevel(Integer stockLevel) {
       this.stockLevel = stockLevel;
   }

}

