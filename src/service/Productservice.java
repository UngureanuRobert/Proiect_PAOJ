package service;

import java.util.*;
import models.*;
import dao.ProductDAO;

public class Productservice {
    private final ProductDAO productDAO = new ProductDAO();

    //add a product
    public Product add_product(String name, String description, Category category)
    {
        Product product = new Product(name, description, category);
        productDAO.create(product);
        return product;
    }

    //get product by id
    public Product get_product_by_id(String id)
    {
        return productDAO.readById(id);
    }

    //see all the products
    public ArrayList<Product> get_products()
    {
        List<Product> products = productDAO.readAll();
        return new ArrayList<>(products);
    }

    //update product
    public void update_product(Product product)
    {
        productDAO.update(product);
    }

    //delete product
    public void delete_product(String id)
    {
        productDAO.delete(id);
    }
}
