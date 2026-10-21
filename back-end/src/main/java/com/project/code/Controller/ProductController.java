package com.project.code.Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.code.Model.Product;
import com.project.code.Repo.InventoryRepository;
import com.project.code.Repo.OrderItemRepository;
import com.project.code.Repo.ProductRepository;
import com.project.code.Service.ServiceClass;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;



@RestController 
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired 
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ServiceClass serviceClass;
    @Autowired
    private InventoryRepository inventoryRepository;

    @PostMapping 
    public Map<String, String> addProduct(@RequestBody Product product) {
        Map<String, String> map = new HashMap<>();
        if(!serviceClass.validateProduct(product)) {
            map.put("message", "Product already present in database");
            return map;
        }
        
        try {
            productRepository.save(product);
            map.put("message", "Product added successfully");
        } catch (DataIntegrityViolationException e) {
            map.put("message", "SKU should be unique");
        }
        return map;
    }

    @GetMapping("/product/{id}")
    public Map<String, Object> getProductbyId(@PathVariable Long id) {
        Map<String, Object> map = new HashMap<>();
        Optional<Product> result = productRepository.findById(id);
        map.put("product", result);
        return map;
    }
    
    @PutMapping
    public Map<String, String> updateProduct(@RequestBody Product product) {
        Map<String, String> map = new HashMap<>();
        
        try {
            productRepository.save(product);
            map.put("message", "Data upated sucessfully");
        } catch (Exception e) {
            map.put("message", "Error occured");
        }

        return map;
    }

    @GetMapping("/category/{name}/{category}") 
    public Map<String, Object> filterbyCategoryProduct(@PathVariable String name, @PathVariable String category){
        Map<String, Object> map = new HashMap<>();

        if(name.equals("null")) {
            map.put("products", productRepository.findByCategory(category));
            return map;
        } else if(category.equals("null")) {
            map.put("products", productRepository.findProductBySubName(name));
            return map;
        }
        map.put("products", productRepository.findProductBySubNameAndCategory(name, category));
        return map;
    }

    @GetMapping
    public Map<String, Object> listProduct(){
        Map<String, Object> map = new HashMap<>();
        map.put("Products",productRepository.findAll());
        return map;
    }
    
    @GetMapping("filter/{category}/{storeid}")
    public Map<String, Object> getProductbyCategoryAndStoreId(@PathVariable String category, @PathVariable Long storeid) {
        Map<String, Object> map = new HashMap<>();

        map.put("Products",productRepository.findByCategoryAndStoreId(storeid, category));
        return map;
    }

    @DeleteMapping("/{id}")
    public Map<String, String> deleteProduct(@PathVariable Long id) {
        Map<String, String> map = new HashMap<>();

        if(!serviceClass.validateProductId(id)) {
            map.put("message", "Id " + id + " not present in database");
            return map;
        }
        inventoryRepository.deleteByProductId(id);
        orderItemRepository.deleteByProductId(id);
        productRepository.deleteById(id);
        
        map.put("message", "Deleted product successfully with id: " + id);
        return map;
    }

    @GetMapping("/searchProduct/{name}")
    public Map<String, Object> searchProduct(@PathVariable String name) {
        Map<String, Object> map = new HashMap<>();
        map.put("Products", productRepository.findProductBySubName(name));
        return map;
    }

  
    
}
