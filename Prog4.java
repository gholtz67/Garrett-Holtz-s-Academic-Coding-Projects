import java.io.*;
import java.util.Scanner;
import java.util.InputMismatchException;

/**************************************************************/
/* Garrett Holtz                                              */
/* CS-102, Summer 2019                                        */
/* Programming Assignment 4                                   */
/**************************************************************/


class Prog4 {
   public static void main(String[] args) {
      final int LEAVE = 9; //Number to end program
      final int ONE = 1; //Number to search call sign
      final int TWO = 2; //Number to search state
      final int THREE = 3; //Number to print all
      final int FOUR = 4; //Number to add a station
      final int FIVE = 5; // Number to delete a station
      Database data = new Database(); //The database which all the data is being used and configured
      Scanner input = new Scanner(System.in); //The scanner which will take in the user's input
      data.readFile(args); //Reads the file
      int choice = 0; //The number which will change based on the user's input
      while(choice != LEAVE){ //The user will choose what to do after the file is read
         choice = data.choose();
         if(choice == ONE){
            System.out.print("Enter Call Sign: ");
            data.searchCallSign(input.next());
         }else if(choice == TWO){
            System.out.print("Enter State: ");
            data.searchState(input.next());
         }else if(choice == THREE){
            data.printAll();
         }else if(choice == FOUR){
            data.addStation();
         }else if(choice == FIVE){
            data.deleteStation();
         }
      }
      System.out.println("\n" + "Have a great day!");
   }
}
