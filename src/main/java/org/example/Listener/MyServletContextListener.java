package org.example.Listener;

import org.example.domein.Account;
import org.example.domein.Data;
import org.example.domein.Material;
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

            // lazy character
            Character sherb = new Character("Sherb", "Male","Lazy","Goat", "January 18th", "Forty winks is never enough." , "Sherb (レム, Remu) is a lazy goat villager introduced in New Horizons. His name comes from sherbet, a type of ice cream. His Japanese name, Rem, may be a reference to REM sleep, further supported by his apparent love of sleeping, and his favorite song being Hypno K.K. He has the nature hobby.");
            sherb.setPicture("https://pbs.twimg.com/media/EU9GRQRWAAAhi-E.jpg");
            Character hornsby = new Character("Hornsby", "Male", "Lazy", "Rhino", "March 20th", "You can't hit a mosquito with a horseshoe.", "Hornsby (みつお, Mitsuo) is a lazy rhino villager from the Animal Crossing series. His name is a play on the horn on his head. His picture quote in New Leaf seems to be a reference to his e-reader card profile. Hornsby was first seen in the GCN games, and did not appear in future iterations until the Welcome amiibo update in New Leaf. He was, however, found in the data for Happy Home Designer, but unlike many other unused villagers, he has no icon and only textures for him exist in the game's code.");
            hornsby.setPicture("https://vignette.wikia.nocookie.net/animalcrossing/images/9/95/Hornsby_NH.png/revision/latest/scale-to-width-down/333?cb=20200312014627");
            Character bob = new Character("Bob", "Male", "Lazy", "Cat", "January 1st", "I'm a cat. Chillin' is in my blood, man.", "Bob (ニコバン, Nikoban) is a lazy cat villager who has appeared in every game of the Animal Crossing series. His name is most likely based off the bobcat. His initial phrase, \"pthhhpth,\" may be a reference to the sound a cat makes when coughing up a hairball (this is further supported by the information on the e-Reader card which indicates he tends to spit often while speaking). His picture phrase in New Leaf is a feline spin on the memetic phrase, \"You only live once.\" He has the play hobby.");
            bob.setPicture("https://vignette.wikia.nocookie.net/animalcrossing/images/b/be/175px-Bob_NLa.png/revision/latest?cb=20200413200623");


            //normal character
            Character lilly = new Character("Lilly", "Female", "Normal", "Frog", "February 4th", "Don't jump to conclusions!", "Lily (レイニー Reinī, Rainy) is a frog villager in the Animal Crossing series with a normal personality. Her name may be a reference to lily pads. Her initial phrase in Animal Crossing is a play on the words \"toad\" and \"totally,\" which make \"toadally.\" She has appeared in every game in the Animal Crossing series so far. She has the education hobby.");
            lilly.setPicture("https://vignette.wikia.nocookie.net/animalcrossing/images/e/e8/Lili_%28New_Horizons%29.png/revision/latest/scale-to-width-down/340?cb=20200221074157&path-prefix=es");
            Character melba = new Character("Melba", "Female", "Normal", "Koala", "April 12th", "I just don't get it. Why would you come all this way just to sit in a dark theater, toasty", "Melba (アデレード, Aderēdo, Adelaide) is a normal koala villager in the Animal Crossing series. She first appeared in Wild World, and subsequently in City Folk and New Leaf. Melba's name may relate to the Australian city of Melbourne, also the Australian opera singer Dame Nelly Melba, who also represents her own ice-cream and Melba toast, which her phrase, \"toasty,\" is a reference to: a dry, crisp, and thinly-sliced piece of toast that originated and is popular in Australia, where koalas are also from. Another theory is that the phrase \"toasty\" references Australia being a constantly warm country. Her Japanese name refers to the Australian city of the same name. She replaces another female koala in the original games (with a different personality) called Huggy. She has the education hobby.");
            melba.setPicture("https://dodo.ac/np/images/thumb/4/43/Melba_NH.png/175px-Melba_NH.png");
            Character kiki = new Character("Kiki", "Female", "Normal", "Cat","October 8th", "Hang in there, kiddo.", "Kiki (キャビア, Kyabia, Caviar) is a normal cat villager which has appeared in every Animal Crossing series game. Her name may come from Hayao Miyazaki's Kiki's Delivery Service, an anime film about a young witch-in-training named Kiki who has a black cat. Her Japanese name refers to Caviar, a fish egg delicacy that cats love. She has the education hobby.\n" +
                    "Kiki may be a reference to the black cat, or it may come from the sound produced when saying \"kittykitty\" fast. Her quote on her picture in Wild World probably comes from the popular motivational poster of a cat hanging from a tree and saying \"Hang in there.\". Her New Leaf picture quote is akin to K.K. Slider's.");
            kiki.setPicture("https://vignette.wikia.nocookie.net/animalcrossing/images/4/4b/Kiki_NL.png/revision/latest/scale-to-width-down/247?cb=20130708021703");

            //sisterly
            Character cherry = new Character("Cherry", "Female","Sisterly", "Dog", "May 11th", "One dog barks at something, the rest bark at him.", "Cherry (ハンナ, Hanna) is a sisterly villager in New Leaf. Her name may come from her fur color, which is a darker pink tone, resembling a cherry. She has a music hobby.");
            cherry.setPicture("https://vignette.wikia.nocookie.net/animalcrossing/images/3/3e/Cherry_NH.png/revision/latest/scale-to-width-down/300?cb=20200524035737");
            Character hazel = new Character("Hazel", "Female","Sisterly","Squirrel","August 30th","Never say never!", "Hazel (アイリス, Airisu, Iris) is a sisterly squirrel villager who first appeared in New Leaf. Her name comes from hazelnut, a type of nut. A certain villager already had the name of Hazel, but due to Nintendo of America's localization error, this villager was given the name of Hazel. That villager was then renamed to Sally due to yet another localization error on Nintendo of America's part. Not only that, but the new name given, Sally, was already owned by another villager too, who was renamed Cally. Her initial phrase is a reference to her uni-brow, hinting that she is proud of it. She has the play hobby.");
            hazel.setPicture("https://vignette.wikia.nocookie.net/animalcrossing/images/1/17/Hazel_NH.png/revision/latest/scale-to-width-down/350?cb=20200309012107");
            Character katt = new Character("Katt", "Female", "Sisterly", "Cat", "April 27th", "MeowMEOWmeow!", "Katt (ちょい, Choi) is a sisterly cat villager who first appeared in New Leaf. Her name means 'cat' in Swedish and Norwegian, and her catchphrase refers to the word \"pretty\"; She is the only sisterly cat so far. She appears on the album cover for Spring Blossoms. \n" +
                    "She has the music hobby.");
            katt.setPicture("https://vignette.wikia.nocookie.net/animalcrossing/images/6/68/Katt_HD.png/revision/latest/scale-to-width-down/350?cb=20180404115701");
            Character phoebe = new Character("Phoebe", "Female", "Sisterly", "Ostrich", "April 22nd", "You know, I really have no idea what you do all day! Are you super sly, or am I just not paying attention?", "Phoebe (ヒノコ, Hinoko) is a sisterly ostrich villager who first appears in New Leaf And New Horizons. Both Phoebe's English and Japanese names are plays on the mythical phoenix--one of the Japanese words for which is hi-no-tori, roughly translating to \"firebird\". Her initial phrase is most likely a reference to the sparks that come from a fire. She appears on the album covers for K.K. Parade and K.K. Salsa. She has the fitness hobby.");
            phoebe.setPicture("https://vignette.wikia.nocookie.net/animalcrossing/images/5/50/Phoebe_NH.png/revision/latest/scale-to-width-down/242?cb=20200308185133");

            //snooty
            Character ankha = new Character("Ankha", "Female", "Snooty", "Cat", "September 22nd", "You again? You just can't seem to stay away from ME!", "Ankha (ナイル, Nairu, Nile) is a snooty cat villager in the Animal Crossing series, appearing in every game except Animal Forest and Wild World. She reappears in City Folk as well as New Leaf. In Animal Forest+, Animal Crossing, and in Animal Forest e+, she is an Islander. Her name is based on the ankh, which is the Egyptian hieroglyphic character that reads \"life.\" Alternatively, her name could come from the famous Egyptian pharaoh Tutankhamun, whose tomb inspired Ankha's hair colors and snake hairpiece. In New Leaf, her picture quote is a reference to the play \"The Merchant of Venice\" as Bassanio says the same about the lead casket.\n" + "Her Japanese name is \"Nile,\" which refers to the African river that many Egyptians settled on. Her German name, \"Kleo\" is short for \"Kleopatra,\" the German version of Cleopatra, the famous Egyptian queen. As an islander in Animal Crossing, Ankha loves cherries and is allergic to apples.\n" + "\n" + "She has the nature hobby.");
            ankha.setPicture("https://vignette.wikia.nocookie.net/animalcrossing/images/7/7e/Ankha_NLa.png/revision/latest?cb=20200413172251");
            Character diana = new Character("Diana", "Female", "Snooty", "Deer", "January 4th", "Don't look at me like that when I'm shopping! Even a fashion maven needs basic goods.", "Diana (ナタリー Natarī, Natalie) is a snooty deer villager who appears in New Leaf. She’s also the only snooty deer. Her name likely stems from Diana, the Roman goddess of the hunt, whose symbol was a deer. Her Spanish name Bambina means \"girl\" in Italian. It also bears a resemblance to \"Bambi\", a movie produced by Disney which features a deer fawn by the same name. Her initial phrase \"no doy\" is a variant of \"no duh\", a rather snooty phrase that is uttered to indicate that something said is quite blatantly obvious. She has the education hobby.");
            diana.setPicture("https://vignette.wikia.nocookie.net/animalcrossing/images/8/84/31a901eed448f9ee30fc1af858e29be5_clipped_rev_2.png/revision/latest?cb=20161008150800");
            Character claudia = new Character("Claudia", "Female", "Snooty", "Tiger", "November 22nd", "It looked chewy, I guess, but it certainly WASN'T fruit!", "Claudia (マリリン, Maririn, Marilyn) is a snooty tiger villager who first appeared in New Leaf. Her Japanese name is most likely a reference to Marilyn Monroe, while her English name a pun on \"claw\". Her initial catchphrase is used commonly in movies or television shows and dramas involving preppy girls and snobby girls. It is similar to the one of Petunia. She has the music hobby.");
            claudia.setPicture("https://vignette.wikia.nocookie.net/animalcrossing/images/d/d6/Claudia_NH.png/revision/latest/scale-to-width-down/283?cb=20200308194357");
            Character freya = new Character("Freya", "Female", "Snooty", "Wolf", "December 14th", "Having lots of hair is a big responsibility. Like owning a pet that sits on your head.", "Freya (ツンドラ, Tsundora, Tundra) is a snooty wolf villager from the Animal Crossing series. Her name comes from the Norse goddess of beauty, Freyja. Her Japanese name is the Japanese word for \"tundra\". Her catchphrase, \"uff da\", is a Norwegian exclamation that is an equivalent of the English \"Oh my\", \"That's too bad\", or \"Oh no\". It can be used to express concern. Freya was absent from Wild World, but reappeared in the subsequent games. She was the only female wolf until Animal Forest e+, where Vivian was introduced. She has the fashion hobby.");
            freya.setPicture("https://vignette.wikia.nocookie.net/animalcrossing/images/5/50/NH-icon-Freya_00.png/revision/latest/scale-to-width-down/248?cb=20200224182111");
            Character velma = new Character("Velma", "Female", "Snooty", "Goat", "January 14th", "Work smarter, not harder.", "Velma (ピティエ, Pithie) is a snooty goat villager from the Animal Crossing series. Her catchphrase comes from the sound goats make.\n" + "She is featured on the cover of K.K. Étude and she has the education hobby.");
            velma.setPicture("https://vignette.wikia.nocookie.net/animalcrossing/images/8/8c/Velma_high_quality.png/revision/latest?cb=20200518173242");

            //cranky
            Character admiral = new Character("Admiral", "Male", "Cranky", "Bird", "January 27th", "Hmm, it's still anyone's game! It all up in the air at this point!", "Admiral is a cranky bird villager in the Animal Crossing series. He was originally exclusive to the GCN games. His name and initial quote are both sailing terms - admiral being a title given to the supreme commander of a ship, and \"aye aye\" is a phrase of affirmation. His name may also be a pun/reference to the famous naval officer and explorer, Admiral Byrd. His Japanese name means \"stubborn\".\n" + "\n" + "Admiral returned to the series in New Leaf, as part of the Welcome amiibo update. By scanning amiibo cards, this villager can be met via the campground and invited to the town.\n" + "\n" + "He has the nature hobby.");
            admiral.setPicture("https://vignette.wikia.nocookie.net/animalcrossing/images/0/07/Admiral_HD.png/revision/latest/scale-to-width-down/350?cb=20180518160412");
            Character apollo = new Character("Apollo", "Male", "Cranky", "Eagle", "July 4th", "I'm not bald! I've just got fine feathers, that's all!", "Apollo (アポロ, Aporo?) is a cranky eagle villager in the Animal Crossing series. He also appears in Dōbutsu no Mori as a minor character. Apollo is a bald eagle, specifically, the national bird of the United States of America, hence his July 4 birthday (Independence Day in the United States). He has the music hobby.\n" + "Apollo's name is probably a reference to Apollo 11, the first manned mission to land on the moon. Its lunar module, LM-5, used the callsign \"Eagle\", and a bald eagle also appears on the mission patch. His name is all the more appropriate considering the rather arrogant personality of the Greek god Apollo (as well as many other deities of ancient mythology). He appears on the album cover for Soulful K.K.");
            apollo.setPicture("https://vignette.wikia.nocookie.net/animalcrossing/images/0/0c/NH-Apollo-Render.png/revision/latest/scale-to-width-down/350?cb=20200202215708");
            Character butch = new Character("Butch", "Male", "Cranky", "Dog", "November 1st", "A house without either a cat or a dog is a sad house indeed.", "Butch (ジョン, Jon, John) is a cranky dog villager in the Animal Crossing series. His name reflects on the cartoon name given to dogs, usually meaning muscular in appearance. Butch's rottweiler appearance may also relate to his name as they too appear masculine and muscular. His initial phrase, \"ROOOOOWF\", is a slight adjustment on the spelling of the common onomatopoeic words \"woof\" and \"ruff\". He is the first dog with a cranky personality. His Wild World picture quote is also exactly the same as Curt's picture quote. He has appeared in every game so far, including Pocket Camp.\n" + "He is featured on the cover of K.K. Country and he has the music hobby.");
            butch.setPicture("https://vignette.wikia.nocookie.net/animalcrossing/images/d/d2/Butch_-_Animal_Crossing_New_Leaf.png/revision/latest/scale-to-width-down/300?cb=20130708032928");











            //            Data.getData().clearCharacterList();

            Account mokko = new Account("mokko", "melanie123", "Melanie");
            Account admin = new Account("admin", "admintest", "test1");
            Account.setAdministrator(admin);

            Material softwood = new Material ("Softwood");
            Material treebranch = new Material("Treebranch");
            Material clay = new Material("Clay");
            Material goldnugget = new Material("Gold nugget");
            Material starfragment = new Material("Iron nugget");
            Material wood = new Material("Wood");
            Material hardwood = new Material("Hard wood");
            Material stone = new Material("Stone");
            Material clumpofweeds = new Material("Clump of weeds");
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


