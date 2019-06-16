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
import java.util.Scanner; // imports scanner

/**
 * Represents a shopping cart interface
 * 
 * @author Siddharth Aneja and Alyssa Odau
 *
 */
public class ShoppingCart {
  /**
   * Shopping cart max capacity
   */
  private static final int CART_CAPACITY = 20;

  /**
   * sales tax to be added at checkout to calculate the total cost
   */
  private static final double TAX_RATE = 0.05;

  /**
   * Gets the Item Description from the marketItems array based on the index provided
   * 
   * @param index of the item within the marketItems array
   * @return item description from the marketItems array
   */
  private static String getItemDescription(int index) {
    return MARKET_ITEMS[index][0];
  }

  /**
   * A perfect-size two-dimensional array that stores the available items in the market
   */
  public static final String[][] MARKET_ITEMS = new String[][] {{"Apple", "$1.59"},
      {"Avocado", "$0.59"}, {"Banana", "$0.49"}, {"Beef", "$3.79"}, {"Blueberry", "$6.89"},
      {"Broccoli", "$1.79"}, {"Butter", "$4.59"}, {"Carrot", "$1.19"}, {"Cereal", "$3.69"},
      {"Cheese", "$3.49"}, {"Chicken", "$5.09"}, {"Chocolate", "$3.19"}, {"Cookie", "$9.5"},
      {"Cucumber", "$0.79"}, {"Eggs", "$3.09"}, {"Grape", "$2.29"}, {"Ice Cream", "$5.39"},
      {"Milk", "$2.09"}, {"Mushroom", "$1.79"}, {"Onion", "$0.79"}, {"Pepper", "$1.99"},
      {"Pizza", "$11.5"}, {"Potato", "$0.69"}, {"Spinach", "$3.09"}, {"Tomato", "$1.79"}};

  /**
   * adds the item with the given identifier index at the end of the cart
   * 
   * @param index of the item within the marketItems array
   * @param cart  shopping cart
   * @param count number of items present within the cart before this add method is called
   * @return the number of items present in the cart after the item with identifier index is added
   */
  public static int add(int index, String[] cart, int count) {
    // if the cart is full then an error is printed and count does'nt change
    if (count == CART_CAPACITY) {
      System.out.println("\"WARNING: The cart is full. You cannot add any new item.\"");
    }
    // if cart is'nt full then the item with that index is added to the end of the cart and count
    // increases by one
    else {
      cart[count] = MARKET_ITEMS[index][0];
      count++;
    }
    return count; // updated number of items in the cart is returned
  }

  /**
   * Returns how many occurrences of the item with index itemIndex are present in the shopping cart
   * 
   * @param itemIndex identifier of the item to count its occurrences in the cart
   * @param cart      shopping cart
   * @param count     number of items present within the cart
   * @return the number of occurrences of item in the cart
   */
  public static int occurrencesOf(int itemIndex, String[] cart, int count) {
    // initialize a variable to store the number of instances of item with index itemIndex
    int occurrences = 0;
    // iterate through the cart and check for item with itemIndex
    // And increment 'occurrences' for every instance found
    for (int i = 0; i < count; i++) {
      if (cart[i].equals(MARKET_ITEMS[itemIndex][0])) {
        occurrences++;
      }
    }
    // prints the number of occurrences, item description and itemIndex to the console
    System.out.println("The number of occurrences of " + getItemDescription(itemIndex) + " (id #"
        + itemIndex + ") is: " + occurrences);
    return occurrences; // number of occurrences is returned
  }

  /**
   * Returns the index of an item within the shopping cart
   * 
   * @param item  description
   * @param cart  Shopping cart
   * @param count number of items present in the shopping cart
   * @return index of the item within the shopping cart, and -1 if the item does not exist in the
   *         cart
   */
  private static int indexOf(String item, String[] cart, int count) {
    // initializes index (of item in shopping cart) to -1
    int index = -1;
    // iterates through the cart to find the item and sets index equal to the index of the
    // item in the cart, if not found index remains -1
    for (int i = 0; i < count; ++i) {
      if (cart[i].equalsIgnoreCase(item)) {
        index = i;
        break;
      }
    }
    return index; // returns the index whether -1 or not
  }

  /**
   * Removes the first occurrence of itemToRemove if found and returns the number of items in the
   * cart after remove operation is completed either successfully or not
   * 
   * @param itemToRemove from shopping cart based on user input
   * @param cart         Shopping cart
   * @param count        number of items present in the shopping cart
   * @return the number of items present in the cart after the item with identifier index is removed
   */
  public static int remove(String itemToRemove, String[] cart, int count) {
    // initializes index (of the itemToRemove) using indexOf method
    int index = indexOf(itemToRemove, cart, count);
    // if the index of itemToRemove is -1, it prints out an error message and count reamins the same
    if (index == -1) {
      System.out.println("WARNING: " + itemToRemove + " not found in the shopping cart.");
    }
    // if index is not -1, the method moves the last item in the cart to the index of the
    // itemToRemove and decrements count
    else {
      cart[index] = cart[count - 1];
      cart[count - 1] = null;
      --count;
    }
    return count; // updated number of items is returned
  }

  /**
   * returns the total value of the cart without tax based on prices from marketItems array
   * 
   * @param cart  Shopping cart
   * @param count number of items present in the shopping cart
   * @return the total cost of items present in the cart
   */
  public static double getSubTotalPrice(String[] cart, int count) {
    double cost = 0.0; // initializes the subtotal cost of all the items
    // iterates through the cart and matches items to marketItems to find their cost
    for (int i = 0; i < count; ++i) {
      for (int j = 0; j < MARKET_ITEMS.length; ++j) {
        if (cart[i].equals(MARKET_ITEMS[j][0])) {
          // adds the cost of each item
          cost = cost + Double.valueOf(MARKET_ITEMS[j][1].substring(1));
          break;
        }
      }
    }
    return cost; // returns the total cost of items in the cart without tax
  }

  /**
   * prints the Market Catalog (item identifiers, description, and unit prices) based on data from
   * the marketItems array
   */
  public static void printMarketCatalog() {
    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
    System.out.println("Item id     Description      Price");
    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
    // prints out all the items in the marketItems array in a formatted way
    for (int i = 0; i < MARKET_ITEMS.length; i++) {
      System.out.println(i + "\t\t" + MARKET_ITEMS[i][0] + "    \t " + MARKET_ITEMS[i][1]);
    }
    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
  }

  /**
   * Displays the contents of the cart separated by commas
   * 
   * @param cart  number of items present in the shopping cart
   * @param count number of items present in the shopping cart
   */
  public static void displayCartContent(String[] cart, int count) {
    System.out.print("Cart Content: ");
    // iterates through the cart and prints out each item followed by a comma
    for (int i = 0; i < count; i++) {
      System.out.print(cart[i] + ", ");
    }
    System.out.println();
  }

  /**
   * Calculates and displays the number of items, subtotal, tax amount and the total cost of the
   * items in the cart
   * 
   * @param cart     number of items present in the shopping cart
   * @param subtotal is the total cost of items in the cart without tax
   * @param count    number of items present in the shopping cart
   */
  public static void checkout(String[] cart, double subtotal, int count) {
    // initializes and calculates the tax on the items in the cart
    double tax = TAX_RATE * subtotal;
    // calculates the total cost of items with tax
    double totalCost = subtotal + tax;
    // prints out the number of items, subtotal, tax and total cost in a formatted way
    System.out.println("#items: " + count + " Subtotal: $" + String.format("%.2f", subtotal)
        + " Tax: $" + String.format("%.2f", tax) + " TOTAL: $" + String.format("%.2f", totalCost));
  }

  /**
   * Prints out the command menu for the shopping cart application
   */
  public static void printCommandMenu() {
    System.out.println("COMMAND MENU:");
    System.out.println(" [P] print the market catalog");
    System.out.println(
        " [A <index>] add one occurrence of an item to" + " the cart given its identifier");
    System.out.println(" [C] checkout");
    System.out.println(" [D] display the cart content");
    System.out.println(
        " [O <index>] number of occurrences of an item in the" + " cart given its identifier");
    System.out.println(
        " [R <index>] remove one occurrence of an item from the" + " cart given its identifier");
    System.out.println(" [Q]uit the application\n");
    System.out.print("ENTER COMMAND: ");
  }

  /**
   * main method used to call the methods and create the interface
   * 
   * @param args
   */
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in); // creates new scanner object for reading user input
    boolean quit = true; // local boolean value that becomes false when user decides to quit
    int count = 0; // count of objects in cart (cart is initially empty)
    String[] cart = new String[CART_CAPACITY]; // shopping cart with capacity to store
    // CART_CAPACITY items
    System.out.println("=============   Welcome to the Shopping Cart App   =============\n");
    // begins loop that creates user interface with program
    do {
      System.out.println();
      printCommandMenu(); // prints menu of user options
      String userInput = sc.nextLine(); // assigns string to user input
      // creates a switch to process user input, ignoring capitalization
      switch (userInput.charAt(0)) {
        case 'P':
        case 'p':
          printMarketCatalog(); // uses method to print market catalog
          break;
        case 'a':
        case 'A':
          // takes index from user input and converts it to an integer value to use in add method
          count = add(Integer.valueOf(userInput.substring(2)), cart, count);
          break;
        case 'c':
        case 'C':
          double subTotal = getSubTotalPrice(cart, count); // gets subtotal of cart using method
          // calls checkout method to print out cost of items
          checkout(cart, subTotal, count);
          break;
        case 'd':
        case 'D':
          displayCartContent(cart, count); // displays contents of cart
          break;
        case 'o':
        case 'O':
          // calls occurrencesOf method to find occurrences of specified item based on index
          // entered by user
          occurrencesOf(Integer.valueOf(userInput.substring(2)), cart, count);
          break;
        case 'R':
        case 'r':
          // removes item of index specified by user
          count = remove(getItemDescription(Integer.valueOf(userInput.substring(2))), cart, count);
          break;
        case 'Q':
        case 'q':
          quit = false; // breaks out of interface loop
          break;
      }
    } while (quit);
    System.out.println("=============  Thank you for using this App!!!!!  =============");
  }
}
