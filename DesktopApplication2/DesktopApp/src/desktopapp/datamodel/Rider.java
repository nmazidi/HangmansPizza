/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopapp.datamodel;

import java.util.Date;

/**
 *
 * @author Nathan
 */
public class Rider {
    private int riderID;
    private String title;
    private String forename;
    private String surname;
    private String email;
    private String phoneNumber;
    private String vehicleType;
    private Date dateOfBirth;
    
    public int getRiderID() {
        return riderID;
    }

    public void setRiderID(int riderID) {
        this.riderID = riderID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }   
    
    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    public Rider() {
    }
    
    public Rider(int ridID, String titl, String firstName, String lastName,
            String emailAddress, String phone, String vehicle, Date dob) {
        this.riderID = ridID;
        this.title = titl;
        this.forename = firstName;
        this.surname = lastName;
        this.email = emailAddress;
        this.phoneNumber = phone;
        this.vehicleType = vehicle;
        this.dateOfBirth = dob;
    }
    
    @Override
    public String toString()
    {
        return "Rider [riderID=" + riderID + ", tltle=" + title + ", "
                + "forename=" + forename + "surname=" + surname + ", "
                + "email=" + email + ", phoneNumber=" + phoneNumber + ", "
                + "vehicleType=" + vehicleType + "dateOfBirth=" + dateOfBirth + "]";                
    }
}
