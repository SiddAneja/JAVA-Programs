/////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION ///////////////////////
// Title: StorageUnitTests.java
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
 * This class tests different methods in Box.java, LinkedBoxNode.java, LinkedBoxList.java
 * 
 * @author Siddharth
 *
 */
public class StorageUnitTests {
  /**
   * This method tests whether the behavior of equals method is correct.
   * 
   * @return true if both tests pass, false otherwise
   */
  public static boolean testBoxEquals() {
    Box testBox = new Box(2, 21); // Creates a new Box
    // Two boolean variables for two tests
    boolean testPassed1 = false;
    boolean testPassed2 = false;

    // Test 1 - testing equals() with an object which is not a box
    LinkedBoxNode test = new LinkedBoxNode(new Box(2, 2));
    if (testBox.equals(test)) {
      testPassed1 = false;
    } else {
      testPassed1 = true;
    }

    // Test 2 - testing equals() with an object which is a box
    Box test2 = new Box(2, 21);
    if (testBox.equals(test2)) {
      testPassed2 = true;
    } else {
      testPassed2 = false;
    }
    // Checking if both tests pass
    if (testPassed1 && testPassed2 == true) {
      return true;
    }
    return false;
  }

  /**
   * This method tests whether the behavior of compareTo method is correctly implemented.
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testBoxCompareTo() {
    // creates two boxes with different weights
    // such that testBox is heavier than test
    Box testBox = new Box(2, 21);
    Box test = new Box(32, 1);
    if (testBox.compareTo(test) > 0) {
      return true;
    }
    return false;
  }

  /**
   * This method tests whether the behavior of add method is correctly implemented.
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testLinkedBoxListAdd() {
    // creates three boxes with the same color but different weights
    // such that testBox2 > testBox3 > testBox1
    Box testBox1 = new Box(3, 2);
    Box testBox2 = new Box(3, 4);
    Box testBox3 = new Box(3, 3);
    // Creates a list with capacity of 10
    LinkedBoxList testList = new LinkedBoxList(10);
    // Add the boxes in the list
    testList.add(testBox1);
    testList.add(testBox2);
    testList.add(testBox3);
    // The add method should be added in descending order such that the head of the list
    // will point towards the heaviest box
    if (testList.get(0).equals(testBox2) && testList.get(1).equals(testBox3)
        && testList.get(2).equals(testBox1)) {
      return true;
    }
    return false;
  }

  /**
   * This method tests whether remove method defined in your LinkedBoxList works correctly.
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testLinkedBoxListRemove() {
    // Creates four boxes of different weights
    Box testBox1 = new Box(2, 2);
    Box testBox2 = new Box(3, 5);
    Box testBox3 = new Box(3, 3);
    Box testBox4 = new Box(3, 4);
    // Creates a list with an initial capacity of 10
    LinkedBoxList testList = new LinkedBoxList(10);
    // Add all the boxes to the list
    testList.add(testBox1);
    testList.add(testBox2);
    testList.add(testBox3);
    testList.add(testBox4);
    // removes the box at index 1, which should be testBox4
    testList.remove(1);
    if (testList.get(0).equals(testBox2) && testList.get(1).equals(testBox3)
        && testList.get(2).equals(testBox1)) {
      return true;
    }
    return false;
  }

  /**
   * This method tests if the contains method is implemented correctly.
   * 
   * @return true if it passes the test, false otherwise
   */
  public static boolean testLinkedBoxListContains() {
    // Creates four boxes of different weights
    Box testBox1 = new Box(2, 2);
    Box testBox2 = new Box(3, 5);
    Box testBox3 = new Box(3, 3);
    Box testBox4 = new Box(3, 4);
    // Creates a list with an initial capacity of 10
    LinkedBoxList testList = new LinkedBoxList(10);
    // Add all the boxes to the list
    testList.add(testBox1);
    testList.add(testBox2);
    testList.add(testBox3);
    testList.add(testBox4);
    testList.remove(1);// removes the box at index 1, which should be testBox4
    if (testList.contains(testBox2) && testList.contains(testBox3) 
        && testList.contains(testBox1)) {
      return true;
    }
    return false;
  }

  /**
   * The main method that calls all the testing methods and runs them
   * @param args from the command line
   */
  public static void main(String[] args) {
    if (testBoxEquals() == false) {
      System.out.println("FAILED: testBoxEquals() method failed to execute.");
    }
    if (testBoxCompareTo() == false) {
      System.out.println("FAILED: testBoxCompareTo() method failed to execute.");
    }
    if (testLinkedBoxListAdd() == false) {
      System.out.println("FAILED: testLinkedBoxListAdd() method failed to execute.");
    }
    if (testLinkedBoxListRemove() == false) {
      System.out.println("FAILED: testLinkedBoxListRemove() method failed to execute.");
    }
    if (testLinkedBoxListContains() == false) {
      System.out.println("FAILED: testLinkedBoxListContains() method failed to execute.");
    }
  }
}
