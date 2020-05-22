package org.example.Listener;

import org.example.persistance.PersistenceManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import org.example.domein.Character;

@WebListener
public class MyServletContextListener implements ServletContextListener {

    Character sherb = new Character("Sherb", "Male","Lazy","Goat", "January 18");
    Character lilly = new Character("Lilly", "Male","Lazy","Goat", "January 16");

    public void contextInitialized(ServletContextEvent sce) {

        try {
            PersistenceManager.loadDataFromAzure();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
        try {
            PersistenceManager.saveDataToAzure();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


