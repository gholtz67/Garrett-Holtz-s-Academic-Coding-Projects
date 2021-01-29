/**************************************************************/
/* Garrett Holtz                                              */
/* CS-102, Summer 2019                                        */
/* Programming Assignment 5                                   */
/**************************************************************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Prog5 extends JFrame {

   private Container contents; //What everything sits inside of
   
   private JPanel buttonPanel, inputPanel, outputPanel; //The three panels which make up the gui
   private JTextArea outputArea; //Where all outputs are going to appear
   private JButton stateSearch, callSignSearch, deleteStation, addStation, printAll; //The buttons to interact with the program
   private JTextField stateInput, callSignInput, deleteCallSignInput; //The input fields needed for the first three buttons
   
   public Prog5( String[] args ) {
   
      super("Stations"); //The title of the window
      
      contents = getContentPane();
      contents.setLayout( new GridLayout(1, 3) ); //1 col, 3 rows
      
      //Creating the first panel
      buttonPanel = new JPanel();
      buttonPanel.setLayout( new GridLayout(5, 1) );//5 col, 1 row
      
      //Creating the buttons
      stateSearch = new JButton( "Search for a State" );
      callSignSearch = new JButton( "Search for a Call Sign" );
      deleteStation = new JButton( "Delete a Station" );
      addStation = new JButton( "Add a Station" );
      printAll = new JButton( "Print All Stations" );
      
      //Creating the button listener/handler
      optionButtonHandler obh = new optionButtonHandler( args );
      
      //Adding listeners to all the buttons
      stateSearch.addActionListener( obh );
      callSignSearch.addActionListener( obh );
      deleteStation.addActionListener( obh );
      addStation.addActionListener( obh );
      printAll.addActionListener( obh );
      
      //Creating the text fields
      stateInput = new JTextField("Enter State Here", 2);
      callSignInput = new JTextField("Enter Call Sign Here", 4);
      deleteCallSignInput = new JTextField("Enter Call Sign Here", 4);
      
      //Add the buttons into the panel
      buttonPanel.add( stateSearch );
      buttonPanel.add( callSignSearch );
      buttonPanel.add( deleteStation );
      buttonPanel.add( addStation );
      buttonPanel.add( printAll );
      
      //Creating the second panel
      inputPanel = new JPanel();
      inputPanel.setLayout( new GridLayout(5, 1) ); //5 col, 1 row. So the inputs line up with their repective buttons
      
      //Add the inputs into the panel
      inputPanel.add( stateInput );
      inputPanel.add( callSignInput );
      inputPanel.add( deleteCallSignInput );
      
      //Creating the third panel
      outputPanel = new JPanel();
      outputPanel.setLayout( new FlowLayout() ); //Only one item going into this panel
      
      //Creating the text area
      outputArea = new JTextArea( "Output Area" );
      outputArea.setEditable( false ); //Make it uneditable so it only for output
      
      //Add the output area to the panel
      outputPanel.add(outputArea);
      
      //Add everything into the main content
      contents.add( buttonPanel );
      contents.add( inputPanel );
      contents.add( outputPanel );
      
      //Set the content's size and make it visible
      setSize( 800, 300 );
      setVisible( true );
  }
  
  public static void main( String[] args ) {
    
    Prog5 gui = new Prog5( args ); //The gui
    gui.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE ); //End the program when closing the window
  }
  
  private class optionButtonHandler implements ActionListener {
      
      private Database data; 
      
      public optionButtonHandler( String[] args ){
         data = new Database();
         data.readFile( args ); //Read in the file
      }
      /**************************************************************/
      /* Method: actionPerformed                                    */
      /* Purpose: To give the correct output based on the user's    */
      /*          inputs.                                           */
      /* Parameters:                                                */
      /*    ActionEvent ae: The action the user performed.          */
      /* Returns:                                                   */
      /*    void                                                    */
      /**************************************************************/ 
      public void actionPerformed( ActionEvent ae ) {
         if( ae.getSource() == stateSearch) { //If the search state button was clicked
            outputArea.setText( data.searchState( stateInput.getText() ) );
            stateInput.setText("Enter State Here");
            callSignInput.setText("Enter Call Sign Here");
            deleteCallSignInput.setText("Enter Call Sign Here");
         }
         if( ae.getSource() == callSignSearch) { //If the call sign button was clicked
            outputArea.setText( data.searchCallSign( callSignInput.getText() ) );
            stateInput.setText("Enter State Here");
            callSignInput.setText("Enter Call Sign Here");
            deleteCallSignInput.setText("Enter Call Sign Here");
         }
         if( ae.getSource() == deleteStation) { //If the delete station button was clicked
            outputArea.setText( data.deleteStation( deleteCallSignInput.getText() ) );
            stateInput.setText("Enter State Here");
            callSignInput.setText("Enter Call Sign Here");
            deleteCallSignInput.setText("Enter Call Sign Here");
         }
         if( ae.getSource() == addStation) { //If the add station button was clicked
            String stateName = JOptionPane.showInputDialog("Please Enter the State's Name");
            String callSign = JOptionPane.showInputDialog("Please Enter the Station's Call Sign");
            String networkAffill = JOptionPane.showInputDialog("Please Enter the Station's Network Affilliation");
            String city = JOptionPane.showInputDialog("Please Enter the Station's City");
            if( (stateName == null) || (callSign == null) || (networkAffill == null) || (city == null) ){
               outputArea.setText( "Please fill in all panels" );
            }else{
               outputArea.setText( data.addStation( stateName, callSign, networkAffill, city ) );
            }
         }
         if( ae.getSource() == printAll) { //If the print all button was clicked
            outputArea.setText( data.printAll() );
         }
      }
  }
}