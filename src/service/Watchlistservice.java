package service;

import models.*;
import java.util.*;

public class Watchlistservice {

    //add to buyer's watchlist
    public boolean add_to_watchlist(Buyer buyer, Auction auction)
    {
        Set<Auction> set_auctions = buyer.get_watchlist().get_auctions();
        if (!set_auctions.contains(auction))
        {
            buyer.get_watchlist().add_auction(auction);
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
        Set<Auction> set_auctions = buyer.get_watchlist().get_auctions();
        if (set_auctions.contains(auction))
        {
            set_auctions.remove(auction);
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
        ArrayList<Auction> whole_watchlist = new ArrayList<>();
        Set<Auction> set_auctions = buyer.get_watchlist().get_auctions();
        for (Auction auction: set_auctions)
        {
            whole_watchlist.add(auction);
        }
        return whole_watchlist;
    }
}
