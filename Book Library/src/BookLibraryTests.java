/////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: BookLibraryTests.java
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
import java.util.ArrayList;

/**
 * Class containing the tests for the Book, Subscriber, Librarian and Library tests
 * 
 * @author Alyssa and Siddharth
 *
 */
public class BookLibraryTests {

  /**
   * Tests the getter methods in the Book class
   * 
   * @return true if test passed, false otherwise
   */
  public static boolean testBookConstructorGetters() {
    Book newBook = new Book("Test Book", "Siddharth"); // creates a new test book
    Book newBook2 = new Book("Test Book 2", "Alyssa"); // creates a second new test book
    // checks that title and author of the first test book created are correct, that ID of book is
    // correct, and that the book is available
    if ((newBook.getAuthor().equals("Siddharth")) && (newBook.getTitle().equals("Test Book"))
        && (newBook.getID() == 1) && (newBook.getBorrowerCardBarCode() == null)) {
      // checks that title and author of the second test book created are correct, that ID of book
      // is correct, and that the book is available
      if ((newBook2.getAuthor().equals("Alyssa")) && (newBook2.getTitle().equals("Test Book 2"))
          && (newBook2.getID() == 2) && (newBook2.getBorrowerCardBarCode() == null)) {
        return true;
      }
    }
    return false;

  }

  /**
   * Tests the returnBook method
   * 
   * @return true if test passed, false otherwise
   */
  public static boolean testBookReturnBook() {
    Book newBook = new Book("Test Book", "Siddharth"); // creates a new test book
    newBook.borrowBook(300); // calls borrowBook method to borrow book
    newBook.returnBook(); // calls returnBook method to return book
    if (newBook.isAvailable() == true) { // checks if test book is available after returning it
      return true;
    }
    return false;
  }

  /**
   * Tests checkoutBook method for subscribers
   * 
   * @return true if test passed, false otherwise
   */
  public static boolean testSubscriberCheckoutBook() {
    // creates a new test subscriber
    Subscriber newSubscriber = new Subscriber("Alyssa", 1234, "Madison", "1234567891");
    // creates a new test book
    Book newBook = new Book("Test Book", "Alyssa");
    // calls checkoutBook method with the test subscriber and test book
    newSubscriber.checkoutBook(newBook);
    // checks that the subscriber card bar code is equal to the bar code that the book is borrowed
    // under, that the book is in the subscriber's checked out books list, and that the book is
    // not available
    if ((newSubscriber.getCARD_BAR_CODE() == newBook.getBorrowerCardBarCode())
        && (newSubscriber.isBookInBooksCheckedOut(newBook)) && (newBook.isAvailable() == false)) {
      return true;
    }
    return false;
  }

  /**
   * Tests the methods in the Librarian class
   * 
   * @return true if test passes, false otherwise
   */
  public static boolean testLibrarianMethods() {
    // creates a new test librarian
    Librarian newLibrarian = new Librarian("Siddharth", "cs300");
    // checks that the librarian's username and password are set correctly
    if (newLibrarian.getUsername().equals("Siddharth") && newLibrarian.checkPassword("cs300")) {
      return true;
    }
    return false;
  }

  /**
   * Tests the findBookByAuthor method in the Library class
   * 
   * @return true if test passes, false otherwise
   */
  public static boolean testLibraryFindBookByAuthor() {
    Library testLibrary = new Library("Madison", "Alyssa", "CS 300"); // creates new test library
    testLibrary.addBook("CS 300 with Mouna", "Siddharth"); // adds book to library
    testLibrary.addBook("CS 300 with Gary", "Alyssa"); // adds second book to library
    // creates a test arraylist and sets it equal to the arraylist returned by the
    // findBookByAuthor method given the author of the book created
    ArrayList<Book> testArrayList = testLibrary.findBookByAuthor("Siddharth");
    // checks that the first element in the arraylist is the title of the book with the given
    // author
    if (testArrayList.get(0).getTitle().equals("CS 300 with Mouna")) {
      return true;
    }
    return false;
  }


  /**
   * The main method that calls tests
   * 
   * @param args
   */
  public static void main(String[] args) {
    // checks if tests failed, and if so, prints error message
    if (testBookConstructorGetters() == false) {
      System.out.println("testBookConstructorGetters() FAILED");
    }
    if (testBookReturnBook() == false) {
      System.out.println("testBookReturnBook() FAILED");
    }
    if (testSubscriberCheckoutBook() == false) {
      System.out.println("testSubscriberCheckoutBook() FAILED");
    }
    if (testLibraryFindBookByAuthor() == false) {
      System.out.print(" testLibraryFindBookByAuthor() FAILED");
    }
  }

}
