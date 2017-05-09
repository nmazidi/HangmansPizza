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
public class Customer {

    private int customerID;
    private String title;
    private String forename;
    private String surname;
    private String email;
    private String phoneNumber;
    private Date dateOfBirth;
    
    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
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

    public void setForename(String forname) {
        this.forename = forname;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }    
   
    public Customer() {
    }
    
    public Customer(int custID, String titl, String firstName, String lastName,
            String emailAddress, String phone, Date dob) {
        this.customerID = custID;
        this.title = titl;
        this.forename = firstName;
        this.surname = lastName;
        this.email = emailAddress;
        this.phoneNumber = phone;
        this.dateOfBirth = dob;
    }
    
    
    @Override
    public String toString()
    {
        return "Customer [customerID=" + customerID + ", tltle=" + title + ", "
                + "forename=" + forename + "surname=" + surname + ", "
                + "email=" + email + ", phoneNumber=" + phoneNumber + ", dateOfBirth=" + dateOfBirth + "]";                
    }
    
    
}
