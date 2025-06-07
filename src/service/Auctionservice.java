package service;

import models.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.Duration;
import dao.BidDAO;
import dao.AuctionDAO;

public class Auctionservice {
    private final AuctionDAO auctionDAO = new AuctionDAO();
    private final BidDAO bidDAO = new BidDAO();

    //open auction
    public Auction open_auction(Product product, Seller seller, LocalDateTime startTime1, LocalDateTime endTime1, double startPrice1)
    {
        List<Auction> auctions = auctionDAO.readAll();
        for (Auction auction: auctions)
        {
            if (auction.get_product().get_id().equals(product.get_id()))
            {
                return null;
            }
        }
        Auction auction = new Auction(product, seller, startTime1, endTime1, startPrice1);
        auctionDAO.create(auction);
        return auction;
    }

    //see all auctions
    public ArrayList<Auction> get_all_auctions()
    {
        List<Auction> auctions = auctionDAO.readAll();
        return new ArrayList<>(auctions);
    }

    //see all acustions with a certain status
    public ArrayList<Auction> get_auctions_by_status(AuctionStatus status)
    {
        ArrayList<Auction> auctions_by_status = new ArrayList<>();
        for (Auction auction: auctionDAO.readAll())
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
        Auction auction = auctionDAO.readById(auctionid);
        if (auction == null || auction.get_auction_status() != AuctionStatus.OPEN)
        {
            return false;
        }
        double current_biggest = auction.get_start_price();
        List<Bid> bids = bidDAO.readAll();
        for (Bid b:bids)
        {
            if (b.get_auction().get_id().equals(auctionid))
            {
                if (b.get_amount() > current_biggest) current_biggest = b.get_amount();
            }
        }
        if (bid.get_amount() <= current_biggest)
        {
            return false;
        }
        else
        {
            bidDAO.create(bid);
            return true;
        }
    }

    //close ALL expired auctions
    public void close_expired_auctions()
    {
        LocalDateTime time = LocalDateTime.now();
        List<Auction> auctions = auctionDAO.readAll();
        for (Auction auction: auctions)
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
        Bid biggest_bid = null;
        double max = 0;
        for (Bid b:bidDAO.readAll())
        {
            if (b.get_auction().get_id().equals(auctionid) && b.get_amount() > max)
            {
                max = b.get_amount();
                biggest_bid = b;
            }
        }
        return biggest_bid;
    }

    //all bids ever for the auction with the given id
    public ArrayList<Bid> get_bid_history(String auctionid)
    {
        ArrayList<Bid> bid_history = new ArrayList<>();
        for (Bid b: bidDAO.readAll())
        {
            if (b.get_auction().get_id().equals(auctionid)) bid_history.add(b);
        }
        return bid_history;
    }

    //returning all auctions of a seller
    public ArrayList<Auction> get_seller_auctions(String sellerid)
    {
        ArrayList<Auction> all_seller_auctions = new ArrayList<>();

        for (Auction auction: auctionDAO.readAll())
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
        for (Auction auction: auctionDAO.readAll())
        {
            if (auction.get_seller().get_id().equals(sellerid) && auction.get_auction_status() == AuctionStatus.CLOSED)
            {
                Bid biggest_bid1 = get_biggest_bid(auction.get_id());
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

        for (Auction auction: auctionDAO.readAll())
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
        for (Auction auction: auctionDAO.readAll())
        {
            if (auction.get_auction_status() == AuctionStatus.CLOSED)
            {
                Bid biggest_bid = get_biggest_bid(auction.get_id());
                if (biggest_bid != null && biggest_bid.get_bidder().get_id().equals(buyerid))
                {
                    won_auctions.add(auction);
                }
            }
        }
        
        return won_auctions;
    }

    //get bid by id
    public Bid get_bid_by_id(String bidid)
    {
        return bidDAO.readById(bidid);
    }

    //update bid
    public void update_bid(Bid bid)
    {
        bidDAO.update(bid);
    }

    //delete bid
    public void delete_bid(String bidid)
    {
        bidDAO.delete(bidid);
    }

    //update auction
    public void update_auction(Auction auction)
    {
        auctionDAO.update(auction);
    }

    //delete auction
    public void delete_auction(String auctionid)
    {
        auctionDAO.delete(auctionid);
    }
}
