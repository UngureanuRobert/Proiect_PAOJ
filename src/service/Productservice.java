package service;

import java.util.*;
import models.*;

public class Productservice {
    private final Map<String, Product> products = new HashMap<>();

    //adding a product
    public Product add_product(String name, String description, Category category)
    {
        Product product = new Product(name, description, category);
        products.put(product.get_id(), product);
        return product;
    }

    //see all the products
    public ArrayList<Product> get_products()
    {
        return new ArrayList<>(products.values());
    }
}
