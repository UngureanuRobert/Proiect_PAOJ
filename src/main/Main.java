package main;

import models.*;
import service.*;

import java.util.*;
import java.time.LocalDateTime;
import java.time.Duration;

public class Main {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        Auctionservice auction_service = new Auctionservice();
        Notificationservice notification_service = new Notificationservice();
        Productservice product_service = new Productservice();
        Userservice user_service = new Userservice();
        Watchlistservice watchlist_service = new Watchlistservice();
        Auditservice audit_service = new Auditservice();

        Map<String, Product> products_by_name = new HashMap<>();
        Map<String, User> users_by_name = new HashMap<>();

        while (true)
        {
            System.out.println("=============================================================");
            System.out.println("========Users========");
            System.out.println("1. Register Buyer");
            System.out.println("2. Register Seller");
            System.out.println("3. Get Buyer by ID");
            System.out.println("4. Get Seller by ID");
            System.out.println("5. Delete Buyer by ID");
            System.out.println("6. Delete Seller by ID");
            System.out.println("7. Get all buyers");
            System.out.println("8. Get all sellers");
            System.out.println("9. Update Buyer by ID");
            System.out.println("10. Update Seller by ID");

            System.out.println("\n========Products========");
            System.out.println("11. Add new product");
            System.out.println("12. Get Product by ID");
            System.out.println("13. Show all products");
            System.out.println("14. Update Product");
            System.out.println("15. Delete Product");

            System.out.println("\n========Auctions========");
            System.out.println("16. Open auction");
            System.out.println("17. Get Auction by ID");
            System.out.println("18. Show all auctions");
            System.out.println("19. Show auctions by status");
            System.out.println("20. Update Auction");
            System.out.println("21. Delete Auction");
            System.out.println("22. Close expired auctions");
            System.out.println("23. Show seller's auctions");
            System.out.println("24. Show seller's winnings");
            System.out.println("25. Show auctions about to expire at a certain time");
            System.out.println("26. Show won auctions by buyer");

            System.out.println("\n========Bids========");
            System.out.println("27. Place bid");
            System.out.println("28. Show biggest bid");
            System.out.println("29. Show bid history");
            System.out.println("30. Get Bid by ID");
            System.out.println("31. Update bid");
            System.out.println("32. Delete bid");

            System.out.println("\n========Watchlist========");
            System.out.println("33. Add auction to watchlist");
            System.out.println("34. Remove auction from watchlist");
            System.out.println("35. Show all auctions in watchlist");
            
            System.out.println("\n========Notifications========");
            System.out.println("36. Send notification to user");
            System.out.println("37. Show user's notifications");
            
            System.out.println("\n0. Exit\n");

            int choice;
            try
            {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e)
            {
                System.out.println("Invalid input");
                continue;
            }

            switch (choice)
            {
                case 1 -> {
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    Buyer buyer = user_service.register_buyer(name, email);
                    users_by_name.put(name.toLowerCase(), buyer);
                    System.out.println("Buyer registered: " + buyer);
                    audit_service.logAction("Register buyer");
                }
                case 2 -> {
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    Seller seller = user_service.register_seller(name, email);
                    users_by_name.put(name.toLowerCase(), seller);
                    System.out.println("Seller registered: " + seller);
                    audit_service.logAction("Register Seller");
                }
                case 3 -> {
                    System.out.print("Buyer ID: ");
                    Buyer buyer = user_service.get_buyer_by_id(scanner.nextLine());
                    System.out.println(buyer != null ? buyer : "Buyer not found");
                    audit_service.logAction("Get Buyer by ID");
                }
                case 4 -> {
                    System.out.print("Seller ID: ");
                    Seller seller = user_service.get_seller_by_id(scanner.nextLine());
                    System.out.println(seller != null ? seller : " Seller not found");
                    audit_service.logAction("Get Seller by ID");
                }
                case 5 -> {
                    System.out.print("Buyer ID: ");
                    Buyer buyer = user_service.delete_buyer(scanner.nextLine());
                    if (buyer != null) 
                    {
                        users_by_name.remove(buyer.get_name().toLowerCase());
                        System.out.println("Buyer deleted");
                    } 
                    else System.out.println("Buyer not found");
                    audit_service.logAction("Delete Buyer by ID");
                }
                case 6 -> {
                    System.out.print("Seller ID: ");
                    Seller seller = user_service.delete_seller(scanner.nextLine());
                    if (seller != null) 
                    {
                        users_by_name.remove(seller.get_name().toLowerCase());
                        System.out.println("Seller deleted");
                    } 
                    else System.out.println("Seller not found");
                    audit_service.logAction("Delete Seller by ID");
                }
                case 7 -> {
                    for (Buyer buyer: user_service.get_all_buyers())
                    {
                        System.out.println(buyer);
                    }
                    audit_service.logAction("Get all buyers");
                }
                case 8 -> {
                    for (Seller seller: user_service.get_all_sellers())
                    {
                        System.out.println(seller);
                    }
                    audit_service.logAction("Get all sellers");
                }
                case 9 -> {
                    System.out.print("Buyer ID: ");
                    Buyer buyer = user_service.get_buyer_by_id(scanner.nextLine());
                    if (buyer == null) 
                    {
                        System.out.println("Buyer not found");
                        break;
                    }
                    System.out.print("New Name: ");
                    buyer.set_name(scanner.nextLine());
                    System.out.print("New Email: ");
                    buyer.set_email(scanner.nextLine());
                    user_service.update_buyer(buyer);
                    System.out.println("Buyer updated");
                    audit_service.logAction("Update buyer by ID");
                }
                case 10 -> {
                    System.out.print("Seller ID: ");
                    Seller seller = user_service.get_seller_by_id(scanner.nextLine());
                    if (seller == null) 
                    {
                        System.out.println("Seller not found");
                        break;
                    }
                    System.out.print("New Name: ");
                    seller.set_name(scanner.nextLine());
                    System.out.print("New Email: ");
                    seller.set_email(scanner.nextLine());
                    user_service.update_seller(seller);
                    System.out.println("Seller updated");
                    audit_service.logAction("Update Seller by ID");
                }
                case 11 -> {
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Description: ");
                    String description = scanner.nextLine();
                    System.out.print("Category (ELECTRONICS, UTILITY, VEHICLES, CLOTHING, TOYS, ART, OTHER):");
                    Category category = Category.valueOf(scanner.nextLine().toUpperCase());
                    Product product = product_service.add_product(name, description, category);
                    products_by_name.put(name.toLowerCase(), product);
                    System.out.println("Product added: " + product);
                    audit_service.logAction("Add new product");
                }
                case 12 -> {
                    System.out.print("Product ID: ");
                    Product product = product_service.get_product_by_id(scanner.nextLine());
                    System.out.println(product != null ? product : "Not found.");
                    audit_service.logAction("Get product by ID");
                }
                case 13 -> {
                    for (Product product : product_service.get_products())
                    {
                        System.out.println(product);
                    } 
                    audit_service.logAction("Show all products");
                }
                case 14 -> {
                    System.out.print("Product ID: ");
                    Product product = product_service.get_product_by_id(scanner.nextLine());
                    if (product == null) {
                        System.out.println("Product not found");
                        break;
                    }
                    System.out.print("New Name: ");
                    product.set_name(scanner.nextLine());
                    System.out.print("New Description: ");
                    product.set_description(scanner.nextLine());
                    System.out.print("New Category: ");
                    product.set_category(Category.valueOf(scanner.nextLine().toUpperCase()));
                    product_service.update_product(product);
                    System.out.println("Product updated");
                    audit_service.logAction("Update product");
                }
                case 15 -> {
                    System.out.print("Product ID: ");
                    product_service.delete_product(scanner.nextLine());
                    System.out.println("Product deleted");
                    audit_service.logAction("Delete product");
                }
                case 16 -> {
                    System.out.print("Product ID: ");
                    Product product = product_service.get_product_by_id(scanner.nextLine());
                    if (product == null)
                    {
                        System.out.println("Product not found");
                        break;
                    }
                    System.out.print("Seller ID: ");
                    Seller seller = (Seller) user_service.get_seller_by_id(scanner.nextLine());
                    if (seller == null)
                    {
                        System.out.println("Seller not found");
                        break;
                    }
                    System.out.print("Duration (measured in minutes): ");
                    LocalDateTime start = LocalDateTime.now();
                    LocalDateTime end = start.plusMinutes(Long.parseLong(scanner.nextLine()));
                    System.out.print("Starting price: ");
                    double price = Double.parseDouble(scanner.nextLine());
                    Auction auction = auction_service.open_auction(product, seller, start, end, price);
                    System.out.println(auction != null ? "Auction opened: " + auction.get_id() : "Auction already exists!");
                    audit_service.logAction("Open auction");
                }
                case 17 -> {
                    System.out.print("Auction ID: ");
                    String auctionId = scanner.nextLine(); 
                    Auction auction = auction_service.get_all_auctions().stream().filter(a -> a.get_id().equals(auctionId)).findFirst().orElse(null);
                    System.out.println(auction != null ? auction : "Auction not found.");
                    audit_service.logAction("Get Auction by ID");
                }
                case 18 -> {
                    for (Auction auction: auction_service.get_all_auctions()) 
                    {
                        System.out.println(auction);
                    }
                    audit_service.logAction("Show all auctions");
                }
                case 19 -> {
                    System.out.print("Choose status (OPEN/CLOSED): ");
                    AuctionStatus status = AuctionStatus.valueOf(scanner.nextLine().toUpperCase());
                    for (Auction auction: auction_service.get_auctions_by_status(status)) 
                    {
                        System.out.println(auction);
                    }
                    audit_service.logAction("Show auctions by status");
                }
                case 20 -> { //updated only sattus, other updates can mess up the database logic
                    System.out.print("Auction ID: ");
                    Auction auction = auction_service.get_all_auctions().stream().filter(auction1 -> auction1.get_id().equals(scanner.nextLine())).findFirst().orElse(null);
                    if (auction == null) 
                    {
                        System.out.println("Auction not found");
                        break;
                    }
                    System.out.print("Choose a new Status (OPEN/CLOSED): ");
                    auction.close_auction();
                    auction_service.update_auction(auction);
                    System.out.println("Auction updated");
                    audit_service.logAction("Update auction");
                }
                case 21 -> {
                    System.out.print("Auction ID: ");
                    auction_service.delete_auction(scanner.nextLine());
                    System.out.println("Auction deleted");
                    audit_service.logAction("Delete auction");
                }
                case 22 -> {
                    auction_service.close_expired_auctions();
                    System.out.println("Expired auctions have been closed!");
                    audit_service.logAction("Close Expired auctions");
                }
                case 23 -> {
                    System.out.print("Seller ID: ");
                    Seller seller = user_service.get_seller_by_id(scanner.nextLine());
                    if (seller == null) 
                    {
                        System.out.println("Seller not found");
                        break;
                    }
                    List<Auction> sellerAuctions = auction_service.get_seller_auctions(seller.get_id());
                    if (sellerAuctions.isEmpty())
                    {
                        System.out.println("No auctions");
                    }
                    else
                    {
                        for (Auction auction: sellerAuctions)
                        {
                            System.out.println(auction);
                        }
                    }
                    audit_service.logAction("Show seller's auctions");
                }
                case 24 -> {
                    System.out.print("Seller ID: ");
                    Seller seller = user_service.get_seller_by_id(scanner.nextLine());
                    if (seller  == null)
                    {
                        System.out.println("Seller not found");
                        break;
                    }
                    double winnings = auction_service.seller_winning(seller.get_id());
                    System.out.println("Total winnings: " + winnings);
                    audit_service.logAction("Show seller's winnings");
                }
                case 25 -> {
                    System.out.print("Minutes: ");
                    long minutes = Long.parseLong(scanner.nextLine());
                    for (Auction auction: auction_service.expiring_auctions(Duration.ofMinutes(minutes)))
                    {
                        System.out.println(auction);
                    }
                    audit_service.logAction("Show expiring auctions");
                }
                case 26 -> {
                    System.out.print("Buyer ID: ");
                    Buyer buyer = user_service.get_buyer_by_id(scanner.nextLine());
                    if (buyer == null)
                    {
                        System.out.println("Buyer not found");
                        break;
                    }
                    List<Auction> wonAuctions = auction_service.auctions_won(buyer.get_id());
                    if (wonAuctions.isEmpty())
                    {
                        System.out.println("No won auctions");
                    }
                    for (Auction auction: wonAuctions) 
                    {
                        System.out.println(auction);
                    }
                    audit_service.logAction("Show won auctions by buyer");
                }
                case 27 -> {
                    System.out.print("Auction ID: ");
                    String auctionId = scanner.nextLine();
                    Auction auction = auction_service.get_all_auctions().stream().filter(auction1 -> auction1.get_id().equals(auctionId)).findFirst().orElse(null);
                    if (auction == null) {
                        System.out.println("Auction not found");
                        break;
                    }
                    System.out.print("Buyer ID: ");
                    Buyer buyer = user_service.get_buyer_by_id(scanner.nextLine());
                    if (buyer == null) {
                        System.out.println("Buyer not found");
                        break;
                    }
                    System.out.print("Amount: ");
                    double amount = Double.parseDouble(scanner.nextLine());
                    Bid bid = new Bid(buyer, auction, amount);
                    boolean placed = auction_service.place_bid(auctionId, bid);
                    System.out.println(placed ? "Bid placed" : "Can't place bid!");
                    audit_service.logAction("Place bid");
                }
                case 28 -> {
                    System.out.print("Auction ID: ");
                    String auctionId = scanner.nextLine();
                    Auction auction = auction_service.get_all_auctions().stream().filter(auction1 -> auction1.get_id().equals(auctionId)).findFirst().orElse(null);
                    if (auction == null)
                    {
                        System.out.println("Auction not found");
                        break;
                    }
                    Bid bid = auction_service.get_biggest_bid(auctionId);
                    System.out.println(bid != null ? bid : "No bids");
                    audit_service.logAction("Show biggest bid");
                }
                case 29 -> {
                    System.out.print("Auction ID: ");
                    String auctionId = scanner.nextLine();
                    Auction auction = auction_service.get_all_auctions().stream().filter(auction1 -> auction1.get_id().equals(auctionId)).findFirst().orElse(null);
                    List<Bid> bids = auction_service.get_bid_history(auctionId);
                    if (bids.isEmpty())
                    {
                        System.out.println("No bids yet");
                    }
                    else
                    {
                        for (Bid bid: bids)
                        {
                            System.out.println(bid);
                        }
                    }
                    audit_service.logAction("Show bid history");
                }
                case 30 -> {
                    System.out.print("Bid ID: ");
                    Bid bid = auction_service.get_bid_by_id(scanner.nextLine());
                    System.out.println(bid != null ? bid : "Bid not found");
                    audit_service.logAction("Get Bid by ID");
                }
                case 31 -> { //updated only amount, other updates can mess up the database logic
                    System.out.print("Bid ID: ");
                    Bid bid = auction_service.get_bid_by_id(scanner.nextLine());
                    if (bid == null) 
                    {
                        System.out.println("Bid not found");
                        break;
                    }
                    System.out.print("New amount: ");
                    bid.set_amount(Double.parseDouble(scanner.nextLine()));
                    auction_service.update_bid(bid);
                    System.out.println("Bid updated");
                    audit_service.logAction("Update bid");
                }
                case 32 -> {
                    System.out.print("Bid ID:");
                    auction_service.delete_bid(scanner.nextLine());
                    System.out.println("Bid deleted");
                    audit_service.logAction("Delete bid");
                }
                case 33 -> {
                    System.out.print("Buyer ID: ");
                    String buyerId = scanner.nextLine();
                    Buyer buyer = user_service.get_buyer_by_id(buyerId);
                    if (buyer == null) 
                    {
                        System.out.println("Buyer not found");
                        break;
                    }
                    System.out.print("Auction ID: ");
                    String auctionId = scanner.nextLine();
                    Auction auction = auction_service.get_all_auctions().stream().filter(auction1 -> auction1.get_id().equals(auctionId)).findFirst().orElse(null);
                    if (auction == null)
                    {
                        System.out.println("Auction not found!");
                        break;
                    }
                    boolean added = watchlist_service.add_to_watchlist(buyer, auction);
                    System.out.println(added ? "Auction added to Watchlist" : "Auction already in watchlist");
                    audit_service.logAction("Add auction to watchlist");
                }
                case 34 -> {
                    System.out.print("Buyer ID: ");
                    String buyerId = scanner.nextLine();
                    Buyer buyer = user_service.get_buyer_by_id(buyerId);
                    if (buyer == null) 
                    {
                        System.out.println("Buyer not found");
                        break;
                    }
                    System.out.print("Auction ID: ");
                    String auctionId = scanner.nextLine();
                    Auction auction = auction_service.get_all_auctions().stream().filter(auction1 -> auction1.get_id().equals(auctionId)).findFirst().orElse(null);
                    if (auction == null)
                    {
                        System.out.println("Auction not found");
                        break;
                    }
                    boolean removed = watchlist_service.remove_from_watchlist(buyer, auction);
                    System.out.println(removed ? "Auction removed from Watchlist" : "Auction is not in watchlist");
                    audit_service.logAction("Remove auction from watchlist");
                }
                case 35 -> {
                    System.out.print("Buyer ID: ");
                    String buyerId = scanner.nextLine();
                    Buyer buyer = user_service.get_buyer_by_id(buyerId);
                    if (buyer == null) 
                    {
                        System.out.println("Buyer not found");
                        break;
                    }
                    List<Auction> watchlist = watchlist_service.get_watchlist(buyer);
                    if (watchlist.isEmpty()) 
                    {
                        System.out.println("Watchlist is empty");
                    } 
                    else 
                    {
                        for (Auction auction: watchlist) 
                        {
                            System.out.println(auction);
                        }
                    }
                    audit_service.logAction("Show all auctions in watchlist");
                }
                case 36 -> {
                    System.out.print("User ID: ");
                    User user = user_service.get_buyer_by_id(scanner.nextLine());
                    if (user == null) 
                    {
                        System.out.println("User not found");
                        break;
                    }
                    System.out.print("Message: ");
                    String message = scanner.nextLine();
                    notification_service.send_notification(user, message);
                    System.out.println("Notification sen");
                    audit_service.logAction("Send notification to user");
                }
                case 37 -> {
                    System.out.print("User ID: ");
                    String userId = scanner.nextLine();
                    User user = user_service.get_buyer_by_id(userId);
                    if (user == null) 
                    {
                        System.out.println("User not found");
                        break;
                    }
                    List<Notification> notes = notification_service.get_all_notifications(user.get_id());
                    if (notes.isEmpty()) 
                    {
                        System.out.println("No notifications!");
                    } 
                    else 
                    {
                        for (Notification notification: notes) 
                        {
                            System.out.println(notification.get_time() + ": " + notification.get_message());
                        }
                    }
                    audit_service.logAction("Show user's notifications");
                }
                case 0 -> {
                    return;
                }
            }
            
        }
    }
}
