import java.util.*; 
import java.lang.*; 
import java.io.*; 
import java.util.Scanner;

/**************************************************************/
/* Garrett Holtz                                              */
/* CS-203, Summer 2020                                        */
/* Programming Assignment 2                                   */
/**************************************************************/
public class Prog2 {
  
    static LinkedList<Integer>FirstSeenDFS = new LinkedList<Integer>(); // For DFS since it's recursive and didn't think of another way to share this variable
  
   /**************************************************************/
   /* Method: BFS                                                */
   /* Purpose: To see if the graph is 2-colorable using          */
   /*          the Breath First Search Algorithm                 */
   /* Parameters:                                                */
   /*    int Graph[][] - The adjacency matrix                    */
   /*    int src - The source vertex                             */
   /*    int Vertices - The number of vertices in the graph      */
   /* Returns:                                                   */
   /*    None: Output prints to console                          */
   /**************************************************************/
    public static void BFS(int Graph[][], int src, int Vertices) { 
        
        // Create a color array to store  
        // colors assigned to all veritces. 
        // Vertex number is used as index  
        // in this array. The value '-1' 
        // of colorArr[index] is used to indicate  
        // that no color is assigned 
        // to vertex 'i'. The value 1 is  
        // used to indicate first color 
        // is assigned and value 0 indicates  
        // second color is assigned. 
        int colorArr[] = new int[Vertices]; 
        for (int index = 0; index < Vertices; index++) 
            colorArr[index] = -1; 
        
        int[][] Output = new int[2][Vertices];
            
        if(Vertices % 2 == 1) {
        
            // Portion to print to console
            for(int row = 0; row < 2; row++){
               for(int column = 0; column < Vertices; column++){
                  if(row == 0) Output[row][column] = column;
                  else Output[row][column] = -1;
                  
                  if(row == 0 && column == 0) System.out.print("Vertices: ");
                  else if(row == 1 && column == 0) System.out.print("Colors: ");
            
                  if(column == Vertices - 1) System.out.print(Output[row][column]);
                  else  System.out.print(Output[row][column] + ", ");
               }
               System.out.println();
            }
            System.out.println();
            return; // To end the method as proved not 2-colorable
        }
        
        // Assign first color to source 
        colorArr[src] = 1; 
  
        // Create a queue of vertex numbers
        LinkedList<Integer>queue = new LinkedList<Integer>(); 
        queue.add(src); 
        
        LinkedList<Integer>FirstSeenBFS = new LinkedList<Integer>();
        FirstSeenBFS.add(src);
  
        // Run while there are vertices in queue
        while (queue.size() != 0) { 
            // Dequeue a vertex from queue 
            int CurrentV = queue.poll(); 
            
            // Return false if there is a self-loop  
            if (Graph[CurrentV][CurrentV] == 1) {
            
               // Portion to print to console
               for(int row = 0; row < 2; row++){
                  for(int column = 0; column < Vertices; column++){
                     if(row == 0) Output[row][column] = column;
                     else Output[row][column] = -1;
                     
                     if(row == 0 && column == 0) System.out.print("Vertices: ");
                     else if(row == 1 && column == 0) System.out.print("Colors: ");
               
                     if(column == Vertices - 1) System.out.print(Output[row][column]);
                     else  System.out.print(Output[row][column] + ", ");;
                  }
                  System.out.println();
               }
               return; // To end the method as proved not 2-colorable
            }
            // Find all non-colored adjacent vertices 
            for (int edge = 0; edge < Vertices; edge++)  { 
                // An edge from CurrentV exists  
                // and destination is not colored 
                if (Graph[CurrentV][edge] == 1 && colorArr[edge] == -1) { 
                    // Assign alternate color to this adjacent edge of CurrentV 
                    colorArr[edge] = 1 - colorArr[CurrentV]; 
                    queue.add(edge); 
                    FirstSeenBFS.add(edge);
                } 
  
                // An edge from CurrentV exists and the 
                // destination is colored with same color as CurrentV 
                else if (Graph[CurrentV][edge] == 1 && colorArr[edge] == colorArr[CurrentV]) {
                
                    // Portion to print to console
                    for(int row = 0; row < 2; row++){
                        for(int column = 0; column < Vertices; column++){
                           if(row == 0) Output[row][column] = column;
                           else Output[row][column] = -1;
                  
                           if(row == 0 && column == 0) System.out.print("Vertices: ");
                           else if(row == 1 && column == 0) System.out.print("Colors: ");
            
                           if(column == Vertices - 1) System.out.print(Output[row][column]);
                           else  System.out.print(Output[row][column] + ", ");
                        }
                        System.out.println();
                    }
                    System.out.println();
                    return; // To end the method as proved not 2-colorable
                }
            } 
        } 
        // Everything past here means the graph is 2-colorable
        // These are portions to print to the console 
        for(int row = 0; row < 2; row++){
            for(int column = 0; column < Vertices; column++){
               if(row == 0) Output[row][column] = column;
               else Output[row][column] = colorArr[column];
               
               if(row == 0 && column == 0) System.out.print("Vertices: ");
               else if(row == 1 && column == 0) System.out.print("Colors: ");
               
               if(column == Vertices - 1) System.out.print(Output[row][column]);
               else  System.out.print(Output[row][column] + ", ");
            }
            System.out.println();
        }
        
        for(int index = 0; index < FirstSeenBFS.size(); index++){
            if(index == 0) System.out.print("The vertices were first seen in this order: ");
            System.out.print(FirstSeenBFS.get(index) + " ");
        }
        System.out.println();
    } 
    
   /**************************************************************/
   /* Method: DFS                                                */
   /* Purpose: To see if the graph is 2-colorable using          */
   /*          the Depth First Search Algorithm                  */
   /* Parameters:                                                */
   /*    int Graph[][] - The adjacency matrix                    */
   /*    int Vertices - The number of vertices in the graph      */
   /* Returns:                                                   */
   /*    None: Output prints to console                          */
   /**************************************************************/
    public static void DFS(int Graph[][], int Vertices){ 
    
        int[] colorArr = new int[Vertices]; // To keep track of the colors for each vertex
        for (int index = 0; index < Vertices; index++) 
            colorArr[index] = -1; 
            
        int[][] Output = new int[2][Vertices];
        int src = 0;
        
        FirstSeenDFS.clear(); // Clear the global variable, incase method is ran multiple times without restart
        FirstSeenDFS.add(0); // Starts at source, vertex 0
        
        // Two colors 1 and 0 
        if( DFSUtil(Graph, colorArr, src, 1, Vertices) == false ){
            // Portion to print to console
            for(int row = 0; row < 2; row++){
                for(int column = 0; column < Vertices; column++){
                   if(row == 0) Output[row][column] = column;
                   else Output[row][column] = -1;
                   
                   if(row == 0 && column == 0) System.out.print("Vertices: ");
                   else if(row == 1 && column == 0) System.out.print("Colors: ");
             
                   if(column == Vertices - 1) System.out.print(Output[row][column]);
                   else  System.out.print(Output[row][column] + ", ");
                }
                System.out.println();
             }
             System.out.println();
        }else{
            // Portion to print to console
            for(int row = 0; row < 2; row++){
               for(int column = 0; column < Vertices; column++){
                  if(row == 0) Output[row][column] = column;
                  else Output[row][column] = colorArr[column];
            
                  if(row == 0 && column == 0) System.out.print("Vertices: ");
                  else if(row == 1 && column == 0) System.out.print("Colors: ");
            
                  if(column == Vertices - 1) System.out.print(Output[row][column]);
                  else  System.out.print(Output[row][column] + ", ");
               }
               System.out.println();
            }
            for(int index = 0; index < FirstSeenDFS.size(); index++){
               if(index == 0) System.out.print("The vertices were first seen in this order: ");
               System.out.print(FirstSeenDFS.get(index) + " ");
            }
            System.out.println();
        } 
    } 
    
    /**************************************************************/
    /* Method: DFSUtil                                            */
    /* Purpose: To assist DFS recursively to find if the          */
    /*          graph is 2-colorable                              */
    /* Parameters:                                                */
    /*    int Graph[][] - The adjacency matrix                    */
    /*    int src - The source vertex                             */
    /*    int Vertices - The number of vertices in the graph      */
    /* Returns:                                                   */
    /*    Boolean: True if graph is 2-colorable, false if not     */
    /**************************************************************/
    static boolean DFSUtil(int Graph[][], int colorArr[], int pos, int color, int Vertices) { 
        if (colorArr[pos] != -1 && colorArr[pos] != color) 
            return false; 
  
        // Color this vertex as pos as color and  
        // all its neighbours as 1 - color 
        colorArr[pos] = color; 
        boolean ans = true; 
        for (int index = 0; index < Vertices; index++) { 
            if (Graph[pos][index] == 1) { 
                if (colorArr[index] == -1) {
                    FirstSeenDFS.add(index);
                    ans &= DFSUtil(Graph, colorArr, index, 1 - color, Vertices); 
                }
                else if (colorArr[index] != -1 && colorArr[index] != 1 - color) 
                    return false; 
            } 
            if (!ans) 
                return false; 
        } 
        return true; 
    } 
    
    public static void main (String[] args) { 
        
        int Matrix_1[][] =
           {{0, 1, 0, 1, 0, 0},
            {1, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1},
            {1, 0, 0, 0, 1, 0},
            {0, 1, 0, 1, 0, 1},
            {0, 0, 1, 0, 1, 0}}; 
            
        int Matrix_2[][] =
          {{0, 1, 0, 1, 0, 0, 0, 0, 0, 0},
           {1, 0, 1, 0, 1, 0, 0, 0, 0, 0},
           {0, 1, 0, 0, 0, 1, 0, 0, 0, 0},
           {1, 0, 0, 0, 1, 0, 0, 0, 0, 0},
           {0, 1, 0, 1, 0, 1, 0, 0, 0, 0},
           {0, 0, 1, 0, 1, 0, 1, 0, 0, 1},
           {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
           {0, 0, 0, 1, 0, 0, 0, 0, 1, 0},
           {0, 0, 0, 0, 1, 0, 0, 1, 0, 1},
           {0, 1, 0, 0, 0, 1, 0, 0, 1, 0}};
           
        int Matrix_3[][] =
          {{0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
           {1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0},
           {0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
           {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
           {0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0},
           {0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0},
           {0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0},
           {0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0},
           {0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1},
           {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0},
           {0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1},
           {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0}};
           
        int Matrix_4[][] =
         {{0, 1, 0, 1, 1, 0, 0, 0, 0, 0},
           {1, 0, 1, 0, 1, 0, 0, 0, 0, 0},
           {0, 1, 0, 0, 0, 1, 0, 0, 0, 0},
           {1, 0, 0, 0, 1, 0, 0, 0, 0, 0},
           {0, 1, 0, 1, 0, 1, 0, 0, 0, 0},
           {0, 0, 1, 0, 1, 0, 1, 0, 0, 1},
           {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
           {0, 0, 0, 1, 0, 0, 0, 0, 1, 0},
           {0, 0, 0, 0, 1, 0, 0, 1, 0, 1},
           {0, 1, 0, 0, 0, 1, 0, 0, 1, 0}};
           
        int Matrix_5[][] =
          {{0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
           {1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0},
           {0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0},
           {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0},
           {1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0},
           {0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0},
           {0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0},
           {0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1},
           {0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0},
           {0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0},
           {0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1},
           {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0}};
            
        Scanner input = new Scanner( System.in ); // The scanner to check for user input
      
        final int ONE = 1; // Number to call BFS on Matrix 1
        final int TWO = 2; // Number to call DFS on Matrix 1
        final int THREE = 3; // Number to call BFS on Matrix 2
        final int FOUR = 4; // Number to call DFS on Matrix 2
        final int FIVE = 5; // Number to call BFS on Matrix 3
        final int SIX = 6; // Number to call DFS on Matrix 3
        final int SEVEN = 7; // Number to call BFS on Matrix 4
        final int EIGHT = 8; // Number to call DFS on Matrix 4
        final int NINE = 9; // Number to run extra test
        final int LEAVE = 0; // Number to stop the program
     
        int choice = -1; // The user's choice, set to -1 so nothing goes off by accident
     
        // This will keep running until the user wants to stop the program by entering 0
        while( choice != LEAVE ) {
            System.out.println( "\nPlease enter a number for what you would like to do.\nYou can find images of what each matrix looks like at the end of the instructions in README.txt.\n" 
                               + "1) Run Breath First Search on Matrix 1\n"
                               + "2) Run Depth First Search on Matrix 1\n"
                               + "3) Run Breath First Search on Matrix 2\n"
                               + "4) Run Depth First Search on Matrix 2\n"
                               + "5) Run Breath First Search on Matrix 3\n"
                               + "6) Run Depth First Search on Matrix 3\n"
                               + "7) Run Breath First Search on Matrix 4\n"
                               + "8) Run Depth First Search on Matrix 4\n"
                               + "0) Leave" );
        
            choice = input.nextInt();
            if( choice == ONE ) {
               System.out.println("Running BFS on Matrix 1");
               BFS(Matrix_1, 0, 6);
               
            }else if( choice == TWO ) {
               System.out.println("Running DFS on Matrix 1");
               DFS(Matrix_1, 6);
               
            }else if( choice == THREE ) {
               System.out.println("Running BFS on Matrix 2");
               BFS(Matrix_2, 0, 10);
               
            }else if( choice == FOUR ) {
               System.out.println("Running DFS on Matrix 2");
               DFS(Matrix_2, 10);
               
            }else if( choice == FIVE ) {
               System.out.println("Running BFS on Matrix 3");
               BFS(Matrix_3, 0, 12);
               
            }else if( choice == SIX ) {
               System.out.println("Running DFS on Matrix 3");
               DFS(Matrix_3, 12);
               
            }else if( choice == SEVEN ) {
               System.out.println("Running BFS on Matrix 4");
               BFS(Matrix_4, 0, 10);
               
            }else if( choice == EIGHT ) {
               System.out.println("Running DFS on Matrix 4");
               DFS(Matrix_4, 10);
               
            }else if( choice == NINE ) { // EXTRA TEST
               //System.out.println("Running DFS on Matrix 5");
               //DFS(Matrix_5, 12);
               
            }else if( choice == LEAVE ) {
               System.out.println( "Have a great day!" );
            }
         }
    } 
}