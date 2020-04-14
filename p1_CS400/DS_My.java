
/**
 * Tis class is the LinkedList implementation of my data Structure required for p1 CS400.
 * 
 * @author Siddharth
 *
 */
public class DS_My implements DataStructureADT {

  /**
   * Inner class which defines the Node in the linked list which stores the key-value pairs.
   * 
   * @author Siddharth
   *
   * @param <K> - Generic data type for keys
   * @param <V> - Generic data type for values
   */
  private class KeyNode<K, V> {
    /**
     * Private field that stores the key for this node.
     */
    private K key;

    /**
     * Private field that stores the value associated with the key in this node.
     */
    private V value;

    /**
     * Private field that stores the reference to the next node in the list.
     */
    private KeyNode next;

    /**
     * Constructor of the KeyNode class.
     * 
     * @param k - key that is stored in the node (cannot be null)
     * @param v - value associated with the key
     */
    private KeyNode(K k, V v) {
      this.key = k;
      this.value = v;
    }

    /**
     * Overloaded constructor
     * 
     * @param k - key that is stored in the node (cannot be null)
     * @param v - value associated with the key
     * @param n - reference to the next node
     */
    private KeyNode(K k, V v, KeyNode n) {
      this.key = k;
      this.value = v;
      this.next = n;
    }

    /**
     * Getter method for the next reference in the list.
     * 
     * @return next KeyNode in the list
     */
    private KeyNode getNext() {
      return this.next;
    }

    /**
     * Setter method for the next reference in the list.
     * 
     * @param n - next KeyNode
     */
    private void setNext(KeyNode n) {
      this.next = n;
    }

  }

  /**
   * Private field that stores the first node (head) of the LinkedList
   */
  private KeyNode head;

  /**
   * Private field to store the size of the list.
   */
  private int size;

  /**
   * Constructor that creates an empty list of size 0.
   */
  public DS_My() {
    this.size = 0;
    this.head = null;
  }

  /**
   * Checks if the List is empty.
   * 
   * @return True is list is empty, False if not.
   */
  private boolean isEmpty() {
    if (this.size == 0 && this.head == null) {
      return true;
    }
    return false;
  }


  /**
   * Add the key,value pair to the data structure and increases size. If key is null, throws
   * IllegalArgumentException("null key"); If key is already in data structure, throws
   * RuntimeException("duplicate key"); Can accept and insert null values.
   * 
   * @param k - Key to be inserted into the list.
   * @param v - Values to be stored (associated with k).
   */
  @Override
  public void insert(Comparable k, Object v) throws IllegalArgumentException, RuntimeException {
    // Checks if key is null
    if (k == null) {
      throw new IllegalArgumentException("ERROR: Null key!");
    }
    // Checks if List is null, if true - adds new node to head
    if (this.isEmpty()) {
      this.head = new KeyNode(k, v);
      size++;
    } else { // if list already has values, enters this branch
      KeyNode runner = this.head; // variable to store the current node in the list
      KeyNode tempBefore = null; // Stores the node before the current node
      // if the List only has one node, enter this branch
      if (runner.getNext() == null) {
        // if the key is less than the key at head, then make this key the new head
        if (k.compareTo(runner.key) < 0) {
          this.head = new KeyNode(k, v, runner);
          size++;
        }
        // if the key is greater than the key at the head, then add this key after the head
        else if (k.compareTo(runner.key) > 0) {
          runner.setNext(new KeyNode(k, v));
          size++;
        }
        // if equal, throw exception
        else {
          throw new RuntimeException("Error: Duplicate key!");
        }
      }
      // if the list has multiple nodes, enter this branch
      else {
        // loop while the runner is not null(i.e till it reaches the end)
        while (runner != null) {
          // throw exception if key is equal to runner's key
          if (k.compareTo(runner.key) == 0) {
            throw new RuntimeException("Error: Duplicate key!");
          }
          // if key is less than runner's key, insert it before the runner
          else if (k.compareTo(runner.key) < 0) {
            tempBefore.setNext(new KeyNode(k, v, runner));
            size++;
            return;
          }
          // if key is greater than the runner's key, iterate to the next node to find the value it
          // is less than
          else {
            tempBefore = runner;
            runner = runner.getNext();
          }
        }
        // if the list reaches the end, add new node to the end
        tempBefore.setNext(new KeyNode(k, v));
      }
    }
  }

  /**
   * If key is found, Removes the key from the data structure and decreases size. If key is null,
   * throws IllegalArgumentException("null key") without decreasing size
   * 
   * @return True if key is found, else false.
   */
  @Override
  public boolean remove(Comparable k) throws IllegalArgumentException {
    // Checks if key is null
    if (k == null) {
      throw new IllegalArgumentException("ERROR: Null key!");
    }
    // variable to store the current node in the list
    KeyNode runner = this.head;
    // variable to store the node before the current node
    KeyNode tempBefore = null;
    // Loops while the key is not equal to the key in the nodes
    while (k.compareTo(runner.key) != 0) {
      // if the next reference is not null enter branch
      if (runner.getNext() != null) {
        // Set tempbefore to current node and the current node to the next one
        tempBefore = runner;
        runner = runner.getNext();
      }
      // if the next reference is null, key does not exist, return false
      else {
        return false;
      }
    }
    // if key equals to a key stored in a node, check if it is at the beginning of the list
    // if at start change the head
    if (tempBefore == null) {
      this.head = runner.getNext();
    }
    // if in middle of list, set the next of the node behind the current node to the node after
    // thereby, deleting this node
    else {
      runner = runner.getNext();
      tempBefore.setNext(runner);
    }
    // decrease size and return true
    size--;
    return true;

  }

  /**
   * Checks if the key is stored in the list. Does not change the size.
   * 
   * @return True if the key exists in the list, else false.
   */
  @Override
  public boolean contains(Comparable k) {
    // if list is empty, returns false
    if (this.head == null) {
      return false;
    }
    // Variable to stores the current node in the list
    KeyNode runner = this.head;
    // Loops while the key is not equal to the key in any node
    while (k.compareTo(runner.key) != 0) {
      // if the next reference is not null, set the current node to the next one
      if (runner.getNext() != null) {
        runner = runner.getNext();
      }
      // if it is null (end of list) then return false as the key does not exist
      else {
        return false;
      }
    }
    // if breaks out of loop, return true as key exists.
    return true;
  }

  /**
   * Returns the value associated with the specified key. get - does not remove key or decrease
   * size.
   * 
   * @return value associated with the key.
   */
  @Override
  public Object get(Comparable k) throws IllegalArgumentException {
    // Checks if key is null
    if (k == null) {
      throw new IllegalArgumentException("ERROR: Null key!");
    }
    // Variable to stores the current node in the list
    KeyNode runner = this.head;
    // Loops while the key is not equal to the key in any node
    while (k.compareTo(runner.key) != 0) {
      // if the next reference is not null, set the current node to the next one
      if (runner.getNext() != null) {
        runner = runner.getNext();
      }
      // if it is null (end of list) then return null as the key does not exist.
      else {
        return null;// TODO
      }
    }
    // if breaks out of loop, return the value
    return runner.value;
  }

  /**
   * Checks and returns the number of elements in the list.
   * 
   * @return Number of elements in the list
   */
  @Override
  public int size() {
    // Set size to 0
    int size = 0;
    if (head != null) {
      // Set current node to the head and increment size
      KeyNode runner = this.head;
      size++;
      runner = runner.getNext();
      // keep moving to the next node and incrementing size till the end is reached.
      while (runner != null) {
        size++;
        if (runner.getNext() != null) {
          runner = runner.getNext();
        } else {
          break;
        }
      }
    }
    return size;
  }



}
