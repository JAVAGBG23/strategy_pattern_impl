package com.example.StrategyPattern.repository;

import com.example.StrategyPattern.models.Auction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuctionRepository extends MongoRepository<Auction, String> {
}
