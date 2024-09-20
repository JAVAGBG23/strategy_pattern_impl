package com.example.StrategyPattern.repository;

import com.example.StrategyPattern.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String>, CustomProductRepository {
}
