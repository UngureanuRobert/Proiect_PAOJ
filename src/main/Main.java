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

        Map<String, Product> products_by_name = new HashMap<>();
        Map<String, User> users_by_name = new HashMap<>();

        while (true)
        {
            System.out.println("=============================================================");
            System.out.println("1. Register Buyer");
            System.out.println("2. Register Seller");
            System.out.println("3. Delete user");
            System.out.println("4. Get all sellers");
            System.out.println("5. Add new product");
            System.out.println("6. Show all products");
            System.out.println("7. Open auction");
            System.out.println("8. Show all auctions");
            System.out.println("9. Show auctions by status");
            System.out.println("10. Place bid");
            System.out.println("11. Show biggest bid");
            System.out.println("12. Show bid history");
            System.out.println("13. Close expired auctions");
            System.out.println("14. Show seller's auctions");
            System.out.println("15. Show seller's winnings");
            System.out.println("16. Show auctions about to expire at a certain time");
            System.out.println("17. Show won auctions by buyer");
            System.out.println("18. Add auction to watchlist");
            System.out.println("19. Remove auction from watchlist");
            System.out.println("20. Show all auctions in watchlist");
            System.out.println("21. Send notification to user");
            System.out.println("22. Show user's notifications");
            System.out.println("0. Exit");

            int choice = Integer.parseInt(scanner.nextLine());
            
            switch(choice)
            {
                case 1 -> {
                    System.out.println("\nRegister Buyer");
                    System.out.print("Name: ");
                    String buyer_name = scanner.nextLine();
                    System.out.print("Email: ");
                    String buyer_email = scanner.nextLine();
                    Buyer buyer = user_service.register_buyer(buyer_name, buyer_email);
                    System.out.println("You've been successfully registered! Your data is: " + buyer.get_id() + " " + buyer.get_name() + " " + buyer.get_email());
                    users_by_name.put(buyer.get_name().toLowerCase(), buyer);
                }
                case 2 -> {
                    System.out.println("\nRegister Seller");
                    System.out.print("Name: ");
                    String seller_name = scanner.nextLine();
                    System.out.print("Email: ");
                    String seller_email = scanner.nextLine();
                    Seller seller = user_service.register_seller(seller_name, seller_email);
                    System.out.println("You've been successfully registered! Your data is: " + seller.get_id() + " " + seller.get_name() + " " + seller.get_email());
                    users_by_name.put(seller_name.toLowerCase(), seller);
                }
                case 3 -> {
                    System.out.println("\nDelete user with given id: ");
                    String userid = scanner.nextLine();
                    User deleted_user = user_service.delete_user(userid);
                    if (deleted_user != null)
                    {
                        System.out.println("User " + deleted_user.get_name() + " deleted");
                        users_by_name.remove(deleted_user.get_name().toLowerCase());
                    }
                    else
                    {
                        System.out.println("User does not exist");
                    }
                }
                case 4 -> {
                    System.out.println();
                    ArrayList<Seller> all_sellers = user_service.get_all_sellers();
                    if (all_sellers.isEmpty())
                    {
                        System.out.println("No sellers yet!");
                        break;
                    }
                    else
                    {
                        System.out.println("All sellers:");
                        for (Seller seller: all_sellers)
                        {
                            System.out.println(seller + "; ");
                        }
                    }
                }
                case 5 -> {
                    System.out.println("\nAdd new product:");
                    System.out.print("Product name:");
                    String product_name = scanner.nextLine();
                    System.out.print("Description: ");
                    String product_description = scanner.nextLine();
                    System.out.print("Input category: ELECTRONICS, UTILITY, VEHICLES, CLOTHING, TOYS, ART, OTHER: ");
                    Category category = Category.valueOf(scanner.nextLine().toUpperCase());
                    Product product = product_service.add_product(product_name, product_description, category);
                    System.out.println("Product added!");
                    products_by_name.put(product_name.toLowerCase(), product);
                }
                case 6 -> {
                    System.out.println();
                    ArrayList<Product> all_products = product_service.get_products();
                    if (all_products.isEmpty())
                    {
                        System.out.println("No products yet!");
                        break;
                    }
                    else
                    {
                        System.out.println("All products: ");
                        for (Product product: all_products)
                        {
                            System.out.println(product + "; ");
                        }
                    }
                }
                case 7 -> {
                    System.out.println("\nProduct name: ");
                    String product_name = scanner.nextLine().toLowerCase();
                    Product product = products_by_name.get(product_name);
                    if (product == null)
                    {
                        System.out.println("No product with such name!");
                        break;
                    }
                    System.out.println("Seller's name: ");
                    String seller_name = scanner.nextLine().toLowerCase();
                    User seller = users_by_name.get(seller_name);
                    if (!(seller instanceof Seller))
                    {
                        System.out.println("User is not a seller or doesn't exist!");
                        break;
                    }
                    Seller seller1 = (Seller) seller;
                    LocalDateTime start = LocalDateTime.now();
                    System.out.println("Auction duration (measured in minutes):");
                    long minutes = Long.parseLong(scanner.nextLine());
                    LocalDateTime end = start.plusMinutes(minutes);

                    Auction auction = auction_service.open_auction(product, seller1, start, end);
                    if (auction == null)
                    {
                        System.out.println("Auction for this product already exists!");
                    }
                    else
                    {
                        System.out.println("Auction is now open! Auction id:" + auction.get_id());
                    }
                }
                case 8 -> {
                    System.out.println("\nAll auctions:");
                    ArrayList<Auction> all_auctions = auction_service.get_all_auctions();
                    if (all_auctions.isEmpty())
                    {
                        System.out.println("No auctions yet!");
                    }
                    for (Auction auction: all_auctions)
                    {
                        System.out.println(auction);
                    }
                }
                case 9 -> {
                    System.out.println("\nChoose auction status: OPEN/CLOSED");
                    String status_string = scanner.nextLine().toUpperCase();
                    AuctionStatus status = AuctionStatus.valueOf(status_string);
                    ArrayList<Auction> all_auctions = auction_service.get_auctions_by_status(status);
                    if (all_auctions.isEmpty())
                    {
                        System.out.println("There is no auction with this status yet!");
                        break;
                    }
                    else
                    {
                        System.out.println("Auctions with status " + status);
                        for (Auction auction: all_auctions)
                        {
                            System.out.println(auction);
                        }
                    }
                }
                case 10 -> {
                    System.out.print("\nPlace bid. Type in auction ID: ");
                    String auctionid = scanner.nextLine();
                    System.out.print("Buyer's name: ");
                    User buyer = users_by_name.get(scanner.nextLine().toLowerCase());
                    if (!(buyer instanceof Buyer))
                    {
                        System.out.println("There is no buyer with this name!");
                        break;
                    }
                    else
                    {
                        System.out.print("Amount: ");
                        double amount = Double.parseDouble(scanner.nextLine());
                        Bid bid = new Bid((Buyer) buyer, amount);
                        boolean bid_placed = auction_service.place_bid(auctionid, bid);
                        if (bid_placed == true)
                        {
                            System.out.println("Bid has been placed!");
                        }
                        else
                        {
                            System.out.println("Bid can't be placed right now.");
                        }
                    }
                }
                case 11 -> {
                    System.out.print("\nShow biggest bid.");
                    System.out.println("Type in the auction ID:");
                    Bid biggest_bid = auction_service.get_biggest_bid(scanner.nextLine());
                    if (biggest_bid == null)
                    {
                        System.out.println("Bid or auction not found!");
                    }
                    else
                    {
                        System.out.println("Biggest bid for this auction: " + biggest_bid.get_amount());
                    }
                }
                case 12 -> {
                    System.out.println("\nShow bid history.");
                    System.out.print("Auction ID:");
                    String auctionid = scanner.nextLine();
                    ArrayList<Bid> bid_history = auction_service.get_bid_history(auctionid);
                    if (bid_history.isEmpty())
                    {
                        System.out.println("There is no bidding history for this auction!");
                        break;
                    }
                    else
                    {
                        System.out.println("Bid history:");
                        for (Bid bid: bid_history)
                        {
                            System.out.println(bid);
                        }
                    }
                }
                case 13 -> {
                    System.out.println("\nAll expired auctions have been closed.");
                    auction_service.close_expired_auctions();
                }
                case 14 -> {
                    System.out.println("\nShow seller's auctions");
                    System.out.print("Seller's name: ");
                    User seller = users_by_name.get(scanner.nextLine().toLowerCase());
                    if (!(seller instanceof Seller))
                    {
                        System.out.println("There is no seller with this name!");
                        break;
                    }
                    ArrayList<Auction> all_auctions = auction_service.get_seller_auctions(seller.get_id());
                    if (all_auctions.isEmpty())
                    {
                        System.out.println("This seller doesn't have any auctions yet!");
                        break;
                    }
                    else
                    {
                        System.out.println("Auctions:");
                        for (Auction auction: all_auctions)
                        {
                            System.out.println(auction);
                        }
                    }
                }
                case 15 -> {
                    System.out.println("\nShow all seller's winnings:");
                    System.out.print("Seller's name:");
                    User seller = users_by_name.get(scanner.nextLine().toLowerCase());
                    if (!(seller instanceof Seller))
                    {
                        System.out.println("No such seller!");
                        break;
                    }
                    double all_winnings = auction_service.seller_winning(seller.get_id());
                    System.out.println("Total winnings: " + all_winnings);
                }
                case 16 -> {
                    System.out.println("\nShow all auctions about to expire:");
                    System.out.print("Minutes:");
                    long minutes = Long.parseLong(scanner.nextLine());
                    ArrayList<Auction> all_auctions = auction_service.expiring_auctions(Duration.ofMinutes(minutes));
                    if (all_auctions.isEmpty())
                    {
                        System.out.println("No auctions expiring in the given time");
                        break;
                    }
                    else
                    {
                        System.out.println("Expiring auctions:");
                        for (Auction auction: all_auctions)
                        {
                            System.out.println(auction);
                        }
                    }
                }
                case 17 -> {
                    System.out.println("\nShow won auctions by buyer:");
                    System.out.print("Buyer's name:");
                    User buyer = users_by_name.get(scanner.nextLine().toLowerCase());
                    if (!(buyer instanceof Buyer))
                    {
                        System.out.println("No such buyer!");
                        break;
                    }
                    ArrayList<Auction> all_auctions = auction_service.auctions_won(buyer.get_id());
                    if (all_auctions.isEmpty())
                    {
                        System.out.println("No auction won by buyer!");
                        break;
                    }
                    else
                    {
                        System.out.println("Won auctions by buyer:");
                        for (Auction auction: all_auctions)
                        {
                            System.out.println(auction);
                        }
                    }
                }
                case 18 -> {
                    System.out.println("\nAdd auction to watchlist.");
                    System.out.print("Buyer's name: ");
                    User buyer = users_by_name.get(scanner.nextLine().toLowerCase());
                    if (!(buyer instanceof Buyer))
                    {
                        System.out.println("Invalid buyer");
                        break;
                    }
                    System.out.print("Auction Id: ");
                    String auctionid = scanner.nextLine();
                    Auction to_add_auction = null;
                    ArrayList<Auction> all_auctions = auction_service.get_all_auctions();
                    for (Auction auction: all_auctions)
                    {
                        if (auction.get_id().equals(auctionid))
                        {
                            to_add_auction = auction;
                            break;
                        }
                    }
                    if (to_add_auction == null)
                    {
                        System.out.println("No auction with this ID.");
                        break;
                    }
                    else
                    {
                        boolean added_to_watchlist = watchlist_service.add_to_watchlist((Buyer) buyer, to_add_auction);
                        if (added_to_watchlist == true)
                        {
                            System.out.println("Auction has been added to watchlist");
                        }
                        else
                        {
                            System.out.println("Auction already in watchlist");
                        }
                    }
                }
                case 19 -> {
                    System.out.println("\nRemove auction from watchlist.");
                    System.out.print("Buyer's name: ");
                    User buyer = users_by_name.get(scanner.nextLine().toLowerCase());
                    if (!(buyer instanceof Buyer))
                    {
                        System.out.println("Invalid buyer");
                        break;
                    }
                    System.out.print("Auction Id: ");
                    String auctionid = scanner.nextLine();
                    Auction to_remove_auction = null;
                    ArrayList<Auction> all_auctions = auction_service.get_all_auctions();
                    for (Auction auction: all_auctions)
                    {
                        if (auction.get_id().equals(auctionid))
                        {
                            to_remove_auction = auction;
                            break;
                        }
                    }
                    if (to_remove_auction == null)
                    {
                        System.out.println("No auction with this ID.");
                        break;
                    }
                    else
                    {
                        boolean removed_from_watchlist = watchlist_service.remove_from_watchlist((Buyer) buyer, to_remove_auction);
                        if (removed_from_watchlist == true)
                        {
                            System.out.println("Auction removed from watchlist.");
                        }
                        else
                        {
                            System.out.println("Auction not in watchlist.");
                        }
                    }
                }
                case 20 -> {
                    System.out.println("\nShow all auctions in watchlist");
                    System.out.print("Buyer's name: ");
                    User buyer = users_by_name.get(scanner.nextLine().toLowerCase());
                    if (!(buyer instanceof Buyer))
                    {
                        System.out.println("Invalid buyer");
                        break;
                    }
                    ArrayList<Auction> watchlist_auctions = watchlist_service.get_watchlist((Buyer) buyer);
                    if (watchlist_auctions.isEmpty())
                    {
                        System.out.println("Watchlist is empty!");
                        break;
                    }
                    else
                    {
                        System.out.println("All auctions in watchlist:");
                        for (Auction auction: watchlist_auctions)
                        {
                            System.out.println(auction);
                        }
                    }
                }
                case 21 -> {
                    System.out.println("\nSend notification to user");
                    System.out.print("User's name: ");
                    User user = users_by_name.get(scanner.nextLine().toLowerCase());
                    if (user == null)
                    {
                        System.out.println("No user with this name!");
                        break;
                    }
                    System.out.print("Message to send: ");
                    String message = scanner.nextLine();
                    notification_service.send_notification(user, message);
                    System.out.println("Notification sent!");
                }
                case 22 -> {
                    System.out.println("\nShow user's notifications:");
                    System.out.print("User's name:");
                    User user = users_by_name.get(scanner.nextLine().toLowerCase());
                    if (user == null)
                    {
                        System.out.println("No user with this name!");
                        break;
                    }
                    ArrayList<Notification> all_notifications = notification_service.get_all_notifications(user.get_id());
                    if (all_notifications.isEmpty())
                    {
                        System.out.println("No notifications yet!");
                        break;
                    }
                    else
                    {
                        for (Notification notification: all_notifications)
                        {
                            System.out.println(notification.get_time() + ": " + notification.get_message());
                        }
                    }
                }
                case 0 -> {
                    System.out.println("Closing.");
                    scanner.close();
                    return;
                }
                default -> {
                    System.out.println("Choose a valid option (1-22)");
                }
            }
        }
    }
}
