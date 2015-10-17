/*
* Name: David Wang 
* Login: cs8bagf
* Date: January 13, 2012
* File: Craps.java
* Sources of Help: Instructions
* This program simulates the game of Craps 
* which when a player clicks on the white space,
* if else statements are executed in order to 
* show the correct outcome. A random generator is
* also available due to the importing of java.util.*. 
*/

import objectdraw.*;
import java.util.*;

public class Craps extends WindowController
{
  /**
	 * 
	 */
	
private static final double TEXT_LEFT = 10;   // x coord of text output
  private static final double MESSAGE_TOP = 50; // y coord of message text
  private static final double STATUS_TOP = 70;  // y coord of status text
  private static final double WINS_TOP = 100;   // y coord of message text
  private static final double LOSSES_TOP = 120; // y coord of message text

  private boolean newGame = true;     // True if starting new game
  private Text status,                // Display status of game
               message,               // Display dice roll value
               winsMsg,               // Display total number of wins
               lossesMsg;             // Display total number of losses 
  private int point;                  // number to roll for win
  private int wins = 0;               // number of total wins
  private int losses = 0;             // number of total losses

  // Generator for roll of a die - DO NOT CHANGE SEED
  private Random dieGenerator = new Random( 17 );
  
  /*
   * Method Name: begin  
   * Create status and message on canvas
   * Parameters: none
   * Return: void
   */
  public void begin()
  {
   status = new Text( "", TEXT_LEFT, STATUS_TOP, canvas );
    message = new Text( "", TEXT_LEFT, MESSAGE_TOP, canvas );
    winsMsg = new Text( "", TEXT_LEFT, WINS_TOP, canvas );
    lossesMsg = new Text( "", TEXT_LEFT, LOSSES_TOP, canvas );
  }
  
  /*
   * Name:onMouseClick
   * Parameters: Location pt
   * Return: void
   * For each click, roll the dice and report the results
   */
  public void onMouseClick( Location pt )
  {
    /* 
     * Get values for both die and display sum.
     * nextInt(6) returns an int between 0 (inclusive) and 6 (exclusive)
     * thus the + 1 to get a value between 1 and 6 inclusive.
     */
    int roll = dieGenerator.nextInt(6) + 1 + dieGenerator.nextInt(6) + 1;
    message.setText( "You rolled a " + roll + "!" );
    
    if ( newGame )                      // start a new game
    {
      if ( roll == 7 || roll == 11 )    // 7 or 11 wins on first throw
      {
        status.setText( "You win!" );
        wins++;
      }
      else if ( roll == 2 || roll == 3 || roll == 12 )  // 2, 3, or 12 loses
      {
        status.setText( "You lose!" );
        losses++;
      }
      else                       // Set roll to be the point to be made
      {
        status.setText( "Try for your point!" );
        point = roll;
        newGame = false;         // no longer a new game
      }
    }
    else                         // continue trying to make the point
    {
      if ( roll == 7 )           // 7 loses when trying for point
      {
        status.setText( "You lose!" );
        newGame = true;          // set to start new game
        losses++;
      }
      else if ( roll == point )  // making the point wins!
      {
        status.setText( "You win!" );
        newGame = true;
        wins++;
      }
      else                       // keep trying
      {
        status.setText( "Keep trying for " + point + " ..." );
      }
    }        

    winsMsg.setText( "You won " + wins + " times!" );
    lossesMsg.setText( "You lost " + losses + " times!" );
  }
}
