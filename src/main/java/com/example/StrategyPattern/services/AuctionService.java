package com.example.StrategyPattern.services;

import com.example.StrategyPattern.models.Auction;
import com.example.StrategyPattern.repository.AuctionRepository;
import com.example.StrategyPattern.strategy.AuctionFilterStrategy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuctionService {
    private AuctionRepository auctionRepository;

    public AuctionService(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }

    // skapa en auction
    public Auction createAuction(Auction auction) {
        return  auctionRepository.save(auction);
    }

    // filtrera auktioner
    public List<Auction> filterAuctionsFromMap(Map<String, String> filters) {
        List<Auction> auctions = auctionRepository.findAll();

        List<AuctionFilterStrategy> strategies = new ArrayList<>();

        if (filters.containsKey("category")) {
            strategies.add(filterByCategory(filters.get("category")));
        }
        if (filters.containsKey("title")) {
            strategies.add(filterByTitle(filters.get("title")));
        }
        if (filters.containsKey("minPrice") && filters.containsKey("maxPrice")) {
            strategies.add(filterByPriceRange(
                    Double.parseDouble(filters.get("minPrice")),
                    Double.parseDouble(filters.get("maxPrice"))
            ));
        }
        return auctions.stream()
                .filter(auction -> strategies.stream().allMatch(strategy -> strategy.filter(auction)))
                .collect(Collectors.toList());
    }


    private AuctionFilterStrategy filterByCategory(String category) {
        return auction -> auction.getCategory().contains(category);
    }

    private AuctionFilterStrategy filterByTitle(String title) {
        return auction -> auction.getTitle().toLowerCase().contains(title.toLowerCase());
    }

    private AuctionFilterStrategy filterByPriceRange(double minPrice, double maxPrice) {
        return auction -> auction.getPrice() >= minPrice && auction.getPrice() <= maxPrice;
    }
}
