/////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Dictionary.java
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
 * Interface representing a dictionary implementation
 * 
 * @author Alyssa and Siddharth
 *
 */
public interface Dictionary {

  /**
   * Checks whether the dictionary is empty or not
   * 
   * @return true if empty, false otherwise
   */
  public boolean isEmpty();

  /**
   * Adds this word definition (word and the provided meaning) to the dictionary Throws
   * IllegalArgumentException if either word or meaning is null or an empty String
   * 
   * @param word    - the word to be added
   * @param meaning - the meaning of the word
   * @return true if the word is successfully added, false if a duplicate word is already in the
   *         dictionary
   */
  public boolean addWord(String word, String meaning);

  /**
   * Returns the meaning of the word s if it is present in this dictionary Throws a
   * NoSuchElementException if the word s was not found in this dictionary
   * 
   * @param s - the word to be looked up
   * @return the meaning of the word looked up
   */
  public String lookup(String s);

  /**
   * Returns the number of words stored in this dictionary
   * 
   * @return the size of the dictionary
   */
  public int size();

}

