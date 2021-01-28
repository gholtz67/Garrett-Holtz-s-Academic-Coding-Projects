import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class Prog4 {
   
   private static int BigCount = 0;
   
   public static int DPminCost(int cost[][], int totRows) { 
      // dist[i] stores minimum cost to reach station i 
      // from station 0. 
      int dist[] = new int[totRows]; 
      for (int row = 0; row < totRows; row++) 
         dist[row] = Integer.MAX_VALUE; 
      dist[0] = 0; 
     
      // Go through every station and check if using it 
      // as an intermediate station gives better path 
      for (int row = 0; row < totRows; row++) 
         for (int column = row + 1; column < totRows; column++) {
            if (dist[column] > dist[row] + cost[row][column]) {
               dist[column] = dist[row] + cost[row][column]; 
               System.out.println("From post " + row + " stop at post " + column);
            }
            BigCount++;
         }
      return dist[totRows - 1]; 
   } 
    
   public static int RminCost(int[][] cost, int post, int col) {
      if(col == cost.length)
         return 0;
      
      int min = Integer.MAX_VALUE;
      int postUpdate = 0;
      for(int column = col; column < cost.length; column++) {
         if(cost[post][column] < min) {
            min = cost[post][column];
            postUpdate = column;
         }
         BigCount++;
      }
      System.out.println("From post " + post + " stop at post " + postUpdate);
      return (RminCost(cost, postUpdate, postUpdate + 1) + min);
   }
    
   public static void main(String[]args) {
      int passes = 0;
      int row = 0;
      int column = 0;
      int[][] MatrixA = new int[6][6];
      try { // To catch a missing file exception
         File myObj = new File("matrixA.txt");
         Scanner myReader = new Scanner(myObj);
         while ( myReader.hasNext() ) { // Will keep reading as long as there is more to the file
            String data = myReader.next();
            if(passes == 0)
               passes++;
            else {
               if(column >= 6) {
                  row++;
                  column = 0;
               }
               //Row 0
               while(row == 0 && column == 0) {
                  MatrixA[row][column] = Integer.MAX_VALUE;
                  column++;
               }
               if(row == 0)
                  MatrixA[row][column] = Integer.parseInt(data);
               
               //Row 1
               while(row == 1 && (column == 0 || column == 1)) {
                  MatrixA[row][column] = Integer.MAX_VALUE;
                  column++;
               }
               if(row == 1)
                  MatrixA[row][column] = Integer.parseInt(data);
               
               //Row 2
               while(row == 2 && (column == 0 || column == 1 || column == 2)) {
                  MatrixA[row][column] = Integer.MAX_VALUE;
                  column++;
               }
               if(row == 2)
                  MatrixA[row][column] = Integer.parseInt(data);
               
               //Row 3
               while(row == 3 && (column == 0 || column == 1 || column == 2 || column == 3)) {
                  MatrixA[row][column] = Integer.MAX_VALUE;
                  column++;
               }
               if(row == 3)
                  MatrixA[row][column] = Integer.parseInt(data);
               
               //Row 4
               while(row == 4 && (column == 0 || column == 1 || column == 2 || column == 3 || column == 4)) {
                  MatrixA[row][column] = Integer.MAX_VALUE;
                  column++;
               }
               if(row == 4)
                  MatrixA[row][column] = Integer.parseInt(data);
               column++;
            }
         }
         myReader.close();
      } catch(FileNotFoundException e) { // File not found
         System.out.println("An error occurred.");
         e.printStackTrace();
      }
      for(int i = 0; i < MatrixA[0].length; i++)
         MatrixA[5][i] = Integer.MAX_VALUE;
         
      passes = 0;
      row = 0;
      column = 0;
      int[][] MatrixB = new int[7][7];
      try { // To catch a missing file exception
         File myObj = new File("matrixB.txt");
         Scanner myReader = new Scanner(myObj);
         while ( myReader.hasNext() ) { // Will keep reading as long as there is more to the file
            String data = myReader.next();
            if(passes == 0)
               passes++;
            else {
               if(column >= 7) {
                  row++;
                  column = 0;
               }
               //Row 0
               while(row == 0 && column == 0) {
                  MatrixB[row][column] = Integer.MAX_VALUE;
                  column++;
               }
               if(row == 0)
                  MatrixB[row][column] = Integer.parseInt(data);
               
               //Row 1
               while(row == 1 && (column == 0 || column == 1)) {
                  MatrixB[row][column] = Integer.MAX_VALUE;
                  column++;
               }
               if(row == 1)
                  MatrixB[row][column] = Integer.parseInt(data);
               
               //Row 2
               while(row == 2 && (column == 0 || column == 1 || column == 2)) {
                  MatrixB[row][column] = Integer.MAX_VALUE;
                  column++;
               }
               if(row == 2)
                  MatrixB[row][column] = Integer.parseInt(data);
               
               //Row 3
               while(row == 3 && (column == 0 || column == 1 || column == 2 || column == 3)) {
                  MatrixB[row][column] = Integer.MAX_VALUE;
                  column++;
               }
               if(row == 3)
                  MatrixB[row][column] = Integer.parseInt(data);
               
               //Row 4
               while(row == 4 && (column == 0 || column == 1 || column == 2 || column == 3 || column == 4)) {
                  MatrixB[row][column] = Integer.MAX_VALUE;
                  column++;
               }
               if(row == 4)
                  MatrixB[row][column] = Integer.parseInt(data);
               
               //Row 5
               while(row == 5 && (column == 0 || column == 1 || column == 2 || column == 3 || column == 4 || column == 5)) {
                  MatrixB[row][column] = Integer.MAX_VALUE;
                  column++;
               }
               if(row == 5) 
                  MatrixB[row][column] = Integer.parseInt(data);
               column++;
            }
         }
         myReader.close();
      } catch(FileNotFoundException e) { // File not found
         System.out.println("An error occurred.");
         e.printStackTrace();
      }
      for(int i = 0; i < MatrixB[0].length; i++)
         MatrixB[6][i] = Integer.MAX_VALUE;
         
      passes = 0;
      row = 0;
      column = 0;
      int[][] MatrixC = new int[8][8];
      try { // To catch a missing file exception
         File myObj = new File("matrixC.txt");
         Scanner myReader = new Scanner(myObj);
         while ( myReader.hasNext() ) { // Will keep reading as long as there is more to the file
            String data = myReader.next();
            if(passes == 0)
               passes++;
            else {
               if(column >= 8) {
                  row++;
                  column = 0;
               }
               //Row 0
               while(row == 0 && column == 0) {
                  MatrixC[row][column] = Integer.MAX_VALUE;
                  column++;
               }
               if(row == 0)
                  MatrixC[row][column] = Integer.parseInt(data);
               
               //Row 1
               while(row == 1 && (column == 0 || column == 1)) {
                  MatrixC[row][column] = Integer.MAX_VALUE;
                  column++;
               }
               if(row == 1)
                  MatrixC[row][column] = Integer.parseInt(data);
               
               //Row 2
               while(row == 2 && (column == 0 || column == 1 || column == 2)) {
                  MatrixC[row][column] = Integer.MAX_VALUE;
                  column++;
               }
               if(row == 2)
                  MatrixC[row][column] = Integer.parseInt(data);
               
               //Row 3
               while(row == 3 && (column == 0 || column == 1 || column == 2 || column == 3)) {
                  MatrixC[row][column] = Integer.MAX_VALUE;
                  column++;
               }
               if(row == 3)
                  MatrixC[row][column] = Integer.parseInt(data);
               
               //Row 4
               while(row == 4 && (column == 0 || column == 1 || column == 2 || column == 3 || column == 4)) {
                  MatrixC[row][column] = Integer.MAX_VALUE;
                  column++;
               }
               if(row == 4)
                  MatrixC[row][column] = Integer.parseInt(data);
               
               //Row 5
               while(row == 5 && (column == 0 || column == 1 || column == 2 || column == 3 || column == 4 || column == 5)) {
                  MatrixC[row][column] = Integer.MAX_VALUE;
                  column++;
               }
               if(row == 5)
                  MatrixC[row][column] = Integer.parseInt(data);
               
               //Row 6
               while(row == 6 && (column == 0 || column == 1 || column == 2 || column == 3 || column == 4 || column == 5 || column == 6)) {
                  MatrixC[row][column] = Integer.MAX_VALUE;
                  column++;
               }
               if(row == 6)
                  MatrixC[row][column] = Integer.parseInt(data);
               column++;
            }
         }
         myReader.close();
      } catch(FileNotFoundException e) { // File not found
         System.out.println("An error occurred.");
         e.printStackTrace();
      }
      for(int i = 0; i < MatrixC[0].length; i++)
         MatrixC[7][i] = Integer.MAX_VALUE;
      System.out.println();
      
      Scanner input = new Scanner( System.in ); // The scanner to check for user input
      
      final int ONE = 1; //
      final int TWO = 2; // 
      final int THREE = 3; // 
      final int FOUR = 4; // 
      final int FIVE = 5; //
      final int SIX = 6; //
      final int SEVEN = 7; //
      final int EIGHT = 8; //
      final int NINE = 9; //
      final int LEAVE = 0; // Number to stop the program
     
      int choice = -1; // The user's choice, set to -1 so nothing goes off by accident
     
      // This will keep running until the user wants to stop the program by entering 0
      while( choice != LEAVE ) {
          System.out.println( "\nPlease enter a number for what you would like to do.\nAll 3 Matrices are from MatrixA.txt, MatrixB.txt, and MatrixC.txt\n" 
                             + "1) Recursive, find the lowest cost of MatrixA\n"
                             + "2) Recursive, find the lowest cost of MatrixB\n"
                             + "3) Recursive, find the lowest cost of MatrixC\n"
                             + "4) Dynamic Program, find the lowest cost of MatrixA\n"
                             + "5) Dynamic Program, find the lowest cost of MatrixB\n"
                             + "6) Dynamic Program, find the lowest cost of MatrixC\n"
                             + "9) Print Matrices\n"
                             + "0) Leave" );
        
          choice = input.nextInt();
          if( choice == ONE ) {
             
             System.out.println("MatrixA");
             System.out.println("Lowest cost = " + RminCost(MatrixA, 0 , 1));
             
          } else if( choice == TWO ) {
             
             System.out.println("MatrixB");
             System.out.println("Lowest cost = " + RminCost(MatrixB, 0 , 1));
             
          }else if( choice == THREE ) {
             
             System.out.println("MatrixC");
             System.out.println("Lowest cost = " + RminCost(MatrixC, 0 , 1));
             
          }else if( choice == FOUR ) { 
            
            System.out.println("MatrixA");
            System.out.println("Lowest cost = " + DPminCost(MatrixA, 6));
            
          }else if( choice == FIVE ) {
            
            System.out.println("MatrixB");
            System.out.println("Lowest cost = " + DPminCost(MatrixB, 7));
             
          }else if( choice == SIX ) {
            
            System.out.println("MatrixC");
            System.out.println("Lowest cost = " + DPminCost(MatrixC, 8));
              
          }else if( choice == SEVEN ) {
             
          }else if( choice == EIGHT ) {
             System.out.println("Current Count = " + BigCount);
             BigCount = 0;
          }else if( choice == NINE ) { 
          
            //Print Matrices
            System.out.println("MatrixA");
            for(int i = 0; i < MatrixA.length; i++) {
               System.out.print("Row " + i + ": ");
               for(int j = 0; j < MatrixA[0].length; j++) {
                  if(j < MatrixA[0].length - 1)
                     System.out.print(MatrixA[i][j] + ", ");
                  else
                     System.out.print(MatrixA[i][j]);
               }
               System.out.println();
            }
            System.out.println();
      
            System.out.println("MatrixB");
            for(int i = 0; i < MatrixB.length; i++) {
               System.out.print("Row " + i + ": ");
               for(int j = 0; j < MatrixB[0].length; j++) {
                  if(j < MatrixB[0].length - 1)
                     System.out.print(MatrixB[i][j] + ", ");
                  else
                     System.out.print(MatrixB[i][j]);
               }
               System.out.println();
            }
            System.out.println();
      
            System.out.println("MatrixC");
            for(int i = 0; i < MatrixC.length; i++) {
            System.out.print("Row " + i + ": ");
               for(int j = 0; j < MatrixC[0].length; j++) {
                  if(j < MatrixC[0].length - 1)
                     System.out.print(MatrixC[i][j] + ", ");
                  else
                     System.out.print(MatrixC[i][j]);
               }
               System.out.println();
            }
            System.out.println();

          }else if( choice == LEAVE ) {
             System.out.println( "Have a great day!" );
          }
      }
   }
}