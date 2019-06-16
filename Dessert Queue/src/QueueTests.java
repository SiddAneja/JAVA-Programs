/////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION ///////////////////////
// Title: QueueTests.java
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
 * This class tests the working of different methods in the other classes in the package.
 * 
 * @author Siddharth
 *
 */
public class QueueTests {

  /**
   * This method checks whether the Guest class is properly implemented by testing out
   * hasDietaryRestriction() and the toString() method.
   * 
   * @return true if the methods are properly implemented else false.
   */
  public static boolean testGuestClass() {
    Guest.resetNextGuestIndex(); // resets the static index in Guest
    // Creates a new Guest with a dietary restriction and checks the implementation of the methods
    Guest test = new Guest("Gluten intolerant");
    if (test.hasDietaryRestriction() && test.toString().equals("#1(Gluten intolerant)")) {
      return true;
    }
    // if fails, returns false
    return false;
  }

  /**
   * This method checks whether the add() method in the ServingQueue class is correctly implemented.
   * 
   * @return true if the methods are properly implemented else false.
   */
  public static boolean testServingQueueAdd() {
    Guest.resetNextGuestIndex(); // resets the static index in Guest
    ServingQueue test = new ServingQueue(3);
    // Adds two Guests to the ServingQueue instance created and checks whether they are printed out
    // correctly
    test.add(new Guest("Lactose intolerant"));
    test.add(new Guest());
    if (test.toString().equals("[#1(Lactose intolerant), #2]")) {
      return true;
    }
    return false;
  }

  /**
   * This method checks whether the remove() method in the ServingQueue class is correctly
   * implemented.
   * 
   * @return true if the methods are properly implemented else false.
   */
  public static boolean testServingQueueRemove() {
    Guest.resetNextGuestIndex();// resets the static index in Guest
    ServingQueue test = new ServingQueue(10);
    // Adds three Guest and then calls the remove method on the ServingQueue which will delete the
    // oldest Guest
    test.add(new Guest("Lactose intolerant"));
    test.add(new Guest());
    test.add(new Guest("Gluten allergy"));
    test.remove();
    if (test.toString().equals("[#2, #3(Gluten allergy)]")) {
      return true;
    }
    return false;
  }

  /**
   * This method checks whether the firstDessertVariableSkips() method in the DessertSolvers class
   * is correctly implemented.
   * 
   * @return true if the methods are properly implemented else false.
   */
  public static boolean testFirstDessertVariableSkips() {
    Guest.resetNextGuestIndex(); // resets the static index in Guest
    // Calls the method on 8 Guests and it'll skip 1 Guest. Therefore we check it against the
    // known value of the last served Guest.
    Guest servedLast = DessertSolvers.firstDessertVariableSkips(8, 1);
    if (servedLast.toString().equals("#8")) {
      return true;
    }
    return false;
  }

  /**
   * This method checks whether the firstDessertVariableCourses() method in the DessertSolvers class
   * is correctly implemented.
   * 
   * @return true if the methods are properly implemented else false.
   */
  public static boolean testFirstDessertVariableCourses() {
    Guest.resetNextGuestIndex(); // resets the static index in Guest
    // Calls the method on 8 Guests and with 3 courses. Therefore we check it against the
    // known value of the last served Guest before dessert.
    Guest servedLast = DessertSolvers.firstDessertVariableCourses(8, 3);
    if (servedLast.toString().equals("#4")) {
      return true;
    }
    return false;
  }

  /**
   * This is the main method of this class. This method calls all the other methods and runs their
   * code and prints out error messages if any of the tests fail.
   * 
   * @param args
   */
  public static void main(String[] args) {
    if (testGuestClass() == false) {
      System.out.println("testGuestClass FAILED!");
    }
    if (testServingQueueAdd() == false) {
      System.out.println("testServingQueueAdd FAILED!");
    }
    if (testServingQueueRemove() == false) {
      System.out.println("testServingQueueRemove FAILED!");
    }
    if (testFirstDessertVariableSkips() == false) {
      System.out.println("testFirstDessertVariableSkips FAILED!");
    }

    if (testFirstDessertVariableCourses() == false) {
      System.out.println("testFirstDessertVariableCourses FAILED!");
    }
  }

}
