import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

/**************************************************************/
/* Garrett Holtz                                              */
/* CS-203, Summer 2020                                        */
/* Programming Assignment 3                                   */
/**************************************************************/
public class Prog3 {
  
  static int bigCount = 0; // For testing total basic operations
  
   /**************************************************************/
   /* Method: isNumeric                                          */
   /* Purpose: To see if the string is all numbers               */
   /* Parameters:                                                */
   /*    String str: The string we are checking                  */
   /* Returns:                                                   */
   /*    boolean: true if a number, false if not                 */
   /**************************************************************/   
   public static boolean isNumeric(String str) {
       if (str == null || str.length() == 0) // Is the string null or empty
           return false;
       
       for (char c : str.toCharArray()) { // Check all characters for if a digit
           if (!Character.isDigit(c)) {
               return false;
           }
       }

       return true;

   }

   /**************************************************************/
   /* Method: printArray                                         */
   /* Purpose: To print an array to console                      */
   /* Parameters:                                                */
   /*    int[] arr: The array to be printed                      */
   /* Returns:                                                   */
   /*    None: Outputs the array to console                      */
   /**************************************************************/ 
   static void printArray(int arr[]) { 
       int n = arr.length; 
       
       for (int i = 0; i < n; ++i) 
           System.out.print(arr[i] + " "); 
       System.out.println("\n"); 
   } 

   /**************************************************************/
   /* Method: bruteMedian                                        */
   /* Purpose: To find the median of the array by                */
   /*          brute forcing out way through                     */
   /* Parameters:                                                */
   /*    int[] arr: The array that will be searched              */
   /*               for the median                               */
   /* Returns:                                                   */
   /*    None: Outputs the median to the console                 */
   /**************************************************************/     
   static void bruteMedian(int arr[]) {
        bigCount = 0;
        int size = arr.length;
        int midIndex = 0;
        double Average = 0;
        
        mergeSort(arr, 0, size - 1);
        
        if( size % 2 == 1 ) {
           midIndex = (size + 1) / 2;
           System.out.println("The median of the array is " + arr[midIndex - 1] + ".");
        } else {
           int num1 = arr[(size / 2) - 1];
           int num2 = arr[size / 2];
           Average = (double)( num1 + num2 ) / 2;
           System.out.println("The median of the array is " + Average + ".");
        }
   }
   
   
   /**************************************************************/
   /* Method: mergeSort                                          */
   /* Purpose: To sort the array using the                       */
   /*          merge sort algorithm                              */
   /* Parameters:                                                */
   /*    int[] arr: The array that will be sorted                */
   /*    int low: Starting index to be sorted                    */
   /*    int high: Last index to be sorted                       */
   /* Returns:                                                   */
   /*    None: Uses linked arrays to sort original               */
   /**************************************************************/    
   public static void mergeSort(int[] arr, int low, int high) {
      if(high <= low) return;
      
      int mid = (low + high) / 2;
      mergeSort(arr, low, mid);
      mergeSort(arr, mid + 1, high);
      
      merge(arr, low, mid, high);
   }    

   /**************************************************************/
   /* Method: mergeS                                             */
   /* Purpose: To support mergeSort and merge the                */
   /*          smaller arrays                                    */
   /* Parameters:                                                */
   /*    int[] arr: The array that will be sorted                */
   /*    int low: Starting index to be sorted                    */
   /*    int mid: The middle index that split the arrays         */
   /*    int high: Last index to be sorted                       */
   /* Returns:                                                   */
   /*    None: Uses linked arrays to merge and                   */
   /*          sort the arrays from mergeSort                    */
   /**************************************************************/ 
   public static void merge(int[] arr, int low, int mid, int high) {
      // Creating temporary subarrays
      int leftArr[] = new int[mid - low + 1];
      int rightArr[] = new int[high - mid];

      // Copying our subarrays into temporaries
      for (int i = 0; i < leftArr.length; i++)
          leftArr[i] = arr[low + i];
      for (int i = 0; i < rightArr.length; i++)
          rightArr[i] = arr[mid + i + 1];

      // Iterators containing current index of temp subarrays
      int leftIndex = 0;
      int rightIndex = 0;

      // Copying from leftArray and rightArray back into array
      for (int i = low; i < high + 1; i++) {
          // If there are still uncopied elements in R and L, copy minimum of the two
          if(leftIndex < leftArr.length && rightIndex < rightArr.length) {
              if (leftArr[leftIndex] < rightArr[rightIndex]) {
                 arr[i] = leftArr[leftIndex];
                 leftIndex++;
              } else {
                  arr[i] = rightArr[rightIndex];
                  rightIndex++;
              }
          } else if(leftIndex < leftArr.length) {
              // If all elements have been copied from rightArray, copy rest of leftArray
              arr[i] = leftArr[leftIndex];
              leftIndex++;
              
          } else if(rightIndex < rightArr.length) {
              // If all elements have been copied from leftArray, copy rest of rightArray
              arr[i] = rightArr[rightIndex];
              rightIndex++;
              
          }
      }
   }

   /**************************************************************/
   /* Method: medianBinary                                       */
   /* Purpose: To find the median of the array by                */
   /*          using binary search                               */
   /* Parameters:                                                */
   /*    int[][] matrix: The matrix that will be searched        */
   /*            for the median                                  */
   /*    int row: The number or rows in the matrix               */
   /*    int column: The number of columns in the matrix         */
   /* Returns:                                                   */
   /*    None: Outputs the median to the console                 */
   /**************************************************************/     
   static void medianBinary(int matrix[][], int row, int column) {
      int min = Integer.MAX_VALUE; // Largest value possible so any value can become min
      int max = Integer.MIN_VALUE; // Smallest value possible so any value can become max
      int desired_count = 0; // The expected index the median will be at in a sorted array
      if( (row * column) % 2 == 1)
        desired_count = (1 + (row * column)) / 2;
      else
        desired_count = (row * column) / 2;
      
      // Find the smallest min and largest max in the entire 2d array
      for(int i = 0; i < row; i++) {
        if(matrix[i][0] < min)
          min = matrix[i][0];
        if(matrix[i][column - 1] > max)
          max = matrix[i][column - 1];
      }
      
      int counter = 0;
      int mid = 0;
      
      while(min < max) {
          counter = 0;
          mid = min + (max - min) / 2;
          
          for(int i = 0; i < row; i++)
              counter += binarySearch(matrix[i], mid); // Find all numbers smaller than min and increment counter by that number
          
          if(counter < desired_count) // Increase min to mid if mid was to small from the last run
              min = mid + 1;
          else
              max = mid; // Decrease max to mid if mid was to large from last run
      }
      System.out.println("The median of the array is " + min + ". The guessed index is " + desired_count + " if in a 1d array.");
   }      

   /**************************************************************/
   /* Method: binarySearch                                       */
   /* Purpose: To count all the numbers less then                */
   /*          K (the key)                                       */
   /* Parameters:                                                */
   /*    int[] arr: The array that will be checked               */
   /*    int K: The key or number we are looking around          */
   /* Returns:                                                   */
   /*    int: The number of numbers less than K                  */
   /**************************************************************/ 
   static int binarySearch(int arr[], int K) {
     int left = 0;
     int right = arr.length - 1;
     int middle = 0;
     int counter = 0;
     
     while(left <= right) {
        middle = (left + right) / 2;
        if( K < arr[middle] )
            right = middle - 1;
        else 
            left = middle + 1;
     }
     
     for(int i = 0; i <= right; i++)
         counter++;
     return counter;
   } 
   
   /*
   static int binarySearch(int arr[], int K) {
      int count = 0;
      for(int i = 0; i < arr.length; i++) {
         bigCount++;
         if( arr[i] <= K ) {
            count++;
         } else if( arr[ i ] > K ){
            i = arr.length;
            bigCount++;
         }
      }
      return count;
   } 
   */  
     
   public static void main(String[] args) {
      int[] Matrix1 = new int[225]; // 1d array version of matrix 1 from matrices.txt
      int[] Matrix2 = new int[400]; // 1d array version of matrix 2 from matrices.txt
      int count = 0; // To keep track of which matrix is being read
      int loopCount = 0; // Index counter for matrix 1
      int loopCount2 = 0; // Index counter for matrix 2
      
      try { // To catch a missing file exception
         File myObj = new File("matrices.txt");
         Scanner myReader = new Scanner(myObj);
         while ( myReader.hasNext() ) { // Will keep reading as long as there is more to the file
            String data = myReader.next();
            if( isNumeric(data) ) {
               if( count == 4 ) { // Past the header for matrix 1, now starts reading in numbers
                  Matrix1[loopCount] = Integer.parseInt(data);
                  loopCount++;
               } else if( count == 8 ) { // Past the header for matrix 2, now starts reading in numbers
                  Matrix2[loopCount2] = Integer.parseInt(data);
                  loopCount2++;
               }
            } else {
               count++;
            }
         }
         myReader.close();
      } catch(FileNotFoundException e) { // File not found
         System.out.println("An error occurred.");
         e.printStackTrace();
      }
      // Print out matrix 1
      System.out.println("\n");
      System.out.println("Matrix1");
      printArray(Matrix1);
      
      // Print out matrix 2
      System.out.println("Matrix2");
      printArray(Matrix2);
      
      int[][] Matrix1_3d = new int[15][15]; // 2d array version of matrix 1
      int[][] Matrix2_3d = new int[20][20]; // 2d array version of matrix 2
      count = 0;
               
      for(int row = 0; row < Matrix1_3d.length; row++) { // Creates matrix1_3d
         for(int column = 0; column < Matrix1_3d[0].length; column++) {
            Matrix1_3d[row][column] = Matrix1[count];
            count++;
         }
      }
      count = 0;
      for(int row = 0; row < Matrix2_3d.length; row++) { // Creates matrix2_3d
        for(int column = 0; column < Matrix2_3d[0].length; column++) {
           Matrix2_3d[row][column] = Matrix2[count];
           count++;
        }
      }
      
      int[] test = {5, 6, 2, 8, 4, 9, 1};
      int[][] test_3d = {{1, 3, 5}, {2, 6, 9}, {3, 6, 9}};
      
      Scanner input = new Scanner( System.in ); // The scanner to check for user input
      
      final int ONE = 1; // Number to call brute force search for median
      final int TWO = 2; // Number to call brute force search for median
      final int THREE = 3; // Number to call binary search for median
      final int FOUR = 4; // Number to call binary search for median
      final int FIVE = 5; //
      final int SIX = 6; //
      final int SEVEN = 7; //
      final int EIGHT = 8; //
      final int NINE = 9; //
      final int LEAVE = 0; // Number to stop the program
     
      int choice = -1; // The user's choice, set to -1 so nothing goes off by accident
     
      // This will keep running until the user wants to stop the program by entering 0
      while( choice != LEAVE ) {
          System.out.println( "\nPlease enter a number for what you would like to do.\nBoth Matrices are from matrices.txt\n" 
                             + "1) Brute force the median for Matrix 1\n"
                             + "2) Brute force the median for Matrix 2\n"
                             + "3) Binary search the median for Matrix 1\n"
                             + "4) Binary search the median for Matrix 2\n"
                             + "0) Leave" );
        
          choice = input.nextInt();
          if( choice == ONE ) {
          
             bruteMedian(Matrix1);
             
          } else if( choice == TWO ) {
          
             bruteMedian(Matrix2);
             
          }else if( choice == THREE ) {
          
             medianBinary(Matrix1_3d, 15, 15); 
             
          }else if( choice == FOUR ) {
          
             medianBinary(Matrix2_3d, 20, 20); 

          }else if( choice == FIVE ) {
             
          }else if( choice == SIX ) {
              
          }else if( choice == SEVEN ) {
             
          }else if( choice == EIGHT ) {
             
          }else if( choice == NINE ) { 
          
            medianBinary(test_3d, 3, 3); 

          }else if( choice == LEAVE ) {
             System.out.println( "Have a great day!" );
          }
      }
   }
}