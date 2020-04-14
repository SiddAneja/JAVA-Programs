///////////////////////////////////////////////////////////////////////////////
//Title: BookHashTable
//Semester: Fall 2020
//
//Author: Siddharth Aneja
//Email: saneja@wisc.edu
//CS Login: aneja@cs.wisc.edu
//Lecturer's Name: Deppeler
//Lecture Number: 002
//Description: The class that provides the implementation of the hash table using arrays and linked lists
//
////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;


/* The hash table used in this program is an array of linked lists.
 * It follows the scheme of chained buckets. If there is a collision at an index of the array
 * the new data is just added to the linked list bucket at that index.
 * 
 * Hashing Algorithm:
 * The JAVA hashCode() method is used on the <String K> to return an int value. This value is then 
 * modded with the tableSize / capacity (integer division) to get a value within our size range.
 */

/** HashTable implementation that uses:
 * @param <K> unique comparable identifier for each <K,V> pair, may not be null
 * @param <V> associated value with a key, value may be null
 */
public class BookHashTable implements HashTableADT<String, Book> {
  
    /**
     * The node that stores the data in the linked list bucket at a given index of the hash table.
     * @author Siddharth
     *
     * @param <String>
     * @param <Book>
     */
    class BNode<String, Book>{
      
      /**
       * Stores the key value that we want to insert into the hash table.
       */
      String key;
      
      /**
       * Reference to the book with the specific key.
       */
      Book book;
      
      /**
       * Reference to the next node in the list.
       */
      BNode next;
      
      /**
       * Constructor of the node
       * @param key - key to store in the node
       * @param book - value associated with the key
       */
      BNode(String key, Book book){
        this.key = key;
        this.book = book;
      }
    }

    /** The initial capacity that is used if none is specified user */
    static final int DEFAULT_CAPACITY = 101;
    
    /** The load factor that is used if none is specified by user */
    static final double DEFAULT_LOAD_FACTOR_THRESHOLD = 0.75;

    /**
     * REQUIRED default no-arg constructor
     * Uses default capacity and sets load factor threshold 
     * for the newly created hash table.
     */
    public BookHashTable() {
        this(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR_THRESHOLD);
    }
    
    /**
     * Hash table array (not yet initialized).
     */
    private BNode[] hashTable;
    
    /**
     * Array that is used to resize() and reHash() the current hash table.
     */
    private BNode[] resizeTable;
    
    /**
     * Stores the capacity of the hash table.
     */
    private int capacity;
    
    /**
     * Stores the load factor threshold of the hash table.
     */
    private double loadFactorThreshold;
    
    /**
     * Stores the number of keys in the hash table.
     */
    private int numKeys;
    
    /**
     * Creates an empty hash table with the specified capacity 
     * and load factor.
     * @param initialCapacity number of elements table should hold at start.
     * @param loadFactorThreshold the ratio of items/capacity that causes table to resize and rehash
     */
    public BookHashTable(int initialCapacity, double loadFactorThreshold) {
        hashTable = new BNode[initialCapacity];
        this.capacity = initialCapacity;
        this.loadFactorThreshold = loadFactorThreshold;
        this.numKeys = 0;
    }
    
    /**
     * The hash function for getting an index value to store data in the hash table.
     * @param key - the key to store
     * @return index of where to store the data in the hash table (array)
     */
    private int hashFunction(String key) {
      //uses JAVA hashCode() to get an int value
      int index = key.hashCode();
      //the value is then modded with the table size to get a value that is in our range
      index = index % capacity;
      //Sometimes after the % (remainder) we might get a negative value, so we added the table size
      //to the remainder to get the mod
      if(index < 0) {
        index += capacity;
      }
      return index;
    }    
    
    /**
     * Method that is called in the insert method to check if the table is almost full and 
     * if it is, then resize it and call the rehash method.
     * @throws DuplicateKeyException - if the same key already exists in the table
     */
    private void resize() throws DuplicateKeyException {
      //calculate the load factor of the current hash table
      double loadFactor = (double)numKeys / capacity;
      //if the load factor is equal to or greater than the threshold, resize
      if(loadFactor >= loadFactorThreshold) {
        //Double the capacity and add 1
        this.capacity = (2*capacity) + 1;
        //create a new table with the new capacity
        resizeTable = new BNode[(2*capacity) + 1];
        //rehash all the current data to the new table
        rehash();
        //set the current hash table to the new one
        hashTable = resizeTable;
      }
    }
    
    /**
     * This method is called by the resize method to store all the previous data into the hash table
     * of bigger size.
     * @throws DuplicateKeyException
     */
    private void rehash() throws DuplicateKeyException {
      //iterate through the entire hash table
      for(int i = 0; i < hashTable.length; i++) {
        //find all the indexes where the data is not null
        if(hashTable[i] != null) {
          //Stores the node at the index of the hash table.
          BNode node = hashTable[i];
          //iterates through the linked list at the index of the array and rehashes all the nodes
          while(true){
            //get the new index
            int index = hashFunction((String)node.key);
            //checks if the index in the new table is already taken or not. If it is, attached it
            // to the end of the list at that index
            if(resizeTable[index] != null) {
              insertHelper((String)node.key,(Book) node.book, resizeTable[index]);
            }
            //if it is empty then create a new node at the index
            else {
              resizeTable[index] = new BNode((String)node.key, (Book)node.book);
            }
            //checks if the next node at the index of the old hash table exists. if it does, make 
            //it the node
            if(node.next != null) {
              node = node.next;
            }else {
              break;
            }
          }
        }
      }
    }
    
    /**
     *Add the key,value pair to the data structure and increase the number of keys.
     *If key is null, throw IllegalNullKeyException;
     *If key is already in data structure, throw DuplicateKeyException();
     *
     *@param key - the key to insert into the data structure.
     *@param value - the book associated with the key.
     */
    @Override
    public void insert(String key, Book value)
        throws IllegalNullKeyException, DuplicateKeyException {
      //if the key is null, throw an exception
      if(key == null) {
        throw new IllegalNullKeyException();
      }
      //call resize on the current hash table
      resize();
      //get the index of where to store the key using the hash function
      int index = hashFunction(key);
      //if the index is not null, then call the helper method to add it to the end of the linked list
      if(hashTable[index] != null) {
        insertHelper(key, value, hashTable[index]);
      }
      //if the index is null, create a new node at the index
      else {
        hashTable[index] = new BNode(key, value);
      }
      //increment number of keys
      numKeys++;
    }
    
    /**
     * Helper insert that iterates through the linked list at an index of the hash table array.
     * It checks if the key being already added exist and if it does, throws an exception. If not, 
     * it added the key (new node) to the end of the list.
     * @param key - key being added to the data structure
     * @param value - the book associated with the key
     * @param node - the node at the particular index of the array
     * @throws DuplicateKeyException - thrown when the key being added already exists
     */
    private void insertHelper(String key, Book value, BNode node) throws DuplicateKeyException {
      // if the node's key equals the key being added throw exception
      if(((String)node.key).equals(key)) {
        throw new DuplicateKeyException();
      }
      //if the next reference from the node is null, added the key to the node's next
      if(node.next == null) {
        node.next = new BNode(key, value);
        return;
      }
      //if it's not null, set node equl to the next reference and call insert helper on it
      else {
        node = node.next;
        insertHelper(key, value, node);
      }
    }

    /**
     * If key is found, remove the key,value pair from the data structure decrease number of keys.
     * Return true
     * If key is null, throw IllegalNullKeyException
     * If key is not found, return false
     * 
     * @param key - key to be removed from data structure
     * @throws IllegalNullKeyException - if the key to be removed is null
     */
    @Override
    public boolean remove(String key) throws IllegalNullKeyException {
      //Checks if the key is null
      if(key == null) {
        throw new IllegalNullKeyException();
      }
      //get the index using the hash function
      int index = hashFunction(key);
      //if the array has no value at the index returned by the hash function, the value does not exist.
      //return false.
      if(hashTable[index] == null) {
        return false;
      }
      //if the array at that index is not null
      else {
        //store the node at the index of the array in node
        BNode node = hashTable[index];
        //check if the first node's key is equal to the key we want to remove.
        if(((String)node.key).equals(key)) {
          //if the keys are a match. set the first node at the index to the next node in the list
          hashTable[index] = node.next;
          //reduce number of keys
          numKeys--;
          //return true
          return true;
        }
        //if the key is not equal to the key of the first node, and the next value of the node
        // is not null, enter
        else if(node.next != null) {
          //loop till the end of the linked list
          while(node != null) {
            //if the next reference of the node has its key equal to the key being removed, enter
            if(node.next != null && ((String)node.next.key).equals(key)) {
              //checks if the value of the node.next's next exists. if it does, set node.next equal
              //to node.next's next  reference (thereby deleting node.next)
              if(node.next.next != null) {
                node.next = node.next.next;
              }
              //else if it the end of the list, set node.next to null
              else {
                node.next = null;
              }
              //reduce number of keys and return true
              numKeys--;
              return true;
            }
            // if the next reference of the node does not have it's key equal to the key we want to
            //remove, then set node to the next reference.
            else {
              node = node.next;
            }
          }
        }
        //if we don't find the key at the linked list at the particular array, return false
        return false;
      }
    }

    /**
     * Returns the value associated with the specified key. 
     * Does not remove key or decrease number of keys
     * If key is null, throw IllegalNullKeyException 
     * If key is not found, throw KeyNotFoundException().
     * 
     * @param key - the key that is linked to the value we want
     * @throws IllegalNullKeyException - if the key is null
     * @throws KeyNotFoundException - if the key does not exist in the data structure
     */
    @Override
    public Book get(String key) throws IllegalNullKeyException, KeyNotFoundException {
      //checks if the key is null
      if(key == null) {
        throw new IllegalNullKeyException();
      }
      //use the hash function to get the index of where the key should be
      int index = hashFunction(key);
      //if that index is null, then throw exception
      if(hashTable[index] == null) {
        throw new KeyNotFoundException();
      }
      //if it is not null, iterate through the linked list at the index to find the key
      else {
        BNode node = hashTable[index];
        //iterate through the list
        while(node != null) {
          //if the key is found, return the book associated with it
          if(((String)node.key).equals(key)) {
            return (Book)node.book;
          }
          else {
            node = node.next;
          }
        }
        //if we reach the end of the list and the key is not found, throw exception
        throw new KeyNotFoundException();
      }
    }

    /**
     * Returns the number of key,value pairs in the data structure.
     */
    @Override
    public int numKeys() {
      return numKeys;
    }

    /**
     * Returns the load factor for this hash table that determines when to increase the capacity 
     * of this hash table.
     */
    @Override
    public double getLoadFactorThreshold() {
      return loadFactorThreshold;
    }

    /**
     * Capacity is the size of the hash table array.
     * This method returns the current capacity.
     */
    @Override
    public int getCapacity() {
      return capacity;
    }

    /**
     * Returns the collision resolution scheme used for this hash table.
     */
    @Override
    public int getCollisionResolutionScheme() {
      //Array with linked list
      return 5;
    }

}