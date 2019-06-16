/////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: ShoppingCart.java
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
// Persons: (identify each person and describe their help in detail)
// Online Sources: (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/**
 * Contains tests for Shopping Cart class
 * 
 * @author Alyssa Odau and Siddharth Aneja
 *
 */
public class ShoppingCartTests {

  /**
   * Checks whether the total number of items within the cart is incremented after adding one item
   * 
   * @return true if the test passes without problems, false otherwise
   */
  public static boolean testCountIncrementedAfterAddingOnlyOneItem() {
    boolean testPassed = true; // boolean local variable evaluated to true if this test passed,
                               // false otherwise
    String[] cart = new String[20]; // shopping cart
    int count = 0; // number of items present in the cart (initially the cart is empty)

    // Add an item to the cart
    count = ShoppingCart.add(3, cart, count); // add an item of index 3 to the cart
    // Check that count was incremented
    if (count != 1) {
      System.out.println("Problem detected: After adding only one item to the cart, "
          + "the cart count should be incremented. But, it was not the case.");
      testPassed = false;
    }
    return testPassed;
  }

  /**
   * Checks whether add and OccurrencesOf return the correct output when only one item is added to
   * the cart
   * 
   * @return true if test passed without problems, false otherwise
   */
  public static boolean testAddAndOccurrencesOfForOnlyOneItem() {
    boolean testPassed = true; // evaluated to true if test passed without problems, false otherwise
    // define the shopping cart as an oversize array of elements of type String
    // we can set an arbitrary capacity for the cart - for instance 10
    String[] cart = new String[10]; // shopping cart
    int count = 0; // number of items present in the cart (initially the cart is empty)

    // check that OccurrencesOf returns 0 when called with an empty cart
    if (ShoppingCart.occurrencesOf(10, cart, count) != 0) {
      System.out.println("Problem detected: Tried calling OccurrencesOf() method when the cart is "
          + "empty. The result should be 0. But, it was not.");
      testPassed = false;
    }

    // add one item to the cart
    count = ShoppingCart.add(0, cart, count); // add an item of index 0 to the cart

    // check that OccurrencesOf("Apples", cart, count) returns 1 after adding the item with key 0
    if (ShoppingCart.occurrencesOf(0, cart, count) != 1) {
      System.out.println("Problem detected: After adding only one item with key 0 to the cart, "
          + "OccurrencesOf to count how many of that item the cart contains should return 1. "
          + "But, it was not the case.");
      testPassed = false;
    }

    return testPassed;
  }

  /**
   * Checks that occurrencesOf method correctly outputs the amount of occurrences of an item in the
   * cart
   * 
   * @return true if test passed without problems, false otherwise
   */
  public static boolean testAddOccurrencesOfDuplicateItems() {
    boolean testPassed = true; // boolean local variable evaluated to true if this test passed,
    // false otherwise
    String[] cart = new String[20]; // shopping cart
    int count = 0; // number of items present in the cart (initially the cart is empty)
    count = ShoppingCart.add(0, cart, count); // add an item of index 0 to the cart
    count = ShoppingCart.add(0, cart, count); // adds another item of index 0 to the cart

    // check that OccurrencesOf("Apples", cart, count) returns 2 after adding the item with key 0
    if (ShoppingCart.occurrencesOf(0, cart, count) != 2) {
      System.out.println("Problem detected: After adding two items with key 0 to the cart, "
          + "OccurrencesOf to count how many of that item the cart contains should return 2. "
          + "But, it was not the case.");
      testPassed = false;
    }
    return testPassed;
  }

  /**
   * Checks that the correct output is returned when the user tries to add too many items to the
   * cart, exceeding its capacity
   * 
   * @return true if test passed without problems, false otherwise
   */
  public static boolean testAddingTooMuchItems() {
    boolean testPassed = true; // boolean local variable evaluated to true if this test passed,
    // false otherwise
    String[] cart = new String[20]; // shopping cart with capacity to store 20 items
    int count = 0; // number of items present in the cart (initially the cart is empty)
    // a for-loop that adds 21 items to the cart, exceeding the cart's capacity
    for (int i = 0; i <= 20; i++) {
      count = ShoppingCart.add(0, cart, count);
    }
    // checks that the count does not increase when an item is added that exceeds the cart's
    // capacity.
    // If it does, error message is printed
    if (ShoppingCart.occurrencesOf(0, cart, count) != 20) {
      System.out.println("Problem detected: Adding another item to the cart should print out"
          + "an error message and return the count as 20. But, it was not the case.");
      testPassed = false;
    }
    return testPassed;
  }

  /**
   * Checks if one item is added in the start and followed by 2 other items then added again does
   * OccurencesOf return the correct count
   * 
   * @return true if test passed without problems, false otherwise
   */
  public static boolean testAddingDuplicateValues() {
    boolean testPassed = true; // boolean local variable evaluated to true if this test passed,
    // false otherwise
    String[] cart = new String[20]; // shopping cart with capacity to store 20 items
    int count = 0; // number of items present in the cart (initially the cart is empty)
    int occurencesOfItem = 2; // number of occurrences of item added in test
    count = ShoppingCart.add(0, cart, count);
    count = ShoppingCart.add(1, cart, count);
    count = ShoppingCart.add(2, cart, count);
    count = ShoppingCart.add(0, cart, count);

    // Checks if occurrencesOf method returns correct number, otherwise prints error message
    if (ShoppingCart.occurrencesOf(0, cart, count) != occurencesOfItem) {
      System.out.println("Problem detected: There should be only two occurences of item "
          + "with index 0 but that's not the case.");
      testPassed = false;
    }
    return testPassed;
  }

  /**
   * Checks that when only one attempt to remove an item present in the cart is made, only one
   * occurrence of that item is removed from the cart
   * 
   * @return true if test passed without problems, false otherwise
   */
  public static boolean testRemoveOnlyOneOccurrenceOfItem() {
    boolean testPassed = true; // boolean local variable evaluated to true if this test passed,
    // false otherwise
    String[] cart = new String[20]; // shopping cart with capacity to store 20 items
    int count = 0; // number of items present in the cart (initially the cart is empty)
    count = ShoppingCart.add(0, cart, count);
    count = ShoppingCart.add(1, cart, count);
    count = ShoppingCart.add(2, cart, count);
    count = ShoppingCart.add(0, cart, count);
    count = ShoppingCart.remove("Apple", cart, count);

    // Checks that after one occurrence of "Apple" is removed, the method returns that there is only
    // one occurrence of "Apple". Otherwise, prints error message.
    if (ShoppingCart.occurrencesOf(0, cart, count) != 1) {
      System.out.println("Problem detected: There should be only one occurence of item with index 0"
          + "but that's not the case.");
      testPassed = false;
    }
    return testPassed;
  }

  /**
   * Checks that remove does not make any change to count (number of items in the cart) when the
   * user tries to remove an item not present within the cart
   * 
   * @return true if test passed without problems, false otherwise
   */
  public static boolean testRemoveItemNotFoundInCart() {
    boolean testPassed = true; // boolean local variable evaluated to true if this test passed,
    // false otherwise
    String[] cart = new String[20]; // shopping cart with capacity to store 20 items
    int count = 0; // number of items present in the cart (initially the cart is empty)
    count = ShoppingCart.add(0, cart, count);
    count = ShoppingCart.add(0, cart, count);
    count = ShoppingCart.remove("Banana", cart, count); // calls remove method to remove item that
    // is not in the cart
    // Checks that removal of item not in the cart does not affect occurrences of items in the cart
    if (ShoppingCart.occurrencesOf(0, cart, count) != 2) {
      System.out.println("Problem detected: Removing \"banana\" from the cart should not reduce "
          + "the count because it isn't present in the cart");
      testPassed = false;
    }
    return testPassed;
  }

  /**
   * Checks what would happen if an attempt is made to remove an item from an empty cart
   * 
   * @return true if test passed without problems, false otherwise
   */
  public static boolean testRemoveItemFromEmptyCart() {
    boolean testPassed = true; // boolean local variable evaluated to true if this test passed,
    // false otherwise
    String[] cart = new String[20]; // shopping cart with capacity to store 20 items
    int count = 0; // number of items present in the cart (initially the cart is empty)
    count = ShoppingCart.remove("Banana", cart, count); // removes nonexistent item from cart
    // checks that count remains 0 and does not become -1, otherwise prints error message
    if (ShoppingCart.occurrencesOf(0, cart, count) != 0) {
      System.out.println("Problem detected: If is the cart is empty then the count remains zero"
          + " and an error message is preinted if the user tries to remove an item");
      testPassed = false;
    }
    return testPassed;
  }

  /**
   * Checks whether getSubtotal returns correct total value for items in cart
   * 
   * @return true if test passed without problems, false otherwise
   */
  public static boolean testSubTotal() {
    boolean testPassed = true; // boolean local variable evaluated to true if this test passed,
    // false otherwise
    String[] cart = new String[20]; // shopping cart with capacity to store 20 items
    int count = 0; // number of items present in cart (initially cart is empty)
    ShoppingCart.add(0, cart, count);
    ShoppingCart.add(0, cart, count);
    double total = 3.18; // subtotal of items added to the cart
    double delta = 0.00001; // used to evaluate similarity of two double values
    // Checks that getSubTotalPrice returns correct value (within 0.00001)
    // otherwise, prints error message
    if ((ShoppingCart.getSubTotalPrice(cart, count) - total) >= delta) {
      testPassed = false;
      System.out.println("Problem detected: Subtotal returned by getSubTotalPrice was "
          + ShoppingCart.getSubTotalPrice(cart, count) + " while it should have been " + total);
    }
    return testPassed;
  }

  /**
   * main method used to call the unit tests
   * 
   * @param args
   */
  public static void main(String[] args) {
    System.out.println("testCountIncrementedAfterAddingOnlyOneItem(): "
        + testCountIncrementedAfterAddingOnlyOneItem());
    System.out.println(
        "testAddAndOccurrencesOfForOnlyOneItem(): " + testAddAndOccurrencesOfForOnlyOneItem());
    System.out
        .println("testAddOccurrencesOfDuplicateItems(): " + testAddOccurrencesOfDuplicateItems());
    System.out.println("testAddingTooMuchItems(): " + testAddingTooMuchItems());
    System.out.println("testAddingDuplicateValues(): " + testAddingDuplicateValues());
    System.out
        .println("testRemoveOnlyOneOccurrenceOfItem(): " + testRemoveOnlyOneOccurrenceOfItem());
    System.out.println("testRemoveItemNotFoundInCart(): " + testRemoveItemNotFoundInCart());
    System.out.println("testRemoveItemFromEmptyCart(): " + testRemoveItemFromEmptyCart());
  }
}
