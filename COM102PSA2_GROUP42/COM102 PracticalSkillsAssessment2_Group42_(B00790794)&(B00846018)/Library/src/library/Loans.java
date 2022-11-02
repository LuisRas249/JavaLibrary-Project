/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author luisr
 */
public class Loans {
    // properties
    private String Barcode;
    private String User_ID;
    private String Issue_Date;
    private String Due_Date;
    private int Num_Renews;
    //constructors
    public Loans(String Barcode, String User_ID, String Issue_Date, String Due_Date, int Num_Renews) {
        this.Barcode = Barcode;
        this.User_ID = User_ID;
        this.Issue_Date = Issue_Date;
        this.Due_Date = Due_Date;
        this.Num_Renews = Num_Renews;
    }

    public Loans() {
        this.Barcode = Barcode;
        this.User_ID = User_ID;
        this.Issue_Date = Issue_Date;
        this.Due_Date = Due_Date;
        this.Num_Renews = Num_Renews;
        
    }

    

    public String getBarcode() {
        return Barcode;
    }

    public void setBarcode(String bCode) {
        this.Barcode = bCode;
    }

    public String getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(String userID) {
        this.User_ID = userID;
    }

    public String getIssue_Date() {
        return Issue_Date;
    }

    public void setIssue_DateInput(String issueDate) {
        this.Issue_Date = issueDate;
    }

    public String getDue_Date() {
        return Due_Date;
    }

    public void setDue_DateInput(String dueDate) {
        this.Due_Date = dueDate;
    }

    public int getNum_Renews() {
        return Num_Renews;
    }

    public void setNum_RenewsInput(int numRenews) {
        this.Num_Renews = numRenews;
    }

    

    public void displayDetails() {
        System.out.println();
        System.out.println("-----NEW LOAN HAS BEEN ADDED-----");
        System.out.println("Barcode: " + this.Barcode);
        System.out.println("User ID: " + this.User_ID);
        System.out.println("Issue Date: " + this.Issue_Date);
        System.out.println("Due Date: " + this.Due_Date);
        System.out.println("Number of Renews: 0");

    }

}
