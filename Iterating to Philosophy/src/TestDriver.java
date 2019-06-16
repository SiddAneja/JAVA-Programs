/////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION ///////////////////////
// Title: TestDriver.java
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
 * This class tests the methods from the other files and checks if they are working correctly.
 * @author Siddharth
 *
 */
public class TestDriver {
  
  /**
   * This method tests if the EvenNumbers class is properly implemented.
   * @return true if the tests pass, false otherwise.
   */
  public static boolean testEvenNumbers() {
    //Creates a new EvenNumber variable with Integer parameter
    EvenNumbers it = new EvenNumbers(44); 
    //The first value returned by the next() method should be the initial parameter itself
    if(it.next() != 44) {
      System.out.println("The first call of EvenNumbers.next() "
          + "did not return the value passed into its constructor.");
      return false;
    }
    //Checks if the next value returned is correct
    if(it.next() != 46) { 
      System.out.println("The second call of EvenNumbers.next() "
          + "did not return the smallest even number, "
          + "that is larger than the previously returned number.");
      return false;
    }
    //hasNext() method should always return true
    if(it.hasNext() != true) { 
      System.out.println("EvenNumbers.next() returned false, "
          + "but should always return true.");
      return false;
    }
    return true;
  }
  
  /**
   * This method tests if the InfiniteIterator class is properly implemented when it uses
   * Integer values and nextPowerOftwo() class.
   * @return true if the tests pass, false otherwise.
   */
  public static boolean testPowersOfTwo() {
    //creates an infinite iterator with an Integer
    InfiniteIterator<Integer> it = new InfiniteIterator<>(8, new NextPowerOfTwo());
    //tests if the next() method returns correct values
    if(it.next() != 8) {
      System.out.println("The first call of InfiniteIterator.next() "
          + "did not return the number passed into its constructor.");
      return false;
    }
    if(it.next() != 16) {
      System.out.println("The second call of InfiniteIterator.next() "
          + "did not return the smallest power of two number, "
          + "that is larger than the previously returned number.");
      return false;
    }
    //hasNext() method should always return true
    if(it.hasNext() != true) {
      System.out.println("InfiniteIterator.next() returned false, "
          + "but should always return true.");
      return false;
    }
    return true;
  }
  
  /**
   * This method tests if the InfiniteIterator class is properly implemented when it uses
   * String values and addExtraSmile() class.
   * @return true if the tests pass, false otherwise.
   */
  public static boolean testAddExtraSmile() {
    //Creates an infinite iterator with a String input
    InfiniteIterator<String> it = new InfiniteIterator<>("Hello", new AddExtraSmile());
    //Checks if the next() method returns the correct values
    if(!it.next().equals("Hello")) {
      System.out.println("The first call of InfiniteIterator.next() "
          + "did not return the string passed into its constructor.");
      return false;
    }
    if(!it.next().contains(" :)")) {
      System.out.println("The second call of InfiniteIterator.next() "
          + "did not return the a string with one more :), "
          + "than the previously returned string.");
      return false;
    }
    //hasNext() method should always return true
    if(it.hasNext() != true) {
      System.out.println("InfiniteIterator.next() returned false, "
          + "but should always return true.");
      return false;
    }
    return true;
  }
  
  /**
   * This method tests if the FiniteIterator class is properly implemented when it uses
   * Integer values and returns the correct number of values based on the number of 
   * iterations provided.
   * @return true if the tests pass, false otherwise.
   */
  public static boolean testFiniteIterator() {
    //Creates an infinite iterator to be used in the Finite iterator
    InfiniteIterator<Integer> infinite = new InfiniteIterator<>(2, new NextPowerOfTwo());
    //Creates a finite iterator which allows 8 iterations
    FiniteIterator<Integer> it = new FiniteIterator<>(infinite, 8);
    String s = "";//creates an empty string to be used in the test
    //Tests all the values returned by the finite iterator
    while(it.hasNext())
      s += " " + it.next();
    if(!s.equals(" 2 4 8 16 32 64 128 256")) {
      System.out.println("Repeatedly called the next() method of a FiniteIterator,"
          + "and the incorrect valuese were returned:" + s);
      return false;
    }
    return true;
  }
  
  /**
   * This method tests of the Generator class is properly implemented.
   * @return true if the tests pass, false otherwise.
   */
  public static boolean testGenerator() {
    //Create Generator which takes Integer values and uses nextPowerOfTwo() class 
    Generator<Integer> generate = new Generator<>(2, new NextPowerOfTwo(), 8);
    String s = ""; //creates an empty string to be used in the test
    //The for each loops steps through all the values in the Generator and concats them 
    //to the String to test if it is correctly implemented
    for(Integer number : generate) {
      s += " " + number;
    }
    if(!s.equals(" 2 4 8 16 32 64 128 256")) {
      System.out.println("Repeatedly called the next() method of a FiniteIterator,"
          + "when generator from Generator and the incorrect valuese were returned:" + s);
      return false;
    }
    return true;
  }

  /**
   * The main method that runs all the testing method and returns errors if any of them fail.
   * @param args - arguments
   */
  public static void main(String[] args) {
    if(testEvenNumbers() == false) {
      System.out.println("testEvenNumbers() FALIED");
    }
    if(testPowersOfTwo() == false) {
      System.out.println("testPowersOfTwo() FALIED");
    }
    if(testAddExtraSmile() == false) {
      System.out.println("testAddExtraSmile() FALIED");
    }
    if(testFiniteIterator() == false) {
      System.out.println("testFiniteIterator() FALIED");
    }
    if(testGenerator() == false) {
      System.out.println("testGenerator() FALIED");
    }
  }

}

/**
 * This class implements the Function interface to produce the next power of two value based
 * on the previous value.
 * @author Siddharth
 *
 */
class NextPowerOfTwo implements Function<Integer,Integer> {
  /**
   * Applies this function to the given argument.
   * @returns the new Integer value
   */
  @Override
  public Integer apply(Integer previous) {
    return 2*previous; //Multiplies the previous value by 2 and returns it
  }
}

/**
 * This class implements the Function interface to produce a new String based on the previous
 * value.
 * @author Siddharth
 */
class AddExtraSmile implements Function<String,String> {
  /**
   * Applies this function to the given argument.
   * @returns the new String value
  */
  @Override
  public String apply(String t) {
    return t + " :)";//adds a smile to the previous string and returns it
  }
}
