package org.example.Listener;

import org.example.persistance.PersistenceManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.time.Duration;

import org.example.domein.Character;
import reactor.core.scheduler.Schedulers;
import reactor.netty.http.HttpResources;

@WebListener
public class MyServletContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {

        System.out.println("Systeem is aan het opstarten.");

        try {
            PersistenceManager.loadDataFromAzure();
            Character sherb = new Character("Sherb", "Male","Lazy","Goat", "January 18th", "Forty winks is never enough." , "Sherb (レム, Remu) is a lazy goat villager introduced in New Horizons. His name comes from sherbet, a type of ice cream. His Japanese name, Rem, may be a reference to REM sleep, further supported by his apparent love of sleeping, and his favorite song being Hypno K.K. He has the nature hobby.");
            sherb.setPicture("https://pbs.twimg.com/media/EU9GRQRWAAAhi-E.jpg");
            Character lilly = new Character("Lilly", "Female", "Normal", "Frog", "February 4th", "Don't jump to conclusions!", "Lily (レイニー Reinī, Rainy) is a frog villager in the Animal Crossing series with a normal personality. Her name may be a reference to lily pads. Her initial phrase in Animal Crossing is a play on the words \"toad\" and \"totally,\" which make \"toadally.\" She has appeared in every game in the Animal Crossing series so far. She has the education hobby.");
            lilly.setPicture("https://vignette.wikia.nocookie.net/animalcrossing/images/e/e8/Lili_%28New_Horizons%29.png/revision/latest/scale-to-width-down/340?cb=20200221074157&path-prefix=es");
            Character hornsby = new Character("Hornsby", "Male", "Lazy", "Rhino", "March 20th", "You can't hit a mosquito with a horseshoe.", "Hornsby (みつお, Mitsuo) is a lazy rhino villager from the Animal Crossing series. His name is a play on the horn on his head. His picture quote in New Leaf seems to be a reference to his e-reader card profile. Hornsby was first seen in the GCN games, and did not appear in future iterations until the Welcome amiibo update in New Leaf. He was, however, found in the data for Happy Home Designer, but unlike many other unused villagers, he has no icon and only textures for him exist in the game's code.");
            hornsby.setPicture("https://vignette.wikia.nocookie.net/animalcrossing/images/9/95/Hornsby_NH.png/revision/latest/scale-to-width-down/333?cb=20200312014627");

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Syseem is aan het afsluiten.");
        Schedulers.shutdownNow();
        HttpResources.disposeLoopsAndConnectionsLater(Duration.ZERO, Duration.ZERO).block();
        try {
            PersistenceManager.saveDataToAzure();
            System.out.println("Opgeslagen");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


