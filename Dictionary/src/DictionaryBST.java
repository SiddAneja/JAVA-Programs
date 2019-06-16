/////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: DictionaryBST.java
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

/**
 * This class implements the Dictionary interface and provides the methods to implement and create
 * the binary search tree.
 * 
 * @author Siddharth and Alyssa
 *
 */
public class DictionaryBST implements Dictionary {

  /**
   * This private DictionaryWord field stores the root values of the BST.
   */
  private DictionaryWord root;

  /**
   * The constructor method of the BST which creates an empty dictionaryBST.
   */
  public DictionaryBST() {
    this.root = null;
  }

  /**
   * This method checks whether or not the dictionary is empty. It is an overridden method of the
   * dictionary interface.
   * 
   * @return true if the dictionary is empty, false otherwise.
   */
  public boolean isEmpty() {
    if (root == null) {
      return true;
    }
    return false;
  }

  /**
   * This method adds this word definition (word and the provided meaning) to the dictionary. It it
   * an overridden method of the dictionary interface.
   * 
   * @param word    - word to be added to dictionary.
   * @param meaning - definition of the word.
   * @return true if the word was successfully added to this dictionary, false if the word was
   *         already in the dictionary.
   * @throws IllegalArgumentException if either word or meaning is null or an empty string.
   */
  public boolean addWord(String word, String meaning) {
    // Checks if word is null or an empty string
    if (word == null || word.equals("")) {
      throw new IllegalArgumentException("Error: Word cannot be null or empty!");
    }
    // checks if meaning is null or an empty string
    if (meaning == null || meaning.equals("")) {
      throw new IllegalArgumentException("Error: Meaning cannot be null or empty!");
    }
    // Creates a new DictionaryWord using the word and meaning parameters
    DictionaryWord newNode = new DictionaryWord(word, meaning);
    // if the tree is empty, the new node is added to the root
    if (root == null) {
      this.root = newNode;
      return true;
    }
    // otherwise the recursive helper method is called
    return addWordHelper(newNode, root);
  }

  /**
   * This method returns the meaning of the word s if it is present in this dictionary. It is an
   * overridden method of the dictionary interface.
   * 
   * @param s - word to find in the dictionary.
   * @return meaning of the word s if it is present in this dictionary.
   */
  public String lookup(String s) {
    // calls the lookup helper method with s and root
    return lookupHelper(s, root);
  }

  /**
   * This method returns the number of words in this dictionary.
   * 
   * @return number of words in the dictionary.
   */
  public int size() {
    // if the dictionary is not empty, calls sizeHelper and returns 1 + the value returned
    // by the helper
    if (root != null) {
      return 1 + sizeHelper(root);
    }
    // if dictionary is null, return 0
    return 0;
  }

  /**
   * Computes and returns the height of this dictionaryBST, as the number of nodes from root to the
   * deepest leaf DictionaryWord node.
   * 
   * @return the height of this Binary Search Tree counting the number of DictionaryWord nodes
   */
  public int height() {
    // if the dictionary is empty, return 0.
    if (root == null) {
      return 0;
    }
    // else call height helper method on the root
    return heightHelper(root);
  }

  /**
   * Returns all the words within this dictionary sorted from A to Z
   * 
   * @return an ArrayList that contains all the words within this dictionary sorted in the ascendant
   *         order
   */
  public ArrayList<String> getAllWords() {
    // if the dictionary is empty, return an empty arraylist
    if (root == null) {
      return new ArrayList<String>();
    }
    // else create an arraylist and initialize it using the getAllWordsHelper method
    ArrayList<String> allWords = getAllWordsHelper(root);
    return allWords;

  }

  /**
   * Recursive helper method to add newWord in the subtree rooted at node
   * 
   * @param newWordNode a new DictionaryWord to be added to this dictionaryBST
   * @param current     the current DictionaryWord that is the root of the subtree where newWord
   *                    will be inserted
   * @return true if the newWordNode is successfully added to this dictionary, false otherwise
   */
  private static boolean addWordHelper(DictionaryWord newWordNode, DictionaryWord current) {
    // Checks if the word to be added is lexicographically smaller than current
    if (newWordNode.getWord().compareToIgnoreCase(current.getWord()) < 0) {
      // checks if the leftChild is null, if true sets the newNode equal to the leftChild of current
      if (current.getLeftChild() == null) {
        current.setLeftChild(newWordNode);
        return true;
      }
      // if leftChild is not null, calls addWordHelper recursively on the leftChild
      else {
        return addWordHelper(newWordNode, current.getLeftChild());
      }
    }
    // Checks if the word to be added is lexicographically larger than current
    else if (newWordNode.getWord().compareToIgnoreCase(current.getWord()) > 0) {
      // Checks if the rightChild is null, if true sets the newNode equal to the
      // rightChild of current
      if (current.getRightChild() == null) {
        current.setRightChild(newWordNode);
        return true;
      }
      // if rightChild is not null, calls addWordHelper recursively on the rightChild
      else {
        return addWordHelper(newWordNode, current.getRightChild());
      }
    }
    // if the words are the same, returns false
    return false;

  }


  /**
   * Recursive helper method to lookup a word s in the subtree rooted at current
   * 
   * @param s       String that represents a word
   * @param current pointer to the current DictionaryWord within this dictionary
   * @return the meaning of the word s if it is present in this dictionary
   * @throws NoSuchElementException if s is not found in this dictionary
   */
  private static String lookupHelper(String s, DictionaryWord current) {
    // Checks if the word to be added is lexicographically smaller than current
    if (current.getWord().compareToIgnoreCase(s) > 0) {
      // if the leftChild of current exists, call lookupHelper on s and the leftChild
      if (current.getLeftChild() != null) {
        return lookupHelper(s, current.getLeftChild());
      }
      // if the leftChild is null, throw a NoSuchElementException
      else {
        throw new NoSuchElementException("Error: " + s + " does not exist!");
      }
    }
    // Checks if the word to be added is lexicographically larger than current
    else if (current.getWord().compareToIgnoreCase(s) < 0) {
      // if rightChild does exist, call lookupHelper on s and the right child
      if (current.getRightChild() != null) {
        return lookupHelper(s, current.getRightChild());
      }
      // if the rightChild is null, throw a NoSuchElementException
      else {
        throw new NoSuchElementException("Error: " + s + " does not exist!");
      }
    }
    // if s is the same as current.getWord() return the meaning in current
    return current.getMeaning();
  }


  /**
   * Recursive helper method that returns the number of dictionary words stored in the subtree
   * rooted at current
   * 
   * @param current current DictionaryWord within this dictionaryBST
   * @return the size of the subtree rooted at current
   */
  private static int sizeHelper(DictionaryWord current) {
    // Initializes an int variable size to 0
    int size = 0;
    // if the leftChild is not null, calls sizeHelper on it recursively and increments size till
    // it is null and increments size by 0.
    if (current.getLeftChild() != null) {
      size += 1 + sizeHelper(current.getLeftChild());
    } else {
      size += 0;
    }
    // similary for the right child
    if (current.getRightChild() != null) {
      size += 1 + sizeHelper(current.getRightChild());
    } else {
      size += 0;
    }
    // returns size
    return size;
  }


  /**
   * Recursive helper method that computes the height of the subtree rooted at current
   * 
   * @param current pointer to the current DictionaryWord within this DictionaryBST
   * @return height of the subtree rooted at current counting the number of DictionaryWord nodes
   *         from the current node to the deepest leaf in the subtree rooted at current
   */
  private static int heightHelper(DictionaryWord current) {
    // checks if the current node is null, if true returns 0.
    if (current == null) {
      return 0;
    }
    // creates two variables which take the height of the left and right side respectively
    // by calling the heightHelper method recursively on one of the side.
    int lDepth = heightHelper(current.getLeftChild());
    int rDepth = heightHelper(current.getRightChild());
    // checks which side is deeper and returns it
    if (lDepth > rDepth) {
      return lDepth + 1;
    } else {
      return rDepth + 1;
    }
  }



  /**
   * Recursive Helper method that returns a list of all the words stored in the subtree rooted at
   * current sorted alphabetically from A to Z
   * 
   * @param current pointer to the current DictionaryWord within this dictionaryBST
   * @return an ArrayList of all the words stored in the subtree rooted at current
   */
  private static ArrayList<String> getAllWordsHelper(DictionaryWord current) {
    // creates an arraylist that will store the words
    ArrayList<String> words = new ArrayList<>();
    // if the left child of current is not null, it calls getAllWordsHelper method on it with the
    // leftChild as parameter and adds the values returned from it to word
    if (current.getLeftChild() != null) {
      words.addAll(getAllWordsHelper(current.getLeftChild()));
    }
    // if the left child is null add the current word to the arraylist
    words.add(current.getWord());
    // checks whether the rightChild is not null, if true, it calls the helper method again
    // on the rightChild
    if (current.getRightChild() != null) {
      words.addAll(getAllWordsHelper(current.getRightChild()));
    }
    // returns the arraylist with all the words
    return words;
  }
}
