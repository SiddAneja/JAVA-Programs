/////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION ///////////////////////
// Title: Guest.java
// Files: none
// Course: CS300 Spring 2019
//
// Author: Siddharth Aneja
// Email: saneja@wisc.edu
// Lecturer's Name: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: none
// Partner Email: NA
// Partner Lecturer's Name: NA
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

/**
 * This class provides the basis to create different Guests with different dietary restrictions and
 * certain accessor and mutator methods.
 * 
 * @author Siddharth
 *
 */
public class Guest {

  /**
   * This private static field keeps count of the number of Guests being created and assigns the
   * Guest with their respective index.
   */
  private static int index;

  /**
   * This private instance field stores the index of the particular Guest.
   */
  private int guestIndex;

  /**
   * This private field stores the dietary restriction of the Guest if they were created with one.
   */
  private String dietaryRestriction = null;

  /**
   * Resets the counting of newly constructed guest indexes, so that the next new Guest that is
   * created will be associated with the guest index of one.
   * 
   * Note: that this will be helpful when running several tests, or solving solving several dessert
   * simulation problems. And that this should never be called from ServingQueue.java
   */
  public static void resetNextGuestIndex() {
    index = 0;
  }

  /**
   * Constructs a new guest with no dietary restrictions. This guest should be associated with an
   * index that is one larger than the previously constructed guest (or 1, if no prior guest, or if
   * resetNextGuestIndex() was called more recently).
   */
  public Guest() {
    index++; // increments the static value
    this.guestIndex = index; // assigns the Guest it's own index
  }

  /**
   * Constructs a new guest with the specified dietary restrictions. This guest should be associated
   * with an index that is one larger than the previously constructed guest (or 1, if no prior
   * guest, or if resetNextGuestIndex() was called more recently).
   * 
   * @param dietaryRestriction describes requirements for what this guest can and cannot eat
   */
  public Guest(String dietaryRestriction) {
    index++;
    this.guestIndex = index;
    // Sets the private dietaryRestriction field to the parameter
    this.dietaryRestriction = dietaryRestriction;
  }

  /**
   * Access whether this guest has any dietary restrictions or not
   * 
   * @return true for guests constructed using new Guest(String), false otherwise
   */
  public boolean hasDietaryRestriction() {
    // Checks whether the private field is null or not
    if (this.dietaryRestriction != null) {
      return true;
    }
    return false;
  }

  /**
   * The string representation of a Guest should be formatted as, for examples: #3(no dairy) for a
   * guest with a guest index of 3 and the dietary restriction of "no dairy", or: #4 for a guest
   * with a guest index of 4 and no dietary restriction
   * 
   * @return string representing the guest index and any dietary restriction they might have
   * @see java.lang.Object#toString()
   */
  public String toString() {
    if (this.hasDietaryRestriction()) {
      return "#" + guestIndex + "(" + dietaryRestriction + ")";
    }
    return "#" + guestIndex;
  }
}
