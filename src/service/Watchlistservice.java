package service;

import models.*;
import java.util.*;

public class Watchlistservice {
    private final Map<String, Set<Auction>> watchlists = new HashMap<>();

    //add to buyer's watchlist
    public boolean add_to_watchlist(Buyer buyer, Auction auction)
    {
        String buyerId = buyer.get_id();
        Set<Auction> set_auctions = watchlists.get(buyerId);
        if (set_auctions == null)
        {
            set_auctions = new HashSet<>();
            watchlists.put(buyerId, set_auctions);
        }
        if (!set_auctions.contains(auction))
        {
            set_auctions.add(auction);
            return true;
        }
        else
        {
            return false;
        }
    }

    //remove from buyer's watchlist
    public boolean remove_from_watchlist(Buyer buyer, Auction auction)
    {
        String buyerId = buyer.get_id();
        Set<Auction> set_auctions = watchlists.get(buyerId);
        Auction removable = null;
        for (Auction auction1: set_auctions)
        {
            if (auction1.get_id().equals(auction.get_id()))
            {
                removable = auction1;
                break;
            }
        }
        if (removable != null)
        {
            set_auctions.remove(removable);
            return true;
        }
        else
        {
            return false;
        }
    }

    //show everything in buyer's watchlist
    public ArrayList<Auction> get_watchlist(Buyer buyer)
    {
        String buyerId = buyer.get_id();
        Set<Auction> set_auctions = watchlists.get(buyerId);
        if (set_auctions == null)
        {
            return new ArrayList<>();
        }
        else
        {
            return new ArrayList<>(set_auctions);
        }
    }
}
