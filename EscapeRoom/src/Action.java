/////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Action.java
// Files: none
// Course: CS300 Spring 2019
//
// Author: Siddharth Aneja
// Email: saneja@wisc.edu
// Lecturer's Name: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Alyssa Odau
// Partner Email: odau@wisc.edu
// Partner Lecturer's Name: Mouna Kacem
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// _x__ Write-up states that pair programming is allowed for this assignment.
// _x__ We have both read and understand the course Pair Programming Policy.
// _x__ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: none
// Online Sources: none
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.ArrayList;

/**
 * This class creates a Action instance in the EscapeRoom class that is used to return the message
 * or Thing(another class) associated with it.
 * 
 * @author Siddharth and Alyssa
 *
 */
public class Action {
  /**
   * Message printed by this action (or null to do nothing).
   */
  private String message;

  /**
   * The Thing that the action is associated with.
   */
  private Thing thing;

  /**
   * Constructor method that takes in one Thing parameter and creates and instance of Action.
   * 
   * @param thing - that is associated with the Action
   */
  public Action(Thing thing) {
    this.thing = thing; // Initializes Thing to Thing parameter
  }

  /**
   * Constructor that takes two parameters and creates and instance of Action.
   * 
   * @param message that is printed by this Action
   * @param thing   that is associated with this action
   */
  public Action(String message, Thing thing) {
    this.message = message; // Initializes message to message parameter
    this.thing = thing; // initializes thing to thing parameter
  }

  /**
   * Constructor method that takes in one String and creates and instance of Action.
   * 
   * @param message that is printed by this action
   */
  public Action(String message) {
    this.message = message;
  } // initialize this new action

  /**
   * This method checks if the Thing associated with the action is null, if not it activates it and
   * adds it to the arrayList which is passed as a parameter and then sets the Thing reference to
   * null. Then it prints out the message associated with that action if it is not null.
   * 
   * @param thingArray
   */
  public void act(ArrayList<Thing> thingArray) {
    // Checks if thing reference for this action is null, if not it activates it, adds it
    // to the arrayList and sets it to null
    if (thing != null) {
      thing.activate();
      thingArray.add(thing);
      this.thing = null;
    }
    // Checks if the message is null, if not it prints it out
    if (!this.message.equals(null)) {
      System.out.println(this.message);
    }
  } // when message is not null, message is printed to System.out
}
