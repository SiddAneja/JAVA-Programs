/**
 * Filename:   TestHashTableDeb.java
 * Project:    p3
 * Authors:    Debra Deppeler (deppeler@cs.wisc.edu)
 * 
 * Semester:   Fall 2018
 * Course:     CS400
 * 
 * Due Date:   before 10pm on 10/29
 * Version:    1.0
 * 
 * Credits:    None so far
 * 
 */

import org.junit.After;
import java.io.FileNotFoundException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/** 
 * Test HashTable class implementation to ensure that required 
 * functionality works for all cases.
 */
public class BookHashTableTest {
  
    /**
     * Default name of books data file.
     */
    public static final String BOOKS = "books.csv";

    /**
     * Empty hash tables that can be used by tests.
     */
    static BookHashTable bookObject;
    
    /**
     * Stores all the books when parsed.
     */
    static ArrayList<Book> bookTable;

    /**
     * Initial capacity of the hash table.
     */
    static final int INIT_CAPACITY = 2;
    
    /**
     * The threshold of the hash table. It tells us when the table should be resized.
     */
    static final double LOAD_FACTOR_THRESHOLD = 0.49;
       
    /**
     * Seeded to make results repeatable (deterministic)
     */
    static Random RNG = new Random(0);

    /** Create a large array of keys and matching values for use in any test */
    @BeforeAll
    public static void beforeClass() throws Exception{
        bookTable = BookParser.parse(BOOKS);
    }
    
    /** Initialize empty hash table to be used in each test */
    @BeforeEach
    public void setUp() throws Exception {
         bookObject = new BookHashTable(INIT_CAPACITY,LOAD_FACTOR_THRESHOLD);
    }

    /** Not much to do, just make sure that variables are reset     */
    @AfterEach
    public void tearDown() throws Exception {
        bookObject = null;
    }

    /**
     * Inserts all the book that have been parsed into the hash table.
     * @param bookTable - Table that stores all the books.
     * @throws IllegalNullKeyException - Thrown if a key is null.
     * @throws DuplicateKeyException - if the key already exists in the hash table
     */
    private void insertMany(ArrayList<Book> bookTable) 
        throws IllegalNullKeyException, DuplicateKeyException {
        for (int i=0; i < bookTable.size(); i++ ) {
            bookObject.insert(bookTable.get(i).getKey(), bookTable.get(i));
        }
    }

    /** IMPLEMENTED AS EXAMPLE FOR YOU
     * Tests that a HashTable is empty upon initialization
     */
    @Test
    public void test000_collision_scheme() {
        if (bookObject == null)
        	fail("Gg");
    	int scheme = bookObject.getCollisionResolutionScheme();
        if (scheme < 1 || scheme > 9) 
            fail("collision resolution must be indicated with 1-9");
    }


    /** IMPLEMENTED AS EXAMPLE FOR YOU
     * Tests that a HashTable is empty upon initialization
     */
    @Test
    public void test000_IsEmpty() {
        //"size with 0 entries:"
        assertEquals(0, bookObject.numKeys());
    }

    /** IMPLEMENTED AS EXAMPLE FOR YOU
     * Tests that a HashTable is not empty after adding one (key,book) pair
     * @throws DuplicateKeyException 
     * @throws IllegalNullKeyException 
     */
    @Test
    public void test001_IsNotEmpty() throws IllegalNullKeyException, DuplicateKeyException {
    	bookObject.insert(bookTable.get(0).getKey(),bookTable.get(0));
        String expected = ""+1;
        //"size with one entry:"
        assertEquals(expected, ""+bookObject.numKeys());
    }
    
    /** IMPLEMENTED AS EXAMPLE FOR YOU 
    * Test if the hash table  will be resized after adding two (key,book) pairs
    * given the load factor is 0.49 and initial capacity to be 2.
    */
    
    @Test 
    public void test002_Resize() throws IllegalNullKeyException, DuplicateKeyException {
    	bookObject.insert(bookTable.get(0).getKey(),bookTable.get(0));
    	int cap1 = bookObject.getCapacity(); 
    	bookObject.insert(bookTable.get(1).getKey(),bookTable.get(1));
    	int cap2 = bookObject.getCapacity();
        //"size with one entry:"
        assertTrue(cap2 > cap1 & cap1 ==2);
    }
    
    /**
     * Adds duplicate key to the data structure and checks if it throws the correct exception.
     */
    @Test
    public void test003_insert_duplicate_key() {
      try {
        bookObject.insert(bookTable.get(0).getKey(), bookTable.get(0));
        bookObject.insert(bookTable.get(0).getKey(), bookTable.get(0));
        fail("Should throw duplicate key exception.");
      }catch(DuplicateKeyException e) {
        //Expected
      }catch(Exception e) {
        fail("unexpected exception");
      }
    }
    
    /**
     * Adds multiple keys to the data structure and then checks if numKeys() is correctly 
     * keeping track of the keys in the data structure.
     */
    @Test
    public void test004_numkeys_returns_correct_value() {
      try {
        bookObject.insert(bookTable.get(0).getKey(), bookTable.get(0));
        bookObject.insert(bookTable.get(1).getKey(), bookTable.get(1));
        bookObject.insert(bookTable.get(2).getKey(), bookTable.get(2));
        if(bookObject.numKeys() != 3) {
          fail("There should be 3 keys in the Object");
        }
      }catch(Exception e) {
        fail("unexpected exception");
      }
    }
    
    /**
     * Adds a key-value pair to the data structure and checks if teh get() method returns the
     * correct value(book) based on the key entered.
     */
    @Test
    public void test005_get_method_returns_correct_values() {
      try {
        bookObject.insert(bookTable.get(0).getKey(), bookTable.get(0));
        Book test = bookObject.get(bookTable.get(0).getKey());
        if(test.getKey() == bookTable.get(0).getKey()) {
          //Expected
        }else {
          fail("object should contain the book");
        }
      }catch(Exception e) {
        fail("unexcepted exception");
      }
    }
    
    /**
     * Tests whether the data structure implementation is able to handle a single insert and remove.
     */
    @Test
    public void test006_insert_one_remove_one() {
      try {
        bookObject.insert(bookTable.get(0).getKey(), bookTable.get(0));
        bookObject.remove(bookTable.get(0).getKey());
        Book test = bookObject.get(bookTable.get(0).getKey());
        fail("get() should throw KeyNotFoundException when called on a deleted key");
      }catch(KeyNotFoundException e) {
        //expected
      }catch(Exception e) {
        fail("unexpected exception");
      }
    }
    
    /**
     * Tests whether the data structure implementation is able to handle multiple inserts and removes.
     */
    @Test
    public void test007_insert_many_remove_many() {
      try {
        for(int i = 0; i < 20; i++) {
          bookObject.insert(bookTable.get(i).getKey(), bookTable.get(i));
        }
        for(int i = 0; i < 20; i++) {
          bookObject.remove(bookTable.get(i).getKey());
        }
        if(bookObject.numKeys() != 0) {
          fail("object should be empty");
        }
      }catch(Exception e) {
        fail("unexpected exception");
      }
    }
    
    /**
     * This test adds a value to the data structure and then removes it. Then, it re-adds the value
     * and checks if the implementation can handle it.
     */
    @Test
    public void test008_reinserting_after_removing() {
      try {
        bookObject.insert(bookTable.get(0).getKey(), bookTable.get(0));
        bookObject.insert(bookTable.get(1).getKey(), bookTable.get(1));
        bookObject.insert(bookTable.get(2).getKey(), bookTable.get(2));
        bookObject.remove(bookTable.get(1).getKey());
        bookObject.insert(bookTable.get(1).getKey(), bookTable.get(1));
        Book test = bookObject.get(bookTable.get(1).getKey());
        if(test != null) {
          //expected
        }
        else {
          fail("book should not be null");
        }
      }catch(KeyNotFoundException e) {
        fail("should not throw KeyNotFoundException");
      }catch(Exception e) {
        fail("unexpected exception");
      }
    }
    
    /**
     * Tests if the data structure implementation is able to handle all the book from the file
     * being added to it.
     */
    @Test
    public void test009_insert_many_works() {
      try {
        this.insertMany(bookTable);
        for (int i=0; i < bookTable.size(); i++ ) {
          Book test = bookObject.get(bookTable.get(i).getKey());
          if(test.getKey() != bookTable.get(i).getKey()) {
            fail("the key values do not match");
          }
      }
      }catch(Exception e) {
        fail("unexpected exception");
      }
    }
    
    /**
     * Tests if the remove method returns false for a value that does not exist in the data structure.
     */
    @Test
    public void test010_removing_key_that_does_not_exist() {
      try {
        boolean test10 = bookObject.remove(bookTable.get(0).getKey());
        if(test10 != false) {
          fail("remove should return false for key that does not exist");
        }
      }catch(Exception e) {
        fail("unexpected exception");
      }
    }
    
    /**
     * Tests if the insert method throws the correct exception for a null key being inserted.
     */
    @Test
    public void test011_null_key_insert() {
      try {
        bookObject.insert(null, null);
        fail("insert should throw exception for null key");
      }catch(IllegalNullKeyException e) {
        //Expected
      }catch(Exception e) {
        fail("unexpected exception");
      }
    }
    
}
