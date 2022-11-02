package library;

import java.io.*;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

/*
COM102 CW File 
 Luis Ras: B00790794  
 Stephen McGirr: B00846018
 Program reads in a list of items, users, and loans and parses themes items into a 2d list
 From here,a menu is displayed to the user to facilitate a number of queries on the data.
 v1.0 07/04/2022
 442 lines (Comments Included)
 */

public class Library {

    public static void main(String[] args) throws IOException {
        // This will display/read all of the files (When the program is started, it reads from the three csv files )
        readAllCSVFiles();
        // This will start the main menu method for the user to do certain operations within the program
        MainMenu();

    }

    public static void MainMenu() throws FileNotFoundException, IOException {
        Scanner input = new Scanner(System.in);
        boolean mainLoop = true;
        // This will allow the menu to be displayed and facilitate a number of queries for the user depending on what has been entered
        int choice;
        do {
            System.out.println();
            System.out.println("-----LIBRARY MAIN MENU-----");
            System.out.print("1.) Loan and Item Search \n");
            System.out.print("2.) Issue an Item \n");
            System.out.print("3.) Renew/Update Existing Loan \n");
            System.out.print("4.) Return Item \n");
            System.out.print("5.) View Current Loan and View All Items \n");
            System.out.print("6.) Exit\n");
            System.out.print("\nEnter Your Menu Choice: ");

            choice = input.nextInt();
            switch (choice) {

                case 1:
                    searchFromFile(); // This will allow the user to open this class method to search if a loan and item data is available
                    MainMenu();//The main menu will open again after the user has completed their query
                    break;

                case 2:
                    getLoans();//This will allow the user to open this class method to add in a new loan in the file
                    MainMenu();//The main menu will open again after the user has completed their query
                    break;

                case 3:
                    getRenewLoans();//This will allow the user to open this class method to update a specific loan in the loan file
                    MainMenu();//The main menu will open again after the user has completed their query
                    break;

                case 4:
                    returnItem();//This will allow the user to open this class method to remove a specific loan
                    MainMenu();//The main menu will open again after the user has completed their query
                    break;

                case 5:
                    Scanner sc1 = new Scanner(new File("src\\LOANS.csv"));//Get LOAN file
                    Scanner sc2 = new Scanner(new File("src\\ITEMS.csv"));//Get Items file
                    //parsing a CSV file into the constructor of Scanner class 
                    sc1.useDelimiter(" ");
                    sc2.useDelimiter(" ");
                    //setting space as delimiter pattern
                    System.out.println();
                    System.out.println("-----CURRENT LIBRARY LOANS-----");
                    while (sc1.hasNext()) {
                        System.out.print(sc1.next()); //This will print out the current loans in the database
                    }
                    System.out.println();
                    System.out.println("-----ALL LIBRARY ITEMS-----");
                    while (sc2.hasNext()) {
                        System.out.print(sc2.next());//This will print out the current items in the database
                    }
                    sc1.close();
                    sc2.close();
                    MainMenu();
                    break;

                case 6:
                    System.out.println("Exiting Program..."); // This option will fully close the program
                    System.exit(0);
                    break;
                default: //If an invalid option has been entered an error message will display
                    System.out.println("This is not a valid Menu Option! Please Select The Available Options In The Menu...");
                    MainMenu();
                    break;
            }
        } while (choice > 7);
    }

    public static void getLoans() {
        PrintWriter pw = null;

        try {
            pw = new PrintWriter("src\\LOANS.csv");
            pw.println("Barcode, User_id, Issue_Date, Due_Date, numRenews");

        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        Loans[] loans = new Loans[10];
        //initilising an instance using the first constructor
        loans[0] = new Loans("340096334", "B00187440", "17/11/2021", "08/12/2021", 0);
        produceLoanReport(pw, loans[0]);
        //initialising an istance using the second constructor
        loans[1] = new Loans("25832497", "B00447489", "16/11/2021", "07/12/2021", 0);
        produceLoanReport(pw, loans[1]);
        //initialising an instanc eusing the default constructor with details being entered from keyboard
        loans[2] = new Loans("240453126", "B00187440", "15/11/2021", "06/12/2021", 0);
        produceLoanReport(pw, loans[2]);
        loans[3] = new Loans("813844363", "B00986808", "01/10/2021", "22/10/2021", 0);
        produceLoanReport(pw, loans[3]);
        loans[4] = new Loans("530038220", "B00986808", "01/10/2021", "22/10/2021", 0);
        produceLoanReport(pw, loans[4]);
        loans[5] = new Loans();
        //create the first instance using the default constructor with details being entered from keyboard
        Scanner cin = new Scanner(System.in);
        readDetailsFromUserInput(cin, loans[5]);
        produceLoanReport(pw, loans[5]);
        loans[5].displayDetails();
        //Read the details of the other multiple Loans from a file
        Scanner in = null;
        //the first scan to get the number of Loans recorded in a file
        int nLines = 0;
        try {
            in = new Scanner(new File("src\\LOANS.csv"));
            while (in.hasNextLine()) {
                nLines++;
                in.nextLine();
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
            System.exit(1);
        }

        Loans[] loansFromFile = new Loans[nLines];
        nLines = 0;
        //the second scan to get the details of each Loan
        try {
            in = new Scanner(new File("src\\LOANS.csv"));
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
            System.exit(1);
        }
        while (in.hasNextLine()) {
            loansFromFile[nLines] = new Loans();
            readFromLoansFile(in, loansFromFile[nLines]);
            loansFromFile[nLines].displayDetails();
            produceLoanReport(pw, loans[nLines]);
            nLines++;
        }

        in.close();
        pw.close();
    }

    public static void getRenewLoans() throws IOException {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter("src\\LOANS.csv");
            pw.println("Barcode, User_id, Issue_Date, Due_Date, numRenews");

        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        Loans[] loans = new Loans[10];
        //initilising an instance using the first constructor
        loans[0] = new Loans("340096334", "B00187440", "17/11/2021", "08/12/2021", 0);
        produceLoanReport(pw, loans[0]);
        //initialising an istance using the second constructor
        loans[1] = new Loans("25832497", "B00447489", "16/11/2021", "07/12/2021", 0);
        produceLoanReport(pw, loans[1]);
        //initialising an instanc eusing the default constructor with details being entered from keyboard
        loans[2] = new Loans("240453126", "B00187440", "15/11/2021", "06/12/2021", 0);
        produceLoanReport(pw, loans[2]);
        loans[3] = new Loans("813844363", "B00986808", "01/10/2021", "22/10/2021", 0);
        produceLoanReport(pw, loans[3]);
        loans[4] = new Loans("530038220", "B00986808", "01/10/2021", "22/10/2021", 0);
        produceLoanReport(pw, loans[4]);
        loans[5] = new Loans();
        //create the first instance using the default constructor with details being entered from keyboard
        Scanner cin = new Scanner(System.in);

        renewItem();
        produceLoanReport(pw, loans[5]);
        //Read the details of the other multiple Loans from a file

        Scanner in = null;

        //the first scan to get the number of Loans recorded in a file
        int nLines = 0;
        try {
            in = new Scanner(new File("src\\LOANS.csv"));
            while (in.hasNextLine()) {
                nLines++;
                in.nextLine();
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
            System.exit(1);
        }

        Loans[] loansFromFile = new Loans[nLines];
        nLines = 0;
        //the second scan to get the details of each Loan
        try {
            in = new Scanner(new File("src\\LOANS.csv"));
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
            System.exit(1);
        }
        while (in.hasNextLine()) {
            loansFromFile[nLines] = new Loans();
            readFromLoansFile(in, loansFromFile[nLines]);
            loansFromFile[nLines].displayDetails();
            produceLoanReport(pw, loans[nLines]);
            nLines++;
        }

        in.close();
        pw.close();
    }

    public static void readAllCSVFiles() throws IOException {
        String ITEMS = "src\\ITEMS.csv";
        String lendableItems = "";

        String USERS = "src\\USERS.csv";
        String libraryUsers = "";

        System.out.println("-----------Library Database----------");
        System.out.println();
        try {
            //BufferedReader basically reads each line of the file
            BufferedReader itemsReader = new BufferedReader(new FileReader(ITEMS));
            BufferedReader usersReader = new BufferedReader(new FileReader(USERS));

            //While the program reads the file and does not equal to null it will print the current files 
            //From the CSV
            System.out.println("-----LIBRARY ITEMS-----");
            while ((lendableItems = itemsReader.readLine()) != null) {
                System.out.println(lendableItems);
            }

            System.out.println();
            System.out.println("-----LIBRARY USERS-----");
            while ((libraryUsers = usersReader.readLine()) != null) {
                System.out.println(libraryUsers);
            }
            System.out.println();
            System.out.println("-----LIBRARY LOANS-----");
            Scanner sc = new Scanner(new File("src\\LOANS.csv"));
            //parsing a CSV file into the constructor of Scanner class 
            sc.useDelimiter(" ");
            //setting comma as delimiter pattern
            while (sc.hasNext()) {
                System.out.print(sc.next());
            }
            sc.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Library.class
                    .getName()).log(Level.SEVERE, null, ex);

        }
    }

    public static void searchFromFile() {
        Scanner s = new Scanner(System.in);

        boolean invalidInput = false;
        System.out.print("Enter Barcode to search: ");
        String Barcode = s.next();
        System.out.print("Enter User ID to search: ");
        String User_ID = s.next();

        String barcode = "";
        String user_id = "";
        try {
            FileInputStream ItemsFile = new FileInputStream("src\\ITEMS.csv");
            FileInputStream usersFile = new FileInputStream("src\\USERS.csv");
            Scanner sc1 = new Scanner(ItemsFile);
            Scanner sc2 = new Scanner(usersFile);
            System.out.println("----------Search Results----------");
            while (sc1.hasNextLine()) {
                barcode = sc1.nextLine();
                if (barcode.startsWith(Barcode)) {
                    System.out.println(barcode);
                    invalidInput = true;
                }
            }
            if (!invalidInput) {
                System.out.println("Invalid Data! Barcode Not Found...");//If the barcode is invalid this message will display
            }
            while (sc2.hasNextLine()) {
                user_id = sc2.nextLine();
                if (user_id.startsWith(User_ID)) {
                    System.out.println(user_id);
                    invalidInput = true;
                }
            }
            if (!invalidInput) {
                System.out.println("Invalid Data! User ID Not Found...");//If the User ID is invalid this message will display
            }
            sc1.close();
            sc2.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.out.println("Invalid data input! please enter valid data!");
        }
    }

    public static void readDetailsFromUserInput(Scanner in, Loans loans) {
        Scanner s = new Scanner(System.in);

        System.out.println("------ISSUE AN ITEM-----");
        System.out.print("Enter Barcode to Database: ");
        String Barcode = s.nextLine();
        loans.setBarcode(Barcode);

        System.out.print("Enter User ID to Database: ");
        String User_ID = s.nextLine();
        loans.setUser_ID(User_ID);

        System.out.print("Enter Issue Date (Books - 4 weeks OR Multimedia - 1 Week): ");
        String Issue_Date = s.nextLine();
        loans.setIssue_DateInput(Issue_Date);

        System.out.print("Enter Due Date(Books - 4 weeks OR Multimedia - 1 Week): ");
        String Due_Date = s.nextLine();
        loans.setDue_DateInput(Due_Date);
    }

    public static void produceLoanReport(PrintWriter out, Loans loans) {
        out.println(loans.getBarcode() + "," + loans.getUser_ID() + "," + loans.getIssue_Date() + "," + loans.getDue_Date() + "," + loans.getNum_Renews());
    }

    private static void readFromLoansFile(Scanner in, Loans loans) {
        String barcode = in.next();
        loans.setBarcode(barcode);
        loans.setUser_ID(in.nextLine());
        loans.setIssue_DateInput(in.nextLine());
        loans.setDue_DateInput(in.nextLine());
        loans.setNum_RenewsInput(in.nextInt());
        in.nextLine();
    }

    public static void renewItem() throws IOException {
        ArrayList<Loans> loans = new ArrayList<Loans>();
        Scanner input = new Scanner(System.in);
        Scanner inputint = new Scanner(System.in);
        final int BMAX = 3;
        final int MMAX = 2;
        int count = 1;

        System.out.println("-----RENEW AN ITEM------");
        searchFromFile();

        System.out.println("Is your item a book (yes/no):");
        String itemType = input.nextLine();
        itemType = itemType.trim();

        if (itemType.equalsIgnoreCase("yes") && count <= BMAX) {
            System.out.println("Enter following Barcode searched: ");
            String Barcode = input.nextLine();

            System.out.println("Enter following User ID searched: ");
            String User_ID = input.nextLine();

            System.out.println("Enter following Issue Date: ");
            String Issue_Date = input.nextLine();

            System.out.println("Enter new due date (2 weeks from now): ");
            String Due_Date = input.nextLine();

            System.out.println("Add Number of Renews: ");
            int Num_Renews = Integer.parseInt(inputint.nextLine());

            loans.add(new Loans(Barcode, User_ID, Issue_Date, Due_Date, Num_Renews));

            for (Loans newLoans : loans) {
                System.out.println(newLoans);

                BufferedWriter bw = null;

                try {
                    bw = new BufferedWriter(new FileWriter("src\\LOANS.csv", false));

                    for (Loans newLoans1 : loans) {
                        bw.write(newLoans1.toString() + "\n");
                      
                    }
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                    e.printStackTrace();
                } finally {
                    try {
                        bw = new BufferedWriter(new FileWriter("src\\LOANS.csv", true));

                    for (Loans newLoans1 : loans) {
                        bw.write(newLoans1.toString() + "\n");
                      
                    }
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                    }
                }
            }
        }
    }

    public static void produceRenewReport(PrintWriter out, Loans loans) {
        out.println(loans.getBarcode() + "," + loans.getUser_ID() + "," + loans.getIssue_Date() + "," + loans.getDue_Date() + "," + 0);
    }

    public static void returnItem() throws FileNotFoundException, IOException {

        Scanner input = new Scanner(System.in);
        System.out.println("-----RETURN AN ITEM------");
        System.out.print("Enter Barcode: ");
        String Barcode = input.nextLine();
        System.out.println("Item Returned: " + Barcode);
        File inputFile = new File("src\\LOANS_1.csv");
        File tempFile = new File("src\\LOANS.csv");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String lineToRemove = "514284220,B00791278,28/03/2022,18/04/2022,1";
        String currentLine;

        while ((currentLine = reader.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            String trimmedLine = currentLine.trim();
            if (trimmedLine.equals(lineToRemove)) {
                continue;
            }
            writer.write(currentLine + System.getProperty("line.separator"));
        }
        writer.close();
        reader.close();
        boolean successful = tempFile.renameTo(inputFile);
    }
}
