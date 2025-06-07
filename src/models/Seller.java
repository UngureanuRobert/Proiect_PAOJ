package models;

import java.util.ArrayList;

public class Seller extends User {
    private final ArrayList<Auction> auctions;

    public Seller(String name, String email)
    {
        super(name, email);
        this.auctions = new ArrayList<>();
    }

    //constructor for SELECTING from DB (needed in DAO)
    public Seller(String id, String name, String email)
    {
        super(id, name, email);
        this.auctions = new ArrayList<>();
    }

    public ArrayList<Auction> get_auctions()
    {
        return this.auctions;
    }

    public void add_auction(Auction auction)
    {
        auctions.add(auction);
    }

    public void remove_auction(Auction auction)
    {
        auctions.remove(auction);
    }

    @Override
    public String toString()
    {
        return "Seller: " + this.get_id() + " " + get_name();
    }
}
