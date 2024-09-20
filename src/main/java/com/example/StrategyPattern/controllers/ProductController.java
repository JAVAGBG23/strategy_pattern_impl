package com.example.StrategyPattern.controllers;

import com.example.StrategyPattern.models.Product;
import com.example.StrategyPattern.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ProductService productService;


    @GetMapping("/filter")
    public List<Product> filterProducts(@RequestParam Map<String, String> filters) {
        return productService.filterProductsFromMap(filters);
    }

    // PostMapping gör exakt som ni brukar göra
}
