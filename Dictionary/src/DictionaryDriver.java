/////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: DictionaryDriver.java
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
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Class representing the implementation of the Dictionary Binary Search Tree
 * 
 * @author Alyssa and Siddharth
 *
 */
public class DictionaryDriver {

  /**
   * Private helper method used to print the menu of the dictionary program
   */
  private static void printMenu() {
    String userCommands = "\n=========================== Dictionary ============================\n"
        + "Enter one of the following options:\n"
        + "[A <word> <meaning>] to add a new word and its definition in the dictionary\n"
        + "[L <word>] to search a word in the dictionary and display its definition\n"
        + "[G] to print all the words in the dictionary in sorted order\n"
        + "[S] to get the count of all words in the dictionary\n"
        + "[H] to get the height of this dictionary implemented as a binary search tree\n"
        + "[Q] to quit the program\n"
        + "======================================================================\n";
    System.out.println(userCommands);
  }

  /**
   * Private helper method used to check the user command to ensure that the correct number of
   * arguments is used
   * 
   * @param line - the user command
   * @return true if arguments are correct, false otherwise
   */
  private static boolean checkCommand(String line) {
    // splits the string entered into separate strings between each whitespace and adds each string
    // to a string array
    String[] words = line.split(" ");
    if (words.length > 1) { // checks if the length of the array is greater than 1
      // if so, prints warning message due to too many arguments entered
      System.out.print("WARNING: Syntax Error");
      return false; // then returns false
    } else {
      return true;
    }

  }

  /**
   * Main method used to implement the user interface for the dictionary program
   * 
   * @param args
   */
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in); // creates a new scanner object
    DictionaryBST dictionary = new DictionaryBST(); // creates a new DictionaryBST object
    while (true) { // loop that iterates until broken out of
      String word; // initializes word string
      String meaning; // initializes meaning string
      String keyInput; // initializes keyInput string
      printMenu(); // calls the printMenu() method to print the menu
      System.out.print("Please enter your command: "); // prompts the user to enter a command
      keyInput = sc.next(); // sets keyInput equal to the user's next input
      // initializes char key as the character at index 0 of keyInput
      char key = keyInput.charAt(0);
      switch (key) { // creates a new switch using the key as input
        case 'a':
        case 'A':
          // if the user enters A or a, enters try block
          try {
            word = sc.next(); // sets word string equal to the user's next input
            // sets the meaning string equal to the remainder of the user's input
            meaning = sc.nextLine().trim();
            // calls the addWord method using the word and meaning
            dictionary.addWord(word, meaning);
            // catches an IllegalArgumentException if thrown and prints message
          } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
          }
          break;
        case 'l':
        case 'L':
          // if user enters L or l keyword, enters try block for implementing lookup
          try {
            word = sc.nextLine().trim(); // sets word equal to the scanner's next input
            // calls checkCommand with word to make sure correct number of arguments are used
            if (checkCommand(word) == false) {
              break; // if it returns false, breaks
            } else {
              // otherwise, prints the word and the meaning
              System.out.println(word + ": " + dictionary.lookup(word));
            }
            // catches NoSuchElementException if thrown and prints message
          } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
          }
          break; // breaks out of loop
        case 'g':
        case 'G':
          // if user enters g or G, checks that no more arguments are entered
          if (!sc.nextLine().equals("")) {
            System.out.print("WARNING: Syntax error "); // if they are, prints warning message
          } else {
            // checks that the getAllWords method doesn't return null
            if (dictionary.getAllWords() != null) {
              // if so, sets Arraylist allWords equal to arrayList returned by getAllWords
              ArrayList<String> allWords = dictionary.getAllWords();
              // iterates through allWords array and prints each word separated by a comma
              for (int i = 0; i < allWords.size(); i++) {
                if (i == allWords.size() - 1) {
                  System.out.println(allWords.get(i));
                } else {
                  System.out.print(allWords.get(i) + ", ");
                }
              }
            } else {
              // otherwise, prints that dictionary is empty
              System.out.println("Dictionary is empty.");
            }
          }
          break; // breaks out of loop
        case 's':
        case 'S':
          // if user enters s or S, checks that no more arguments were entered
          if (!sc.nextLine().equals("")) {
            // if they were, prints syntax error
            System.out.print("WARNING: Syntax error ");
          } else {
            // otherwise, calls size() method to print size
            System.out.println(dictionary.size());
          }
          break; // breaks out of loop
        case 'h':
        case 'H':
          // if user enters h or H, checks that no more arguments are entered
          if (!sc.nextLine().equals("")) {
            // if they are, prints syntax error
            System.out.print("WARNING: Syntax error ");
          } else {
            // otherwise, calls the height method to print the height of the dictionary BST
            System.out.println(dictionary.height());
          }
          break; // breaks out of loop
        case 'q':
        case 'Q':
          // if user enters q or Q, checks that no more arguments are entered
          if (!sc.nextLine().equals("")) {
            // if more are entered, prints syntax error
            System.out.print("WARNING: Syntax error ");
            continue;
          } else {
            break;
          }
        default:
          // if none of above keys are entered, prints warning message and breaks out of loop
          System.out.println("WARNING: Unregcognized command.");
          break;
      }
      // checks if key is equal to q or Q, and if so, breaks
      if (key == 'q' || key == 'Q') {
        break;
      }
    }
    // prints end message
    System.out.print("============================== END ===================================");
    sc.close();
  }

}
