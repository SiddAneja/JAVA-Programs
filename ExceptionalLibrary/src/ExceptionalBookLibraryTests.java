/////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: ExceptionalBookLibraryTests.java
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

import java.text.ParseException;

/**
 * @author Alyssa and Siddharth
 *
 *         This class tests the methods implemented in ExceptionalLibrary
 */
public class ExceptionalBookLibraryTests {

  /**
   * Tests the libraryParseCardBarCode method in ExceptionalLibrary
   * 
   * @return true if passed, false otherwise
   */
  public static boolean testLibraryParseCardBarCode() {
    // creates a new test Library object
    ExceptionalLibrary test = new ExceptionalLibrary("Madison", "TEST", "CS300");
    // creates a string representing a bar code to test
    String testBarCode = "2019000001";
    // creates an int representing a valid bar code value
    int validBarCode = 2019000001;
    // surrounds code with try block in case ParseException is thrown by method
    try {
      // checks that when the parseCardBarCode method is called with the bar code string, the method
      // will return the correct integer value
      if (test.parseCardBarCode(testBarCode, 111) == validBarCode) {
        return true;
      }
      // catches any ParseException thrown by the method and, if so, prints the stack trace
    } catch (ParseException e) {
      e.printStackTrace();

    }
    return false;
  }

  /**
   * Tests the libraryParseRunLibrarianCheckoutBookCommand method in ExceptionalLibrary
   * 
   * @return true if passed, false otherwise
   */
  public static boolean testLibraryParseRunLibrarianCheckoutBookCommand() {
    // creates a new test library
    ExceptionalLibrary testLibrary = new ExceptionalLibrary("Madison", "TESTLIBRARY", "cs300");
    // creates a string array containing the commands that a librarian would input to add a book
    String[] addBookCommands = {"1", "TestBook", "cs300"};
    // creates a string array containing the commands that a subscriber would input to checkout a
    // book
    String[] addSubscriberCommands = {"2", "TestSub1", "1111", "Madison", "1234567890"};
    // creates a string array containing the commands that a librarian would use to add a
    // subscriber
    String[] commands = {"3", "2019000001", "1"};
    // surrounds code with try block in case ParseException is thrown by any of methods called
    try {
      // calls the parseRunLibrarianAddBookCommand to add a book to the test library
      testLibrary.parseRunLibrarianAddBookCommand(addBookCommands);
      // calls the parseRunLibrarianAddSubscriberCommand method to add a subscriber to the test
      // library
      testLibrary.parseRunLibrarianAddSubscriberCommand(addSubscriberCommands);
      // calls the parseRunLibrarianCheckoutBookCommand method to checkout the created book to the
      // created subscriber
      testLibrary.parseRunLibrarianCheckoutBookCommand(commands);
      // catches any ParseException thrown by the methods called and prints error message
    } catch (ParseException e) {
      System.out.println("ERROR: while processing commands");
      // catches any InstantiationException thrown by the parseRunLibrarianAddSubscriberCommand
      // method and prints error message
    } catch (InstantiationException e) {
      System.out.println("ERROR: while processing commands");
    }
    // calls findBook method with the index of the book created, and calls the isAvailable method
    // to check that the book found is unavailable after being checked out
    if (testLibrary.findBook(1).isAvailable() == false) {
      return true;
    }
    return false;
  }

  /**
   * Tests the libraryParseRunSubscriberReturnBookCommand method in ExceptionalLibrary
   * 
   * @return true if passed, false otherwise
   */
  public static boolean testLibraryParseRunSubscriberReturnBookCommand() {
    // creates a new test library
    ExceptionalLibrary testLibrary = new ExceptionalLibrary("Madison", "TESTLIBRARY", "cs300");
    // creates a string array containing the commands that a librarian would input to add a book
    String[] addBookCommands = {"1", "TestBook", "cs300"};
    // calls the parseRunLibrarianAddSubscriberCommand method to add a subscriber to the test
    // library
    String[] addSubscriberCommands = {"2", "TestSub2", "1111", "Madison", "1234567890"};
    // creates a string array containing the commands that a subscriber would use to check out a
    // book
    String[] checkoutCommands = {"1", "2"};
    // creates a string array containing the commands that a subscriber would use to return a book
    String[] returnCommands = {"2", "2"};
    // surrounds the code in a try block to catch any ParseException or InstantiationException
    // thrown by the methods called
    try {
      // calls the parseRunLibrarianAddBookCommand method with the addBookCommands string array
      // created above to add a book to the test library
      testLibrary.parseRunLibrarianAddBookCommand(addBookCommands);
      // calls the parseRunLibrarianAddSubscriberCommand method with the addSubscriberCommands
      // string array created above to add a subscriber to the test library
      testLibrary.parseRunLibrarianAddSubscriberCommand(addSubscriberCommands);
      // calls the parseRunSubscriberCheckoutBookCommand method with the checkoutCommands array
      // created above to check out a book to the added subscriber
      testLibrary.parseRunSubscriberCheckoutBookCommand(checkoutCommands,
          testLibrary.findSubscriber(2019000002));
      // calls the parseRunSubscriberReturnBookCommand method with the returnCommands array created
      // above to return a book checked out by the subscriber to the library
      testLibrary.parseRunSubscriberReturnBookCommand(returnCommands,
          testLibrary.findSubscriber(2019000002));
      // catches any ParseException thrown by the methods called and prints error message
    } catch (ParseException e) {
      System.out.println("ERROR: while processing commands");
      // catches any InstantiationException thrown by the methods called and prints error message
    } catch (InstantiationException e) {
      System.out.println("ERROR: while processing commands");
    }
    // calls the findBook method with the index of the created book, and the isAvailable method
    // to check that the book is available after being checked out and returned
    if (testLibrary.findBook(2).isAvailable() == true) {
      return true;
    }
    return false;
  }

  /**
   * Tests the parseRunLibrarianAddSubscriberCommand method in ExceptionalLibrary
   * 
   * @return true if passed, false otherwise
   */
  public static boolean testParseRunLibrarianAddSubscriberCommand() {
    // creates a new test library
    ExceptionalLibrary testLibrary = new ExceptionalLibrary("Madison", "TESTLIBRARY", "cs300");
    // creates a string array containing the commands a librarian would use to create a subscriber
    String[] addSubscriberCommands = {"2", "TestSub3", "1111", "Madison", "1234567890"};
    // surrounds code with try block to catch any ParseException or InstantiationException thrown
    try {
      // calls parseRunLibrarianAddSubscriberCommand method with the addSubscriberCommands array
      // created above to add a subscriber to the test library
      testLibrary.parseRunLibrarianAddSubscriberCommand(addSubscriberCommands);
      // catches any ParseException thrown and prints an error message
    } catch (ParseException e) {
      System.out.println("ERROR: while processing commands");
      // catches any InstantiationException thrown and prints an error message
    } catch (InstantiationException e) {
      System.out.println("ERROR: while processing commands");
    }
    // checks that the subscriber created by the method can be found by calling the findSubscriber
    // method with the subscriber's bar code and checking that it does not return null
    if (testLibrary.findSubscriber(2019000003) != null) {
      return true;
    }
    return false;
  }

  /**
   * Tests the libraryParseRunLibrarianSaveBooksCommand method and the
   * libraryParseRunLibrarianLoadBooksCommand method in ExceptionalLibrary
   * 
   * @return true if passed, false otherwise
   */
  public static boolean testLibraryParseRunLibrarianSaveAndLoadBooksCommand() {
    // creates a new test library
    ExceptionalLibrary testLibrary = new ExceptionalLibrary("Madison", "TESTLIBRARY", "cs300");
    // creates a string array containing the commands that a librarian would use to save a list of
    // books to a file
    String[] commands = {"S", "testSavedFile.data"};
    // creates a string array containing the commands that a librarian would use to add a book
    String[] addBookCommands = {"1", "TestBook3", "cs300"};
    // surrounds code in try block to catch any ParseException thrown
    try {
      // calls parseRunLibrarianAddBookCommand method with the addBookCommands array to add a book
      // to the test library
      testLibrary.parseRunLibrarianAddBookCommand(addBookCommands);
      // calls the parseRunLibrarianSaveBooksCommand method with the commands array to save the
      // list of books in the library to a file
      testLibrary.parseRunLibrarianSaveBooksCommand(commands);
      // catches any ParseException thrown in the methods called and prints error message
    } catch (ParseException e) {
      System.out.println("ERROR: while processing commands");
      return false;
    }
    return true;
  }

  /**
   * Calls the test methods in the class and runs them
   */
  public static void main(String[] args) {
    // calls test method and prints error message if test returns false
    if (testLibraryParseCardBarCode() == false) {
      System.out.println("FAILED: testLibraryParseCardBarCode()");
    }
    // calls test method and prints error message if test returns false
    if (testLibraryParseRunLibrarianCheckoutBookCommand() == false) {
      System.out.println("FAILED: testLibraryParseRunLibrarianCheckoutBookCommand()");
    }
    // calls test method and prints error message if test returns false
    if (testLibraryParseRunSubscriberReturnBookCommand() == false) {
      System.out.println("FAILED: testLibraryParseRunSubscriberReturnBookCommand()");
    }
    // calls test method and prints error message if test returns false
    if (testParseRunLibrarianAddSubscriberCommand() == false) {
      System.out.println("FAILED: testParseRunLibrarianAddSubscriberCommand()");
    }
    // calls test method and prints error message if test returns false
    if (testLibraryParseRunLibrarianSaveAndLoadBooksCommand() == false) {
      System.out.println("FAILED: testLibraryParseRunLibrarianSaveAndLoadBooksCommand()");
    }

  }

}
