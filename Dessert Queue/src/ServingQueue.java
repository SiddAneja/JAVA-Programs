/////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION ///////////////////////
// Title: ServingQueue.java
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
 * This class provides an array based queue with circular indexing that can store Guests.
 * 
 * @author Siddharth
 *
 */
public class ServingQueue {

  /**
   * An array that stores Guests and has circular indexing. It is the basis of this Serving Queue.
   */
  private Guest[] array;

  /**
   * A private field that keeps track of the number of elements in the array.
   */
  private int size;

  /**
   * Stores the index of the element at the front of the list (or oldest element)
   */
  private int front;

  /**
   * Stores the index of the element at the end of the list (or latest element)
   */
  private int back;

  /**
   * Creates a new array based queue with a capacity of "seatsAtTable" guests. This queue should be
   * initialized to be empty.
   * 
   * @param seatsAtTable the size of the array holding this queue data
   */
  public ServingQueue(int seatsAtTable) {
    array = new Guest[seatsAtTable];
    front = -1; // initially front is -1 as no element is in the array
    back = -1; // initially back is also -1
  }

  /**
   * Checks whether there are any guests in this serving queue.
   * 
   * @return true when this queue contains zero guests, and false otherwise.
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Adds a single new guest to this queue (to be served after the others that were previously 
   * added to the queue).
   * 
   * @param newGuest is the guest that is being added to this queue.
   * @throws IllegalStateException when called on a ServingQueue with an array that is full
   */
  public void add(Guest newGuest) {
    // Checks if the array is full, if true then throws an exception
    if (size == array.length) {
      throw new IllegalStateException("Error: The Queue is already full!");
    }
    // if the array is empty, adds newGuest to index 0 and sets front and back to 0
    if (size == 0) {
      array[0] = newGuest;
      front = 0;
      back = 0;
    }
    // if array already has elements, enter branch
    else {
      // increments the back index to the next available index
      back = (front + (size)) % array.length;
      // sets newGuest at the new back index
      array[back] = newGuest;
    }
    // increments size
    size++;
  }

  /**
   * Accessor for the guest that has been in this queue for the longest. This method does not add 
   * or remove any guests.
   * 
   * @return a reference to the guest that has been in this queue the longest.
   * @throws IllegalStateException when called on an empty ServingQueue
   */
  public Guest peek() {
    // Checks if the array is full, if true then throws an exception
    if (size == 0) {
      throw new IllegalStateException("Error: The Queue is empty!");
    }
    // returns the oldest element in the array
    return array[front];
  }

  /**
   * Removes the guest that has been in this queue for the longest.
   * 
   * @return a reference to the specific guest that is being removed.
   * @throws IllegalStateException when called on an empty ServingQueue
   */
  public Guest remove() {
    // Checks if the array is empty, if true then throws an exception
    if (size == 0) {
      throw new IllegalStateException("Error: The Queue is empty!");
    }
    // Makes a new Guest variable and stores the Guest at the front index of the array
    Guest oldestGuest = array[front];
    // sets the element at the front index of the array to null
    array[front] = null;
    // increments the front index
    front = (front + 1) % array.length;
    size--; // decrements size
    return oldestGuest;// returns the removed Guest reference
  }

  /**
   * The string representation of the guests in this queue should display each of the guests in this
   * queue (using their toString() implementation), and should display them in a comma separated
   * list that is surrounded by a set of square brackets. (this is similar to the formatting of
   * java.util.ArrayList.toString()). The order that these guests are presented in should be (from
   * left to right) the guest that has been in this queue the longest, to the guest that has been in
   * this queue the shortest. When called on an empty ServingQueue, returns "[]".
   * 
   * @return string representation of the ordered guests in this queue
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    // creates a String that will be returned
    String queue = "[";
    // Loop that iterates through the array
    for (int i = 0; i <= back; i++) {
      // checks if the array isnt empty
      if (array[i] != null) {
        // Creates a temp String that stores the toString() value of the element at index i
        String temp = array[i].toString();
        // cocats the temp String to the String that will be returned
        queue = queue + temp;
        // if i is equal to the back index, break out of the for loop, else add a comma to the
        // String to be returned
        if (i == back) {
          break;
        } else {
          queue = queue + ", ";
        }
      }
    }
    // add "]" to the end of the String before returning it
    queue = queue + "]";
    return queue;
  }
}
