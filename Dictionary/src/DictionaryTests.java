/////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: DictionaryTests.java
// Files: none
// Course: CS300 Spring 2019
//
// Author: Siddharth Aneja
// Email: saneja@wisc.edu
// Lecturer's Name: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Alyssa Odau
// Partner Email: odau@wisc.edu
// Partner Lecturer's Name: Mouna Kacem
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
 * Class implementing tests to check the good-functioning of the methods in the DictionaryBST class
 * 
 * @author Alyssa and Siddharth
 *
 */
public class DictionaryTests {

  /**
   * Test checking the good functioning of the isEmpty() method in the DictionaryBST class
   * 
   * @return true if test passes, false otherwise
   */
  public static boolean testDictionaryIsEmpty() {
    DictionaryBST test = new DictionaryBST(); // creates new DictionaryBST
    if (test.isEmpty()) { // checks if DictionaryBST is empty
      return true; // if so, returns true
    }
    return false; // otherwise, returns false
  }

  /**
   * Tests the good functioning of the AddWord() method in the DictionaryBST class
   * 
   * @return true if test passed, false otherwise
   */
  public static boolean testDictionaryAdd() {
    DictionaryBST test = new DictionaryBST(); // creates new DictionaryBST
    // adds three new words to DictionaryBST using addWord method
    test.addWord("a", "apple");
    test.addWord("b", "banana");
    test.addWord("c", "cat");
    // checks that calling the lookup method returns the correct meaning to check that the word
    // was successfully added
    if (test.lookup("c").equalsIgnoreCase("cat")) {
      return true; // if so, returns true
    }
    return false; // otherwise returns false
  }

  /**
   * Test method checking the good functioning of the height() method in the DictionaryBST class
   * 
   * @return true if passed, false otherwise
   */
  public static boolean testDictionaryHeight() {
    DictionaryBST test = new DictionaryBST(); // creates new DictionaryBST
    // adds words to DictionaryBST
    test.addWord("e", "elephant");
    test.addWord("d", "donkey");
    test.addWord("b", "ball");
    test.addWord("c", "cat");
    test.addWord("f", "funny");
    test.addWord("a", "apple");
    // creates int height and sets it equal to int returned by height() method
    int height = test.height();
    if (height == 4) { // checks that height is correct number
      return true; // if so, returns true
    }
    return false; // otherwise, returns false
  }

  /**
   * Test method checking the good functioning of the getAllWords method in the DictionaryBST class
   * 
   * @return true if passed, false otherwise
   */
  public static boolean testGetAllWords() {
    DictionaryBST test = new DictionaryBST(); // creates new DictionaryBST
    // adds words to DictionaryBST
    test.addWord("e", "elephant");
    test.addWord("d", "donkey");
    test.addWord("b", "ball");
    test.addWord("c", "cat");
    test.addWord("f", "funny");
    test.addWord("a", "apple");
    // checks that when getAllWords method is called, it returns the correct sequence of words
    if (test.getAllWords().toString().equals("[a, b, c, d, e, f]")) {
      return true; // if so, returns true
    }
    return false; // otherwise, returns false
  }

  /**
   * Test method checking the good functioning of the size method in the DictionaryBST class
   * 
   * @return true if passed, false otherwise
   */
  public static boolean testSize() {
    DictionaryBST test = new DictionaryBST(); // creates a new DictionaryBST
    // adds 6 new words to the DictionaryBST
    test.addWord("e", "elephant");
    test.addWord("d", "donkey");
    test.addWord("b", "ball");
    test.addWord("c", "cat");
    test.addWord("f", "funny");
    test.addWord("a", "apple");
    // checks that the size method returns 6 when called for this DictionaryBST
    if (test.size() == 6) {
      return true; // if so, returns true
    }
    return false; // otherwise, returns false
  }



  /**
   * Main method running the test methods
   * 
   * @param args
   */
  public static void main(String[] args) {
    // for each test method, checks if it returns false
    if (testDictionaryIsEmpty() == false) {
      // if so, prints an error message
      System.out.println("FAILED: testDictionaryIsEmpty().");
    }
    if (testDictionaryAdd() == false) {
      System.out.println("FAILED: testDictionaryAdd().");
    }
    if (testDictionaryHeight() == false) {
      System.out.println("FAILED: testDictionaryHeight().");
    }
    if (testGetAllWords() == false) {
      System.out.println("FAILED: testGetAllWords().");
    }
    if (testSize() == false) {
      System.out.println("FAILED: testSize().");
    }
  }

}

