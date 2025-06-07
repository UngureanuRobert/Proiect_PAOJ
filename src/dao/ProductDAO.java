package dao;

import models.Product;
import models.Category;
import util.DBConnection;
import java.sql.*;
import java.util.*;

public class ProductDAO {
    public void create(Product product)
    {
        String sql = "INSERT INTO product(id, name, description, category) VALUES (?, ?, ?, ?)";
        try (Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(sql))
            {
                ps.setString(1, product.get_id());
                ps.setString(2, product.get_name());
                ps.setString(3, product.get_description());
                ps.setString(4, product.get_category().toString());
                ps.executeUpdate();
            } catch (SQLException e)
            {
                System.out.println(e.getMessage());
            }
    }
    public Product readById(String id)
    {
        String sql = "SELECT * FROM product WHERE id = ?";
        try (Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(sql))
            {
                ps.setString(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next())
                {
                    return new Product(rs.getString("id"), rs.getString("name"), rs.getString("description"), Category.valueOf(rs.getString("category")));
                }
            } catch (SQLException e)
            {
                System.out.println(e.getMessage());
            }
        return null;
    }
    public List<Product> readAll()
    {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product";
        try (Connection c = DBConnection.getConnection();
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(sql))
            {
                while (rs.next())
                {
                    products.add(new Product(rs.getString("id"), rs.getString("name"), rs.getString("description"), Category.valueOf(rs.getString("category"))));
                }
            } catch (SQLException e)
            {
                System.out.println(e.getMessage());
            }
        return products;
    }
    public void update(Product product)
    {
        String sql = "UPDATE product SET name = ?, description = ?, category = ? WHERE id = ?";
        try (Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(sql))
            {
                ps.setString(1, product.get_name());
                ps.setString(2, product.get_description());
                ps.setString(3, product.get_category().toString());
                ps.setString(4, product.get_id());
                ps.executeUpdate();
            } catch (SQLException e)
            {
                System.out.println(e.getMessage());
            }
    }
    public void delete(String id)
    {
        String sql = "DELETE FROM product WHERE id = ?";
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
