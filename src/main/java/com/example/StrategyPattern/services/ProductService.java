package com.example.StrategyPattern.services;

import com.example.StrategyPattern.models.Product;
import com.example.StrategyPattern.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> filterProductsFromMap(Map<String, String> filters){
        return productRepository.findProductsWithFilters(filters);
    }

    // createProduct skapa en metod precis som vanligt
}
