/////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: DraggableThing.java
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
 * This class extends VisibleThing and implements the creation and movement of an instance of a
 * draggable object.
 * 
 * @author Alyssa and Siddharth
 *
 */
public class DraggableThing extends VisibleThing {

  /**
   * Private boolean field tracking whether the mouse was pressed during the last update(), set to
   * true if mouse was pressed and false otherwise.
   */
  private boolean mouseWasPressed;

  /**
   * Private boolean field that is set to true when the object is being dragged by the user, and
   * false otherwise.
   */
  private boolean isDragging;

  /**
   * Private integer field tracking the horizontal position of the mouse during the last update.
   */
  private int oldMouseX;

  /**
   * Private integer field tracking the vertical position of the mouse during the last update.
   */
  private int oldMouseY;

  /**
   * Constructor that takes in a String name, int x position and int y position to create a new
   * instance of a draggable object.
   * 
   * @param name - the name of the object
   * @param x    - the initial horizontal position
   * @param y    - the initial vertical position
   */
  public DraggableThing(String name, int x, int y) {
    super(name, x, y);
  } // initialize new thing

  /**
   * This method overrides the super classes update() method. It calls VisibleThing update(), then
   * moves according to mouse drag each time isDragging changes from true to false, the drop()
   * method below will be called once and any action objects returned from that method should then
   * be returned from update()
   * 
   * @return action - returned by drop() method called within this method.
   */
  @Override
  public Action update() {
    super.update(); // Calls update() method of super class
    // Checks if the MouseWasPressed was false and if it is currently pressed and
    // over the object.
    if (this.mouseWasPressed == false && Thing.getProcessing().mousePressed == true
        && isOver(Thing.getProcessing().mouseX, Thing.getProcessing().mouseY)) {
      this.mouseWasPressed = true; // sets mouseWasPressed to true
      this.isDragging = true; // Sets isDragging to true
      // Sets the oldMouseX to the current X coordinate of the mouse
      this.oldMouseX = Thing.getProcessing().mouseX;
      // Sets the oldMouseY to the current Y coordinate of the mouse
      this.oldMouseY = Thing.getProcessing().mouseY;
      // returns null so that update can be called again
      return null;
    }
    // Checks if the mouseWasPressed and isDragging is true
    if (isDragging && mouseWasPressed) {
      // Uses the move() method to move the object my the distance the mouse moved between
      // the oldMouse coordinates and the current coordinates
      move(Thing.getProcessing().mouseX - this.oldMouseX,
          Thing.getProcessing().mouseY - this.oldMouseY);
      // Changes the oldMouseX to the current X coordinate
      this.oldMouseX = Thing.getProcessing().mouseX;
      // Changes the oldMouseY to the current Y coordinate
      this.oldMouseY = Thing.getProcessing().mouseY;
      // if the mouse is currently not pressed (that is object is dropped) it enters this branch
      if (Thing.getProcessing().mousePressed == false) {
        this.mouseWasPressed = false; // Sets mouseWasPressed to false
        this.isDragging = false; // Sets isDragging to false
        return drop();// calls drop() and returns the object returned by it
      }
      return null;
    }
    return null;
  }

  /**
   * This method returns null. Subclass types will override this drop() method.
   * 
   * @return null
   */
  protected Action drop() {
    return null;
  }
}

