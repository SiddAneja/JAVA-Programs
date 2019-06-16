/////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Library.java
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
import java.util.Scanner;

/**
 * This class models a simple book library. The main method of this class implements the management
 * system for this library.
 * 
 * @author Siddharth and Alyssa
 *
 */
public class Library {

  /**
   * Street address of this library
   */
  private String address;

  /**
   * this library's librarian
   */
  private Librarian librarian;

  /**
   * list of the books in this library
   */
  private ArrayList<Book> books;

  /**
   * list of this library's subscribers
   */
  private ArrayList<Subscriber> subscribers;

  /**
   * Displays the main menu for this book library application
   */
  private static void displayMainMenu() {
    System.out.println("\n--------------------------------------------------------");
    System.out.println("     Welcome to our Book Library Management System");
    System.out.println("--------------------------------------------------------");
    System.out.println("Enter one of the following options:");
    System.out.println("[1 <password>] Login as a librarian");
    System.out.println("[2 <card bar code> <4-digits pin>] Login as a Subscriber");
    System.out.println("[3] Exit"); // Exit the application
    System.out.println("--------------------------------------------------------");
  }

  /**
   * Displays the menu for a Subscriber
   */
  private static void displaySubscriberMenu() {
    System.out.println("\n--------------------------------------------------------");
    System.out.println("    Welcome to Subscriber's Space");
    System.out.println("--------------------------------------------------------");
    System.out.println("Enter one of the following options:");
    System.out.println("[1 <book ID>] Check out a book");
    System.out.println("[2 <book ID>] Return a book");
    System.out.println("[3 <title>] Search a Book by title");
    System.out.println("[4 <author>] Search a Book by author");
    System.out.println("[5] Print list of books checked out");
    System.out.println("[6] Print history of returned books");
    System.out.println("[7 <address>] Update address");
    System.out.println("[8 <phone number>] Update phone number");
    System.out.println("[9] Logout"); // Exit to Main menu
    System.out.println("--------------------------------------------------------");
  }

  /**
   * Displays the menu for the Librarian
   */
  private static void displayLibrarianMenu() {
    System.out.println("\n--------------------------------------------------------");
    System.out.println("    Welcome to Librarian's Space");
    System.out.println("--------------------------------------------------------");
    System.out.println("Enter one of the following options:");
    System.out.println("[1 <title> <author>] Add new Book");
    System.out.println("[2 <name> <pin> <address> <phone number>] Add new subscriber");
    System.out.println("[3 <card bar code> <book ID>] Check out a Book");
    System.out.println("[4 <card bar code> <book ID>] Return a Book for a subscriber");
    System.out.println("[5 <card bar code>] Display Personal Info of a Subscriber");
    System.out.println("[6 <card bar code>] Display Books Checked out by a Subscriber");
    System.out.println("[7] Display All Books");
    System.out.println("[8 <book ID>] Remove a Book");
    System.out.println("[9] Logout"); // Exit to main menu
    System.out.println("--------------------------------------------------------");
  }

  /**
   * Display the Application GoodBye and logout message.
   */
  private static void displayGoodByeLogoutMessage() {
    System.out.println("\n--------------------------------------------------------");
    System.out.println("       Thanks for Using our Book Library App!!!!");
    System.out.println("--------------------------------------------------------");
  }

  /**
   * Creates a new Library and initializes all its instance fields. Initially both books and
   * subscribers lists are empty.
   * 
   * @param address           - Address of this Library
   * @param librarianUsername - username of the librarian of this book library
   * @param librarianLogin    - password of the librarian of this book library
   */
  public Library(String address, String librarianUsername, String librarianLogin) {
    this.address = address; // Sets the private field address to the parameter address
    books = new ArrayList<Book>(); // Initializes books to an empty arraylist
    subscribers = new ArrayList<Subscriber>(); // Initializes subscribers to an empty arraylist
    // creates a new librarian object based on parameters
    librarian = new Librarian(librarianUsername, librarianLogin);
  }

  /**
   * Returns the librarian of this library
   * 
   * @return the Librarian
   */
  public Librarian getLibrarian() {
    return librarian;
  }

  /**
   * Returns the address of this library
   * 
   * @return the address
   */
  public String getAddress() {
    return address;
  }

  /**
   * Returns a Book given a book identifier if found, and null otherwise. If the book is not found,
   * this method displays the following message: "Error: this book identifier didn't match any of
   * our books identifiers."
   * 
   * @param bookId - identifier of the book to find
   * @return reference to the Book if found and null otherwise
   */
  public Book findBook(int bookId) {
    // Iterates through books arraylist to find the book with the given bookID
    for (int i = 0; i < books.size(); i++) {
      if (books.get(i).getID() == bookId) {
        return books.get(i);
      }
    }
    System.out.print("Error: this book identifier didn't match any of our books identifiers.\n");
    return null;
  }

  /**
   * Returns the list of books having a given title in this library. The comparison used by this
   * method is case insensitive
   * 
   * @param title - title of the book(s) to find
   * @return ArrayList of the books having a given title in this library
   */
  public ArrayList<Book> findBookByTitle(String title) {
    // create a new arraylist which stores all the books that have the same title
    ArrayList<Book> returnArrayList = new ArrayList<Book>();
    // iterate through books and find books with the same title as the provided one,
    // then add the book instance to the returnArrayList
    for (int i = 0; i < books.size(); i++) {
      if (books.get(i).getTitle().equalsIgnoreCase(title)) {
        returnArrayList.add(books.get(i));
      }
    }
    return returnArrayList;
  }

  /**
   * Returns the list of books having a given author. The comparison used by this method is case
   * insensitive
   * 
   * @param author - author of the book(s) to find
   * @return ArrayList of the books having a given author (0 or more books can be found)
   */
  public ArrayList<Book> findBookByAuthor(String author) {
    // create a new arraylist which stores all the books that have the same author
    ArrayList<Book> returnArrayList = new ArrayList<Book>();
    // iterate through books and find books with the same author as the provided one,
    // then add the book instance to the returnArrayList
    for (int i = 0; i < books.size(); i++) {
      if (books.get(i).getAuthor().equalsIgnoreCase(author)) {
        returnArrayList.add(books.get(i));
      }
    }
    return returnArrayList;
  }

  /**
   * Adds a new book to the library (to the books list). This method displays the following message:
   * "Book with Title " + title + " is successfully added to the library."
   * 
   * @param title  - title of the new book
   * @param author - author of the new book
   */
  public void addBook(String title, String author) {
    // creates a new book instance with the parameters provided and adds it to the books array
    books.add(new Book(title, author));
    System.out.print("Book with Title " + title + " is successfully added to the library.\n");
  }

  /**
   * Removes a book given its identifier from the library (from books list)
   * 
   * @param bookId - identifier of the book to remove
   * @return a reference to the removed book, and null if the book is not found in this library or
   *         if it is not available
   */
  public Book removeBook(int bookId) {
    // iterates through the books arraylist and checks to see if it contains a book with
    // the provided bookID and if it is available. Then it removes the book instance from
    // the books arraylist and returns the removed reference
    for (int i = 0; i < books.size(); i++) {
      if (books.get(i).getID() == bookId && books.get(i).isAvailable() == true) {
        Book bookReference = books.get(i);
        books.remove(i);
        return bookReference;
      }
    }
    return null;
  }

  /**
   * Adds a new subscriber to this library (to subscribers list). This method displays the following
   * message: "Library card with bar code " + card bar code + " is successfully issued to the new
   * subscriber " + name + "."
   * 
   * @param name        - name of the new subscriber
   * @param pin         - 4-digit personal identifier number of the new subscriber
   * @param address     - address of the new subscriber
   * @param phoneNumber - phone number of the new subscriber
   */
  public void addSubscriber(String name, int pin, String address, String phoneNumber) {
    // creates a new Subscriber with the parameters provided and adds it to the
    // subscribers arraylist
    subscribers.add(new Subscriber(name, pin, address, phoneNumber));
    System.out.println(
        "Library card with bar code " + subscribers.get(subscribers.size() - 1).getCARD_BAR_CODE()
            + " is " + "successfully issued to the new subscriber " + name + ".");
  }

  /**
   * Finds a subscriber given its cardBarCode. This method displayed the following message: "Error:
   * this card bar code didn't match any of our records." and returns null if the provided
   * cardBarCode did not match with any of the subscribers' card bar codes
   * 
   * @param cardBarCode - of the subscriber to find
   * @return a reference to the subscriber if found, otherwise null
   */
  public Subscriber findSubscriber(int cardBarCode) {
    // iterates through the subscribers arraylist and checks if it has a subscriber with
    // the given cardBarCode and returns it if found, else null.
    for (int i = 0; i < subscribers.size(); i++) {
      if (subscribers.get(i).getCARD_BAR_CODE() == cardBarCode) {
        return subscribers.get(i);
      }
    }
    System.out.print("Error: this card bar code didn't match any of our records.\n");
    return null;
  }

  /**
   * Displays a list of books
   * 
   * @param books - ArrayList of books
   */
  public static void displayBooks(ArrayList<Book> books) {
    if (books.isEmpty()) { // empty list
      System.out.println("No books available.");
    } else {
      // Traverse the list of books checked out by this subscriber and display its content
      for (int i = 0; i < books.size(); i++) {
        System.out.print("<Book ID>: " + books.get(i).getID() + " ");
        System.out.print("<Title>: " + books.get(i).getTitle() + " ");
        System.out.print("<Author>: " + books.get(i).getAuthor() + " ");
        System.out.println("<Is Available>: " + books.get(i).isAvailable());
      }
    }
  }

  /**
   * Reads and processes the user commands with respect to the menu of this application
   * 
   * @param scanner Scanner object used to read the user command lines
   */
  public void readProcessUserCommand(Scanner scanner) {
    final String promptCommandLine = "ENTER COMMAND: ";
    displayMainMenu(); // display the library management system main menu
    System.out.print(promptCommandLine);
    String command = scanner.nextLine(); // read user command line
    String[] commands = command.trim().split(" "); // split user command
    while (commands[0].trim().charAt(0) != '3') { // '3': Exit the application
      switch (commands[0].trim().charAt(0)) {
        case '1': // login as librarian commands[1]: password
          if (this.librarian.checkPassword(commands[1])) {
            // read and process librarian commands
            readProcessLibrarianCommand(scanner);
          } else { // error password
            System.out.println("Error: Password incorrect!");
          }
          break;
        case '2': // login as subscriber commands[1]: card bar code
          Subscriber subscriber = this.findSubscriber(Integer.parseInt(commands[1]));
          if (subscriber != null) {
            if (subscriber.getPin() == Integer.parseInt(commands[2])) // correct PIN
              // read and process subscriber commands
              readProcessSubscriberCommand(subscriber, scanner);
            else
              System.out.println("Error: Incorrect PIN.");
          }
          break;
      }
      // read and split next user command line
      displayMainMenu(); // display the library management system main menu
      System.out.print(promptCommandLine);
      command = scanner.nextLine(); // read user command line
      commands = command.trim().split(" "); // split user command line
    }
  }

  /**
   * Reads and processes the librarian commands according to the librarian menu
   * 
   * @param scanner Scanner object used to read the librarian command lines
   */
  private void readProcessLibrarianCommand(Scanner scanner) {
    final String promptLibrarianCommand = "ENTER COMMAND: ";
    displayLibrarianMenu(); // Display the Librarian menu of the System
    System.out.print(promptLibrarianCommand);
    String command = scanner.nextLine(); // read user command line
    String[] commands = command.trim().split(" "); // split user command
    Subscriber subscriber; // create a subscriber
    while (commands[0].trim().charAt(0) != '9') { // '9': Logout to main menu
      switch (commands[0].trim().charAt(0)) {
        case '1': // add a new book
                  // commands[1]: title, commands[2]: author
          addBook(commands[1], commands[2]);
          break;
        case '2': // Add new subscriber
          // commands[1]: name, commands[2]: pin, commands[3]: address, commands[4]: phone number
          addSubscriber(commands[1], Integer.parseInt(commands[2]), commands[3], commands[4]);
          break;
        case '3': // checkout a book
                  // commands[1]: card bar code, commands[2]: bookID
          // creates a book to store the reference of the book with the given bookID
          Book bookToCheckout = findBook(Integer.parseInt(commands[2]));
          // uses the cardBarCode to find the subscriber and stores the reference in 'subscriber'
          subscriber = findSubscriber(Integer.parseInt(commands[1]));
          subscriber.checkoutBook(bookToCheckout);
          break;
        case '4': // Return a book for a subscriber
                  // commands[1]: card bar code, commands[2]: bookID
          // creates a book to store the reference of the book with the given bookID
          Book bookToReturn = findBook(Integer.parseInt(commands[2]));
          // uses the cardBarCode to find the subscriber and stores the reference in 'subscriber'
          subscriber = findSubscriber(Integer.parseInt(commands[1]));
          subscriber.returnBook(bookToReturn);
          break;
        case '5': // Display personal info of a subscriber
          subscriber = findSubscriber(Integer.parseInt(commands[1]));
          subscriber.displayPersonalInfo();
          break;
        case '6': // Display books checked out by subscriber
                  // commands[1]: card bar code
          // uses the cardBarCode to find the subscriber and stores the reference in 'subscriber'
          subscriber = findSubscriber(Integer.parseInt(commands[1]));
          subscriber.displayBooksCheckedOut();
          break;
        case '7': // Display all books
          displayBooks(books);
          break;
        case '8': // remove a book
                  // commands[1]: bookID
          removeBook(Integer.parseInt(commands[1]));
          break;
      }
      // read and split next user command line
      displayLibrarianMenu(); // Display the Librarian menu of the System
      System.out.print(promptLibrarianCommand);
      command = scanner.nextLine(); // read user command line
      commands = command.trim().split(" "); // split user command line
    }
  }

  /**
   * Reads and processes the subscriber commands according to the subscriber menu
   * 
   * @param subscriber Current logged in subscriber
   * @param scanner    Scanner object used to read the librarian command lines
   */
  private void readProcessSubscriberCommand(Subscriber subscriber, Scanner scanner) {
    final String promptSubscriberCommand = "ENTER COMMAND: ";
    displaySubscriberMenu();// Display the Subscriber menu of the System
    System.out.print(promptSubscriberCommand);
    String command = scanner.nextLine(); // read user command line
    String[] commands = command.trim().split(" "); // split user command
    ArrayList<Book> bookSearched; // create an arraylist to store books
    while (commands[0].trim().charAt(0) != '9') { // '9': Logout to main menu
      switch (commands[0].trim().charAt(0)) {
        case '1': // checkout a book
                  // commands[1]: bookID
          // creates a book to store the reference of the book with the given bookID
          Book bookToCheckout = findBook(Integer.parseInt(commands[1]));
          subscriber.checkoutBook(bookToCheckout);
          break;
        case '2': // Return a book
                  // commands[2]: bookID
          // creates a book to store the reference of the book with the given bookID
          Book bookToReturn = findBook(Integer.parseInt(commands[1]));
          subscriber.checkoutBook(bookToReturn);
          break;
        case '3': // Search a book by its title
                  // commands[1]: title
          // creates a book to store the reference of the book with the given title
          bookSearched = findBookByTitle(commands[1]);
          displayBooks(bookSearched);
          break;
        case '4': // Search a book by its author
                  // commands[1]: author
          // creates a book to store the reference of the book with the given author
          bookSearched = findBookByAuthor(commands[1]);
          displayBooks(bookSearched);
          break;
        case '5': // Print list of books checked out
          subscriber.displayBooksCheckedOut();
          break;
        case '6': // Print history of returned books
          subscriber.displayHistoryBooksReturned();
          break;
        case '7': // update address
                  // commands[1]: new address
          subscriber.setAddress(commands[1]);
          break;
        case '8': // update phone number
                  // commands[1]: new phone number
          subscriber.setPhoneNumber(commands[1]);
          break;
      }
      // read and split next user command line
      displaySubscriberMenu(); // Display the Subscriber menu of the System
      System.out.print(promptSubscriberCommand);
      command = scanner.nextLine(); // read user command line
      commands = command.trim().split(" "); // split user command line
    }
  }

  /**
   * Main method that represents the driver for this application
   * 
   * @param args
   */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in); // create a scanner object to read user inputs
    // create a new library object
    Library madisonLibrary = new Library("Madison, WI", "april", "abc");
    // read and process user command lines
    madisonLibrary.readProcessUserCommand(scanner);
    displayGoodByeLogoutMessage(); // display good bye message
    scanner.close();// close this scanner
  }

}
