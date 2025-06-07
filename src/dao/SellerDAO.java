package dao;

import models.Seller;
import util.DBConnection;
import java.sql.*;
import java.util.*;

public class SellerDAO {
    public void create(Seller seller)
    {
        String sql = "INSERT INTO seller(id, name, email) VALUES (?, ?, ?)";
        try (Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(sql))
            {
                ps.setString(1, seller.get_id());
                ps.setString(2, seller.get_name());
                ps.setString(3, seller.get_email());
                ps.executeUpdate();
            } catch (SQLException e)
            {
                System.out.println(e.getMessage());
            }
    }
    public Seller readById(String id)
    {
        String sql = "SELECT * FROM seller WHERE id = ?";
        try (Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(sql))
            {
                ps.setString(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next())
                {
                    return new Seller(rs.getString("id"), rs.getString("name"), rs.getString("email"));
                }
            } catch (SQLException e)
            {
                System.out.println(e.getMessage());
            }
        return null;
    }
    public List<Seller> readAll()
    {
        List<Seller> sellers = new ArrayList<>();
        String sql = "SELECT * FROM seller";
        try (Connection c = DBConnection.getConnection();
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(sql))
            {
                while (rs.next())
                {
                    sellers.add(new Seller(rs.getString("id"), rs.getString("name"), rs.getString("email")));
                }
            } catch (SQLException e)
            {
                System.out.println(e.getMessage());
            }
        return sellers;
    }
    public void update(Seller seller)
    {
        String sql = "UPDATE seller SET name = ?, email = ? WHERE id = ?";
        try (Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(sql))
            {
                ps.setString(1, seller.get_name());
                ps.setString(2, seller.get_email());
                ps.setString(3, seller.get_id());
                ps.executeUpdate();
            } catch (SQLException e)
            {
                System.out.println(e.getMessage());
            }
    }
    public void delete(String id)
    {
        String sql = "DELETE FROM seller WHERE id = ?";
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
