///////////////////////////////////////////////////////////////////////////////
//Title: BALSTTest
//Semester: Fall 2020
//
//Author: Siddharth Aneja
//Email: saneja@wisc.edu
//CS Login: aneja@cs.wisc.edu
//Lecturer's Name: Deppeler
//Lecture Number: 002
//Description: JUnit Test class for a Red-Black Tree.
//
////////////////////////////////////////////////////////////////////////////

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;

//@SuppressWarnings("rawtypes")
public class BALSTTest {

    BALST<String,String> balst1;	
    BALST<Integer,String> balst2;

    /**
     * @throws java.lang.Exception
     */
    @BeforeEach
    void setUp() throws Exception {
        balst1 = createInstance();
	balst2 = createInstance2();
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterEach
    void tearDown() throws Exception {
        balst1 = null;
	balst2 = null;
    }

    protected BALST<String, String> createInstance() {
        return new BALST<String,String>();
    }

    protected BALST<Integer,String> createInstance2() {
        return new BALST<Integer,String>();
    }

    /** 
     * Insert three values in sorted order and then check 
     * the root, left, and right keys to see if rebalancing 
     * occurred.
     */
    @Test
    void testBALST_001_insert_sorted_order_simple() {
        try {
            balst2.insert(10, "10");
            if (!balst2.getKeyAtRoot().equals(10)) 
                fail("avl insert at root does not work");
            
            balst2.insert(20, "20");
            if (!balst2.getKeyOfRightChildOf(10).equals(20)) 
                fail("avl insert to right child of root does not work");
            
            balst2.insert(30, "30");
            Integer k = balst2.getKeyAtRoot();
            if (!k.equals(20)) 
                fail("avl rotate does not work");
            
            // IF rebalancing is working,
            // the tree should have 20 at the root
            // and 10 as its left child and 30 as its right child

            Assert.assertEquals(balst2.getKeyAtRoot(), new Integer(20));
            Assert.assertEquals(balst2.getKeyOfLeftChildOf(20),new Integer(10));
            Assert.assertEquals(balst2.getKeyOfRightChildOf(20),new Integer(30));
            
        } catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception AVL 000: "+e.getMessage() );
        }
    }

    /** 
     * Insert three values in reverse sorted order and then check 
     * the root, left, and right keys to see if rebalancing 
     * occurred in the other direction.
     */
    @Test
    void testBALST_002_insert_reversed_sorted_order_simple() {
      try {
        balst2.insert(30,"30");
        if (!balst2.getKeyAtRoot().equals(30)) 
          fail("avl insert at root does not work");
      
        balst2.insert(20, "20");
        if (!balst2.getKeyOfLeftChildOf(30).equals(20)) 
            fail("avl insert to left child of root does not work");
        
        balst2.insert(10, "10");
        Integer k = balst2.getKeyAtRoot();
        if (!k.equals(20)) 
            fail("avl rotate does not work");
        
        // IF rebalancing is working,
        // the tree should have 20 at the root
        // and 10 as its left child and 30 as its right child
        
        Assert.assertEquals(balst2.getKeyAtRoot(), new Integer(20));
        Assert.assertEquals(balst2.getKeyOfLeftChildOf(20),new Integer(10));
        Assert.assertEquals(balst2.getKeyOfRightChildOf(20),new Integer(30));
      } catch (Exception e) {
        e.printStackTrace();
        fail( "Unexpected exception AVL 000: "+e.getMessage() );
      }
    }

    /** 
     * Insert three values so that a right-left rotation is
     * needed to fix the balance.
     * 
     * Example: 10-30-20
     * 
     * Then check the root, left, and right keys to see if rebalancing 
     * occurred in the other direction.
     */
    @Test
    void testBALST_003_insert_smallest_largest_middle_order_simple() {
      try {
        balst2.insert(10,"10");
        if (!balst2.getKeyAtRoot().equals(10)) 
          fail("avl insert at root does not work");
      
        balst2.insert(30, "30");
        if (!balst2.getKeyOfRightChildOf(10).equals(30)) 
            fail("avl insert to right child of root does not work");
        
        balst2.insert(20, "20");
        Integer k = balst2.getKeyAtRoot();
        if (!k.equals(20)) 
            fail("avl rotate does not work " + k);
        
        // IF rebalancing is working,
        // the tree should have 20 at the root
        // and 10 as its left child and 30 as its right child
        
        Assert.assertEquals(balst2.getKeyAtRoot(), new Integer(20));
        Assert.assertEquals(balst2.getKeyOfLeftChildOf(20),new Integer(10));
        Assert.assertEquals(balst2.getKeyOfRightChildOf(20),new Integer(30));
      } catch (Exception e) {
        e.printStackTrace();
        fail( "Unexpected exception AVL 000: "+e.getMessage() );
      }
    }

    /** 
     * Insert three values so that a left-right rotation is
     * needed to fix the balance.
     * 
     * Example: 30-10-20
     * 
     * Then check the root, left, and right keys to see if rebalancing 
     * occurred in the other direction.
     */
    @Test
    void testBALST_004_insert_largest_smallest_middle_order_simple() {
      try {
        balst2.insert(30,"30");
        if (!balst2.getKeyAtRoot().equals(30)) 
          fail("avl insert at root does not work");
      
        balst2.insert(10, "10");
        if (!balst2.getKeyOfLeftChildOf(30).equals(10)) 
            fail("avl insert to left child of root does not work");
        
        balst2.insert(20, "20");
        Integer k = balst2.getKeyAtRoot();
        if (!k.equals(20)) 
            fail("avl rotate does not work " + k);
        
        // IF rebalancing is working,
        // the tree should have 20 at the root
        // and 10 as its left child and 30 as its right child
        
        Assert.assertEquals(balst2.getKeyAtRoot(), new Integer(20));
        Assert.assertEquals(balst2.getKeyOfLeftChildOf(20),new Integer(10));
        Assert.assertEquals(balst2.getKeyOfRightChildOf(20),new Integer(30));
      } catch (Exception e) {
        e.printStackTrace();
        fail( "Unexpected exception AVL 000: "+e.getMessage() );
      }
    }
    
    /**
     * Insert a value at the root of the tree and then remove it.
     * After its removed the root should be null and the tree should be empty.
     */
    @Test
    void testBALST_005_insert_root_and_remove_to_get_null_tree() {
      try {
        balst2.insert(10,"10");
        balst2.remove(10);
        if(balst2.numKeys() != 0) {
          fail("Tree should be empty");
        }
        if(balst2.contains(10)){
          fail("tree should not contain 10");
        }
      }catch(Exception e) {
        e.printStackTrace();
        fail( "Unexpected exception AVL 000: "+e.getMessage() );
      }
    }
    
    /**
     * Insert values into a Red-Black Tree and then use the remove method to 
     * delete a node with one child. Then check if it was correctly replaced and removed 
     * from the tree.
     */
    @Test
    void testBALST_006_removing_a_node_with_one_child() {
      try {
        balst2.insert(20, "30");
        balst2.insert(10, "20");
        balst2.insert(30, "10");
        balst2.insert(5, "5");
        balst2.remove(10);
        if( balst2.numKeys() != 3) {
          fail("tree should be size 3");
        }
        if(balst2.contains(10)){
          fail("tree should not contain 10 after removal");
        }
        if(!balst2.getKeyOfLeftChildOf(20).equals(5)) {
          fail("The left child of root should be 5");
        }
      }catch(Exception e) {
        e.printStackTrace();
        fail( "Unexpected exception AVL 000: "+e.getMessage() );
      }
    }
    
    /**
     * Insert values into a Red-Black Tree and then use the remove method to 
     * delete a node with two children. Then check if it was correctly replaced 
     * by the in-order predecessor and removed from the tree.
     */
    @Test
    void testBALST_007_adding_nultiple_values_and_removing_node_with_two_children() {
      try {
        balst2.insert(7, "7");
        balst2.insert(14, "14");
        balst2.insert(18, "18");
        balst2.insert(23, "23");
        balst2.insert(1, "1");
        balst2.insert(11, "11");
        balst2.insert(20, "20");
        if(balst2.getKeyAtRoot() != 14) {
          fail("root should be 14 ");
        }
        if(balst2.getKeyOfLeftChildOf(14) != 7) {
          fail("incorrect left child of root");
        }
        if(balst2.getKeyOfRightChildOf(14) != 20) {
          fail("incorrect right child of root ");
        }
        if(balst2.getKeyOfRightChildOf(7) != 11) {
          fail("incorrect right child of 7");
        }
        if(balst2.getKeyOfLeftChildOf(7) != 1) {
          fail("incorrect left child of 7");
        }
        if(balst2.getKeyOfRightChildOf(20) != 23) {
          fail("incorrect right child of 20");
        }
        if(balst2.getKeyOfLeftChildOf(20) != 18) {
          fail("incorrect left child of 20");
        }
        
        if(!balst2.contains(14)) {
          fail("Should contain 14");
        }
        if(!balst2.contains(7)) {
          fail("Should contain 7");
        }
        if(!balst2.contains(20)) {
          fail("Should contain 20");
        }
        if(!balst2.contains(1)) {
          fail("Should contain 1");
        }
        if(!balst2.contains(11)) {
          fail("Should contain 11");
        }
        if(!balst2.contains(23)) {
          fail("Should contain 23");
        }
        if(!balst2.contains(18)) {
          fail("Should contain 18");
        }
        balst2.remove(20);
        if(balst2.contains(20)) {
          fail("should not contain key after being removed");
        }
        if(balst2.numKeys() != 6) {
          fail("incorrectnumber of keys");
        }
        if(balst2.getKeyOfRightChildOf(14) == 20) {
          fail("key cannot be child after being removed");
        }
      }catch(Exception e) {
        e.printStackTrace();
        fail( "Unexpected exception AVL 000: "+e.getMessage() );
      }
    }
    
    /**
     * Inserting 7 key values and then removing the 7 values then checking
     * if the tree contains these 7 values.
     */
    @Test
    void testBALST_008_add7_remove7_contains7() {
      try {
        balst2.insert(7, "7");
        balst2.insert(14, "14");
        balst2.insert(18, "18");
        balst2.insert(23, "23");
        balst2.insert(1, "1");
        balst2.insert(11, "11");
        balst2.insert(20, "20");
        balst2.remove(7);
        balst2.remove(14);
        balst2.remove(18);
        balst2.remove(23);
        balst2.remove(1);
        balst2.remove(11);
        balst2.remove(20);
        if(balst2.contains(7)) {
          fail("should not contain 7 after being removed");
        }
        if(balst2.contains(14)) {
          fail("should not contain 14 after being removed");
        }
        if(balst2.contains(20)) {
          fail("should not contain 20 after being removed");
        }
        if(balst2.contains(23)) {
          fail("should not contain 23 after being removed");
        }
        if(balst2.contains(18)) {
          fail("should not contain 18 after being removed");
        }
        if(balst2.contains(1)) {
          fail("should not contain 1 after being removed");
        }
        if(balst2.contains(11)) {
          fail("should not contain 11 after being removed");
        }
      }catch(Exception e) {
        e.printStackTrace();
        fail( "Unexpected exception AVL 000: "+e.getMessage() );
        }
    }
    
    /**
     * Inserting a duplicate key and checking if the BST throws an exception.
     */
    @Test
    void test009_testing_duplicate_key_exception() {
      try {
        balst2.insert(10, "10");
        balst2.insert(10, "10");
        fail("Should throw exception");
      }catch(DuplicateKeyException e) {
        //Expected
      }catch(Exception e) {
        e.printStackTrace();
        fail( "Unexpected exception AVL 000: "+e.getMessage() );
      }
    }
}
