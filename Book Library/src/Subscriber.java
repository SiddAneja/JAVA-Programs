/////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Subscriber.java
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
 * This class models a public library subscriber. A subscriber is a card holder who can borrow
 * (checkout) and return library books
 * 
 * @author Alyssa and Siddharth
 */
public class Subscriber {


  /**
   * Static int field representing the maximum number of books that can be checked out to one
   * subscriber
   */
  private final static int MAX_BOOKS_CHECKED_OUT = 10;

  /**
   * Static int field representing the card bar code of the next subscriber to be created
   */
  private static int nextCardBarCode = 2019000001;

  /**
   * Instance int field representing the 4-digit Personal Identification Number to verify the
   * identity of this subscriber
   */
  private int pin;

  /**
   * The final Integer field representing the card bar code of this subscriber
   */
  private final Integer CARD_BAR_CODE;

  /**
   * The name of this subscriber
   */
  private String name;

  /**
   * The address of this subscriber
   */
  private String address;

  /**
   * The phone number of this subscriber
   */
  private String phoneNumber;

  /**
   * An arraylist of books checked out by the subscriber and not yet returned. A subscriber can have
   * at most 10 checked out books
   */
  private ArrayList<Book> booksCheckedOut;

  /**
   * An arraylist listing the books returned by this subscriber
   */
  private ArrayList<Book> booksReturned;

  /**
   * Creates a new subscriber with given name, address, and phone number, and initializes its other
   * instance fields
   * 
   * @param name        - name of the subscriber
   * @param pin         - 4-digits personal information number of this subscriber
   * @param address     - address of this subscriber
   * @param phoneNumber - phone number of this subscriber
   */
  public Subscriber(String name, int pin, String address, String phoneNumber) {
    this.name = name; // sets private field name equal to parameter name
    this.pin = pin; // sets private field pin equal to parameter pin
    this.address = address; // sets private field address equal to parameter address
    this.phoneNumber = phoneNumber; // sets private field phone number equal to parameter phone
    // number
    booksCheckedOut = new ArrayList<Book>(); // creates new arraylist to initialize booksCheckedOut
    // field
    booksReturned = new ArrayList<Book>(); // creates new arraylist to initialize booksReturned
    // field
    CARD_BAR_CODE = nextCardBarCode; // sets CARD_BAR_CODE equal to the next available bar code
    ++nextCardBarCode; // increments nextCardBarCode
  }

  /**
   * Returns this subscriber's address
   * 
   * @return the address
   */
  public String getAddress() {
    return address;
  }

  /**
   * Updates this subscriber's address
   * 
   * @param address - the address to set
   */
  public void setAddress(String address) {
    this.address = address; // sets private field address equal to parameter address
  }

  /**
   * Returns this subscriber's phone number
   * 
   * @return the phone number
   */
  public String getPhoneNumber() {
    return phoneNumber;
  }

  /**
   * Updates this subscriber's phone number
   * 
   * @param phoneNumber - the phoneNumber to set
   */
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber; // sets private field phone number equal to parameter phone
    // number
  }

  /**
   * Returns this subscriber PIN (4-digits Personal Identification Number)
   * 
   * @return the pin
   */
  public int getPin() {
    return pin;
  }

  /**
   * Returns this subscriber's card bar code
   * 
   * @return the CARD_BAR_CODE
   */
  public Integer getCARD_BAR_CODE() {
    return CARD_BAR_CODE;
  }

  /**
   * Returns this subscriber's name
   * 
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Checks out an available book. The checkout operation fails if the maximum number of checked out
   * books by this subscriber is already reached
   * 
   * @param book - reference to the book to be checked out by this subscriber
   */
  public void checkoutBook(Book book) {
    // checks that the size of the arraylist of books checked out by the subscriber does not
    // exceed the MAX_BOOKS_CHECKED_OUT
    if (booksCheckedOut.size() < MAX_BOOKS_CHECKED_OUT) {
      // checks that book is not already checked out by this subscriber
      if (isBookInBooksCheckedOut(book) == false) {
        if (book.isAvailable() == true) { // checks that book is available
          // if requirements are met, adds book to booksCheckedOut arraylist
          booksCheckedOut.add(book);
          // calls borrowBook method in order to record that the book is currently checked out by
          // the subscriber with the given CARD_BAR_CODE
          book.borrowBook(CARD_BAR_CODE);
        } else {
          // if book is not available, prints message to inform user
          System.out.println("Sorry, " + book.getTitle() + " is not available.");
        }
      } else {
        // if book is already checked out by this subscriber, prints message to inform user
        System.out.println("You have already checked out " + book.getTitle() + " book.");
      }
    } else {
      // if subscriber is attempting to check out more than MAX_BOOKS_CHECKED_OUT, prints error
      // message to inform user
      System.out.println(
          "Checkout Failed: You cannot check out more than " + MAX_BOOKS_CHECKED_OUT + " books.");
    }
  }

  /**
   * Returns a library book
   * 
   * @param book - reference to the book to return by this subscriber
   */
  public void returnBook(Book book) {
    // checks that book is checked out by this subscriber
    if (isBookInBooksCheckedOut(book) == true) {
      // calls returnBook method to return the book
      book.returnBook();
      // removes the book from the booksCheckedOut arraylist
      booksCheckedOut.remove(book);
      // adds the book to the booksReturned arraylist
      booksReturned.add(book);
    } else {
      // if the user attempts to return a book that they did not check out, prints error message
      System.out.println("Return failed: You cannot return a book you didn't check out");
    }
  }

  /**
   * Checks if this subscriber booksCheckedOut list contains a given book
   * 
   * @param book book to check if it is within this subscriber booksCheckedOut list
   * @return true if booksCheckedOut contains book, false otherwise
   */
  public boolean isBookInBooksCheckedOut(Book book) {
    return booksCheckedOut.contains(book);
  }

  /**
   * Checks if this subscriber booksCheckedOut list contains a given book
   * 
   * @param book - book to check if it is within this subscriber booksCheckedOut list
   * @return true if booksCheckedOut contains book, false otherwise
   */
  public boolean isBookInBooksReturned(Book book) {
    return booksReturned.contains(book);
  }

  /**
   * Displays the list of the books checked out and not yet returned
   */
  public void displayBooksCheckedOut() {
    if (booksCheckedOut.isEmpty()) // empty list
      System.out.println("No books checked out by this subscriber");
    else
      // Traverse the list of books checked out by this subscriber and display its content
      for (int i = 0; i < booksCheckedOut.size(); i++) {
        System.out.print("Book ID: " + booksCheckedOut.get(i).getID() + " ");
        System.out.print("Title: " + booksCheckedOut.get(i).getTitle() + " ");
        System.out.println("Author: " + booksCheckedOut.get(i).getAuthor());
      }
  }

  /**
   * Displays the history of the returned books by this subscriber
   */
  public void displayHistoryBooksReturned() {
    if (booksReturned.isEmpty()) // empty list
      System.out.println("No books returned by this subscriber");
    else
      // Traverse the list of borrowed books already returned by this subscriber and display its
      // content
      for (int i = 0; i < booksReturned.size(); i++) {
        System.out.print("Book ID: " + booksReturned.get(i).getID() + " ");
        System.out.print("Title: " + booksReturned.get(i).getTitle() + " ");
        System.out.println("Author: " + booksReturned.get(i).getAuthor());
      }
  }

  /**
   * Displays this subscriber's personal information
   */
  public void displayPersonalInfo() {
    System.out.println("Personal information of the subscriber: " + CARD_BAR_CODE);
    System.out.println("  Name: " + name);
    System.out.println("  Address: " + address);
    System.out.println("  Phone number: " + phoneNumber);
  }


}
