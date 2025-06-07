package dao;

import models.Buyer;
import util.DBConnection;
import java.sql.*;
import java.util.*;

public class BuyerDAO {
    public void create(Buyer buyer)
    {
        String sql = "INSERT INTO buyer(id, name, email) VALUES (?, ?, ?)";
        try (Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(sql))
            {
                ps.setString(1, buyer.get_id());
                ps.setString(2, buyer.get_name());
                ps.setString(3, buyer.get_email());
                ps.executeUpdate();
            } catch (SQLException e)
            {
                System.out.println(e.getMessage());
            }
    }
    public Buyer readById(String id)
    {
        String sql = "SELECT * FROM buyer WHERE id = ?";
        try (Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(sql))
            {
                ps.setString(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next())
                {
                    return new Buyer(rs.getString("id"), rs.getString("name"), rs.getString("email"));
                }
            } catch (SQLException e)
            {
                System.out.println(e.getMessage());
            }
        return null;
    }
    public List<Buyer> readAll()
    {
        List<Buyer> buyers = new ArrayList<>();
        String sql = "SELECT * FROM buyer";
        try (Connection c = DBConnection.getConnection();
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(sql))
            {
                while (rs.next())
                {
                    buyers.add(new Buyer((rs.getString("id")), rs.getString("name"), rs.getString("email")));
                }
            } catch (SQLException e)
            {
                System.out.println(e.getMessage());
            }
        return buyers;
    }
    public void update(Buyer buyer)
    {
        String sql = "UPDATE buyer SET name = ?, email = ? WHERE id = ?";
        try (Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(sql))
            {
                ps.setString(1, buyer.get_name());
                ps.setString(2, buyer.get_email());
                ps.setString(3, buyer.get_id());
                ps.executeUpdate();
            } catch (SQLException e)
            {
                System.out.println(e.getMessage());
            }
    }
    public void delete(String id)
    {
        String sql = "DELETE FROM buyer WHERE id = ?";
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
