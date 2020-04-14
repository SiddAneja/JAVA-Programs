
/**
 * This class implements the Comparable<> interface and provides code for the ticket that will
 * be stored in the max-heap.
 * @author Siddharth
 *
 */
public class SupportTicket implements Comparable<SupportTicket>{

  /**
   * Private field that stores the help message provided by the user for the ticket.
   */
  private String message;
  
  /**
   * The constructor method that creates a new SupportTicket that takes a non-null message as a
   * parameter.
   * @param message provided by the user for the SupportTicket.
   */
  public SupportTicket(String message) {
    if(message == null) {
      throw new NullPointerException ("Error: Message cannot be null!");
    }
    this.message = message;
  }
  
  /**
   * This method overrides the toString() method and returns the message if it is not null.
   */
  @Override
  public String toString() {
    if(message == null) {
      throw new NullPointerException("Error: No message!");
    }
    return message;
  }
  
  /**
   * This method overrides the compareTo() method in the comparable interface.
   * It compares the this message to the message of arg0 and returns a negative, zero or positive 
   * value depending on whether this message is smaller, equal to or greater than the arg0
   * message.
   * @param arg0 - The SupportTicket to compare to.
   */
  @Override
  public int compareTo(SupportTicket arg0) {
    int returnValue = this.message.length() - arg0.message.length();
    if(returnValue == 0) {
      returnValue = this.message.compareTo(arg0.message);
    }
    return returnValue;
  }
  
}
