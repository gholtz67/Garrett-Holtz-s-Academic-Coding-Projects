import java.io.*;
import java.util.Scanner;
import java.util.InputMismatchException;

class Prog2 {
   
    
   public static void main(String[] args) {
      final int LEAVE = 9; 
      final int ONE = 1;
      final int TWO = 2;
      final int THREE = 3; 
      final int FOUR = 4;
      final int FIVE = 5;
      Database data = new Database();
      Scanner input = new Scanner(System.in);
      data.readFile(args); //Reads the file
      int choice = 0;
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

class Station {
   //The 4 Parts of the Station
   private String callSign;
   private String networkAffilliation;
   private String city;
   private String state;
   
   //Sets the call sign of the station
   public void setCallSign(String callSign){
      this.callSign = callSign;
   }
   //Sets the network affilliation of the station
   public void setNetworkAffill(String networkAffilliation){
      this.networkAffilliation = networkAffilliation;
   }
   //Sets the city of the station
   public void setCity(String city){
      this.city = city;
   }
   //Sets the state of the station
   public void setState(String state){
      this.state = state;
   }
   
   //Gets the call sign of the station
   public String getCallSign(){
      return callSign;
   }
   //Gets the network affilliation of the staion
   public String getNetworkAffill(){
      return networkAffilliation;
   }
   //Gets the city of the station
   public String getCity(){
      return city;
   }
   //Gets the state of the station
   public String getState(){
      return state;
   }
}

class Database {

   Station[] holder = new Station[7];
   LinkedList States = new LinkedList();
   
   //Will read in the file and set up the linked list
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
      
      boolean duplicate;
      //To fill the outer LinkedList with states
      for(int spot = 0; spot< holder.length; spot++){
         duplicate = false;
         //To check if there are duplicate/multiple states with the same name
         for(int dupeCheck = 0; dupeCheck < States.size(); dupeCheck++){
            if(((State)States.get(dupeCheck)).getState().equals(holder[spot].getState())){
               ((State)States.get(dupeCheck)).addStation(holder[spot].getCallSign(), holder[spot].getNetworkAffill(), holder[spot].getCity());
               duplicate = true;
               dupeCheck = States.size();
            }
         }
         //If the state already exists, don't create another
         if(duplicate == true){
         
         //Create a new state 
         }else{
            State temp = new State(holder[spot].getState());
            States.add(0, temp);
            ((State)States.get(0)).addStation(holder[spot].getCallSign(), holder[spot].getNetworkAffill(), holder[spot].getCity());
         }
      }
      for(int spot = 0; spot < States.size(); spot++){
         ((State)States.get(spot)).sortStations();   
      }
      sortStates();
   }
   
   //User Chooses what he/she wants to do
   public static int choose(){
      Scanner input = new Scanner(System.in);
      System.out.println("Current Available Commands:");
      System.out.println("1 --> Search for a Call Sign");
      System.out.println("2 --> Search for a State");
      System.out.println("3 --> Print All Records");
      System.out.println("4 --> Add a Station");
      System.out.println("5 --> Delete a Station");
      System.out.println("9 --> Exit");
      
      System.out.print("Your choice? ");
      int option = 0;
      try{
         option = input.nextInt();
      }
      catch(InputMismatchException exc){
         System.out.println("\nEnter a number for one of the options please\n");
      }
      return option;
   }
   //Will search for the given call sign and print the station
   public void searchCallSign(String callSign){
      System.out.println(); //To make room for the output
      boolean checker = false;
      for(int spot = 0; spot < States.size(); spot++){
         for(int stationNum = 0; stationNum < ((State)States.get(spot)).numStations(); stationNum++){
            if(((State)States.get(spot)).getCallSign(stationNum).equals(callSign)){//If the call sign is found, print 
               System.out.println(((State)States.get(spot)).getState() + ": " + ((State)States.get(spot)).getCallSign(stationNum) + ", " + 
                                  ((State)States.get(spot)).getNetworkAffill(stationNum) + ", " + ((State)States.get(spot)).getCity(stationNum));
               checker = true;  
            }
         }
      }
      if(checker == false)
         System.out.println("Call Sign not found");
      System.out.println();
   }
   //Will search the given state and print its stations
   public void searchState(String State){
      System.out.println(); //Creates space in console
      boolean checker = false;
      
      //To search along the state names
      for(int spot = 0; spot < States.size(); spot++){
         //To search inside each state
         for(int stationNum = 0; stationNum < ((State)States.get(spot)).numStations(); stationNum++){
            //If the state is found, print
            if(((State)States.get(spot)).getState().equals(State)){ 
            System.out.println(((State)States.get(spot)).getState() + ": " + ((State)States.get(spot)).getCallSign(stationNum) + ", " + 
                               ((State)States.get(spot)).getNetworkAffill(stationNum) + ", " + ((State)States.get(spot)).getCity(stationNum));
            checker = true;
            }
         }
      }
      if(checker == false)
         System.out.println("State not found");
      System.out.println();
   }
   //Will print out all the information of each station in each state
   public void printAll(){
      System.out.println();//Creates space in console
      
      //To search along the state names
      for(int spot = 0; spot < States.size(); spot++){
         //To search inside each state
         for(int stationNum = 0; stationNum < ((State)States.get(spot)).numStations(); stationNum++){
               System.out.println(((State)States.get(spot)).getState() + ": " + ((State)States.get(spot)).getCallSign(stationNum) + ", " +
                                  ((State)States.get(spot)).getNetworkAffill(stationNum) + ", " + ((State)States.get(spot)).getCity(stationNum));
         }
      }
      System.out.println();//Creates space in console
   }
   //Will add the station the user gives if it doesn't exist
   public void addStation(){
      Scanner input = new Scanner(System.in);
      System.out.print("State acronym: ");
      String State = input.next();
      System.out.print("Station's call sign: ");
      String CallSign = input.next();
      System.out.print("Station's network affilliation: ");
      String NetworkAffill = input.next();
      System.out.print("Station's city: ");
      String City = input.next();
      
      //To search along the state names
      for(int spot = 0; spot < States.size(); spot++){
         //If the state is found
         if(((State)States.get(spot)).getState().equals(State)){
            //Search inside the state to see if the station exists
            for(int stationNum = 0; stationNum < ((State)States.get(spot)).numStations(); stationNum++){   
               if(((State)States.get(spot)).getCallSign(stationNum).equals(CallSign) && 
                     ((State)States.get(spot)).getNetworkAffill(stationNum).equals(NetworkAffill) && 
                     ((State)States.get(spot)).getCity(stationNum).equals(City)){
                  System.out.println("Station already exists"); 
               //If station doesn't exist, create it 
               }else{
                  ((State)States.get(spot)).addStation(CallSign, NetworkAffill, City);
                  ((State)States.get(spot)).sortStations();
               }
            }
         //If the state isn't found, create a new one
         }else{            
            State temp = new State(State);
            States.add(0, temp);
            ((State)States.get(0)).addStation(CallSign, NetworkAffill, City);
            sortStates();
            spot = States.size();
         }
      }
   }
   //Will delete the station with the call sign given by the user
   public void deleteStation(){
      Scanner input = new Scanner(System.in);
      System.out.print("Station's call sign: ");
      String CallSign = input.next();
      boolean exists = false;
      
      //To search along the state names
      for(int spot = 0; spot < States.size(); spot++){
         //To search inside each state
         for(int stationNum = 0; stationNum < ((State)States.get(spot)).numStations(); stationNum++){
            //If the call sign was found ask if you want to delete it
            if(((State)States.get(spot)).getCallSign(stationNum).equals(CallSign)){
               System.out.print("Station found, confirm that you want to delete(y/n): ");
               String answer = input.next();
               //If the user says either "y" or "yes", delete the station
               if(answer.equals("y") || answer.equals("yes")){
                  ((State)States.get(spot)).deleteStation(CallSign);
                  System.out.println("\nStation has been deleted\n");
                  exists = true;
               //If the user types anything else, don't delete the station
               }else{
                  System.out.println("\nStation has not been deleted\n");
                  exists = true;
               }
            }
         }
      }
      //If the station wasn't found it must not exist
      if(exists == false){
         System.out.println("\nStation not found\n");
      }
   }
   
   public void sortStates(){
      if(States.size() == 1){
         return;
      }
      boolean swapped = false;
      State temp;
      for(int spot = 0; spot < States.size(); spot++){
         swapped = false;
         for(int inner = 0; inner < States.size() - spot - 1; inner++){
            if(((State)States.get(inner)).getState().compareTo(((State)States.get(inner + 1)).getState()) > 0){
               temp = ((State)States.get(inner));
               States.remove(inner);
               States.add(inner + 1, temp);
               swapped = true;
            }
         }
         if(swapped == false){
            break;
         }
      }
   }
}

class State{
   String stateName;
   LinkedList Stations = new LinkedList();
   
   //Creates the state with the given name
   public State(String stateName){
      this.stateName = stateName;
   }
   //Returns the amount of stations in this state
   public int numStations(){
      return Stations.size();
   }
   //Will add a station to this state
   public void addStation(String callSign, String networkAffill, String city){
      Station temp = new Station();
      Stations.add(0, temp);
      ((Station)Stations.get(0)).setCallSign(callSign);
      ((Station)Stations.get(0)).setNetworkAffill(networkAffill);
      ((Station)Stations.get(0)).setCity(city);
      sortStations();
   }
   //Will delete a station from this state
   public void deleteStation(String callSign){
      int spot;
      for(spot = 0; spot < Stations.size(); spot++){
         if(((Station)Stations.get(spot)).getCallSign().equals(callSign)){
            Stations.remove(spot);
         }
      }
      if(spot == Stations.size() - 1)
         System.out.println("Station does not exist");
   }
   //Will sort the stations in this state
   public void sortStations(){
      if(Stations.size() == 1){
         return;
      }
      boolean swapped = false;
      Station temp;
      for(int spot = 0; spot < Stations.size(); spot++){
         swapped = false;
         for(int inner = 0; inner < Stations.size() - spot - 1; inner++){
            if(((Station)Stations.get(inner)).getCallSign().compareTo(((Station)Stations.get(inner + 1)).getCallSign()) > 0){
               temp = ((Station)Stations.get(inner));
               Stations.remove(inner);
               Stations.add(inner + 1, temp);
               swapped = true;
            }
         }
         if(swapped == false){
            break;
         }
      }
   }
   //Returns this state's name
   public String getState(){
      return stateName;
   }
   //Gets this state's Call Sign
   public String getCallSign(int index){
      if(index >= Stations.size())
         throw new IndexOutOfBoundsException();
      return ((Station)Stations.get(index)).getCallSign();
   }
   //Gets this state's Network Affilliation
   public String getNetworkAffill(int index){
      if(index >= Stations.size())
         throw new IndexOutOfBoundsException();
      return ((Station)Stations.get(index)).getNetworkAffill();
   }
   //Gets this state's City
   public String getCity(int index){
      if(index >= Stations.size())
         throw new IndexOutOfBoundsException();
      return ((Station)Stations.get(index)).getCity();
   }
}

class Node{
   Object datum;
   Node next;
   
   public Node(){
      datum = null;
      next = null;
   }
   
   public Object getDatum(){
      return datum;
   }
   public Node getNext(){
      return next;
   }
   public void setDatum(Object datum){
      this.datum = datum;
   }
   public void setNext(Node next){
      this.next = next;
   }
}

class LinkedList implements ListInterface{
   Node head;
   public LinkedList(){
      head = null;
   }
   public boolean isEmpty(){
      return(head == null);
   }
   public int size(){
      int count = 0;
      Node current = head;
      while(current != null){
         count++;
         current = current.getNext();
      }
      return count;
   }
   public Object get(int index){
      Node current = head;
      while((current != null)&&(index != 0)){
         index--;
         current = current.getNext();
      }
      if(current == null)
         throw new IndexOutOfBoundsException();
      else
         return current.getDatum();
   }
   public void add(int index, Object item){
      Node current = head;
      Node previous = null;
      while((current != null)&&(index != 0)){
         index--;
         previous = current;
         current = current.getNext();
      }
      if(index != 0)
         throw new IndexOutOfBoundsException();
      Node splice = new Node();
      splice.setDatum(item);
      splice.setNext(current);
      if(previous == null)
         head = splice;
      else
         previous.setNext(splice);
   }
   
   public Object remove(int index){
      Node current = head;
      Node previous = null;
      while((current != null)&&(index != 0)){
         index--;
         previous = current;
         current = current.getNext();
      }
      if(current == null)
         throw new IndexOutOfBoundsException();
      if(previous == null)
         head = current.getNext();
      else
         previous.setNext(current.getNext());
      return current.getDatum();
   }
   public void removeAll(){
      head = null;
   }
}