package models;

import java.time.LocalDateTime;
import java.util.UUID;

public class Payment {
    private final String id;
    private final Buyer buyer;
    private final Seller seller;
    private final Product product;
    private final double amount;
    private final LocalDateTime time;

    public Payment(Buyer buyer, Seller seller, Product product, double amount, LocalDateTime time)
    {
        this.id = UUID.randomUUID().toString();
        this.buyer = buyer;
        this.seller = seller;
        this.product = product;
        this.amount = amount;
        this.time = time;
    }

    public String get_id()
    {
        return this.id;
    }

    public Buyer get_buyer()
    {
        return this.buyer;
    }

    public Seller get_seller()
    {
        return this.seller;
    }

    public Product get_product()
    {
        return this.product;
    }

    public double get_amount()
    {
        return this.amount;
    }

    public LocalDateTime get_time()
    {
        return this.time;
    }

    @Override
    public String toString()
    {
        return "User " + this.buyer.get_name() + " paid user " + this.seller.get_name() + " amount " + this.amount + " at " + this.time + ", payment id " + this.id;
    }
}
