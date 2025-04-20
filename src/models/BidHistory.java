package models;

import java.util.Comparator;
import java.util.TreeSet;

public class BidHistory implements Comparator<Bid>{
    private final TreeSet<Bid> bids = new TreeSet<>(this);

    @Override
    public int compare(Bid bid1, Bid bid2)
    {
        int comp = Double.compare(bid1.get_amount(), bid2.get_amount());
        if (comp != 0)
        {
            return comp;
        }
        return bid1.get_time().compareTo(bid2.get_time());
    }

    public void add_bid(Bid bid)
    {
        bids.add(bid);
    }

    public TreeSet<Bid> get_all_bids()
    {
        return bids;
    }

    public Bid get_biggest_bid()
    {
        return bids.isEmpty()? null:bids.last();
    }

    public int count()
    {
        return bids.size();
    }
}
