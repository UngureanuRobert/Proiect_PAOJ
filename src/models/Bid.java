package models;

import java.time.LocalDateTime;
import java.util.UUID;

public class Bid {
    private final String id;
    private final Buyer bidder;
    private final Auction auction;
    private double amount;
    private final LocalDateTime time;

    public Bid(Buyer bidder, Auction auction, double amount)
    {
        this.id = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.bidder = bidder;
        this.auction = auction;
        this.amount = amount;
        this.time = LocalDateTime.now();
    }

    public Bid(String id, String auction_id, String buyer_id, double amount, LocalDateTime time)
    {
        this.id = id;
        this.auction = new Auction(auction_id, "", "", LocalDateTime.now(), LocalDateTime.now(), AuctionStatus.OPEN, 0.0); //dummy
        this.bidder = new Buyer(buyer_id, "", ""); //dummy
        this.amount = amount;
        this.time = time;
    }

    public String get_id()
    {
        return this.id;
    }

    public Buyer get_bidder()
    {
        return this.bidder;
    }

    public Auction get_auction()
    {
        return this.auction;
    }

    public double get_amount()
    {
        return this.amount;
    }

    public LocalDateTime get_time()
    {
        return this.time;
    }

    public void set_amount(double amount)
    {
        this.amount = amount;
    }

    @Override
    public String toString()
    {
        return "Bid " + this.id + " - Bidder: " + this.bidder.get_name() + " - " + this.amount + " - " + this.time;
    }
}
