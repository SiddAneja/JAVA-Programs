/////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: DictionaryWord.java
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
 * This class provides methods and code for implementing the nodes of the Binary Search Tree.
 * 
 * @author Siddharth and Alyssa
 */
public class DictionaryWord {

  /**
   * Private final String variable word that represents the search key for this dictionary word.
   */
  private final String word;

  /**
   * Private final String field that stores the meaning of the word that this dictionary node
   * defines.
   */
  private final String meaning;

  /**
   * Private DictionaryWord field that stores the leftChild of the the current DictionaryWord.
   */
  private DictionaryWord leftChild;

  /**
   * Private DictionaryWord field that stores the rightChild of the the current DictionaryWord.
   */
  private DictionaryWord rightChild;

  /**
   * The constructor method that creates a new dictionary word with the provided word and its
   * meaning pair. It throws an IllegalArgumentException when the word or meaning are either
   * references to an empty string or null references.
   * 
   * @param word    - the word to be added to the dictionary.
   * @param meaning - the definition of the word
   */
  public DictionaryWord(String word, String meaning) {
    // Checks if word is null or an empty string
    if (word == null || word.equals("")) {
      throw new IllegalArgumentException("Error: word cannot be null or empty!");
    }
    // checks if meaning is null or an empty string
    if (meaning == null || meaning.equals("")) {
      throw new IllegalArgumentException("Error: meaning cannot be null or  empty!");
    }
    // Sets word and meaning to the parameters provided
    this.word = word;
    this.meaning = meaning;
  }

  /**
   * Getter for the left child of this dictionary word.
   * 
   * @return the left child for this node.
   */
  public DictionaryWord getLeftChild() {
    return this.leftChild;
  }

  /**
   * Setter for the left child of this dictionary word.
   * 
   * @param leftChild - The left Child to be set for this node.
   */
  public void setLeftChild(DictionaryWord leftChild) {
    this.leftChild = leftChild;
  }

  /**
   * Getter for the right child of this dictionary word.
   * 
   * @return the right child for this node.
   */
  public DictionaryWord getRightChild() {
    return this.rightChild;
  }

  /**
   * Setter for the right child of this dictionary word.
   * 
   * @param rightChild - The right Child to be set for this node.
   */
  public void setRightChild(DictionaryWord rightChild) {
    this.rightChild = rightChild;
  }

  /**
   * Getter for the word of this dictionary word.
   * 
   * @return The word stored in this node.
   */
  public String getWord() {
    return this.word;
  }

  /**
   * Getter for the meaning of the word of this dictionary word.
   * 
   * @return the meaning of the word stored in this node.
   */
  public String getMeaning() {
    return this.meaning;
  }

  /**
   * Returns a String representation of this DictionaryWord. Overrides the toString() method already
   * provided.
   * 
   * @return String representation of this DictionaryWord
   */
  public String toString() {
    return (word + ": " + meaning);
  }
}
