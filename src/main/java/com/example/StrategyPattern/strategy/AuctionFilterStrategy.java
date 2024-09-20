package com.example.StrategyPattern.strategy;

import com.example.StrategyPattern.models.Auction;

@FunctionalInterface
public interface AuctionFilterStrategy {
    boolean filter(Auction auction);
}
