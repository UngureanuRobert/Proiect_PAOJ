package models;

import java.util.HashSet;
import java.util.Set;

public class Watchlist {
    private final Buyer buyer;
    private final Set<Auction> auctions = new HashSet<>();

    public Watchlist(Buyer buyer)
    {
        this.buyer = buyer;
    }

    public Set<Auction> get_auctions()
    {
        return this.auctions;
    }

    public Buyer get_buyer()
    {
        return this.buyer;
    }

    public void add_auction(Auction auction)
    {
        this.auctions.add(auction);
    }

    public void remove_auction(Auction auction)
    {
        this.auctions.remove(auction);
    }


}
