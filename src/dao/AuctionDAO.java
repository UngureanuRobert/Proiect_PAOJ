package dao;

import models.Auction;
import models.AuctionStatus;
import util.DBConnection;
import java.sql.*;
import java.util.*;

public class AuctionDAO {
    public void create(Auction auction)
    {
        String sql = "INSERT INTO auction(id, product_id, seller_id, start_time, end_time, status, start_price) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(sql))
            {
                ps.setString(1, auction.get_id());
                ps.setString(2, auction.get_product().get_id());
                ps.setString(3, auction.get_seller().get_id());
                ps.setTimestamp(4, Timestamp.valueOf(auction.get_start_time()));
                ps.setTimestamp(5, Timestamp.valueOf(auction.get_end_time()));
                ps.setString(6, auction.get_auction_status().toString());
                ps.setDouble(7, auction.get_start_price());
                ps.executeUpdate();
            } catch (SQLException e)
            {
                System.out.println(e.getMessage());
            }
    }
    public Auction readById(String id)
    {
        String sql = "SELECT * FROM auction WHERE id = ?";
        try (Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(sql))
            {
                ps.setString(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next())
                {
                    return new Auction(rs.getString("id"), rs.getString("product_id"), rs.getString("seller_id"), rs.getTimestamp("start_time").toLocalDateTime(), rs.getTimestamp("end_time").toLocalDateTime(), AuctionStatus.valueOf(rs.getString("status")), rs.getDouble("start_price"));
                }
            } catch (SQLException e)
            {
                System.out.println(e.getMessage());
            }
        return null;
    }
    public List<Auction> readAll()
    {
        List<Auction> auctions = new ArrayList<>();
        String sql = "SELECT * FROM auction";
        try (Connection c = DBConnection.getConnection();
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(sql))
            {
                while (rs.next())
                {
                    auctions.add(new Auction(rs.getString("id"), rs.getString("product_id"), rs.getString("seller_id"), rs.getTimestamp("start_time").toLocalDateTime(), rs.getTimestamp("end_time").toLocalDateTime(), AuctionStatus.valueOf(rs.getString("status")), rs.getDouble("start_price")));
                }
            } catch (SQLException e)
            {
                System.out.println(e.getMessage());
            }
        return auctions;
    }
    public void update(Auction auction)
    {
        String sql = "UPDATE auction SET product_id = ?, seller_id = ?, start_time = ?, end_time = ?, status = ?, start_price = ? WHERE id = ?";
        try (Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(sql))
            {
                ps.setString(1, auction.get_product().get_id());
                ps.setString(2, auction.get_seller().get_id());
                ps.setTimestamp(3, Timestamp.valueOf(auction.get_start_time()));
                ps.setTimestamp(4, Timestamp.valueOf(auction.get_end_time()));
                ps.setString(5, auction.get_auction_status().toString());
                ps.setDouble(6, auction.get_start_price());
                ps.setString(7, auction.get_id());
                ps.executeUpdate();
            } catch (SQLException e)
            {
                System.out.println(e.getMessage());
            }
    }
    public void delete(String id)
    {
        String sql = "DELETE FROM auction WHERE id = ?";
        try (Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(sql))
            {
                ps.setString(1, id);
                ps.executeUpdate();
            } catch (SQLException e)
            {
                System.out.println(e.getMessage());
            }
    }
}
