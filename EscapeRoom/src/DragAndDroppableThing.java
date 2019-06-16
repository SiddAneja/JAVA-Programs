/////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: DragAndDroppableThing.java
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
 * Class extending DraggableThing to create and update an instance of a draggable and droppable
 * object.
 * 
 * @author Alyssa and Siddharth
 *
 */
public class DragAndDroppableThing extends DraggableThing {

  /**
   * Private VisibleThing field representing the target object over which the draggable and
   * droppable object can be dropped.
   */
  private VisibleThing target;

  /**
   * Private Action field representing the action that results from dropping this object over the
   * target object.
   */
  private Action action;

  /**
   * Constructor used to create a new draggable and droppable object.
   * 
   * @param name   - the name of the object
   * @param x      - the horizontal position of the object
   * @param y      - the vertical position of the object
   * @param target - the target VisibleThing object
   * @param action - the action resulting from the object being dropped over the target
   */
  public DragAndDroppableThing(String name, int x, int y, VisibleThing target, Action action) {
    super(name, x, y);
    this.target = target; // sets target field equal to target parameter
    this.action = action; // sets action field equal to action parameter
  } // initialize new object

  /**
   * Overrides the drop() method from the super class to return an action when the draggable and
   * droppable object is dropped over an active target.
   * 
   * @return action associated with object
   */
  @Override
  protected Action drop() {
    // checks that the object is over the target object, and that the target is active
    if (this.isOver(target) && target.isActive()) {
      this.deactivate(); // deactivates the object
      target.deactivate(); // deactivates the target
      return action;
    } else {
      return null;
    }
  }

}
