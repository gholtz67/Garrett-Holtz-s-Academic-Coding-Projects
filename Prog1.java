import java.util.Scanner;
import java.util.InputMismatchException;

/**************************************************************/
/* Garrett Holtz                                              */
/* CS-203, Summer 2020                                        */
/* Programming Assignment 1                                   */
/**************************************************************/

public class Prog1 {
      
   public static void main( String[]args ) {
      
      int Nums[] = new int[100]; //The array that will hold the list of numbers
      //Nums = NumberGen(Nums.length);
      Nums = NumberGen(Nums.length);   
           
      Scanner input = new Scanner( System.in ); //The scanner to check for user input
      
      final int ONE = 1; //Number to call Method 1
      final int TWO = 2; //Number to call Method 2
      final int SEVEN = 7;
      final int EIGHT = 8;
      final int NINE = 9; //Number to make a new list of numbers
      final int LEAVE = 0; //Number to stop the program
      
      int choice = -1; //The user's choice, set to -1 so nothing goes off by accident
      
      //This will keep running until the user wants to stop the program by entering 0
      while( choice != LEAVE ) {
         System.out.println( "\nPlease enter a number for what you would like to do.\n" 
                              + "1) Run Method 1\n"
                              + "2) Run Method 2\n"
                              + "7) New List Greatest to Least\n"
                              + "8) New List Least to Greastest\n"
                              + "9) New List\n"
                              + "0) Leave" );
         
         choice = input.nextInt();
         if( choice == ONE ) {
            Method1( Nums );
         }else if( choice == TWO ) {
            Method2( Nums );
         }else if( choice == SEVEN ) {
            Nums = PremadeGtoL( Nums.length );
         }else if( choice == EIGHT ) {
            Nums = PremadeLtoG( Nums.length );
         }else if( choice == NINE ) {
            Nums = NumberGen(Nums.length);
         }else if( choice == LEAVE ) {
            System.out.println( "Have a great day!" );
         }
      }
   }
   
   /**************************************************************/
   /* Method: NumberGen                                          */
   /* Purpose: To generate a list of random numbers up to        */
   /*           Num                                              */
   /* Parameters:                                                */
   /*    int Num - The number of numbers generated               */
   /* Returns:                                                   */
   /*    int[]:  The numbers generated                           */
   /**************************************************************/
   public static int[] NumberGen(int Num) {
      int Array[] = new int[Num]; //This is the array that will hold the numbers for what will be generated
      System.out.println( "This is the list." );
      for( int i = 0; i < Array.length; i++ ) { //This for loop will put a random number into each slot in the array
         Array[i] = (int)( ( Math.random() * 999 ) + 1 );
         if( i == 25 || i == 50 || i == 75 ){ //Helps organize the console
            System.out.println();
         }
         if( i < Array.length - 1 ) {  //Helps keep the console organized
            System.out.print( Array[i] + ", " );
         }else{
            System.out.println( Array[i] );
         }
      }
      return Array;
   }
   
   /**************************************************************/
   /* Method: NumberGen                                          */
   /* Purpose: To generate a list of random numbers up to        */
   /*           Num                                              */
   /* Parameters:                                                */
   /*    int Num - The number of numbers generated               */
   /* Returns:                                                   */
   /*    int[]:  The numbers generated                           */
   /**************************************************************/
   public static int[] PremadeGtoL(int Num) {
      int count = 0;
      int Array[] = new int[Num]; //This is the array that will hold the numbers for what will be generated
      System.out.println( "This is the list." );
      for( int i = Array.length; i > 0; i-- ) { //This for loop will put a random number into each slot in the array
         Array[count] = i;
         if( i == 25 || i == 50 || i == 75 ){ //Helps organize the console
            System.out.println();
         }
         if( i > 1 ) {  //Helps keep the console organized
            System.out.print( Array[count] + ", " );
         }else{
            System.out.println( Array[count] );
         }
         count++;
      }
      return Array;
   }
   
   public static int[] PremadeLtoG(int Num) {
      int count = 0;
      int Array[] = new int[Num]; //This is the array that will hold the numbers for what will be generated
      System.out.println( "This is the list." );
      for( int i =0; i < Array.length; i++ ) { //This for loop will put a random number into each slot in the array
         Array[count] = i;
         if( i == 25 || i == 50 || i == 75 ){ //Helps organize the console
            System.out.println();
         }
         if( i < Array.length - 1 ) {  //Helps keep the console organized
            System.out.print( Array[count] + ", " );
         }else{
            System.out.println( Array[count] );
         }
         count++;
      }
      return Array;
   }
   
   /**************************************************************/
   /* Method: Method1                                            */
   /* Purpose: To find the maximum, minimum, and the range       */
   /*          of the list                                       */
   /* Parameters:                                                */
   /*    int Array[] - The array we are looking for the          */
   /*                  maximum, minimum, and range for in        */
   /* Returns:                                                   */
   /*    Void                                                    */
   /**************************************************************/   
   public static void Method1( int Array[] ) {
      int MaxNum; //The maximum number from the array
      int MinNum; //The minimum number from the array
      
      int runs = 0; //REMOVE
      
      //Initial check of the first 2 numbers to preset the max and min
      if( Array[0] > Array[1] ) { 
         MaxNum = Array[0];
         MinNum = Array[1];
      }else{
         MaxNum = Array[1];
         MinNum = Array[0];
      }
      runs++; //REMOVE
      
      //Goes through every item in the array to see if it's bigger or smaller than the max and min
      for( int index = 2; index < Array.length; index++ ) {
         if( Array[index] <= MaxNum && Array[index] >= MinNum ) runs+=2; //REMOVE
        
         if( Array[index] > MaxNum ) {
            MaxNum = Array[index];
            runs++; //REMOVE
         }else if( Array[index] < MinNum ) {
            MinNum = Array[index];
            runs+=2; //REMOVE
         }
         
         
      }  
      //Prints the maximum, minimum, and the range into the console
      System.out.println( "Maximum Number: " + MaxNum + ", Minimum Number: " + MinNum + ", Range: " + (MaxNum - MinNum ) );
      System.out.println( "Runs: " + runs ); //REMOVE
   }
   
   /**************************************************************/
   /* Method: Method2                                            */
   /* Purpose: To find the maximum, minimum, and the range       */
   /*          of the list                                       */
   /* Parameters:                                                */
   /*    int Array[] - The array we are looking for the          */
   /*                  maximum, minimum, and range for in        */
   /* Returns:                                                   */
   /*    Void                                                    */
   /**************************************************************/     
   public static void Method2 ( int Array[] ) {
      int MaxNums[] = new int[Array.length / 2]; //Will hold all the maximum numbers for first for loop
      int MinNums[] = new int[Array.length / 2]; //Will hold all the minimum numbers for first for loop
      int MaxNum; //The maximum number
      int MinNum; //The minimum number
      int count = 0; //Counts with first loop to properly set indexes
      
      int runs = 0; //REMOVE
      
      //Checks each pair of 2 values and puts them in either the MaxNums array or MinNums array
      for( int index = 0; index < Array.length; index+=2 ) {
         if( Array[index] > Array[index + 1] ) {
            MaxNums[count] = Array[index];
            MinNums[count] = Array[index + 1];
            runs++; //REMOVE
         }else{
            MaxNums[count] = Array[index + 1];
            MinNums[count] = Array[index];
            runs++; //REMOVE
         }
         count++;
      }
      
      //Checks each number in each array to find the actual maximum and minimum
      MaxNum = MaxNums[0];
      MinNum = MinNums[0];
      for( int index = 1; index < MaxNums.length; index++ ) {
         if( MaxNums[index] < MaxNum && MinNums[index] > MinNum ) runs+=2; //REMOVE
         
         if( MaxNums[index] > MaxNum ) {
            MaxNum = MaxNums[index];
            runs+=2; //REMOVE
         }
         if( MinNums[index] < MinNum ) {
            MinNum = MinNums[index]; 
            runs+=2; //REMOVE
         }
      }
      //Prints the maximum, minimum, and the range into the console
      System.out.println( "Maximum Number: " + MaxNum + ", Minimum Number: " + MinNum + ", Range: " + (MaxNum - MinNum ) );
      System.out.println( "Runs: " + runs ); //REMOVE
   }
   
}