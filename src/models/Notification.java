package models;

import java.time.LocalDateTime;
import java.util.UUID;

public class Notification {
    private final String id;
    private final User user;
    private final String message;
    private final LocalDateTime time;

    public Notification(User user, String message)
    {
        this.id = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.user = user;
        this.message = message;
        this.time = LocalDateTime.now();
    }

    public String get_id()
    {
        return this.id;
    }

    public User get_user()
    {
        return this.user;
    }

    public String get_message()
    {
        return this.message;
    }

    public LocalDateTime get_time()
    {
        return this.time;
    }

    @Override
    public String toString()
    {
        return this.time + " - " + this.user.get_name() + ": " + this.message;
    }
}
