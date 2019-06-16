/////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Book.java
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
 * A class to model a simple book.
 * 
 * @author Siddharth and Alyssa
 *
 */
public class Book {
  /**
   * class variable that represents the identifier of the next book
   */
  private static int nextId = 1;

  /**
   * unique identifier of this book
   */
  private final int ID;

  /**
   * name of the author of this book
   */
  private String author;

  /**
   * title of this book
   */
  private String title;

  /**
   * card bar code of the borrower of this book when borrowerCardBarCode == null, the book is
   * available (no one has the book)
   */
  private Integer borrowerCardBarCode;

  /**
   * Construct a new Book object and initialize its instance fields
   * 
   * @param title  - title of this new book
   * @param author - author of this new book
   */
  public Book(String title, String author) {
    this.title = title; // sets the private field title to the parameter title
    this.author = author; // sets the private field author to the parameter author
    ID = nextId; // sets the ID of the book to nextID before incrementing nextID
    ++nextId;
  }

  /**
   * Return the author of this book
   * 
   * @return the author
   */
  public String getAuthor() {
    return author;
  }

  /**
   * Return the title of this book
   * 
   * @return the title
   */
  public String getTitle() {
    return title;
  }

  /**
   * Return the the borrower's card bar code of this book if the book has been checked out or null
   * if not
   * 
   * @return the borrowerCardBarCode
   */
  public Integer getBorrowerCardBarCode() {
    // checks to see if borrowerCardBarCode is available (not null) and returns it
    if (borrowerCardBarCode != null) {
      return borrowerCardBarCode;
    } else {
      return null;
    }
  }

  /**
   * Returns the ID of this Book object
   * 
   * @return the ID of this book
   */
  public int getID() {
    return ID;
  }

  /**
   * Records the borrower's card bar code of this book if the book is available. If this book is not
   * available, this method does nothing.
   * 
   * @param borrowerCardBarCode - the borrowerCardBarCode to set
   */
  public void borrowBook(Integer borrowerCardBarCode) {
    // checks to see if the book is available, i.e borrowerCardBarCode is null
    if (this.borrowerCardBarCode == null) {
      // sets the private field borrowerCardBarCode to the parameter
      this.borrowerCardBarCode = borrowerCardBarCode;
    }
  }

  /**
   * Sets this book to be available. When the borrowerCardBarCode is set to null, no one is
   * borrowing it
   */
  public void returnBook() {
    borrowerCardBarCode = null;
  }

  /**
   * Checks if this book is available
   * 
   * @return true if no one is borrowing this book, false otherwise
   */
  public boolean isAvailable() {
    if (borrowerCardBarCode == null) {
      return true;
    } else {
      return false;
    }
  }
}
