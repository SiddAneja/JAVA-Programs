/////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: EscapeRoom.java
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

import processing.core.PApplet;
import processing.core.PImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class extends the PApplet class and is the main class that is used to run the application.
 * It uses method of PApplet like setup(), draw(), settings() to run the application interface.
 * 
 * @author Siddharth and Alyssa
 *
 */
public class EscapeRoom extends PApplet {
  /**
   * creates a variable of type PImage to store the background image of the game.
   */
  private PImage backgroundImage;

  /**
   * creates an arrayList of type 'Thing' and stores all the things in the game in it.
   */
  private ArrayList<Thing> allThings;

  /**
   * Sets the size of the game window.
   */
  public void settings() {
    size(800, 600);
  }

  /**
   * This method loads a background image, prints out some introductory text, and then reads in a
   * set of thing descriptions from a text file with the provided name. The image is stored in
   * this.backgroundImage, and the activated things are added to the this.allThings list.
   * 
   * @param filename - relative path of file to load, relative to current working directory
   */
  private void loadRoom(String filename) {
    // start reading file contents
    Scanner fin = null;
    int lineNumber = 1; // report first line in file as lineNumber 1
    try {
      fin = new Scanner(new File(filename));

      // read and store background image
      String backgroundImageFilename = fin.nextLine().trim();
      backgroundImageFilename = "images" + File.separator + backgroundImageFilename + ".png";
      backgroundImage = loadImage(backgroundImageFilename);
      lineNumber++;

      // read and print out introductoy text
      String introductoryText = fin.nextLine().trim();
      System.out.println(introductoryText);
      lineNumber++;

      // then read and create new things, one line per thing
      while (fin.hasNextLine()) {
        String line = fin.nextLine().trim();
        if (line.length() < 1)
          continue;

        // fields are delimited by colons within a given line
        String[] parts = line.split(":");
        Thing newThing = null;

        // first letter in line determines the type of thing to create
        if (Character.toUpperCase(line.charAt(0)) == 'C')
          newThing = loadNewClickableThing(parts);
        else if (Character.toUpperCase(line.charAt(0)) == 'D')
          newThing = loadNewDragAndDroppableThing(parts);

        // even deactivated object references are being added to allThings, so they can be found
        // these deactivated object references will be removed, when draw() is first called
        allThings.add(newThing);
        if (Character.isLowerCase(line.charAt(0))) // lower case denotes non-active object
          newThing.deactivate();
        lineNumber++;
      }

      // catch and report warnings related to any problems experienced loading this file
    } catch (FileNotFoundException e) {
      System.out.println("WARNING: Unable to find or load file: " + filename);
    } catch (RuntimeException e) {
      System.out.println("WARNING: Problem loading file: " + filename + " line: " + lineNumber);
      e.printStackTrace();
    } finally {
      if (fin != null)
        fin.close();
    }
  }

  /**
   * Helper method to retrieve thing references from allThings, based on their names. If multiple
   * things have that name, this method will return the first (lowest-index) reference found.
   * 
   * @param name is the name of the object that is being found
   * @return a reference to a thing with the specified name, or null when none is found
   */
  private Thing findThingByName(String name) {
    // Iterates through allThings arrayList and finds the instance of Thing with the name
    // same as the parameter, and returns it. If not found return null and an error.
    for (int i = 0; i < allThings.size(); i++)
      if (allThings.get(i).hasName(name)) {
        return allThings.get(i);
      }
    System.out.println("WARNING: Failed to find thing with name: " + name);
    return null;
  }

  /**
   * This method creates and returns a new ClickableThing based on the properties specified as
   * strings within the provided parts array.
   * 
   * @param parts contains the following strings in this order: 
   * - C: indicates that a ClickableThing is being created 
   * - name: the name of the newly created thing 
   * - x: the starting x position (as an int) for this thing 
   * - y: the starting y position (as an int) for this thing
   * - message: a string of text to display when this thing is clicked 
   * - name of thing to activate (optional): activates this thing when clicked
   * @return the newly created object
   */
  private ClickableThing loadNewClickableThing(String[] parts) {
    // C: name: x: y: message: name of object to activate (optional)
    String name = parts[1].trim();
    int x = Integer.parseInt(parts[2].trim());
    int y = Integer.parseInt(parts[3].trim());
    String message = parts[4].trim();
    Thing activate = null;
    if (parts.length > 5)
      activate = findThingByName(parts[5].trim());
    // create new thing
    ClickableThing newThing = new ClickableThing(name, x, y, new Action(message, activate));
    return newThing;
  }

  /**
   * This method creates and returns a new DragAndDroppableThing based on the properties specified
   * as strings within the provided parts array.
   * 
   * @param parts contains the following strings in this order: 
   * - D: indicates that a DragAndDroppableThing is being created 
   * - name: the name of the newly created thing
   * - x: the starting x position (as an int) for this thing
   * - y: the starting y position (as an int) for this thing 
   * - message: a string of text to display when this thing is dropped on target
   * - name of thing to activate (optional): activates this thing when dropped on target
   * @return the newly created object
   */
  private DragAndDroppableThing loadNewDragAndDroppableThing(String[] parts) {
    // D: name: x: y: target: message: name of object to activate (optional)
    String name = parts[1].trim();
    int x = Integer.parseInt(parts[2].trim());
    int y = Integer.parseInt(parts[3].trim());
    Thing dropTarget = findThingByName(parts[4].trim());
    if (!(dropTarget instanceof VisibleThing))
      dropTarget = null;
    String message = parts[5].trim();
    Thing activate = null;
    if (parts.length > 6)
      activate = findThingByName(parts[6].trim());
    // create new thing
    DragAndDroppableThing newThing = new DragAndDroppableThing(name, x, y,
        (VisibleThing) dropTarget, new Action(message, activate));
    return newThing;
  }

  /**
   * The setup() function is called once when the program starts. It's used to define initial
   * environment properties such as screen size and background color and to load media such as
   * images and fonts as the program starts. There can only be one setup() function for each program
   * and it shouldn't be called again after its initial execution.
   * 
   */
  public void setup() {
    Thing.setProcessing(this); // Uses the setProcessing method from Thing
    allThings = new ArrayList<Thing>(); // Initializes the allThings arrayList
    // Calls loadRoom with the String to the files for the project
    loadRoom("rooms" + File.separator + "computerCenter.room");
  }

  /**
   * Called directly after setup() and continuously executes the lines of code contained inside its
   * block until the program is stopped or noLoop() is called. The draw() function is called
   * automatically and should never be called explicitly. It should always be controlled with
   * noLoop(), redraw() and loop().
   * 
   */
  public void draw() {
    this.image(backgroundImage, 0, 0); // Draws the images every time draw is called
    // Iterates through the allThing arrayList and call updates for every instance of thing
    // and if the action returned after calling update is not null, then call the method act()
    for (int i = 0; i < allThings.size(); i++) {
      Action returnAction = allThings.get(i).update();
      if (returnAction != null) {
        returnAction.act(allThings);
      }
    }
    // Iterates through allThings arrayList again deletes every inactive instance of thing
    for (int i = 0; i < allThings.size(); i++) {
      if (allThings.get(i).isActive() == false) {
        allThings.remove(i);
      }
    }
  }

  /**
   * The main method of that calls the main() method of PApplet to run the interface of the
   * appliaction.
   * 
   * @param args
   */
  public static void main(String[] args) {
    PApplet.main("EscapeRoom"); // uses main() method of PApplet to run the interface
  }
}
