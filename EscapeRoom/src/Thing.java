/////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Thing.java
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

import processing.core.PApplet;

/**
 * This class is used to create instances of the objects(key, chest etc.) in the EscapeRoom
 * application.
 * 
 * @author Siddharth and Alyssa
 *
 */
public class Thing {
  /**
   * Creates and initializes a reference of PApplet to null.
   */
  private static PApplet processing = null;

  /**
   * This method is used to set the private PApplet variable to the parameter passed in the method.
   * 
   * @param processing - the PApplet reference set be associated with this Thing reference
   */
  public static void setProcessing(PApplet processing) {
    Thing.processing = processing; // initializes processing field
  }

  /**
   * Accessor method to retrieve this static field.
   * 
   * @return the processing field value associated with this reference
   */
  protected static PApplet getProcessing() {
    return Thing.processing;
  }

  /**
   * The constant name identifying this object.
   */
  private final String NAME;

  /**
   * Active means thing is visible and can be interacted with.
   */
  private boolean isActive;

  /**
   * Constructor method that initialize name, and set isActive to true.
   * 
   * @param name to identify this object
   */
  public Thing(String name) {
    this.NAME = name; // Initializes NAME to name parameter
    this.isActive = true; // Initializes isActive to true
  }

  /**
   * This method is a helper that returns true only when contents of name equal NAME.
   * 
   * @param name
   * @return
   */
  public boolean hasName(String name) {
    // Checks if name parameter equals NAME, if true returns true
    if (name.equals(this.NAME)) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * This method checks if the Thing reference is currently active, if so return true else false.
   * 
   * @return
   */
  public boolean isActive() {
    // returns true only when isActive is true
    if (this.isActive == true) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * This method changes isActive to true.
   */
  public void activate() {
    this.isActive = true;
  }

  /**
   * This method changes isActive to false.
   */
  public void deactivate() {
    this.isActive = false;
  }

  /**
   * This method returns null. Subclass types will override this update() method.
   * 
   * @return null
   */
  public Action update() {
    return null;
  }
}
