package models;

import java.util.UUID;

public class Product {
    private final String id;
    private String name;
    private String description;
    private Category category;

    public Product(String name, String description, Category category)
    {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public String get_id()
    {
        return this.id;
    }

    public String get_name()
    {
        return this.name;
    }

    public String get_description()
    {
        return this.description;
    }

    public Category get_category()
    {
        return this.category;
    }

    public void set_name(String name)
    {
        this.name = name;
    }

    public void set_description(String description)
    {
        this.description = description;
    }

    public void set_category(Category category)
    {
        this.category = category;
    }

    @Override
    public String toString()
    {
        return "product: " + this.name + "; category: " + this.category;
    }
}
