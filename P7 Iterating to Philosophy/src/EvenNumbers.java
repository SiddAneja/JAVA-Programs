/////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION ///////////////////////
// Title: EvenNumbers.java
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
 * This class implements Iterator<T> interface and creates an iterator that returns
 * even numbers. 
 * @author Siddharth
 *
 */
public class EvenNumbers implements Iterator<Integer>{
  
    /**
     * Private instance field that stores the first Integer value in the iterator.
     */
    private Integer number;
    
    /**
     * Private boolean field that is true only when the next() method is called the first time.
     */
    private boolean firstTimeCalled;
    
    /**
     * Constructor method that takes an Integer parameter and creates an iterator.
     * @param number
     */
    public EvenNumbers(Integer number) {
      this.number = number; //sets the private field to the parameter
      firstTimeCalled = true; 
    }
    
    /**
     * Returns the next element in the iteration.
     * This method overrides the predefined next() method of the iterator package.
     * @returns next Integer element
     */
    @Override
    public Integer next() {
      //checks if the method is being called for the first time
      if(firstTimeCalled) {
        firstTimeCalled =false;
        return number;
      }
      //returns the next even number, if not called the first time
      else {
        number += 2;
        return number;
      }
    }
    
    /**
     * Returns true if the iteration has more elements. 
     * But in this case, it always returns true.
     * This method overrides the predefined hasNext() method of the iterator package.
     */
    @Override
    public boolean hasNext() {
      return true;
    }
}
