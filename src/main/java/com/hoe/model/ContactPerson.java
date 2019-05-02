package com.hoe.model;

public class ContactPerson {

    private final String contactID;
    private String name;
    private String phoneNumber;
    private String email;
    private String website;
    private String affiliation;
    private String other;

    public ContactPerson(String id, String name, String contactID){
        this.contactID = contactID; // TODO(1): Method that generates ID's
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getWebsite() {
        return website;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public String getOther() {
        return other;
    }

    @Override
    public String toString() {
        return name + " - " + phoneNumber;
    }
}
