/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopapp.datamodel;

/**
 *
 * @author Nathan
 */
public class Admin {

    private int adminID;
    private int branchID;
    private String forename;
    private String surname;
    private String username;
    
    public Admin() {
    }
    
    public Admin (int admID, int branID, String firstName, String lastName, String user) {
        this.adminID = admID;
        this.branchID = branID;
        this.forename = firstName;
        this.surname = lastName;
        this.username = user;               
    }
    
    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public int getBranchID() {
        return branchID;
    }

    public void setBranchID(int branchID) {
        this.branchID = branchID;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String toString() {
        return "Admin [adminID=" + adminID + ", branchID=" + branchID + ", "
                + "forename=" + forename + ", surname" + surname + ", username=" + username + "]";                  
    }   
    
    
            
}
