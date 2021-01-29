import java.io.*;
import java.util.Scanner;

class Prog1 {
   public static void main(String[] args) {
      Database data = new Database();
      Scanner input = new Scanner(System.in);
      data.readFile(args); //Reads the file
      int choice = 0;
      while(choice != 9){ //The user will choose what to do after the file is read
         choice = data.choose();
         if(choice == 1){
            System.out.print("Enter Call Sign: ");
            data.searchCallSign(input.next());
         }else if(choice == 2){
            System.out.print("Enter State: ");
            data.searchState(input.next());
         }else if(choice == 3){
            data.printAll();
         }else{
            System.out.println("Please enter a number for one of the options." + "\n");
         }
      }
      System.out.println("\n" + "Have a great day!");
   }
}

class Station {
   //The 4 Parts of the Station
   private String cSign;
   private String network;
   private String cityLocation;
   private String stateLocation;
   
   //Setting Each Part for the Station
   public void setCallSign(String sign){
      cSign = sign;
   }
   public void setNetworkAffill(String affilliation){
      network = affilliation;
   }
   public void setCity(String city){
      cityLocation = city;
   }
   public void setState(String state){
      stateLocation = state;
   }
   
   //Getting Each Part from the Station
   public String getCallSign(){
      return cSign;
   }
   public String getNetworkAffill(){
      return network;
   }
   public String getCity(){
      return cityLocation;
   }
   public String getState(){
      return stateLocation;
   }
}

class Database {
   private int counterOut;
   //An Array of Stations 
   private static Station[] holder = new Station[7];
   
   public void readFile(String[] args){
      Scanner file = null;
      try { //Attmepts to read the file and gives proper responses to the user for main exceptions
         file = new Scanner(new File(args[0]));
      }
      catch(FileNotFoundException exc){
         System.out.println("File not found");
         System.exit(0);
      }
      catch(ArrayIndexOutOfBoundsException exc){
         System.out.println("No argument given to program");
         System.exit(0);
      }
      
      //To fill array holder with Stations and fill the parts
      int counter = 0;
      while(file.hasNext()){
         String line = file.nextLine();
         Scanner pieces = new Scanner(line);
         pieces.useDelimiter("/");
         holder[counter] = new Station(); //Actually sets the null objects inside holder to stations
         holder[counter].setCallSign(pieces.next());
         holder[counter].setNetworkAffill(pieces.next());
         holder[counter].setCity(pieces.next());
         holder[counter].setState(pieces.next());
         counter++;
      }
      System.out.println();
   }
   
   //User Chooses what to do
   public static int choose(){
      Scanner input = new Scanner(System.in);
      System.out.println("Current Available Commands:");
      System.out.println("1 --> Search for a call sign");
      System.out.println("2 --> Search for a state");
      System.out.println("3 --> Print all records");
      System.out.println("9 --> Exit");
      
      System.out.print("Your choice? ");
      int option = input.nextInt();
      return option;
   }
   
   public void searchCallSign(String CallSign){
      System.out.println(); //To make room for the output
      boolean checker = false;
      for(int i = 0; i < holder.length; i++){
         if(holder[i].getCallSign().equals(CallSign)){ //If the call sign is found, print 
            System.out.println(CallSign + ": " + holder[i].getNetworkAffill() + ", " + 
                               holder[i].getCity() + ", " + holder[i].getState());
            checker = true;  
         }
      }
      if(checker == false)
         System.out.println("Call Sign not found");
      System.out.println();
      confirm();
   }
   
   public void searchState(String State){
      System.out.println(); //To make room for the output
      boolean checker = false;
      for(int i = 0; i < holder.length; i++){
         if(holder[i].getState().equals(State)){ //If the state is found, print
            System.out.println(holder[i].getCallSign() + ": " + holder[i].getNetworkAffill() + ", " + 
                               holder[i].getCity() + ", " + State);
            checker = true;
         }
      }
      if(checker == false)
         System.out.println("State not found");
      System.out.println();
      confirm();
   }
   
   public void printAll(){
      System.out.println();
      for(int i = 0; i < holder.length; i++) 
         System.out.println(holder[i].getCallSign() + ": " + holder[i].getNetworkAffill() + ", " + 
                            holder[i].getCity() + ", " + holder[i].getState());
      System.out.println();
      confirm();
   }
   
   public void confirm(){
      Scanner check = new Scanner(System.in);
      System.out.print("Was the outputed information correct? y/n: ");
      String choice = check.next();
      if(choice.equals("y"))
         System.out.println("Thank you for the feedback!");
      else if(choice.equals("n"))
         System.out.println("Thank you for the feedback!");
      else
         System.out.println("Please pick either yes or no next time plesae.");
      System.out.println();
   }
}
