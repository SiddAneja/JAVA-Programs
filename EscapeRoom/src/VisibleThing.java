/////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: VisibleThing.java
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

import processing.core.PImage;
import java.io.File;

/**
 * This class extends the Thing class in order to create and update a visible object
 * 
 * @author Alyssa and Siddharth
 *
 */
public class VisibleThing extends Thing {

  /**
   * Private PImage field graphically representing this object.
   */
  private PImage image;

  /**
   * Private integer field representing the horizontal position of the object's left side in pixels
   */
  private int x;

  /**
   * Private integer field representing vertical position of the object's top side in pixels
   */
  private int y;

  /**
   * Constructor creating a new VisibleThing object
   * 
   * @param name - the name of the object
   * @param x    - the horizontal position of the object
   * @param y    - the vertical position of the object
   */
  public VisibleThing(String name, int x, int y) {
    super(name);
    this.x = x; // sets the x field equal to the x parameter
    this.y = y; // sets the y field equal to the y parameter
    // sets the image field equal to an image loaded using the name of the object
    image = getProcessing().loadImage("images" + File.separator + name + ".png");
  }

  /**
   * Method that updates the position of the object each time it is called
   * 
   * @return null
   */
  @Override
  public Action update() {
    // draws the image at the new position
    getProcessing().image(image, x, y);
    return null;
  }

  /**
   * Method moves the object by adding the change in position to the original position.
   * 
   * @param dx - the change in horizontal position
   * @param dy - the change in vertical position
   */
  public void move(int dx, int dy) {
    this.x = this.x + dx; // updates the x position by adding the horizontal change
    this.y = this.y + dy; // updates the y position by adding the vertical change
  }

  /**
   * Method checking if coordinates are over the object
   * 
   * @param x - horizontal position
   * @param y - vertical position
   * @return true if the coordinates are over the object, and false otherwise
   */
  public boolean isOver(int x, int y) {
    // checks that the x parameter is greater than the left-hand side of the object, and that the
    // x parameter is less than the right side of the object (thus, lying in between the left and
    // right side), then checks the same with the top and bottom side of the object using the y
    // coordinate
    if ((x >= this.x) && (x <= this.x + image.width) && (y >= this.y)
        && (y <= this.y + image.height)) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Method checking that other object is over this object. If so, returns true, and otherwise
   * returns false
   * 
   * @param other - the other object to check
   * @return true if the objects are overlaid, false otherwise
   */
  public boolean isOver(VisibleThing other) {
    // checks that the other object's right side is greater than this object's left hand side, and
    // that the other object's left side is less than or equal to the right side of this object
    // (thus lying between the left and right sides of this object), and does the same for the
    // top and bottom side of the object using the y position of both objects
    if (((other.x + other.image.width >= this.x) && (other.x <= this.x + this.image.width))
        && ((other.y + other.image.height >= this.y) && (other.y <= this.y + this.image.height))) {
      return true;
    } else {
      return false;
    }
  }
}
// return true only when other's image overlaps this one's
