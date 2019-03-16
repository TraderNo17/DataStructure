//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: p02 
// Files: BST,BSTTEST,AVL,AVLTEST,BSTNODE
// Course: cs 400, spring 2019
//
// Author: Yida Wu
// Email: ywu479@wisc.edu
// Lecturer's Name: Deb
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:
// Partner Email:
// Partner Lecturer's Name:
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
// Online Sources: https:
//www.geeksforgeeks.org/binary-search-tree-set-2-delete/
//help me develop remove method.
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

// import org.junit.jupiter.api.AfterAll;
// import org.junit.jupiter.api.BeforeAll;

// TODO: Add tests to test the rebalancing of the AVL tree operations

// @SuppressWarnings("rawtypes")
public class AVLTest extends BSTTest {

  AVL<String, String> avl;
  AVL<Integer, String> avl2;

  /**
   * @throws java.lang.Exception
   */
  @BeforeEach
  void setUp() throws Exception {
    dataStructureInstance = bst = avl = createInstance();
    dataStructureInstance2 = bst2 = avl2 = createInstance2();
  }

  /**
   * @throws java.lang.Exception
   */
  @AfterEach
  void tearDown() throws Exception {
    avl = null;
    avl2 = null;
  }


  /*
   * (non-Javadoc)
   * 
   * @see DataStructureADTTest#createInstance()
   */
  @Override
  protected AVL<String, String> createInstance() {
    return new AVL<String, String>();
  }


  /*
   * (non-Javadoc)
   * 
   * @see DataStructureADTTest#createInstance2()
   */
  @Override
  protected AVL<Integer, String> createInstance2() {
    return new AVL<Integer, String>();
  }

  /**
   * Insert three values in sorted order and then check the root, left, and right keys to see if
   * rebalancing occurred.
   */
  @Test
  void testAVL_001_insert_sorted_order_simple() {
    try {
      avl2.insert(10, "10");
      if (!avl2.getKeyAtRoot().equals(10))
        fail("avl insert at root does not work");

      avl2.insert(20, "20");
      if (!avl2.getKeyOfRightChildOf(10).equals(20))
        fail("avl insert to right child of root does not work");

      avl2.insert(30, "30");
      Integer k = avl2.getKeyAtRoot();
      if (!k.equals(20))
        fail("avl rotate does not work");

      // IF rebalancing is working,
      // the tree should have 20 at the root
      // and 10 as its left child and 30 as its right child

      Assertions.assertEquals(avl2.getKeyAtRoot(), new Integer(20));
      Assertions.assertEquals(avl2.getKeyOfLeftChildOf(20), new Integer(10));
      Assertions.assertEquals(avl2.getKeyOfRightChildOf(20), new Integer(30));

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 000: " + e.getMessage());
    }
  }

  /**
   * Insert three values in reverse sorted order and then check the root, left, and right keys to
   * see if rebalancing occurred in the other direction.
   */
  @Test
  void testAVL_002_insert_reversed_sorted_order_simple() {

    // TODO: implement this test
    try {
      avl2.insert(30, "30");
      if (!avl2.getKeyAtRoot().equals(30))
        fail("avl insert at root does not work");

      avl2.insert(20, "20");
      if (!avl2.getKeyOfLeftChildOf(30).equals(20))
        fail("avl insert to right child of root does not work");

      avl2.insert(10, "10");
      Integer k = avl2.getKeyAtRoot();
      if (!k.equals(20))
        fail("avl rotate does not work");

      // IF rebalancing is working,
      // the tree should have 20 at the root
      // and 10 as its left child and 30 as its right child

      Assertions.assertEquals(avl2.getKeyAtRoot(), new Integer(20));
      Assertions.assertEquals(avl2.getKeyOfLeftChildOf(20), new Integer(10));
      Assertions.assertEquals(avl2.getKeyOfRightChildOf(20), new Integer(30));

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 001: " + e.getMessage());
    }

  }

  /**
   * Insert three values so that a right-left rotation is needed to fix the balance.
   * 
   * Example: 10-30-20
   * 
   * Then check the root, left, and right keys to see if rebalancing occurred in the other
   * direction.
   */
  @Test
  void testAVL_003_insert_smallest_largest_middle_order_simple() {

    // TODO: implement this test
    try {
      avl2.insert(10, "10");
      if (!avl2.getKeyAtRoot().equals(10))
        fail("avl insert at root does not work");

      avl2.insert(30, "30");
      if (!avl2.getKeyOfRightChildOf(10).equals(30))
        fail("avl insert to right child of root does not work");

      avl2.insert(20, "20");
      Integer k = avl2.getKeyAtRoot();
      if (!k.equals(20))
        fail("avl rotate does not work");

      // IF rebalancing is working,
      // the tree should have 20 at the root
      // and 10 as its left child and 30 as its right child

      Assertions.assertEquals(avl2.getKeyAtRoot(), new Integer(20));
      Assertions.assertEquals(avl2.getKeyOfLeftChildOf(20), new Integer(10));
      Assertions.assertEquals(avl2.getKeyOfRightChildOf(20), new Integer(30));

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 002: " + e.getMessage());
    }

  }

  /**
   * Insert three values so that a left-right rotation is needed to fix the balance.
   * 
   * Example: 30-10-20
   * 
   * Then check the root, left, and right keys to see if rebalancing occurred in the other
   * direction.
   */
  @Test
  void testAVL_003_insert_largest_smallest_middle_order_simple() {

    // TODO: implement this test
    try {
      avl2.insert(30, "30");
      if (!avl2.getKeyAtRoot().equals(30))
        fail("avl insert at root does not work");

      avl2.insert(10, "10");
      if (!avl2.getKeyOfLeftChildOf(30).equals(10))
        fail("avl insert to right child of root does not work");

      avl2.insert(20, "20");
      Integer k = avl2.getKeyAtRoot();
      if (!k.equals(20))
        fail("avl rotate does not work");

      // IF rebalancing is working,
      // the tree should have 20 at the root
      // and 10 as its left child and 30 as its right child

      Assertions.assertEquals(avl2.getKeyAtRoot(), new Integer(20));
      Assertions.assertEquals(avl2.getKeyOfLeftChildOf(20), new Integer(10));
      Assertions.assertEquals(avl2.getKeyOfRightChildOf(20), new Integer(30));

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 003: " + e.getMessage());
    }


  }

  @Test
  void testAVL_004_insert_remove_check() {
    try {
      avl2.insert(30, "30");
      if (!avl2.getKeyAtRoot().equals(30))
        fail("avl insert at root does not work");

      avl2.insert(10, "10");
      if (!avl2.getKeyOfRightChildOf(30).equals(10))
        fail("avl insert to right child of root does not work");

      avl2.insert(20, "20");
      if (!avl2.getKeyOfRightChildOf(10).equals(20)) {
        fail("avl insert to right child of 10 does not work");
      }
      avl2.insert(40, "40");
      avl2.remove(40);
      Integer k = avl2.getKeyAtRoot();
      if (!k.equals(20))
        fail("avl rotate does not work");

      // IF rebalancing is working,
      // the tree should have 20 at the root
      // and 10 as its left child and 30 as its right child

      Assertions.assertEquals(avl2.getKeyAtRoot(), new Integer(20));
      Assertions.assertEquals(avl2.getKeyOfLeftChildOf(20), new Integer(10));
      Assertions.assertEquals(avl2.getKeyOfLeftChildOf(40), new Integer(30));

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 004: " + e.getMessage());
    }
  }

  @Test
  void testAVL_005_insert_two_nodes_contains() {
    try {
      avl2.insert(30, "30");
      if (!avl2.getKeyAtRoot().equals(30))
        fail("avl insert at root does not work");

      avl2.insert(10, "10");
      if (!avl2.getKeyOfLeftChildOf(30).equals(10))
        fail("avl insert to right child of root does not work");

      avl2.insert(20, "20");
      Integer k = avl2.getKeyAtRoot();
      if (!k.equals(20))
        fail("avl rotate does not work");

      // IF rebalancing is working,
      // the tree should have 20 at the root
      // and 10 as its left child and 30 as its right child
      if (!avl2.contains(10)) {
        fail("miss a 10 key");
      }
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 005: " + e.getMessage());
    }

  }

  @Test
  void testAVL_006_getChild_in_avl() {
    try {
      avl2.insert(30, "30");
      if (!avl2.getKeyAtRoot().equals(30))
        fail("avl insert at root does not work");

      avl2.insert(10, "10");
      avl2.insert(40, "40");
      if (!avl2.getKeyOfLeftChildOf(30).equals(10))
        fail("avl fails to detect 10");
      if (!avl2.getKeyOfRightChildOf(30).equals(40))
          fail("avl fails to detect 40");

      // IF rebalancing is working,
      // the tree should have 30 at the root
      // and 10 as its left child and 40 as its right child

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 006: " + e.getMessage());
    }
  }
  
  @Test
  void testAVL_007_remove_all_avl() {
    try {
      avl2.insert(30, "30");
      if (!avl2.getKeyAtRoot().equals(30))
        fail("avl insert at root does not work");

      avl2.insert(10, "10");
      avl2.insert(40, "40");
      avl2.remove(10);
      avl2.remove(40);
      avl2.remove(30);
      if (avl2.contains(30))
        fail("avl fails to remove 10");

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 007: " + e.getMessage());
    }
  }

  // TODO: Add your own tests

  // Add tests to make sure that rebalancing occurs even if the
  // tree is larger. Does it maintain it's balance?
  // Does the height of the tree reflect it's actual height
  // Use the traversal orders to check.

  // Can you insert many and still "get" them back out?

  // Does delete work? Does the tree maintain balance when a key is deleted?

}
