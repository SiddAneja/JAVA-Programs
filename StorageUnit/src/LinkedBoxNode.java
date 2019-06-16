/////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION ///////////////////////
// Title: LinkedBoxNode.java
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
 * This class represents the nodes in linkedList and stores the Box data and next variable.
 * 
 * @author Siddharth
 *
 */
public class LinkedBoxNode {

  /**
   * Box that represents the data for this Linked node.
   */
  private Box box;

  /**
   * Reference to the next Linked Box Node.
   */
  private LinkedBoxNode next;

  /**
   * A constructor that creates a new LinkedBoxNode object with a given box and without
   * referring to any next LinkedBoxNode.
   * 
   * @param box that will store the data in this Node
   */
  public LinkedBoxNode(Box box) {
    this.box = box;
  }

  /**
   * A constructor that creates a new LinkedBoxNode object and sets its instance fields box 
   * and next to the specified ones.
   * 
   * @param box  that will store the data in this Node
   * @param next - stores the next LinkedBoxNode
   */
  public LinkedBoxNode(Box box, LinkedBoxNode next) {
    this.box = box;
    this.next = next;
  }

  /**
   * Getter for the instance field next for this node.
   * 
   * @return next - stores the next LinkedBoxNode
   */
  public LinkedBoxNode getNext() {
    return this.next;
  }

  /**
   * Mutator for the instance field next for this node.
   * 
   * @param next - the new next LinkedBoxNode after this Node
   */
  public void setNext(LinkedBoxNode next) {
    this.next = next;
  }

  /**
   * Getter for the instance field Box for this node.
   * 
   * @return Box that is stored in this Node
   */
  public Box getBox() {
    return this.box;
  }

  /**
   * Mutator for the instance field Box for this node.
   * 
   * @param box - the new Box stored in this Node
   */
  public void setBox(Box box) {
    this.box = box;
  }
}
