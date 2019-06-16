/////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Fountain.java
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
import java.util.Random;// import the random class

public class Fountain {
  /**
   * Generate a private static Random field which is used to randomize the particles of the
   * fountain.
   */
  private static Random randGen;

  /**
   * Create an array of type Particles that stores the particles of the fountain.
   */
  private static Particle[] particles;

  /**
   * Creates a private static field which will contain the x position of the base of the fountain.
   */
  private static int positionX;

  /**
   * Creates a private static field which will contain the y position of the base of the fountain.
   */
  private static int positionY;

  /**
   * Creates a private static field which will contain the start color of the fountain.
   */
  private static int startColor;

  /**
   * Creates a private static field which will contain the end color of the fountain.
   */
  private static int endColor;

  /**
   * This method is responsible for moving, accelerating and drawing of the particle in the particle
   * array based on the index provided. This method also updates the age of the particle every time
   * it is called for it's index.
   * 
   * @param index of the particle to be updated
   */
  private static void updateParticle(int index) {
    // increments the age of the particle by 1 each time updateParticle() is called
    particles[index].setAge(particles[index].getAge() + 1);
    // sets the color and the transparency of the circles based on the particle
    Utility.fill(Utility.color(23, 141, 235), particles[index].getTransparency());
    // creates a circle in the application using the position and size of the specified particle
    Utility.circle(particles[index].getPositionX(), particles[index].getPositionY(),
        particles[index].getSize());
    // updates the velocity of the particle to create gravitational effect
    particles[index].setVelocityY(particles[index].getVelocityY() + 0.3f);
    // updates the position of the particle by adding the velocity of the particle each time the
    // method is called
    particles[index]
        .setPositionX(particles[index].getPositionX() + particles[index].getVelocityX());
    particles[index]
        .setPositionY(particles[index].getPositionY() + particles[index].getVelocityY());
  }

  /**
   * This method steps through the particle array and looks for null references within this array
   * that are changed to newly reference particles. It keeps creating random new particles until the
   * end of the array is reached or it has created the specified (numberOfParticles) number of
   * particles.
   * 
   * @param numberOfParticles is the number of particles that this method creates
   */
  private static void createNewParticles(int numberOfParticles) {
    // creates a variable called number that represents the number of particles to be created
    int number = numberOfParticles;
    // iterates through particles array and breaks out of loop once number reaches 0 or end of
    // array is reached
    for (int i = 0; i < particles.length; ++i) {
      // Does not create any more particles and breaks out of the loop if the specified number
      // of particles are created
      if (number == 0) {
        break;
      }
      // checks if particle array at index i is null, and if so, creates new particle
      else if (particles[i] == null) {
        particles[i] = new Particle();
        // sets randomly generated X position of new particle, within bounds of +/- 3 pixels of
        // 'positionX' (min inclusive, max exclusive)
        particles[i].setPositionX(positionX - 3f + randGen.nextFloat() * (5.9999f));
        // sets randomly generated Y position of new particle, within bounds of +/- 3 pixels of
        // 'positionY' (min inclusive, max exclusive)
        particles[i].setPositionY(positionY - 3f + randGen.nextFloat() * (5.9999f));
        // sets the size of the particle equal to a randomly generated float value between 4 and
        // 11 (min inclusive, max exclusive)
        particles[i].setSize(4f + randGen.nextFloat() * (6.9999f));
        // sets the color of the particle to a random value between the startColor and endColor
        // fields, using the lerpColor method in the Utility class
        particles[i].setColor(Utility.lerpColor(startColor, endColor, randGen.nextInt()));
        // sets the X velocity of the particle equal to a randomly generated float value between
        // -1 and 1 (min inclusive, max exclusive)
        particles[i].setVelocityX(-1 + randGen.nextFloat() * (1.9999f));
        // sets the Y velocity of the particle equal to a randomly generated float value between
        // -5 and -10 (min inclusive, max exclusive)
        particles[i].setVelocityY(-10 + randGen.nextFloat() * (4.9999f));
        // sets the age of the particle equal to a randomly generated integer value between 0 and
        // 40 (min and max inclusive)
        particles[i].setAge(randGen.nextInt(41));
        // sets the transparency of the particle equal to a randomly generated integer value
        // between 32 and 127 (min and max inclusive)
        particles[i].setTransparency(randGen.nextInt(96) + 32);
        --number;
      }
    }
  }


  /**
   * This method steps through the particles array and sets the particles with Age greater than
   * 'maxAge' to a null reference.
   * 
   * @param maxAge is the maximum age a particle can attain before it is replaced
   */
  private static void removeOldParticles(int maxAge) {
    // for-loop iterates through the particles array
    for (int i = 0; i < particles.length; ++i) {
      // checks that the particles array at index i is not null, to avoid a null pointer exception
      if (particles[i] != null) {
        // checks if the age of the particle at index i is greater than the maxAge parameter, and
        // if so, sets the particle at index i equal to null reference
        if (particles[i].getAge() > maxAge) {
          particles[i] = null;
        }
      }
    }
  }


  /**
   * This method registers the x and y coordinate of the mouse clicked on screen and moves the base
   * of the fountain to that position.
   * 
   * @param x is the X coordinate of where the mouse was clicked on screen
   * @param y is the Y coordinate of where the mouse was clicked on screen
   */
  public static void mousePressed(int x, int y) {
    // sets the positionX value equal to the X position of the mouse when clicked
    positionX = x;
    // sets the positionY value equal to the Y position of the mouse when clicked
    positionY = y;
  }

  /**
   * This method registers the key pressed by the user when the program is running and saves a
   * screenshot if the key pressed is the'p' key.
   * 
   * @param key is the character pressed by the user
   */
  public static void keyPressed(char key) {
    if (key == 'p') { // checks if key parameter passed to method equals 'p'
      Utility.save("screenshot.png"); // uses Utility save method to save screenshot
    }
  }

  /**
   * This method is the fundamental method that is called by Utility.runApplication() in main and is
   * responsible for initializing all the private static fields and running the tests.
   */
  public static void setup() {
    // checks if test method passed, if not prints "FAILED"
    if ((testUpdateParticle() == false) || (testRemoveOldParticles() == false)) {
      System.out.print("FAILED");
    }
    // initializes randGen field equal to a new random generator
    randGen = new Random();
    // initializes positionX field equal to 400 (the center of the screen)
    positionX = 400;
    // initializes positionY field equal to 300 (the center of the screen)
    positionY = 300;
    // initializes startColor and endColor fields using the color method in Utility
    startColor = Utility.color(23, 141, 235);
    endColor = Utility.color(23, 200, 255);
    // initializes particles value equal to a new Particle array with length 800
    particles = new Particle[800];
  }

  /**
   * This method is called repeatedly during program execution and is responsible for constantly
   * clearing the background, creating new particles using the creatNewParticles method, stepping
   * through the particles array and calling updateParticle method for each index and deleting old
   * particles using removeParticles method. It generates and keeps the particle fountain running
   * indefinitely.
   */
  public static void update() {
    // sets the background color to a specific color value using the background and color methods
    // in Utility class
    Utility.background(Utility.color(235, 213, 186));
    // calls the createNewParticles method with an argument of 10 to create 10 new particles
    createNewParticles(10);
    // iterates through the particles array, and checks that the particle at index i does not equal
    // null. If not, calls the updateParticle method using the index i to update the position,
    // velocity, and age of each particle
    for (int i = 0; i < particles.length; ++i) {
      if (particles[i] != null) {
        updateParticle(i);
      }
    }
    // calls the removeOldParticles method with the argument of 80 to remove all particles over the
    // age of 80
    removeOldParticles(80);
  }

  /**
   * Creates a single particle at position (3,3) with velocity (-1,-2). Then checks whether calling
   * updateParticle() on this particle's index correctly results in changing its position to
   * (2,1.3).
   * 
   * @return true when no defect is found, and false otherwise
   */
  private static boolean testUpdateParticle() {
    // creates a new particles array as an instance of the Particle class, with a length of 1
    particles = new Particle[] {new Particle()};
    // sets the X position of the particle at index 0 of the particles array equal to 3f
    particles[0].setPositionX(3f);
    // sets the Y position of the particle at index 0 of the particles array equal to 3f
    particles[0].setPositionY(3f);
    // sets the X velocity of the particle at index 0 of the particles array equal to -1f
    particles[0].setVelocityX(-1f);
    // sets the Y velocity of the particle at index 0 of the particles array equal to -2f
    particles[0].setVelocityY(-2f);
    // calls the updateParticle method using the particle at index 0 of the particles array
    updateParticle(0);
    // checks that the X position of the particle has been updated to 2f and the Y position has
    // been updated to 1.3f. If this is true, the updateParticle method is working correctly and
    // the test method returns true
    if ((particles[0].getPositionX() == 2f) && (particles[0].getPositionY() == 1.3f)) {
      return true;
    }
    // otherwise, the test method returns false
    else {
      return false;
    }
  }

  /**
   * Calls removeOldParticles(6) on an array with three particles (two of which have ages over six
   * and another that does not). Then checks whether the old particles were removed and the young
   * particle was left alone.
   * 
   * @return true when no defect is found, and false otherwise
   */
  private static boolean testRemoveOldParticles() {
    // creates a new particles array as an instance of the Particle class, with a length of 3
    particles = new Particle[] {new Particle(), new Particle(), new Particle()};
    // sets the age of the particle at index 0 of the particles array to 7
    particles[0].setAge(7);
    // sets the age of the particle at index 1 of the particles array to 3
    particles[1].setAge(3);
    // sets the age of the particle at index 2 of the particles array to 9
    particles[2].setAge(9);
    // calls the removeOldParticles method with a maxAge of 6
    removeOldParticles(6);
    // checks that removeOldParticles sets the particles at index 0 and 2 of the particles array
    // equal to null, because their ages were above the maxAge (6). If so, the removeOldParticles
    // method is functioning correctly and the test method returns true
    if (particles[0] == null && particles[2] == null) {
      return true;
    }
    // otherwise, the method returns false
    else {
      return false;
    }
  }

  /**
   * main method used to call Utility.runApplication which calls the setup() and update() method to
   * animate the fountain.
   * 
   * @param args
   */
  public static void main(String[] args) {
    // calls the runApplication method from the Utility class to run the application
    Utility.runApplication();
  }

}
