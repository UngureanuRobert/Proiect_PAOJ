package service;

import models.User;
import models.Buyer;
import models.Seller;
import java.util.*;

public class Userservice {
    private final Map<String, User> users = new HashMap<>();

    //register buyer
    public Buyer register_buyer(String name, String email)
    {
        Buyer buyer = new Buyer(name, email);
        users.put(buyer.get_id(), buyer);
        return buyer;
    }

    //register seller
    public Seller register_seller(String name, String email)
    {
        Seller seller = new Seller(name, email);
        users.put(seller.get_id(), seller);
        return seller;
    }

    //delete user
    public User delete_user(String userid)
    {
        User user_removed = users.remove(userid);
        return user_removed;
    }

    //see all sellers
    public ArrayList<Seller> get_all_sellers()
    {
        ArrayList<Seller> all_sellers = new ArrayList<>();
        for (User user: users.values())
        {
            if (user instanceof Seller) all_sellers.add((Seller) user);
        }
        return all_sellers;
    }

}
