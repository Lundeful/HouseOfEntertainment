package com.hoe.model;

import com.hoe.model.dataloading.FileSelecter;
import com.hoe.model.dataloading.JobjLoader;
import com.hoe.model.datasaving.DirectorySelector;
import com.hoe.model.datasaving.JobjSaver;

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

    public boolean removeShow(Show s) {
        return database.removeShow(s);
    }
    public ArrayList<Show> getShows() {
        return database.getShows();
    }

    public boolean save() {
        try {
            DirectorySelector d = new DirectorySelector();
            JobjSaver save = new JobjSaver();
            save.saveData(d.directoryChooser() + "save.ser", database);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public Boolean load() {
        try {
            FileSelecter f = new FileSelecter();
            JobjLoader loader = new JobjLoader();
            database = loader.loadData(f.fileChooser());
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
