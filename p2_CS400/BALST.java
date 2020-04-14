///////////////////////////////////////////////////////////////////////////////
//Title: BALST
//Semester: Fall 2020
//
//Author: Siddharth Aneja
//Email: saneja@wisc.edu
//CS Login: aneja@cs.wisc.edu
//Lecturer's Name: Deppeler
//Lecture Number: 002
//Description: The class that provides the implementation of the Red-Black Tree ADT.
//
////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.awt.Color;

/**
 * 
 * Class to implement a BalanceSearchTree. Can be of type AVL or Red-Black. Note which tree you
 * implement here and as a comment when you submit.
 * 
 * @param <K> is the generic type of key
 * @param <V> is the generic type of value
 */
public class BALST<K extends Comparable<K>, V> implements BALSTADT<K, V> {

  /**
   * Inner class that creates the Nodes for the Red-Black tree
   * @author Siddharth
   *
   * @param <K> - Comparable generic type for key
   * @param <V> - Generic type for value
   */
  class RBTNode<K, V> {
    
    /**
     * Comparable key that is inserted in the node of the tree.
     */
    K key;
    
    /**
     * The value related to the key in the tree.
     */
    V value;
    
    /**
     * Stores the reference to the left child of this node.
     */
    RBTNode<K, V> left;
    
    /**
     * Stores the reference to the right child of this node.
     */
    RBTNode<K, V> right;
    
    /**
     * Stores the reference to the parent of the node in the tree.
     */
    RBTNode<K, V> parent;
    
    /**
     * Stores the color of the node in the tree (either RED or BLACK).
     */
    Color color;


    /**
     * Constructor method of the RBTNode.
     * @param key - Key stored in the tree
     * @param value - Values associated with the key
     * @param leftChild - Left child of this key
     * @param rightChild - Right child of this key
     */
    RBTNode(K key, V value, RBTNode<K, V> leftChild, RBTNode<K, V> rightChild) {
      this.key = key;
      this.value = value;
      this.left = leftChild;
      this.right = rightChild;
      this.color = Color.RED;
      this.parent = null;
    }

    /**
     * Constructor with less parameters
     * @param key - Key stored in the tree
     * @param value - Values associated with the key
     */
    RBTNode(K key, V value) {
      this(key, value, null, null);
    }

  }


  /**
   * Root of the Red-Black Tree.
   */
  private RBTNode<K, V> root;

  /**
   * Stores the number of keys in the tree.
   */
  private int numKeys;
  
  /**
   * List that stores keys in order during traversal.
   */
  private ArrayList<K> keyList;
  
  /**
   * Constructor of the RBT.
   */
  public BALST() {
    this.numKeys = 0;
    this.root = null;
    keyList = new ArrayList<>();
  }

  /**
   * Returns the key that is in the root node of this BST.
   * If root is null, returns null.
   * @return key found at root node, or null
   */
  @Override
  public K getKeyAtRoot() {
    return root.key;
  }

  /**
   * Tries to find a node with a key that matches the specified key.
   * If a matching node is found, it returns the returns the key that is in the left child.
   * If the left child of the found node is null, returns null.
   * 
   * @param key A key to search for
   * @return The key that is in the left child of the found key
   * 
   * @throws IllegalNullKeyException if key argument is null
   * @throws KeyNotFoundException if key is not found in this BST
   */
  @Override
  public K getKeyOfLeftChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
    //if key is null, throw an exception
    if (key == null) {
      throw new IllegalArgumentException("argument to get() is null");
    }
    return getKeyOfLeftChildOf(root, key);
  }
  
  /**
   * A private method that keeps looping and returns the key of the child we need to find.
   * @param node - Current node in the tree.
   * @param key - The parent that we need to find left child of.
   * @return - Left child key of the provided key.
   * @throws KeyNotFoundException - if key is not found in this BST
   */
  private K getKeyOfLeftChildOf(RBTNode<K, V> node, K key) throws KeyNotFoundException{
    //Start loop till current node is not null
    while (node != null) {
      //Compare the key to the nodes key
      int cmp = key.compareTo(node.key);
      //if the key is less than the nodes key, go to left child
      if (cmp < 0) {
        node = node.left;
      }
      //if key is greater than nodes key, go to right child
      else if (cmp > 0) {
        node = node.right;
      }
      //if equal, return the left child
      else  {
        if(node.left.key != null) {
          return node.left.key;
        }
        else {
          return null;
        }
      }
  }
    //if reached the end of tree, throw KeyNotFoundException
  throw new KeyNotFoundException();
  }

  /**
   * Tries to find a node with a key that matches the specified key.
   * If a matching node is found, it returns the returns the key that is in the right child.
   * If the right child of the found node is null, returns null.
   * 
   * @param key A key to search for
   * @return The key that is in the right child of the found key
   * 
   * @throws IllegalNullKeyException if key is null
   * @throws KeyNotFoundException if key is not found in this BST
   */
  @Override
  public K getKeyOfRightChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    return getKeyOfRightChildOf(root, key);
  }
  
  /**
   * A private method that keeps looping and returns the key of the child we need to find.
   * @param node - Current node in the tree.
   * @param key - The parent that we need to find left child of.
   * @return - Right child key of the provided key.
   * @throws KeyNotFoundException - if key is not found in this BST
   */
  private K getKeyOfRightChildOf(RBTNode<K, V> node, K key) throws KeyNotFoundException{
    //Start loop till current node is not null
    while (node != null) {
      //Compare the key to the nodes key
      int cmp = key.compareTo(node.key);
      //if the key is less than the nodes key, go to left child
      if (cmp < 0) {
        node = node.left;
      }
      //if key is greater than nodes key, go to right child
      else if (cmp > 0) {
        node = node.right;
      }
      //if equal, return the right child
      else  {
        if(node.right.key != null) {
          return node.right.key;
        }
        else {
          return null;
        }
      }
  }
  //if reached the end of tree, throw KeyNotFoundException
  throw new KeyNotFoundException();
  }

  /**
   * Returns the height of this BST.
   * H is defined as the number of levels in the tree.
   * 
   * If root is null, return 0
   * If root is a leaf, return 1
   * Else return 1 + max( height(root.left), height(root.right) )
   * 
   * @return the number of levels that contain keys in this BINARY SEARCH TREE
   */
  @Override
  public int getHeight() {
 // if the dictionary is empty, return 0.
    if (root == null) {
      return 0;
    }
    // else call height helper method on the root
    return heightHelper(root);
  }
  
  /**
   * Recursive helper method that computes the height of the subtree rooted at current
   * 
   * @param current pointer to the current RBTNode within this BST
   * @return height of the subtree rooted at current counting the number of nodes
   *         from the current node to the deepest leaf in the subtree rooted at current
   */
  private int heightHelper(RBTNode current) {
    // checks if the current node is null, if true returns 0.
    if (current == null) {
      return 0;
    }
    // creates two variables which take the height of the left and right side respectively
    // by calling the heightHelper method recursively on one of the side.
    int lDepth = heightHelper(current.left);
    int rDepth = heightHelper(current.right);
    // checks which side is deeper and returns it
    if (lDepth > rDepth) {
      return lDepth + 1;
    } else {
      return rDepth + 1;
    }
  }

  /**
   * Returns the keys of the data structure in sorted order.
   * In the case of binary search trees, the visit order is: L V R
   * 
   * If the SearchTree is empty, an empty list is returned.
   * 
   * @return List of Keys in-order
   */
  @Override
  public List<K> getInOrderTraversal() {
    keyList = new ArrayList<>();
    return inOrderHelper(root);
  }

  /**
   * Recursive helper method for getting the in-order traversal.
   * @param current node of the tree
   * @return list that has the BST keys in in-order
   */
  private ArrayList<K> inOrderHelper(RBTNode<K, V> current) {
    //if the node is not null, call the method recursively on L then add the current node
    //the call it recursively on R.
    if (current != null) {
      if(current.left != null) {
        inOrderHelper(current.left);
      }
      keyList.add(current.key);
      if(current.right != null) {
        inOrderHelper(current.right);
      }
    }
    return keyList;
  }

  /**
   * Returns the keys of the data structure in pre-order traversal order.
   * In the case of binary search trees, the order is: V L R
   * 
   * If the SearchTree is empty, an empty list is returned.
   * 
   * @return List of Keys in pre-order
   */
  @Override
  public List<K> getPreOrderTraversal() {
    keyList = new ArrayList<>();
    return preOrderHelper(root);
  }

  /**
   * Recursive helper method for getting the pre-order traversal.
   * @param current node of the tree
   * @return List with keys in pre-order
   */
  private ArrayList<K> preOrderHelper(RBTNode<K, V> current) {
    //if the node is not null, first add the current node, then call pre-order recursively 
    // on L then on R.
    if (current != null) {
      keyList.add(current.key);
      if(current.left != null) {
        preOrderHelper(current.left);
      }
      if(current.right != null) {
        preOrderHelper(current.right);
      }
    }
    return keyList;
  }

  /**
   * Returns the keys of the data structure in post-order traversal order.
   * In the case of binary search trees, the order is: L R V 
   * 
   * If the SearchTree is empty, an empty list is returned.
   * 
   * @return List of Keys in post-order
   */
  @Override
  public List<K> getPostOrderTraversal() {
    keyList = new ArrayList<>();
    return postOrderHelper(root);
  }

  /**
   * Recursively helper method for getting the post order traversal.
   * @param current node of the tree
   * @return
   */
  private ArrayList<K> postOrderHelper(RBTNode<K, V> current) {
  //if the node is not null, first add the helper recursively on L then on R then add current
    if (current != null) {
      if(current.left != null) {
        postOrderHelper(current.left);
      }
      if(current.right != null) {
        postOrderHelper(current.right);
      }
      keyList.add(current.key);
    }
    return keyList;
  }

  /**
   * Returns the keys of the data structure in level-order traversal order.
   * 
   * The root is first in the list, then the keys found in the next level down,
   * and so on. 
   * 
   * If the SearchTree is empty, an empty list is returned.
   * 
   * @return List of Keys in level-order
   */
  @Override
  public List<K> getLevelOrderTraversal() {
    keyList = new ArrayList<>();
    return levelOrderHelper(root);
  }

  /**
   * @param current
   * @return
   */
  private ArrayList<K> levelOrderHelper(RBTNode<K, V> current) {
    ArrayList<K> tempVals = new ArrayList<K>();
    if (root == null)
      return keyList;

    LinkedList<RBTNode<K, V>> cur = new LinkedList<RBTNode<K, V>>();
    LinkedList<RBTNode<K, V>> next = new LinkedList<RBTNode<K, V>>();
    cur.add(root);

    while (!cur.isEmpty()) {
      RBTNode<K, V> node = cur.remove();

      if (node.left != null)
        next.add(node.left);
      if (node.right != null)
        next.add(node.right);

      tempVals.add(node.key);
      if (cur.isEmpty()) {
        cur = next;
        next = new LinkedList<RBTNode<K, V>>();
        for (int i = 0; i < tempVals.size(); ++i) {
          keyList.add(tempVals.get(i));
        }
        tempVals = new ArrayList<K>();
      }
    }
    return keyList;
  }
  
  /**
   * Get the sibling of the node.
   * @param n - node to get the sibling of
   * @return sibling node of n
   */
  private RBTNode siblingOf(RBTNode n) {
    //if node is null or parent is null, return null
    if (n == null || n.parent == null) {
      return null; 
    }
    //if node is equal to node's parent's left child, return the right child of the parent
    if (n == n.parent.left) {
      return n.parent.right;
    }
    //else return the left child of the parent
    return n.parent.left;
  }
  
  /**
   * Re-structure the tree by rotating the node left.
   * @param n - node to rotate right
   * @return the parent node after restructuring
   */
  private RBTNode rotateLeft(RBTNode n) {
    //Store the parent the current node
    RBTNode nodeP = n.parent;
    //make the node's right child's, left child to node.
    n.right.left = n;
    //set the node's right child's, left child's parent to the node's right child
    n.right.left.parent = n.right;
    //set the node to the node's right child
    n = n.right;
    //set the parent of the new node to nodeP
    n.parent = nodeP;
    //change the node's parent (nodeP) left or right child to the new node
    if(nodeP != null && nodeP.left != null && nodeP.left == n.left) {
      nodeP.left = n;
    }
    else if(nodeP != null && nodeP.right != null && nodeP.right == n.left) {
      nodeP.right = n;
    }
    //set the old node's right child to null
    n.left.right = null;
    return n;
  }

  /**
   * Re-structure the tree by rotating the node right.
   * @param n - node to rotate right
   * @return the parent node after restructuring
   */
  private RBTNode rotateRight(RBTNode n) {
    //Store the parent the current node
    RBTNode nodeP = n.parent;
    //make the node's left child's, right child equal to node
    n.left.right = n;
    //make the node's left child's, right child's parent equal to the node's left child
    n.left.right.parent = n.left;
    //set node to the node's left child
    n = n.left;
    //set the new nodes parent to nodeP
    n.parent = nodeP;
    //change the node's parent (nodeP) left or right child to the new node
    if(nodeP != null && nodeP.left != null && nodeP.left == n.right) {
      nodeP.left = n;
    }
    else if(nodeP != null && nodeP.right != null && nodeP.right == n.right) {
      nodeP.right = n;
    }
    //set the old node's left child to null
    n.right.left = null;
    return n;
  }
  
  /** 
   * Add the key,value pair to the data structure and increase the number of keys.
   * If key is null, throw IllegalNullKeyException;
   * If key is already in data structure, throw DuplicateKeyException(); 
   * Do not increase the num of keys in the structure, if key,value pair is not added.
   */
  @Override
  public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    root = insert(root, null, key, value);
    root.color = Color.BLACK;
    numKeys++;
  }
  
  /**
   * Recursively called insert method.
   * @param node - current node.
   * @param parentNode - parent of the current node.
   * @param key - key to be inserted
   * @param value - value associated with the key to be inserted
   * @return the root when its recursive
   * @throws DuplicateKeyException is thrown when key is already in tree
   */
  private RBTNode insert(RBTNode node, RBTNode parentNode, K key, V value) throws DuplicateKeyException {
    //if the tree is empty add the new node to the root
    if(root == null) {
      RBTNode n = new RBTNode(key, value);
      node = n;
      n.parent = parentNode;
      return n;
    }
    //Compare the key to be inserted with the key at the current node
    int cmp = key.compareTo((K) node.key);
    //if the key is less than the key in the current node go to the left child
    if (cmp < 0) {
      //if the left child is null, insert the new node into the node's left child
      if(node.left == null) {
        node.left = new RBTNode(key, value);
        node.left.parent = node;
      }
      //if not null, call the insert on the node's left child
      else {
        node = insert(node.left, node, key, value);
      }
    }
    //if the right child is null, insert the new node into the node's right child
    else if(cmp > 0) {
      //if the right child is null, insert the new node into the node's right child
      if(node.right == null) {
        node.right = new RBTNode(key, value);
        node.right.parent = node;
      }
      //if not null, call the insert on the node's right child
      else {
        node = insert(node.right, node, key, value);
      }
    }
    //if the key is equal to node's key then throw an exception
    else {
      throw new DuplicateKeyException();
    }
    
    if(node != null && node.parent != null && (node.left != null || node.right != null)) {
      //if the node's right child is not null
      if(node.right != null) {
        if(node.color.equals(Color.RED) && node.right.color.equals(Color.RED)) {
          //Check if sibling is not null
          if(siblingOf(node) != null) {
            //if sibling exists, and is red
            if(siblingOf(node).color.equals(Color.RED)) {
              //Re-coloring
              node = recoloring(node);
            }
            else {
              //TNR of parent, grandparent and right child of parent
              node = tnr(node, 1);
            }
          }
          //TNR of parent, grandparent and right child - if sibling is null
          else {
            node = tnr(node, 1);
          }
        }
        //No violation
        else {
          if(node.parent != null) {
            node = node.parent;
          }
        }
      }
      //left child is not null
      else if(node.left != null) {
        if(node.color.equals(Color.RED) && node.left.color.equals(Color.RED)) {
          if(siblingOf(node) != null) {
            //if sibling exists, and is red
            if(siblingOf(node).color.equals(Color.RED)) {
              //Re-coloring 
              node = recoloring(node);
            }
            else {
              //TNR of parent, grandparent and left child of parent
              node = tnr(node, -1);
            }
          }
          //TNR of parent, grandparent and left child - if sibling is null
          else {
            node = tnr(node, -1);
          }
        }
        //No violation
        else {
          if(node.parent != null) {
            node = node.parent;
          }
        }
      }
    }
    return node;
  }
  
  /**
   * If the Red property violation occurs  and the parent's sibling is not null and RED
   * then re-coloring fix takes place.
   * @param parentNode - node where the fix is called
   * @return the new grandparent node
   */
  private RBTNode recoloring(RBTNode parentNode) {
    //set grandparent color to red
    parentNode.parent.color = Color.RED;
    //set parent's color to black
    parentNode.color = Color.BLACK;
    //set the sibling of parent to black
    siblingOf(parentNode).color = Color.BLACK;
    if(parentNode.parent != null) {
      return parentNode.parent;
    }
    return parentNode;
  }
   
  /**
   * re-structures the BST if the red-property is violated and the sibling is null or BLACK
   * c = 1 for right
   * c = -1 for left
   * @param node - node at which re-structuring has to be done
   * @param c - which child is not null
   * @return - new grandparent after restructuring
   */
  private RBTNode tnr(RBTNode node, int c) {
    //checks if the parent is not null and atleast one child is not null
    if(node.parent != null && (node.left != null || node.right != null)) {
      //if the sibling is null and the node has one left child, and it is itself a left child
      //we only need a right rotate at teh grandparent
      if(siblingOf(node) == null && c == -1 && node == node.parent.left) {
        node = rotateRight(node.parent);
        node.color = Color.BLACK;
        node.right.color = Color.RED;
      }
      //if the sibling is null, the node has one right child and it is itself a left child of its
      //parent, we need a left rotate at the node first and then a right rotate at the grandparent
      else if(siblingOf(node) == null && c == 1 && node == node.parent.left) {
        node = rotateLeft(node);
        node = rotateRight(node.parent);
        node.color = Color.BLACK;
        node.right.color = Color.RED;
      }
      //if the sibling is null, the node has one right child and it is itself a right child
      //we only need a left rotate at the grandparent
      else if(siblingOf(node) == null && c == 1 && node == node.parent.right) {
        node = rotateLeft(node.parent);
        node.color = Color.BLACK;
        node.left.color = Color.RED;
      }
      //if teh sibling is null, the node has one left child and it is itself a right child
      //we need a right rotate at the node and then a left rotate at the grandparent
      else if(siblingOf(node) == null && c == -1 && node == node.parent.right) {
        node = rotateRight(node);
        node = rotateLeft(node.parent);
        node.color = Color.BLACK;
        node.left.color = Color.RED;
      }
    }
    //return the grandparent
    if(node.parent != null) {
      return node.parent;
    }
    return node;
  }

  /** 
   * If key is found, remove the key,value pair from the data structure and decrease num keys.
   * If key is not found, do not decrease the number of keys in the data structure.
   * If key is null, throw IllegalNullKeyException
   * If key is not found, throw KeyNotFoundException().
   */
  @Override
  public boolean remove(K key) throws IllegalNullKeyException, KeyNotFoundException{
    //if key is null, throw an exception
    if(key == null) {
      throw new IllegalNullKeyException();
    }
    //if root is null return false
    if(root == null) {
      return false;
    }
    //call helper 
    remove(key, root, null);
    return true;
  }
  
  /**
   * Remove helper method that takes Node parameter.
   * @param key - to be deleted
   * @param node - current node in the tree
   * @param p - parent of current node
   * @throws KeyNotFoundException
   */
  private void remove(K key, RBTNode node, RBTNode p) throws KeyNotFoundException{
    //if the node is null, throw a key not found exception
    if(node == null) {
      throw new KeyNotFoundException();
    }
    //stores the parent of the current node
    RBTNode parentNode = p;
    //loops while true
    while(true) {
      //compare the key to be deleted to the key in the current node
      int cmp = key.compareTo((K) (node.key));
      //if the key is less than the current nodes key
      if(cmp < 0) {
        //if the current nodes left key is not null, make current equal to current's left child
        if(node.left != null) {
          parentNode = node;
          node = node.left;
        }
        //if its null, then the key does not exist so throw new Exception
        else {
          throw new KeyNotFoundException();
        }
      }
      //if the current nodes right key is not null, make current equal to current's right child
      else if(cmp > 0) {
        if(node.right != null) {
          parentNode = node;
          node = node.right;
        }
        //if its null, then the key not exist so throw new Exception
        else {
          throw new KeyNotFoundException();
        }
      }
      //if the key is found and is equal to the current nodes key
      else {
        //Node that has to be delted has 2 children
        if (node.left != null && node.right != null) {
          RBTNode maxFromLeft = findMax(node.left);//in-order predecessor
          //Replacing the key-value pair in node with those of the in-order predecessor
          //and then deleting the predecessor node
          node.key = maxFromLeft.key;
          node.value = maxFromLeft.value;
          remove((K) maxFromLeft.key, node.left, node);
          break;
      }
        //Node to be deleted has one left child
      else if(node.left != null) {
          //if node has at most 1 child, which is left
          RBTNode trash = node;
          if(node == root) {
            root = node.right;
          }
          //if the current node is the left child of the parent then make the parent's left child
          //equal to the current's left child
          else if(parentNode != null && parentNode.left != null && node == parentNode.left) {
            parentNode.left = node.left;
          }
          //if the current node is the right child of the parent then make the parent's right child
          //equal to the current's left child
          else if(parentNode != null && parentNode.right != null && node == parentNode.right) {
            parentNode.right = node.left;
          }
          else {
            node = node.left;
          }
          trash = null;
          numKeys--;
          break;
      }
        //Node to be deleted has one right child
      else if(node.right != null) {
          //node has at most 1 child, which is right
          RBTNode trash = node;
          if(node == root) {
            root = node.right;
          }
          //if the current node is the left child of the parent then make the parent's left child
          //equal to the current's right child
          else if(parentNode != null && parentNode.left != null && node == parentNode.left) {
            parentNode.left = node.right;
          }
          //if the current node is the right child of the parent then make the parent's right child
          //equal to the current's left child
          else if(parentNode != null && parentNode.right != null && node == parentNode.right) {
            parentNode.right = node.right;
          }
          else {
            node = node.right;
          }
          trash = null;
          numKeys--;
          break;
      }
        //Node to be deleted has no children
      else {
        //if the current node is the left child of the parent then make the parent's left child
        //equal to null
        if(parentNode != null && parentNode.left != null && node == parentNode.left) {
          parentNode.left = null;
        }
        //if the current node is the right child of the parent then make the parent's right child
        //equal to null
        else if(parentNode != null && parentNode.right != null && node == parentNode.right) {
          parentNode.right = null;
        }
        else if(node == root) {
          root = null;
        }
        node = null;
          numKeys--;
          break;
      }
      }
    }
  }
  
  /**
   * Finds and returns the in-order predecessor of the node
   * @param n - node to find the in-order predecessor of
   * @return in-order predecessor of the node
   */
  private RBTNode findMax(RBTNode n) {
    if(n.right != null) {
      return findMax(n.right);
    }
    return n;
  }

  /**
   *  Returns the value associated with the specified key
   *
   * Does not remove key or decrease number of keys
   * If key is null, throw IllegalNullKeyException 
   * If key is not found, throw KeyNotFoundException().
   */
  @Override
  public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
    //if key is null throw an exception
    if(key == null) {
      throw new IllegalNullKeyException();
    }
    return get(key, root);
  }
  
  /**
   * recursive method that returns the value associated with the key passed as an argument.
   * @param key - to find the value of
   * @param node - current node
   * @return Value associated with the key param
   * @throws KeyNotFoundException is thrown if the key does not exist in the BST
   */
  private V get(K key, RBTNode node) throws KeyNotFoundException {
    //if the key is less than the key in node
    if (((Comparable<K>) node.key).compareTo(key) > 0) {
      //if the left child is not null, call helper method on left child
      if (node.left != null) {
        return get(key, node.left);
      }
      //throw exception if key does not exist
      else {
        throw new KeyNotFoundException();
      }
    }
    //if the key is greater than the key in node
    else if (((Comparable<K>) node.key).compareTo(key) < 0) {
      //if the right child is not null, call helper method on right child
      if (node.right != null) {
        return get(key, node.right);
      }
      //throw exception if key does not exist
      else {
        throw new KeyNotFoundException();
      }
    }
    return (V) node.value;
  }

  /** 
   * Returns true if the key is in the data structure
   * If key is null, throw IllegalNullKeyException 
   * Returns false if key is not null and is not present 
   */
  @Override
  public boolean contains(K key) throws IllegalNullKeyException {
    //if the key is null, throw exception
    if(key == null) {
      throw new IllegalNullKeyException();
    }
    //if root is null, return false
    if(root == null) {
      return false;
    }
    return contains(key, root);
  }
  
  /**
   * Recursive helper method for contains.
   * @param key - To find
   * @param node - current node
   * @return true if the key is found in tree else false.
   */
  private boolean contains(K key, RBTNode<K, V> node) {
    //if current node is null, return false
    if(node == null) {
      return false;
    }
    //if key is less than the key in the current node
    if (((Comparable<K>) node.key).compareTo(key) > 0) {
      //if the current node's left child is not null, call helper method on left child
      if (node.left != null) {
        return contains(key, node.left);
      }
      //if null return false (key does not exist)
      else {
        return false;
      }
    }
    //if key is greater than the key in the current node
    else if (((Comparable<K>) node.key).compareTo(key) < 0) {
      //if the current node's right child is not null, call helper method on the right child
      if (node.right != null) {
        return contains(key, node.right);
      }
      //if null return false (key does not exist)
      else {
        return false;
      }
    }
    //if found, return true
    else {
      return true;
    }
  }
  
  /**
   *  Returns the number of key,value pairs in the data structure
   */
  @Override
  public int numKeys() {
    return numKeys;
  }

  /**
   * Print the tree.
   */
  @Override
  public void print() {
    print("", root, false);
  }
  
  /**
   * Recursive print method
   * @param prefix
   * @param n
   * @param isLeft
   */
  private void print(String prefix, RBTNode n, boolean isLeft) {
    if (n != null) {
        print(prefix + "     ", n.right, false);
        System.out.println (prefix + ("|-- ") + n.key);
        print(prefix + "     ", n.left, true);
    }
}

}
