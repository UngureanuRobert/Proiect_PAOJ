package dao;

import models.Bid;
import util.DBConnection;
import java.sql.*;
import java.util.*;

public class BidDAO {
    public void create(Bid bid)
    {
        String sql = "INSERT INTO bid(id, amount, auction_id, buyer_id, bid_time) VALUES(?, ?, ?, ?, ?)";
        try (Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(sql))
            {
                ps.setString(1, bid.get_id());
                ps.setDouble(2, bid.get_amount());
                ps.setString(3, bid.get_auction().get_id());
                ps.setString(4, bid.get_bidder().get_id());
                ps.setTimestamp(5, Timestamp.valueOf(bid.get_time()));
                ps.executeUpdate();
            } catch (SQLException e)
            {
                System.out.println(e.getMessage());
            }
    }
    public Bid readById(String id)
    {
        String sql = "SELECT * FROM bid WHERE id = ?";
        try (Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(sql))
            {
                ps.setString(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next())
                {
                    return new Bid(rs.getString("id"), rs.getString("auction_id"), rs.getString("buyer_id"), rs.getDouble("amount"), rs.getTimestamp("bid_time").toLocalDateTime());
                }
            } catch (SQLException e)
            {
                System.out.println(e.getMessage());
            }
        return null;
    }
    public List<Bid> readAll()
    {
        List<Bid> bids = new ArrayList<>();
        String sql = "SELECT * FROM bid";
        try (Connection c = DBConnection.getConnection();
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(sql))
            {
                while (rs.next())
                {
                    bids.add(new Bid(rs.getString("id"), rs.getString("auction_id"), rs.getString("buyer_id"), rs.getDouble("amount"), rs.getTimestamp("bid_time").toLocalDateTime()));
                }
            } catch (SQLException e)
            {
                System.out.println(e.getMessage());
            }
        return bids;
    }
    public void update(Bid bid)
    {
        String sql = "UPDATE bid SET amount = ?, auction_id = ?, buyer_id = ?, bid_time = ? WHERE id = ?";
        try (Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(sql))
            {
                ps.setDouble(1, bid.get_amount());
                ps.setString(2, bid.get_auction().get_id());
                ps.setString(3, bid.get_bidder().get_id());
                ps.setTimestamp(4, Timestamp.valueOf(bid.get_time()));
                ps.setString(5, bid.get_id());
                ps.executeUpdate();
            } catch (SQLException e)
            {
                System.out.println(e.getMessage());
            }
    }
    public void delete(String id)
    {
        String sql = "DELETE FROM bid WHERE id = ?";
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
