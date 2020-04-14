
/**
 * This class implements the HelpDeskInterface to provide methods for the functioning of the 
 * max-heap that stores SupportTickets.
 * @author Siddharth
 *
 */
public class HelpDesk implements HelpDeskInterface{
  
  /**
   * This protected SupporTicket array is the zero indexed Max-heap.
   */
  protected SupportTicket[] array; 
  
  /**
   * This protected field stores the size of the max-heap.
   */
  protected int size;
  
  /**
   * Private field that stores the maximum capacity of the heap.
   */
  private final int MAX_CAPACITY;
  
  /**
   * The constructor that creates the array with a capacity provided as the parameter.
   * @param capacity
   */
  public HelpDesk(int capacity) {
    array = new SupportTicket[capacity];
    MAX_CAPACITY = capacity;
    size = 0;
  }

  /**
   * Creates and adds a new SupportTicket to this HelpDesk.
   * Overrides the method implemented in the HelpDeskInterface.
   * @param message names the client and describes their need for support.
   * @throws NullPointerException when the String message argument is null.
   * @throws IndexOutOfBoundsException when called on HelpDesk with a full array
   */
  @Override
  public void createNewTicket(String message) {
    if(message == null) {
      throw new NullPointerException ("Error: message is null!");
    }
    if(size == MAX_CAPACITY) {
      throw new IndexOutOfBoundsException ("Error: Queue is full!");
    }
    //creates a new SupportTicket and adds it to the array
    SupportTicket newTicket = new SupportTicket(message);
    array[size] = newTicket;
    //calls the propogateUp method from the index to which the new ticket was added
    propagateUp(size);
    size++;
  }

  /**
   * Returns the message within this HelpDesk that has the highest priority.
   * Overrides the method implemented in the HelpDeskInterface.
   * This method does not change the state of this HelpDesk.
   * @return the message within the highest priority SupportTicket.
   * @throws IllegalStateException when called on a HelpDesk with zero SupportTickets.
   */
  @Override
  public String checkNextTicket() {
    if(size == 0) {
      throw new IllegalStateException ("Error: Queue is empty!");
    }
    return array[0].toString();
  }

  /**
   * Returns and removes the message within this HelpDesk that has the highest priority.
   * Overrides the method implemented in the HelpDeskInterface.
   * @return the message within the highest priority SupportTicket (prior to its removal).
   * @throws IllegalStateException when called on a HelpDesk with zero SupportTickets.
   */
  @Override
  public String closeNextTicket() {
    if(size == 0) {
      throw new IllegalStateException ("Error: Queue is empty!");
    }
    //Replaces the first element in the array with the last one and changes the last one to null
    //then returns the first element.
    SupportTicket returnTicket = array[0];
    array[0] = array[size - 1];
    array[size - 1] = null;
    size--;
    //calls propagateDown method on the root
    propagateDown(0);
    return returnTicket.toString();
  }
  
  /**
   * Given an index into the heap array, this method returns that index's parent index.
   * @param index - To find the parent of this index.
   * @return The parent SupportTicket of the index provided.
   */
  protected static int parentOf(int index) {
    return ((index - 1)/2);
  }
  
  /**
   * Given an index into the heap array, this method returns that index's left child index.
   * @param index - To find the left child of this index.
   * @return The left child of the index provided.
   */
  protected static int leftChildOf(int index) {
    return (2 * index) + 1;
  }
  
  /**
   * Given an index into the heap array, this method returns that index's right child index.
   * @param index - To find the right Child of this index.
   * @return The right child of the index provided.
   */
  protected static int rightChildOf(int index) {
    return (2 * index) + 2;
  }
  
  /**
   * Given two indexes into the heap array, this method swaps the SupportTickets at those indexes.
   * @param indexA - The first index of the SupportTicket to be swapped.
   * @param indexB - The other index of the SupportTicket which is swapped.
   */
  protected void swap(int indexA, int indexB) {
    SupportTicket tempA = array[indexA];
    SupportTicket tempB = array[indexB];
    array[indexA] = tempB;
    array[indexB] = tempA;
  }
  
  /**
   *  Given an index into the heap array, this method recursively swaps any SupportTickets 
   *  necessary to enforce the heap's order property between this index and the heap's root.
   * @param index - where to start the propagation method.
   */
  protected void propagateUp(int index) {
    //Keeps looping while the index is greater than 0
    while(index > 0) {
      //find the parent index of the index provided
      int parentIndex = parentOf(index);
      //if parentIndex is out of the index range, throw an Exception
      if(parentIndex < 0 || parentIndex > MAX_CAPACITY) {
        throw new IndexOutOfBoundsException ("Error: Invalid index!");
      }
      //if the message at index is less than the parent's message, break out of the loop
      if(array[index].compareTo(array[parentIndex]) <= 0) {
        break;
      }
      //if not, swap the parent and child
      else {
        swap(index, parentIndex);
        index = parentIndex;
      }
    }
  }
  
  /**
   * Given an index into the heap array, this method recursively swaps any SupportTickets 
   * necessary to enforce the heap's order property between this index and it's children.
   * @param index - where to start the propagation method.
   */
  protected void propagateDown(int index) {
    //find the leftChild of the index
    int childIndex = leftChildOf(index);
    //if the childIndex is out of range, throw Exception
    if(childIndex < 0 || childIndex > MAX_CAPACITY) {
      throw new IndexOutOfBoundsException ("Error: Invalid index!");
    }
    //Find the value of the array at index 
    SupportTicket value = array[index];
    //Keep looping while childIndex is less than the size
    while(childIndex < size) {
      //set maxValue to the value at index
      SupportTicket maxValue = value;
      //set maxIndex to -1
      int maxIndex = -1;
      //Create a loop that checks both the left and right child of the index
      for(int i = 0; i < 2 && i + childIndex < size; i++) {
        //find the child which has a message greater than the message at index
        if(array[i + childIndex].compareTo(maxValue) > 0) {
          //if found, set max value equal to the value of the child
          maxValue = array[i + childIndex];
          //set max index to the child's index
          maxIndex = i + childIndex;
        }
      }
      //if after checking the children, the value and max value are equal break out of the loop
      if(maxValue.compareTo(value) == 0) {
        break;
      }
      //if not, swap the index and the child 
      else {
        swap(index, maxIndex);
        index = maxIndex;
        childIndex = leftChildOf(index);
      }
    }
  }
  
}
