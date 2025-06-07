package service;

import java.io.*;
import java.time.LocalDateTime;

public class Auditservice {
    private static Auditservice instance = null;

    public Auditservice() {}

    public static Auditservice getInstance()
    {
        if (instance == null)
        {
            instance = new Auditservice();
        }
        return instance;
    }

    public void logAction(String action_name)
    {
        try (PrintWriter pw = new PrintWriter(new FileWriter("audit.csv", true)))
        {
            String timestamp = LocalDateTime.now().toString();
            pw.println(action_name + ", " + timestamp);
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
