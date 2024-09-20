package com.example.StrategyPattern.controllers;

import com.example.StrategyPattern.models.Auction;
import com.example.StrategyPattern.services.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auction")
public class AuctionController {
    @Autowired
    private AuctionService auctionService;

    @GetMapping("/filter")
    public List<Auction> filterAuctions(@RequestParam Map<String, String> filters) {
        return auctionService.filterAuctionsFromMap(filters);
    }
}
