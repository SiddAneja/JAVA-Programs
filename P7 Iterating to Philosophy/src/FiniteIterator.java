/////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION ///////////////////////
// Title: FiniteIterator.java
// Files: core.jar
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

import java.util.Iterator;

/**
 * This class implements Iterator<T> interface and creates an finite iterator with a given length
 * that returns values based on the Function<T,T> used to create the next values.
 * This class is a generic iterator.
 * @author Siddharth
 */
public class FiniteIterator<T> implements Iterator<T> {
  
  /**
   * A private instance field that stores an infinite operator used to create the 
   * finite iterator.
   */
  private InfiniteIterator<T> infinite;
  
  /**
   * Private field that stores the maximum number of iterations.
   */
  private int length;
  
  /**
   * A private helper field that counts the number of iteration already occurred.
   */
  private int counter;
  
  /**
   * A constructor method that creates a finite iterator from an infinite iterator by
   * specifying the number of iterations.
   * @param infinite - an infinite iterator
   * @param length - the maximum number of iterations
   */
  public FiniteIterator(InfiniteIterator<T> infinite, int length) {
    this.infinite = infinite;
    this.length = length;
    this.counter = 0;
  }
  
  /**
   * Returns the next element in the iteration.
   * This method overrides the predefined next() method of the iterator package.
   * @returns next element
   */
  public T next() {
    //checks if the iterator has a next value, if not returns null
    if(hasNext()) {
      counter++;
      return infinite.next();
    }
    return null;
  }
  
  /**
   * Returns true if the iteration has more elements and it hasn't reached the max. iterations.
   * This method overrides the predefined hasNext() method of the iterator package.
   */
  public boolean hasNext() {
    //if the maximum iterations has not been reached, returns true else false
    if(length != counter) {
      return true;
    }
    else {
      return false;
    }
  }
}
