package models;

public class Buyer extends User{
    private final Watchlist watchlist;
    private final BidHistory bidhistory;

    public Buyer(String name, String email)
    {
        super(name, email);
        this.watchlist = new Watchlist(this);
        this.bidhistory = new BidHistory();
    }

    //constructor for SELECTING from DB (needed in DAO)
    public Buyer(String id, String name, String email)
    {
        super(id, name, email);
        this.watchlist = new Watchlist(this);
        this.bidhistory = new BidHistory();
    }

    public Watchlist get_watchlist()
    {
        return this.watchlist;
    }

    public BidHistory get_bidhistory()
    {
        return this.bidhistory;
    }

    @Override
    public String toString()
    {
        return "Buyer: " + this.get_id() + " " + this.get_name();
    }
}
