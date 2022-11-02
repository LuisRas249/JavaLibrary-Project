/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

/**
 *
 * @author luisr
 * 
 * 
 */
public class Users {
    // properties
    private String User_ID;
    private String First_Name;
    private String Last_Name;
    private String Email;
    //constructors
    Users(String User_ID, String First_Name, String Last_Name, String Email) {
        this.User_ID = User_ID;
        this.First_Name = First_Name;
        this.Last_Name = Last_Name;
        this.Email = Email;
    }
    
    public String getUser_ID(){
        return User_ID;
    }
    public String getFirst_Name(){
        return First_Name;
    }
    public String getLast_Name(){
        return Last_Name;
    }
    public String getEmail(){
        return Email;
    }
    

    public String toString() {
        return User_ID+ " " + First_Name + " " + Last_Name + " " + Email;
    }
}
