package com.hoe.model;

import com.hoe.model.exceptions.NotEnoughSeatsException;

import java.time.Clock;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class TestDataBase {
    IDCreator id = new IDCreator();
    Show s = new Show("","");
    Location l = new Location("","");
    ContactPerson c = new ContactPerson("", "", "");
    Ticket t = new Ticket("",s,"","");

    public String[] generateRandomWords(int numberOfWords){
        String[] randomStrings = new String[numberOfWords];
        Random random = new Random();
        for(int i = 0; i < numberOfWords; i++)
        {
            char[] word = new char[random.nextInt(8)+3]; // words of length 3 through 10. (1 and 2 letter words are boring.)
            for(int j = 0; j < word.length; j++)
            {
                word[j] = (char)('a' + random.nextInt(26));
            }
            randomStrings[i] = new String(word);
        }
        return randomStrings;
    }

    public Database generateTestObjects() throws NotEnoughSeatsException {
        Database database = new Database();
        Location l1 = new Location(id.randomKeyGen(l), "Big Hall");
        l1.setNumberOfSeats(578);
        l1.setTypeOfLocation("Theatre");

        Location l2 = new Location(id.randomKeyGen(l), "Small stage");
        l2.setTypeOfLocation("Theatre");
        l2.setNumberOfSeats(300);

        Location l3 = new Location(id.randomKeyGen(l), "Supreme");
        l2.setNumberOfSeats(258);
        l2.setTypeOfLocation("Movie theatre");

        Location l4 = new Location(id.randomKeyGen(l), "IMAX");
        l4.setTypeOfLocation("Movie Theatre");
        l4.setNumberOfSeats(420);

        Location l5 = new Location((id.randomKeyGen(l)), "Liv Ullmann stage");
        l5.setTypeOfLocation("Movie Theatre");
        l5.setNumberOfSeats(568);

        Location l6 = new Location(id.randomKeyGen(l), "Ultra Supreme");
        l6.setTypeOfLocation("Movie Theatre");
        l6.setNumberOfSeats(301);

        database.addLocation(l1);
        database.addLocation(l2);
        database.addLocation(l3);
        database.addLocation(l4);
        database.addLocation(l5);
        database.addLocation(l6);

        ContactPerson c1 = new ContactPerson(id.randomKeyGen(c), "Emma Hansen", "54937654");
        c1.setWebsite("www.vg.no");
        c1.setEmail("e.h@gmail.com");
        ContactPerson c2 = new ContactPerson(id.randomKeyGen(c), "Oskar Pettersen", "98776123");
        c2.setWebsite("www.nrk.no");
        c2.setEmail("o.p@gmail.com");
        ContactPerson c3 = new ContactPerson(id.randomKeyGen(c), "Jakob Olsen", "78690435");
        c3.setWebsite("www.stackoverflow.com");
        c3.setEmail("j.o@gmail.com");
        ContactPerson c4 = new ContactPerson(id.randomKeyGen(c), "Aksel Nilsen", "90478234");
        c4.setEmail("a.n@gmail.com");
        ContactPerson c5 = new ContactPerson(id.randomKeyGen(c), "Amalie Berg", "94587143");
        c5.setEmail("a.m@gmail.com");
        ContactPerson c6 = new ContactPerson(id.randomKeyGen(c), "Sara Eriksen", "95476985");
        c6.setEmail("s.e@gmail.com");
        database.addContact(c1);
        database.addContact(c2);
        database.addContact(c3);
        database.addContact(c4);
        database.addContact(c5);
        database.addContact(c6);

        /*addShow("Harry potter", "Movie", "28-10-2019", "", l4, "120", "");
        addShow("Cats", "Stage show", "", "Midnight", l2, "260", "");
        addShow("Bohemian Rhapsody", "Movie", "Mid-night", "", l4, "190", "");
        addShow("AC/DC", "Concert", "", "", l1, "499", ""); */
        String[] ord = {"Little", "Curse", "Captain", "Harry", "Amazing", "Missing", "Dragon", "Hotel", "Red", "Five",
                "High", "Best", "Family", "White", "Black", "Movie", "Man", "Woman", "True", "False", "The", "Hacker",
                "Mighty", "Dancing", "Huge", "Single", "Big", "Schindler's", "Angry", "Lord of the", "Unusual",
        "Terminator 5:", "American"};
        String[] ord2 = {"Dead", "Walking", "Game", "Weapon", "Lord", "Kill", "Amazing", "Suit", "Machine", "Jesus",
        "Store", "Fight", "Killing", "Computer", "Shining", "Fantastic", "Flight", "Airplane", "Train", "Bond",
        "Chungus", "Death", "Girl", "Batman", "List", "Matrix", "Spiderman", "- Endgame", "Whiplash", "Joker", "History"
        ,"Knight"};

        for (int i = 0; i < 1000; i++) {
            int loc = i % 6;
            int per = i % 6;
            int showWord1 = i % ord.length;
            int showWord2 = i % ord2.length;

            Show show = new Show(id.randomKeyGen(s),ord[showWord1] + " " + ord2[showWord2]);
            show.setLocation(database.getLocations().get(loc));
            show.setAvailableTickets(show.getLocation().getNumberOfSeats());
            show.setTime(randomTime());
            show.setDate(randomDate());
            show.setShowType(show.getLocation().getTypeOfLocation());
            show.setContactPerson(database.getContacts().get(per));
            for(int j = 0; j < show.getAvailableTickets()-10; j++ ){
                Ticket ticket = new Ticket(id.randomKeyGen(t),show, "9" + String.valueOf(randomInt(7)), String.valueOf(randomInt(3)));
                database.addTicket(ticket);
            }
            database.addShow(show);
        }
        return database;
    }

    private int randomInt(int n){
        int m = (int) Math.pow(10, n - 1);
        return m + new Random().nextInt(9 * m);
    }

    private String randomDate(){
        LocalDate today = LocalDate.now();
        long start = today.toEpochDay();


        LocalDate maxDate = LocalDate.of(2021,01,01);
        long end = maxDate.toEpochDay();

        long randomDay = ThreadLocalRandom.current().longs(start, end).findAny().getAsLong();
        return LocalDate.ofEpochDay(randomDay).toString();

    }

    private String randomTime(){
        LocalTime currentLocal = LocalTime.of(0,0);
        long  starTime = currentLocal.toSecondOfDay();

        LocalTime max = LocalTime.of(23,59);
        long endTime = max.toSecondOfDay();

        long  randomTime = ThreadLocalRandom.current().longs(starTime, endTime).findAny().getAsLong();
        return LocalTime.ofSecondOfDay(randomTime).toString();

    }
}
