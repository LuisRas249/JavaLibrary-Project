/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.io.Serializable;

/**
 *
 * @author luisr
 */
class Items implements Serializable {
    // properties
    private int Barcode;
    private String Author;
    private String Title;
    private String Type;
    private int Year;
    private int ISBN;
    
    
    //constructors
    Items(int Barcode, String Author, String Title, String Type, int Year, int ISBN) {
        this.Barcode = Barcode;
        this.Author = Author;
        this.Title = Title;
        this.Type = Type;
        this.Year = Year;
        this.ISBN = ISBN;
    }
    
    public int getBarcode(){
        return Barcode;
    }
    public String getUser_ID(){
        return Author;
    }
    public String getTitle(){
        return Title;
    }
    public String getType(){
        return Type;
    }
    public int getYear(){
        return Year;
    }
    public int getISBN(){
        return ISBN;
    }

    public String toString() {
        return Barcode + " " + Author+ " " + Title + " " + Type + " " + Year + "" + ISBN;
    }
    
}
