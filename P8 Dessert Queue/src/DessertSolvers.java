/////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION ///////////////////////
// Title: DessertSolvers.java
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
 * This class solves the problem of which Guest will receive the desert first based on the order
 * they arrived. It provides two methods for two different scenarios. One where there is only one
 * entree before the dessert and the user can provide how many guests should be skipped while
 * providing food. And the other is where only one guest is skipped while handing out meals but the
 * number of courses are provided by the user.
 * 
 * @author Siddharth
 *
 */
public class DessertSolvers {

  /**
   * This method creates a ServingQueue with the numberOfGuests provided and then serves the guests
   * the entree and skips guestsSkipped number of guests. It returns the last person to be served
   * the entree. (first person to get the dessert) This method throws IllegalArgumentException if
   * the numberOfGuests or guestsSkipped is negative.
   * 
   * @param numberOfGuests - number of Guests at the table
   * @param guestsSkipped  - Number of Guests to skip after serving one guest
   * @return the Guest who will be served dessert first (served entree last)
   */
  public static Guest firstDessertVariableSkips(int numberOfGuests, int guestsSkipped) {
    // Checks if the numberOfGuests is negative, if true throws exception
    if (numberOfGuests < 0) {
      throw new IllegalArgumentException("Error: Number of guests cannot be negative!");
    }
    // Checks if the guestsSkipped is negative, if true throws exception
    if (guestsSkipped < 0) {
      throw new IllegalArgumentException("Error: Number of guests skipped cannot be negative!");
    }
    // Creates a ServingQueue with the size of numberOfGuests and adds new Guests to it.
    ServingQueue dessert = new ServingQueue(numberOfGuests);
    for (int i = 0; i < numberOfGuests; i++) {
      dessert.add(new Guest());
    }
    // simulate Serving
    Guest served = null; // The Guest who is being served
    // A loop which will go on until all the guests have been served
    while (!dessert.isEmpty()) {
      // the oldest guest in the queue is served and removed from the list
      served = dessert.remove();
      // Branch which is entered if the queue is still not empty
      if (!dessert.isEmpty()) {
        // loop which skips the number of guestsSkipped after serving a Guest by removing and
        // adding them to the queue
        for (int i = 0; i < guestsSkipped; i++) {
          dessert.add(dessert.remove());
        }
      }
    }
    // Served stores the reference to the last Guest to be served after exiting the loop
    return served;
  }

  /**
   * This method creates a ServingQueue with the numberOfGuests provided and then serves the guests
   * the number of coursesServed and skips one guest. It returns the last Guest to be served before
   * dessert. (first one to be served dessert)
   * 
   * @param numberOfGuests - number of Guests at the table
   * @param guestsSkipped  - Number of Guests to skip after serving one guest
   * @return the Guest who will be served dessert first (served entree last)
   */
  public static Guest firstDessertVariableCourses(int numberOfGuests, int coursesServed) {
    // Checks if the numberOfGuests is negative, if true throws exception
    if (numberOfGuests < 0) {
      throw new IllegalArgumentException("Error: Number of guests cannot be negative!");
    }
    // Checks if the coursesServed is negative, if true throws exception
    if (coursesServed < 0) {
      throw new IllegalArgumentException("Error: Number of courses served cannot be negative!");
    }
    int i; // variable for looping
    // Creates a ServingQueue with the size of numberOfGuests and adds new Guests to it.
    ServingQueue queue1 = new ServingQueue(numberOfGuests);
    for (i = 0; i < numberOfGuests; i++) {
      queue1.add(new Guest());
    }
    // checks if the coursesServed is one, if true it returns the first guest to be served
    if (coursesServed == 1) {
      return queue1.remove();
    }
    // simulate serving
    Guest served = null; // The Guest who is being served
    // A loop which goes on until it has served all the guests the number of courses before dessert
    while ((coursesServed - 1) != 0) {
      coursesServed--; // decrements the coursesServed, to end loop eventually
      // Creates another queue which stores the order in which the Guests in the main serving
      // queue were served and removed.
      ServingQueue queue2 = new ServingQueue(numberOfGuests);
      // This loop serves the Guests in the main queue by skipping one guest
      while (!queue1.isEmpty()) {
        // the oldest guest in the queue is served and removed from the list
        // (stores the last Guest to be served before loop breaks)
        served = queue1.remove();
        // The guest is then added to the second queue for the next course
        queue2.add(served);
        // if the queue isn't empty, skips one guest
        if (!queue1.isEmpty()) {
          queue1.add(queue1.remove());
        }
      } // end of loop
        // Now we change the order of the queue by bringing the Guest served last to the start of
        // the queue
        // add the last served Guest to the start of the empty queue and then use queue2 to add
        // the rest of the guests
      queue1.add(served);
      for (i = 0; i < numberOfGuests - 1; i++) {
        queue1.add(queue2.remove());
      }
    }
    // After all the main courses are served, the loop will break and the first Guest to get the
    // dessert will be stored in served (last person to receive the meal before the dessert)
    return served;
  }
}
