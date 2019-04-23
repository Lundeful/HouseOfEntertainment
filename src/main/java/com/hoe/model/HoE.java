package com.hoe.model;

public class HoE {
    private Database database;

    public HoE() {
        database = new Database();
    }

    public void addShow(String showName) {
        database.addShow(new Show("ID-Temp", showName)); // TODO: Use ID-generator instead
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
}
