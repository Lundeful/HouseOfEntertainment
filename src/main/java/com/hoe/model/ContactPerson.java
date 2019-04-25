package com.hoe.model;

/**
 * This is the standard Contact Person class. This class defines what a Contact Person is and what sort of data fields
 * the object should have.
 */
public class ContactPerson {

    private final String contactID;
    private String name;
    private String phoneNumber;
    private String email;
    private String website;
    private String affiliation;
    private String other;

    /**
     * Standard constructor for the ContactPerson class.
     * @param contactID Takes in a unique ID for the Contact Person.
     */
    public ContactPerson(String contactID){
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

    public String getContactID() {
        return contactID;
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

    /**
     * This method is meant to print out a string with a CSV format so that is it easier to save.
     * @return Returns a String with complete information and in CSV-format.
     */
    public String toCSVString(){
        return getContactID() + "|" + getName() + "|" + getPhoneNumber() + "|" + getEmail() + "|" +
                getWebsite() + "|" + getAffiliation() + "|" + getOther();
    }

}
