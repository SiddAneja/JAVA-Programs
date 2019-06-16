
/**
 * This class creates test benches to test the different methods created in the other classes.
 * @author Siddharth
 *
 */
public class HelpDeskTestSuite extends HelpDesk{
  
  /**
   * The constructor that calls the super classes constructor.
   * @param capacity of the array.
   */
  public HelpDeskTestSuite(int capacity) {
    super(capacity);
  }

  /**
   * This method tests the propagateUp() method by creating an array and calling the method and 
   * checking the value based on predetermined calculations.
   * @return true if the test passes, else false.
   */
  public static boolean testPropagateUp() {
    //Creates a new HelpDesk with a capacity of 5 and adds messages in a random order and then
    //calls the propagateUP method to see if it arranges the messages in the correct order
    HelpDesk test = new HelpDesk(5);
    test.array[0] = new SupportTicket("bbb");
    test.array[1] = new SupportTicket("ccc");
    test.array[2] = new SupportTicket("ddd");
    test.array[3] = new SupportTicket("aaa");
    test.array[4] = new SupportTicket("eee");
    test.propagateUp(4);
    if(test.array[0].toString().equals("eee")) {
      return true;
    }
    return false;
  }
  
  /**
   * This method tests the propagateDown() method by creating an array and calling the method and 
   * checking the value based on predetermined calculations.
   * @return true if the test passes, else false.
   */
  public static boolean testPropagateDown() {
    //Creates a new HelpDesk with a capacity of 5 and adds messages in a random order and then
    //removes the longest message after calling propagateUp and then replaces it with the last
    //message .
    //then calls the propagateDown method to see if it arranges the messages in the correct order
    HelpDesk test = new HelpDesk(5);
    test.size = 5;
    test.array[0] = new SupportTicket("bbb");
    test.array[1] = new SupportTicket("ccc");
    test.array[2] = new SupportTicket("ddd");
    test.array[3] = new SupportTicket("aaa");
    test.array[4] = new SupportTicket("eee");
    test.propagateUp(4);
    test.array[0] = test.array[4];
    test.array[4] = null;
    test.size = 4;
    test.propagateDown(0);
    if(test.array[3].toString().equals("aaa")) {
      return true;
    }
    return false;
  }
  
  /**
   * This method tests the createNewTicket() by creating two tickets and checking if they are
   * added in the correct order.
   * @return true if the test passes, else false.
   */
  public static boolean testCreateNewTicket() {
    HelpDesk test = new HelpDesk(2);
    test.createNewTicket("aaa");
    test.createNewTicket("bbb");
    if(test.array[0].toString().equals("bbb")) {
      return true;
    }
    return false;
  }
  
  /**
   * This method tests the checkNextTicket() by creating three tickets and checking if they are
   * added in the correct order.
   * @return true if the test passes, else false.
   */
  public static boolean testCheckNextTicket() {
    HelpDesk test = new HelpDesk(3);
    test.createNewTicket("aaa");
    test.createNewTicket("bbb");
    test.createNewTicket("ccc");
    if(test.checkNextTicket().toString().equals("ccc")) {
      return true;
    }
    return false;
  }
  
  /**
   * This method tests the closeNextTicket() by creating  5 tickets and checking if they are in 
   * the correct order after removing the longest message.
   * @return true if the test passes, else false.
   */
  public static boolean testCloseNextTicket() {
    HelpDesk test = new HelpDesk(5);
    test.createNewTicket("aaa");
    test.createNewTicket("ccc");
    test.createNewTicket("ddd");
    test.createNewTicket("bbb");
    test.createNewTicket("eee");
    test.closeNextTicket();
    if(test.array[3].toString().equals("aaa")) {
      return true;
    }
    return false;
  }
  
  /**
   * This method tests the parentOf() method by giving it a known value and then checking if 
   * it returns the correct value.
   * @return true if the test passes, else false.
   */
  public static boolean testParentOf() {
    if(parentOf(4) == 1) {
      return true;
    }
    return false;
  }
  
  /**
   * This method tests the childOf() method by giving it a known value and then checking if 
   * it returns the correct value.
   * @return true if the test passes, else false.
   */
  public static boolean testChildOf() {
    if(leftChildOf(3) == 7 && rightChildOf(3) == 8) {
      return true;
    }
    return false;
  }
  
  /**
   * This method tests the swap() method by adding two tickets and then swapping them and checking 
   * if they are swapped correctly.
   * @return
   */
  public static boolean testSwapMethod() {
    HelpDesk test = new HelpDesk(5);
    test.createNewTicket("aaa");
    test.createNewTicket("ccc");
    test.swap(0, 1);
    if(test.array[0].toString().equals("aaa") && test.array[1].toString().equals("ccc")) {
      return true;
    }
    return false;
  }
  
  /**
   * The main method that calls all the tests and prints errors if any.
   * @param args
   */
  public static void main(String[] args) {
    if(testPropagateUp() == false) {
      System.out.println("FAILED: testPropagateUp()");
    }
    if(testPropagateDown() == false) {
      System.out.println("FAILED: testPropagateDown()");
    }
    if(testCreateNewTicket() == false) {
      System.out.println("FAILED: testCreateNewTicket()");
    }
    if(testCheckNextTicket() == false) {
      System.out.println("FAILED: testCheckNextTicket()");
    }
    if(testCloseNextTicket() == false) {
      System.out.println("FAILED: testCloseNextTicket()");
    }
    if(testParentOf() == false) {
      System.out.println("FAILED: testParentOf()");
    }
    if(testChildOf() == false) {
      System.out.println("FAILED: testChildOf()");
    }
    if(testSwapMethod() == false) {
      System.out.println("FAILED: testSwapMethod()");
    }
  }

}
