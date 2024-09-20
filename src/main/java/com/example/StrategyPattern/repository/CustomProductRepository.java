package com.example.StrategyPattern.repository;

import com.example.StrategyPattern.models.Product;

import java.util.List;
import java.util.Map;

public interface CustomProductRepository {
    List<Product> findProductsWithFilters(Map<String, String> filters);
}
