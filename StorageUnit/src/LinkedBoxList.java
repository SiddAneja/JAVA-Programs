/////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION ///////////////////////
// Title: LinkedBoxList.java
// Files: none
// Course: CS300 Spring 2019
//
// Author: Siddharth Aneja
// Email: saneja@wisc.edu
// Lecturer's Name: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: none
// Partner Email: NA
// Partner Lecturer's Name: NA
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
 * This method represents the List with LinkedBoxNodes that stores boxes.
 * 
 * @author Siddharth
 *
 */
public class LinkedBoxList {

  /**
   * Head of this LinkedBoxList (refers to the element stored at index 0 within this list).
   */
  private LinkedBoxNode head;

  /**
   * Number of boxes already stored in this list.
   */
  private int size;

  /**
   * Capacity of this LinkedBoxList maximum number of box elements that this LinkedBoxList can
   * store.
   */
  private int capacity;

  /**
   * A constructor that creates an empty LinkedBoxList with a given initial capacity.
   * 
   * @param capacity - initial capacity of the list
   */
  public LinkedBoxList(int capacity) {
    this.capacity = capacity;
  }

  /**
   * This method returns the size of this list.
   * 
   * @return size of the list
   */
  public int size() {
    int size = 0;
    if (!isEmpty()) { // if the list is not empty, traverse it and count the number of elements
      LinkedBoxNode runner = head; // initialize the runner to the head of the list
      size++;
      while (runner.getNext() != null) { // traverse the list
        size++;
        runner = runner.getNext();
      }
    }
    return size;
  }

  /**
   * This method returns the capacity of this list.
   * 
   * @return capacity of the list
   */
  public int getCapacity() {
    return this.capacity;
  }

  /**
   * This method expands the capacity of this LinkedBoxList with the specified number a of
   * additional elements.
   * 
   * @param a - additional capacity of the list
   */
  public void expandCapacity(int a) {
    this.capacity += a;
  }

  /**
   * This method checks whether this LinkedBoxList is empty returns true if this LinkedBoxList is
   * empty, false otherwise.
   * 
   * @return true if the list is empty, false otherwise
   */
  public boolean isEmpty() {
    // Checks if there are no nodes in the list
    if (this.size == 0 && this.head == null) {
      return true;
    }
    return false;
  }

  /**
   * This method checks whether this LinkedBoxList is full returns true if this list is full, false
   * otherwise.
   * 
   * @return true if the list is full, false otherwise
   */
  public boolean isFull() {
    // Checks if the size of the boxes in the list has reached the capacity
    if (this.size == this.capacity) {
      return true;
    }
    return false;
  }

  /**
   * This method adds a new box into this sorted list in descending order of weight. Throws
   * IllegalArgumentException if newBox is null. Throws IllegalStateException if this list is full.
   * 
   * @param newBox - a new Box that is to be added to the list
   * @throws IllegalArgumentException
   * @throws IllegalStateException
   */
  public void add(Box newBox) throws IllegalArgumentException, IllegalStateException {
    // Checks if newBox is a null reference
    if (newBox == null) {
      throw new IllegalArgumentException("ERROR: Null Box object!");
    }
    // Checks if the list is already full
    if (this.isFull()) {
      throw new IllegalStateException("ERROR: List is full!");
    }
    // If the list is empty the newBox is added to the head of the list
    if (this.isEmpty()) {
      this.head = new LinkedBoxNode(newBox);
      size++; // increment the size
    }
    // If the list is not empty, the method enters this branch
    else {
      Integer index = null; // index of the box that will succeed newBox( i.e it weighs less)
      // iterates through all the nodes to find the box before which newBox will be placed
      for (int i = 0; i < this.size(); i++) {
        if (newBox.compareTo(this.get(i)) >= 0) {
          index = i;
          break;
        }
      }
      // if the box is at the start this branch will execute
      if (index != null && index == 0) {
        LinkedBoxNode temp = this.head; // creates a temporary reference to the current head
        this.head = new LinkedBoxNode(newBox, temp); // changes the head to the newBox
        size++;
      }
      // if the box is somewhere within the list, this will execute
      else if (index != null && index < this.size()) {
        LinkedBoxNode tempBefore = null; // reference to the node which will precede the newNode
        LinkedBoxNode tempAfter = null; // reference to the node which will succeed the newNode
        LinkedBoxNode runner = this.head; // temporary variable to acquire the nodes
        LinkedBoxNode newNode = new LinkedBoxNode(newBox); // new BoxNode which is to be added
        // iterates through the list to get the reference for the above variables
        while (runner.getBox() != null) {
          if (runner.getBox().equals(this.get(index - 1))) {
            tempBefore = runner;
          } else if (runner.getBox().equals(this.get(index))) {
            tempAfter = runner;
          }
          // To get the next reference if it is not null, else breaks out of the loop
          if ((runner = runner.getNext()) == null) {
            break;
          }
        }
        // adds the newBox to the list
        tempBefore.setNext(newNode);
        newNode.setNext(tempAfter);
        size++; // increment size
      }
      // if the nexBox is the lightest, it is added to the end of the list
      else {
        LinkedBoxNode runner = this.head; // temporary variable to acquire the nodes
        while (runner.getBox() != null) {
          // gets the last reference of the list
          if (runner.getBox().equals(this.get(this.size() - 1))) {
            break;
          }
          // To get the next reference if it is not null, else breaks out of the loop
          if ((runner = runner.getNext()) == null) {
            break;
          }
        }
        runner.setNext(new LinkedBoxNode(newBox));// adds the newBox to the end
        size++; // increment size
      }
    }
  }


  // Checks if this list contains a box that matches with (equals) a specific box object
  // Returns true if this list contains findBox, false otherwise
  public boolean contains(Box findBox) {
    for (int i = 0; i < this.size(); i++) {
      if (findBox.equals(this.get(i))) {
        return true;
      }
    }
    return false;
  }

  /**
   * This method returns a box stored in this list given its index. 
   * Throws IndexOutOfBoundsException if index is out of the range 0..size-1.
   * 
   * @param index of the Node to get the box from in the list
   * @return Box at the index
   * @throws IndexOutOfBoundsException
   */
  public Box get(int index) throws IndexOutOfBoundsException {
    // Checks if the index is within the correct range
    if (index < 0 && index > this.size - 1) {
      throw new IndexOutOfBoundsException("ERROR: The index is out of bounds!");
    }
    // If the index is 0, return the box at head
    if (index == 0) {
      return head.getBox();
    }
    // if the index is not the head, enters this branch
    else {
      LinkedBoxNode temp = this.head.getNext(); // temporary variable to acquire the nodes
      int i = 1; // sets a loop variable i equal to 0
      // Loops through all indexes to get the box at index
      while (i < index) {
        temp = temp.getNext();
        ++i;
      }
      return temp.getBox();
    }
  }

  /**
   * This method removes a returns the box stored at index from this LinkedBoxList. Throws
   * IndexOutOfBoundsException if index is out of bounds. Index should be in the range of [0..
   * size()-1].
   * 
   * @param index of the box to remove from the list
   * @return the reference to the removed Box
   * @throws IndexOutOfBoundsException
   */
  public Box remove(int index) throws IndexOutOfBoundsException {
    Box removedBox = null; // Creates a null reference to the Box to be returned
    // If the index is 0, remove the head of the list
    if (index == 0) {
      removedBox = this.head.getBox(); // get the reference of the box to be removed
      this.head = this.head.getNext(); // sets the head to the node at index 1
      size--;// decrement size
    }
    // if the index is within the list but not the end of the list, enter this branch
    else if (index < this.size() - 1) {
      LinkedBoxNode tempBefore = null; // reference to the node which is before the node to delete
      LinkedBoxNode tempAfter = null; // reference to the node which is after the node to delete
      LinkedBoxNode runner = this.head; // temporary variable to acquire the nodes
      // iterates through the list to get the reference for the above variables
      while (runner.getBox() != null) {
        if (runner.getBox().equals(this.get(index - 1))) {
          tempBefore = runner;
        } else if (runner.getBox().equals(this.get(index + 1))) {
          tempAfter = runner;
        }
        // To get the next reference if it is not null, else breaks out of the loop
        if ((runner = runner.getNext()) == null) {
          break;
        }
      }
      removedBox = this.get(index); // get the reference of the box to be removed
      // Sets the next field of the previous node to the node after the node at index,
      // thereby removing the Box at index
      tempBefore.setNext(tempAfter);
      size--;// decrement size
    }
    // if the index is the end of the list, enter this branch
    else {
      removedBox = this.get(this.size() - 1); // get the reference of the box to be removed
      LinkedBoxNode runner = this.head; // temporary variable to acquire the nodes
      while (runner.getBox() != null) {
        // gets the last reference of the list
        if (runner.getBox().equals(this.get(this.size() - 2))) {
          break;
        }
        // To get the next reference if it is not null, else breaks out of the loop
        if ((runner = runner.getNext()) == null) {
          break;
        }
      }
      runner.setNext(null); // sets the next reference of the node at index -1 to null
      size--; // decrement size
    }
    return removedBox;
  }

  /**
   * This method removes all the boxes from this list.
   */
  public void clear() {
    // Loops until the list is empty and keeps removing the Node at index 0
    while (!isEmpty()) {
      this.remove(0);
    }
    size = 0; // sets size to 0
  }

  /**
   * Returns a String representation for this LinkedBoxList
   */
  @Override
  public String toString() {
    StringBuilder result = new StringBuilder(); // creates a StringBuilder object
    String newLine = System.getProperty("line.separator");
    result.append("------------------------------------------------" + newLine);
    if (!isEmpty()) {
      LinkedBoxNode runner = head;
      int index = 0;
      // traverse the list and add a String representation for each box
      while (runner != null) {
        result.insert(0,
            "Box at index " + index + ": " + runner.getBox().getWeight() + " lbs" + newLine);
        runner = runner.getNext();
        index++;
      }
      result.insert(0, "Box List Content:" + newLine);
    }
    result.insert(0, "List size: " + size + " box(es)." + newLine);
    result.insert(0, "Box List is empty: " + isEmpty() + newLine);
    result.insert(0, "------------------------------------------------" + newLine);
    return result.toString();
  }
}

