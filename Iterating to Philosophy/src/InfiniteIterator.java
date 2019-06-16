/////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION ///////////////////////
// Title: InfiniteIterator.java
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
import java.util.function.Function;

/**
 * This class implements Iterator<T> interface and creates an infinite iterator that returns
 * values based on the Function<T,T> used to create it.
 * This class is a generic iterator.
 * @author Siddharth
 */
public class InfiniteIterator<T> implements Iterator<T> {
  
  /**
   * A generic private field that stores the start value of the iterator.
   */
  private T startValue;
  
  /**
   * A generic Function field that stores an instance of the class that creates the next
   * iteration based previous values.
   */
  private Function<T,T> getNextValue;
  
  /**
   * Private boolean field that is true only when the next() method is called the first time.
   */
  private boolean firstTimeCalled;
  
  /**
   * A constructor method that creates an infinite iterator based on generic inputs.
   * @param startValue - the value of the first iteration
   * @param nextValue - the instance of a class that creates the next value
   */
  public InfiniteIterator(T startValue, Function<T,T> nextValue) {
    this.startValue = startValue;
    this.getNextValue = nextValue;
    firstTimeCalled = true;
  }
  
  /**
   * Returns the next element in the iteration.
   * This method overrides the predefined next() method of the iterator package.
   * @returns next element
   */
  @Override
  public T next() {
    //Checks if the method is being called for the first time
    if(firstTimeCalled) {
      firstTimeCalled =false;
      return startValue;
    }
    //returns the next number based on the previous value, if not called the first time
    else {
      startValue = getNextValue.apply(startValue);
      return startValue;
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
