package service;

import java.util.*;
import models.*;

public class Notificationservice {
    private final ArrayList<Notification> notifications = new ArrayList<>();

    //send notification
    public Notification send_notification(User user, String message)
    {
        Notification notification = new Notification(user, message);
        notifications.add(notification);
        return notification;
    }

    //returning all available notifications right now
    public ArrayList<Notification> get_all_notifications(String userid)
    {
        ArrayList<Notification> all_notifications = new ArrayList<>();
        for (Notification notification: notifications)
        {
            if (notification.get_user().get_id().equals(userid))
            {
                all_notifications.add(notification);
            }
        }
        return all_notifications;
    }
}
