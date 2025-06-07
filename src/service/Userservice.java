package service;

import models.Buyer;
import models.Seller;
import java.util.*;

import dao.SellerDAO;
import dao.BuyerDAO;

public class Userservice {
    private final BuyerDAO buyerDAO = new BuyerDAO();
    private final SellerDAO sellerDAO = new SellerDAO();

    //register buyer
    public Buyer register_buyer(String name, String email)
    {
        Buyer buyer = new Buyer(name, email);
        buyerDAO.create(buyer);
        return buyer;
    }

    //register seller
    public Seller register_seller(String name, String email)
    {
        Seller seller = new Seller(name, email);
        sellerDAO.create(seller);
        return seller;
    }

    //delete buyer
    public Buyer delete_buyer(String userid)
    {
        Buyer buyer = buyerDAO.readById(userid);
        if (buyer != null)
        {
            buyerDAO.delete(userid);
            return buyer;
        }
        return null;
    }

    //delete seller
    public Seller delete_seller(String userid)
    {
        Seller seller = sellerDAO.readById(userid);
        if (seller != null)
        {
            sellerDAO.delete(userid);
            return seller;
        }
        return null;
    }

    //see all sellers
    public ArrayList<Seller> get_all_sellers()
    {
        List<Seller> sellers = sellerDAO.readAll();
        return new ArrayList<>(sellers);
    }

    //see all buyers
    public ArrayList<Buyer> get_all_buyers()
    {
        List<Buyer> buyers = buyerDAO.readAll();
        return new ArrayList<>(buyers);
    }

    //update seller
    public void update_seller(Seller seller)
    {
        sellerDAO.update(seller);
    }

    //update buyer
    public void update_buyer(Buyer buyer)
    {
        buyerDAO.update(buyer);
    }

    //get seller by id
    public Seller get_seller_by_id(String id)
    {
        return sellerDAO.readById(id);
    }

    public Buyer get_buyer_by_id(String id)
    {
        return buyerDAO.readById(id);
    }

}
