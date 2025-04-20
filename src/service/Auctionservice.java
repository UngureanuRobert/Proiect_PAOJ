package service;

import models.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.Duration;

public class Auctionservice {
    private final Map<String, Auction> auctions = new HashMap<>();

    //open auction
    public Auction open_auction(Product product, Seller seller, LocalDateTime startTime1, LocalDateTime endTime1)
    {
        for (Auction auction: auctions.values())
        {
            if (auction.get_product().get_id().equals(product.get_id()))
            {
                return null;
            }
        }
        Auction auction = new Auction(product, seller, startTime1, endTime1);
        auctions.put(auction.get_id(), auction);
        seller.add_auction(auction);
        return auction;
    }

    //see all auctions
    public ArrayList<Auction> get_all_auctions()
    {
        return new ArrayList<>(auctions.values());
    }

    //see all acustions with a certain status
    public ArrayList<Auction> get_auctions_by_status(AuctionStatus status)
    {
        ArrayList<Auction> auctions_by_status = new ArrayList<>();
        for (Auction auction: auctions.values())
        {
            if (auction.get_auction_status() == status)
            {
                auctions_by_status.add(auction);
            }
        }
        return auctions_by_status;
    }

    //place bid for an auction
    public boolean place_bid(String auctionid, Bid bid)
    {
        Auction auction = auctions.get(auctionid);
        if (auction == null || auction.get_auction_status() != AuctionStatus.OPEN)
        {
            return false;
        }
        else
        {
            auction.place_bid(bid);
            return true;
        }
    }

    //close ALL expired auctions
    public void close_expired_auctions()
    {
        LocalDateTime time = LocalDateTime.now();
        for (Auction auction: auctions.values())
        {
            if (auction.get_auction_status() == AuctionStatus.OPEN && time.isAfter(auction.get_end_time()))
            {
                auction.close_auction();
            }
        }
    }

    //biggest bid for the auction with the given id
    public Bid get_biggest_bid(String auctionid)
    {
        Auction auction = auctions.get(auctionid);
        if (auction == null)
        {
            return null;
        }
        return auction.get_biggest_bid();
    }

    //all bids ever for the auction with the given id
    public ArrayList<Bid> get_bid_history(String auctionid)
    {
        Auction auction = auctions.get(auctionid);
        if (auction == null)
        {
            return new ArrayList<>();
        }
        else
        {
            ArrayList<Bid> bidhistory = new ArrayList<>(auction.get_bid_history().get_all_bids());
            return bidhistory;
        }
    }

    //returning all auctions of a seller
    public ArrayList<Auction> get_seller_auctions(String sellerid)
    {
        ArrayList<Auction> all_seller_auctions = new ArrayList<>();

        for (Auction auction: auctions.values())
        {
            if (auction.get_seller().get_id().equals(sellerid))
            {
                all_seller_auctions.add(auction);
            }
        }

        return all_seller_auctions;
    }

    //sum of a seller's winnings
    public double seller_winning(String sellerid)
    {
        double S = 0;
        for (Auction auction: auctions.values())
        {
            if (auction.get_seller().get_id().equals(sellerid) && auction.get_auction_status() == AuctionStatus.CLOSED)
            {
                Bid biggest_bid1 = auction.get_biggest_bid();
                if (biggest_bid1 != null)
                {
                    S += biggest_bid1.get_amount();
                }
            }
        }
        return S;
    }

    //auctions about to expire(close) in the given time parameter
    public ArrayList<Auction> expiring_auctions(Duration duration)
    {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiration_date = now.plus(duration);
        ArrayList<Auction> expiring_auctions = new ArrayList<>();

        for (Auction auction: auctions.values())
        {
            LocalDateTime ending_time = auction.get_end_time();
            if (auction.get_auction_status() == AuctionStatus.OPEN && ending_time.isAfter(now) && ending_time.isBefore(expiration_date))
            {
                expiring_auctions.add(auction);
            }
        }
        return expiring_auctions;
    }

    //all auctions won by a buyer
    public ArrayList<Auction> auctions_won(String buyerid)
    {
        ArrayList<Auction> won_auctions = new ArrayList<>();
        for (Auction auction: auctions.values())
        {
            if (auction.get_auction_status() == AuctionStatus.CLOSED)
            {
                Bid biggest_bid = auction.get_biggest_bid();
                if (biggest_bid != null && biggest_bid.get_bidder().get_id().equals(buyerid))
                {
                    won_auctions.add(auction);
                }
            }
        }
        
        return won_auctions;
    }
}
