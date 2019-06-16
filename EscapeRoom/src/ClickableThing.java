/////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION ///////////////////////
// Title: ClickableThing.java
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
 * This class extends the VisibleThing class to create and implement an instance of a clickable
 * object.
 * 
 * @author Alyssa and Siddharth
 *
 */
public class ClickableThing extends VisibleThing {

  /**
   * Private Action field representing the action returned from update when this object is clicked.
   */
  private Action action;

  /**
   * Private boolean field that tracks whether the mouse was pressed during the last update. If so,
   * the boolean is set to true, otherwise it is set to false.
   */
  private boolean mouseWasPressed;

  /**
   * Constructor creating a new instance of a clickable object
   * 
   * @param name   - the name of the object
   * @param x      - the horizontal position of the object
   * @param y      - the vertical position of the object
   * @param action - the action returned from the object being clicked
   */
  public ClickableThing(String name, int x, int y, Action action) {
    super(name, x, y);
    this.action = action; // sets the action field equal to the action parameter
  } // initializes this new object

  /**
   * Method overriding the update() method from VisibleThing in order to update the object
   * 
   * @return action - the action associated with the clickable object
   */
  @Override
  public Action update() {
    super.update(); // calls the update method from the super class
    // checks that the mouse is currently pressed and was not pressed during the last update, and
    // that the coordinates of the mouse are over the object
    if (getProcessing().mousePressed == true && this.mouseWasPressed == false
        && this.isOver(getProcessing().mouseX, getProcessing().mouseY)) {
      // sets mouseWasPressed to true if the mouse is currently pressed, and sets it to false if
      // the mouse is not currently pressed
      mouseWasPressed = getProcessing().mousePressed;
      return action;
    } else {
      // sets mouseWasPressed to true if the mouse is currently pressed, and sets it to false if
      // the mouse is not currently pressed
      mouseWasPressed = getProcessing().mousePressed;
      return null;
    }
  }
}
