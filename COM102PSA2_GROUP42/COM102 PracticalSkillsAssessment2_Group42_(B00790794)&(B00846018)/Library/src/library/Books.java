/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

/**
 *
 * @author luisr
 */
public class Books extends Loans{
    protected int BMAX;
    
     public Books(String Barcode, String User_ID, String Issue_Date, String Due_Date, int Num_Renews, int BMAX) {
        super(Barcode, User_ID, Issue_Date, Due_Date, Num_Renews);
        this.BMAX = 3;
    }
}
