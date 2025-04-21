package models;

import java.time.LocalDateTime;
import util.AuctionIdGenerator;
import java.time.Duration;

public class Auction {
    private final String id;
    private final Product product;
    private final Seller seller;
    private final BidHistory bidHistory;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private AuctionStatus status;
    private final double startPrice;

    public Auction(Product product, Seller seller, LocalDateTime startTime1, LocalDateTime endTime1, double startPrice)
    {
        this.id = AuctionIdGenerator.generate_id();
        this.product = product;
        this.seller = seller;
        this.bidHistory = new BidHistory();
        this.startTime = startTime1;
        this.endTime = endTime1;
        this.status = AuctionStatus.OPEN;
        this.startPrice = startPrice;
    }
    //getteriiiiii
    public String get_id()
    {
        return this.id;
    }

    public Product get_product()
    {
        return this.product;
    }

    public Seller get_seller()
    {
        return this.seller;
    }

    public LocalDateTime get_start_time()
    {
        return this.startTime;
    }

    public LocalDateTime get_end_time()
    {
        return this.endTime;
    }

    public AuctionStatus get_auction_status()
    {
        return this.status;
    }

    public BidHistory get_bid_history()
    {
        return this.bidHistory;
    }

    public double get_start_price()
    {
        return this.startPrice;
    }

    //functii imp
    public void close_auction()
    {
        this.status = AuctionStatus.CLOSED;
    }

    public boolean is_open()
    {
        return this.status == AuctionStatus.OPEN;
    }

    public boolean is_expired()
    {
        return LocalDateTime.now().isAfter(endTime);
    }

    public Duration get_remaining_time()
    {
        return Duration.between(LocalDateTime.now(), endTime);
    }

    public void place_bid(Bid bid)
    {
        if (status == AuctionStatus.OPEN)
        {
            this.bidHistory.add_bid(bid);
        }
    }

    public Bid get_biggest_bid()
    {
        return this.bidHistory.get_biggest_bid();
    }

    @Override
    public String toString()
    {
        return "Auction " + this.id + this.product.get_name() + " (" + this.status + ") - " + this.seller + "; starting price: " + this.startPrice;
    }
}