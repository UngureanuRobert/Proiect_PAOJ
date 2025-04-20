package models;

import java.util.UUID;

public class User {
    private final String id;
    private String name;
    private String email;

    public User(String name, String email)
    {
        this.id = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.name = name;
        this.email = email;
    }

    public String get_id()
    {
        return this.id;
    }

    public String get_name()
    {
        return this.name;
    }

    public String get_email()
    {
        return this.email;
    }

    public void set_name(String name)
    {
        this.name = name;
    }

    public void set_email(String email)
    {
        this.email = email;
    }

    @Override
    public String toString()
    {
        return "User: " + this.id + " " + this.name + "; email: " + this.email;
    }
}
