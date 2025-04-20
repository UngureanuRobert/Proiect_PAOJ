package models;

import java.time.LocalDateTime;

public class Bid {
    private final Buyer bidder;
    private final double amount;
    private final LocalDateTime time;

    public Bid(Buyer bidder, double amount)
    {
        this.bidder = bidder;
        this.amount = amount;
        this.time = LocalDateTime.now();
    }

    public Buyer get_bidder()
    {
        return this.bidder;
    }

    public double get_amount()
    {
        return this.amount;
    }

    public LocalDateTime get_time()
    {
        return this.time;
    }

    @Override
    public String toString()
    {
        return "Bidder: " + this.bidder.get_name() + " - " + this.amount + " Euro " + " - " + this.time;
    }
}
