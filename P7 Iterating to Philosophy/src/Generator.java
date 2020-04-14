/////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION ///////////////////////
// Title: Generator.java
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
 * This class implements the Iterable interface and creates a generic finite iterator.
 * @author Siddharth
 */
public class Generator<T> implements Iterable<T>{
  
  /**
   * A private instance field that stores an infinite operator used to create the generator.
   */
  private T firstValue;
  
  /**
   * A generic Function field that stores an instance of the class that creates the next
   * iteration based previous values.
   */
  private Function<T,T> generateNextFromLast;
  
  /**
   * Private field that stores the maximum number of iterations.
   */
  private int length;
  
   /**
   * A constructor method that creates a generator given the first value and method to create 
   * the next value.
   * @param firstValue - the start value
   * @param generateNextFromLast - the instance of a class that creates the next value
   */
  public Generator(T firstValue, Function<T,T> generateNextFromLast) {
    this.firstValue = firstValue;
    this.generateNextFromLast = generateNextFromLast;
  }
  
  /**
   * A constructor method that creates a finite generator given the first value, method 
   * to create the next value and the maximum iterations.
   * @param firstValue -  the start value
   * @param generateNextFromLast - the instance of a class that creates the next value
   * @param length - Maximum number of iterations
   */
  public Generator(T firstValue, Function<T,T> generateNextFromLast, int length) {
    this.firstValue = firstValue;
    this.generateNextFromLast = generateNextFromLast;
    this.length = length;
  }

  /**
   * Returns an iterator over elements of type T.
   * @return an iterator
   */
  public Iterator<T> iterator() {
    //if no length is specified, it creates an infinite iterator
    if(length == 0) {
      return new InfiniteIterator<>(firstValue, generateNextFromLast);
    }
    //otherwise, a finite iterator is returned
    else {
      return new FiniteIterator<>(new InfiniteIterator<>(firstValue, generateNextFromLast),length);
    }
  }
}
