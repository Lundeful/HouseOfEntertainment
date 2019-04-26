package com.hoe.model;

import java.util.ArrayList;


public class HoE {
    private Database database;

    public HoE() {
        database = new Database();
    }

    public void addShow(String name, String type, String date, String time, Location location,
                        String ticketPrice, String program) {
        Show show = new Show("TEMP-ID", formatInput(name)); // TODO: Use ID-generator

        show.setShowType(formatInput(type));
        show.setDate(formatInput(date));
        show.setTime(formatInput(time));
        show.setLocation(location);
        show.setTicketPrice(formatInput(ticketPrice));
        show.setProgram(formatInput(program));

        database.addShow(show);
    }

    private String formatInput(String s) {
        return s.trim();
    }

    public boolean removeShow(String showID) {
        for (Show show: database.getShows()) {
            if (show.getShowID().equals(showID)) {
                database.removeShow(show);
                return true; // Show with matching ID found
            }
        }
        return false; // No shows with matching show ID found
    }

    public ArrayList<Show> getShows() {
        return database.getShows();
    }
}
