package com.example.StrategyPattern.repository;

import com.example.StrategyPattern.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@Component
public class CustomProductRepositoryImpl implements CustomProductRepository{
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Product> findProductsWithFilters(Map<String, String> filters) {
        Query query = new Query();
        List<Consumer<Query>> criteriaList = new ArrayList<>();

        addCriteria(filters.get("category"), q -> q.addCriteria(Criteria.where("category").in(filters.get("category"))), criteriaList);
        addCriteria(filters.get("title"), q -> q.addCriteria(Criteria.where("title").regex(filters.get("title"), "i")), criteriaList);
        addPriceCriteria(filters.get("minPrice"), filters.get("maxPrice"), criteriaList);

        criteriaList.forEach(consumer -> consumer.accept(query));

        return mongoTemplate.find(query, Product.class);
    }

    private <T> void addCriteria(T filterValue, Consumer<Query> criteriaFunction, List<Consumer<Query>> criteriaList) {
        if(filterValue != null) {
            criteriaList.add(criteriaFunction);
        }
    }

    private void addPriceCriteria(String minPrice, String maxPrice, List<Consumer<Query>> criteriaList) {
        try {
            if(minPrice != null && maxPrice !=null) {
                double min = Double.parseDouble(minPrice);
                double max = Double.parseDouble(maxPrice);
                criteriaList.add(q -> q.addCriteria(
                        Criteria.where("price")
                                .gte(min)
                                .lte(max)
                ));
            }
        } catch (NumberFormatException e) {
            System.out.println("Felaktig formatering av prisvärde");
        }
    }
}
