/////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION ///////////////////////
// Title: Box.java
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

import java.util.Random;

/**
 * This class represents the Box object in the Storage unit application and uses the Comparable<T>
 * interface.
 * 
 * @author Siddharth
 *
 */
public class Box implements Comparable<Box> {
  /**
   * A random object to generate random numbers
   */
  private static Random randGen = new Random();

  /**
   * An int variable that stores the color of the Box reference.
   */
  private int color;

  /**
   * An int variable that stores the weight of the box in lbs between 1 inclusive and 31 exclusive.
   */
  private int weight;

  /**
   * A constructor that creates a new Box and initializes its instance fields color and weight to
   * random values.
   */
  public Box() {
    this.color = randGen.nextInt(); // Sets color to a random int
    this.weight = randGen.nextInt(30) + 1; // Sets weight to a random int between 1 and 30
  }

  /**
   * A constructor that creates a new Box and initializes its instance fields color and weight to
   * the specified values. Throws IllegalArgumentException if the provided weight value is out of
   * the range of [1..30]
   * 
   * @param color  of the box
   * @param weight of the box
   */
  public Box(int color, int weight) {
    // Checks if the weight parameter is within the correct range, then sets the values
    if (weight >= 1 && weight <= 30) {
      this.color = color;
      this.weight = weight;
    }
    // if the weight provided is out of the range, it throws an error
    else
      throw new IllegalArgumentException("ERROR: Incorrect argument for weight!");
  }

  /**
   * This method returns true if the specified other object is a Box and this box and other have
   * the same color and same weight. Otherwise, it returns false.
   * It overrides the predefined equals() method.
   * 
   * @param other - A reference to an Object that we have to compare to
   */
  @Override
  public boolean equals(Object other) {
    // Checks if the other Object is a Box
    if (other.getClass() == this.getClass()) {
      // Checks if the other Box has the same color and weight as this Box
      if (((Box) other).getColor() == this.color && ((Box) other).getWeight() == this.weight) {
        return true;
      }
    }
    return false;
  }

  /**
   * This method returns a negative integer, a positive integer, or zero as this box is lighter
   * than, heavier than, or has the same weight as the specified otherBox. It overrides the
   * predefined compareTo() method.
   * 
   * @param otherBox - A Box reference that we have to compare this Box with
   */
  @Override
  public int compareTo(Box otherBox) {
    // Subtracts the weigth of the otherBox from this Box, then returns the value
    int returnVal = this.getWeight() - otherBox.getWeight();
    return returnVal;
  }

  /**
   * Getter for the instance field color of this box.
   * 
   * @return color of this Box
   */
  public int getColor() {
    return this.color;
  }

  /**
   * Getter for the instance field weight of this box.
   * 
   * @return weight of this Box
   */
  public int getWeight() {
    return this.weight;
  }


}
