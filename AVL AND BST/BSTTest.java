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

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

// TODO: Add tests to test that binary search tree operations work

public class BSTTest extends DataStructureADTTest {

  BST<String, String> bst;
  BST<Integer, String> bst2;

  /**
   * @throws java.lang.Exception
   */
  @BeforeEach
  void setUp() throws Exception {
    // The setup must initialize this class's instances
    // and the super class instances.
    // Because of the inheritance between the interfaces and classes,
    // we can do this by calling createInstance() and casting to the desired type
    // and assigning that same object reference to the super-class fields.
    dataStructureInstance = bst = createInstance();
    dataStructureInstance2 = bst2 = createInstance2();
  }

  /**
   * @throws java.lang.Exception
   */
  @AfterEach
  void tearDown() throws Exception {
    dataStructureInstance = bst = null;
    dataStructureInstance2 = bst2 = null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see DataStructureADTTest#createInstance()
   */
  @Override
  protected BST<String, String> createInstance() {
    return new BST<String, String>();
  }

  /*
   * (non-Javadoc)
   * 
   * @see DataStructureADTTest#createInstance2()
   */
  @Override
  protected BST<Integer, String> createInstance2() {
    return new BST<Integer, String>();
  }

  /**
   * Test that empty trees still produce a valid but empty traversal list for each of the four
   * traversal orders.
   */
  @Test
  void testBST_001_empty_traversal_orders() {
    try {

      List<String> expectedOrder = new ArrayList<String>();

      // Get the actual traversal order lists for each type
      List<String> inOrder = bst.getInOrderTraversal();
      List<String> preOrder = bst.getPreOrderTraversal();
      List<String> postOrder = bst.getPostOrderTraversal();
      List<String> levelOrder = bst.getLevelOrderTraversal();

      // UNCOMMENT IF DEBUGGING THIS TEST
      System.out.println("   EXPECTED: " + expectedOrder);
      System.out.println("   In Order: " + inOrder);
      System.out.println("  Pre Order: " + preOrder);
      System.out.println(" Post Order: " + postOrder);
      System.out.println("Level Order: " + levelOrder);

      assertEquals(expectedOrder, inOrder);
      assertEquals(expectedOrder, preOrder);
      assertEquals(expectedOrder, postOrder);
      assertEquals(expectedOrder, levelOrder);

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 002: " + e.getMessage());
    }

  }

  /**
   * Test that trees with one key,value pair produce a valid traversal lists for each of the four
   * traversal orders.
   */
  @Test
  void testBST_002_check_traversals_after_insert_one() {

    try {

      List<Integer> expectedOrder = new ArrayList<Integer>();
      expectedOrder.add(10);
      bst2.insert(10, "ten");
      if (bst2.numKeys() != 1)
        fail("added 10, size should be 1, but was " + bst2.numKeys());

      List<Integer> inOrder = bst2.getInOrderTraversal();
      List<Integer> preOrder = bst2.getPreOrderTraversal();
      List<Integer> postOrder = bst2.getPostOrderTraversal();
      List<Integer> levelOrder = bst2.getLevelOrderTraversal();

      // UNCOMMENT IF DEBUGGING THIS TEST
      System.out.println("   EXPECTED: " + expectedOrder);
      System.out.println("   In Order: " + inOrder);
      System.out.println("  Pre Order: " + preOrder);
      System.out.println(" Post Order: " + postOrder);
      System.out.println("Level Order: " + levelOrder);

      assertEquals(expectedOrder, inOrder);
      assertEquals(expectedOrder, preOrder);
      assertEquals(expectedOrder, postOrder);
      assertEquals(expectedOrder, levelOrder);

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 003: " + e.getMessage());
    }

  }


  /**
   * Test that the in-order traversal order is correct if the items are entered in a way that
   * creates a balanced BST
   * 
   * Insert order: 20-10-30 In-Order traversal order: 10-20-30
   */
  @Test
  void testBST_003_check_inOrder_for_balanced_insert_order() {
    // insert 20-10-30 BALANCED
    try {
      bst2.insert(20, "1st key inserted");
      bst2.insert(10, "2nd key inserted");
      bst2.insert(30, "3rd key inserted");

      // expected inOrder 10 20 30
      List<Integer> expectedOrder = new ArrayList<Integer>();
      expectedOrder.add(10); // L
      expectedOrder.add(20); // V
      expectedOrder.add(30); // R

      // GET IN-ORDER and check
      List<Integer> actualOrder = bst2.getInOrderTraversal();
      assertEquals(expectedOrder, actualOrder);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 004: " + e.getMessage());
    }
  }

  /**
   * Test that the pre-order traversal order is correct if the items are entered in a way that
   * creates a balanced BST
   * 
   * Insert order: 20-10-30 Pre-Order traversal order: 20-10-30
   */
  @Test
  void testBST_004_check_preOrder_for_balanced_insert_order() {
    try {
      bst2.insert(20, "1st key inserted");
      bst2.insert(10, "2nd key inserted");
      bst2.insert(30, "3rd key inserted");

      // expected preOrder 20-10-30
      List<Integer> expectedOrder = new ArrayList<Integer>();
      expectedOrder.add(20); // L
      expectedOrder.add(10); // V
      expectedOrder.add(30); // R

      // GET pre-ORDER and check
      List<Integer> actualOrder = bst2.getPreOrderTraversal();
      assertEquals(expectedOrder, actualOrder);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 005: " + e.getMessage());
    }

  }

  /**
   * Test that the post-order traversal order is correct if the items are entered in a way that
   * creates a balanced BST
   * 
   * Insert order: 20-10-30 Post-Order traversal order: 10-30-20
   */
  @Test
  void testBST_005_check_postOrder_for_balanced_insert_order() {
    try {
      bst2.insert(20, "1st key inserted");
      bst2.insert(10, "2nd key inserted");
      bst2.insert(30, "3rd key inserted");

      // expected postOrder 10 30 20
      List<Integer> expectedOrder = new ArrayList<Integer>();
      expectedOrder.add(10); // L
      expectedOrder.add(30); // V
      expectedOrder.add(20); // R

      // GET post-ORDER and check
      List<Integer> actualOrder = bst2.getPostOrderTraversal();
      assertEquals(expectedOrder, actualOrder);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 006: " + e.getMessage());
    }
    // TODO implement this test

  }

  /**
   * Test that the level-order traversal order is correct if the items are entered in a way that
   * creates a balanced BST
   * 
   * Insert order: 20-10-30 Level-Order traversal order: 20-10-30
   */
  @Test
  void testBST_006_check_levelOrder_for_balanced_insert_order() {
    try {
      bst2.insert(20, "1st key inserted");
      bst2.insert(10, "2nd key inserted");
      bst2.insert(30, "3rd key inserted");

      // expected levelOrder 20 10 30
      List<Integer> expectedOrder = new ArrayList<Integer>();
      expectedOrder.add(20); // L
      expectedOrder.add(10); // V
      expectedOrder.add(30); // R

      // GET lever-ORDER and check
      List<Integer> actualOrder = bst2.getLevelOrderTraversal();
      assertEquals(expectedOrder, actualOrder);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 007: " + e.getMessage());
    }

  }

  /**
   * Test that the in-order traversal order is correct if the items are entered in a way that
   * creates an un-balanced BST
   * 
   * Insert order: 10-20-30 In-Order traversal order: 10-20-30
   */
  @Test
  void testBST_007_check_inOrder_for_not_balanced_insert_order() {

    try {
      bst2.insert(10, "1st key inserted");
      bst2.insert(20, "2nd key inserted");
      bst2.insert(30, "3rd key inserted");

      // expected inOrder 10 20 30
      List<Integer> expectedOrder = new ArrayList<Integer>();
      expectedOrder.add(10); // L
      expectedOrder.add(20); // V
      expectedOrder.add(30); // R

      // GET in-ORDER and check
      List<Integer> actualOrder = bst2.getInOrderTraversal();
      assertEquals(expectedOrder, actualOrder);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 008: " + e.getMessage());
    }

  }

  /**
   * Test that the pre-order traversal order is correct if the items are entered in a way that
   * creates an un-balanced BST
   * 
   * Insert order: 10-20-30 Pre-Order traversal order: 10-20-30
   */
  @Test
  void testBST_008_check_preOrder_for_not_balanced_insert_order() {

    try {
      bst2.insert(10, "1st key inserted");
      bst2.insert(20, "2nd key inserted");
      bst2.insert(30, "3rd key inserted");

      // expected preOrder 10 20 30
      List<Integer> expectedOrder = new ArrayList<Integer>();
      expectedOrder.add(10); // L
      expectedOrder.add(20); // V
      expectedOrder.add(30); // R

      // GET pre-ORDER and check
      List<Integer> actualOrder = bst2.getPreOrderTraversal();
      assertEquals(expectedOrder, actualOrder);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 009: " + e.getMessage());
    }

  }

  /**
   * Test that the post-order traversal order is correct if the items are entered in a way that
   * creates an un-balanced BST
   * 
   * Insert order: 10-20-30 Post-Order traversal order: 30-20-10
   */
  @Test
  void testBST_009_check_postOrder_for_not_balanced_insert_order() {

    try {
      bst2.insert(10, "1st key inserted");
      bst2.insert(20, "2nd key inserted");
      bst2.insert(30, "3rd key inserted");

      // expected postOrder 30 20 10
      List<Integer> expectedOrder = new ArrayList<Integer>();
      expectedOrder.add(30); // L
      expectedOrder.add(20); // V
      expectedOrder.add(10); // R

      // GET post-ORDER and check
      List<Integer> actualOrder = bst2.getPostOrderTraversal();
      assertEquals(expectedOrder, actualOrder);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 010: " + e.getMessage());
    }

  }

  /**
   * Test that the level-order traversal order is correct if the items are entered in a way that
   * creates an un-balanced BST
   * 
   * Insert order: 10-20-30 Level-Order traversal order: 10-20-30 (FIXED ON 2/14/18)
   */
  @Test
  void testBST_010_check_levelOrder_for_not_balanced_insert_order() {

    try {
      bst2.insert(10, "1st key inserted");
      bst2.insert(20, "2nd key inserted");
      bst2.insert(30, "3rd key inserted");

      // expected levelOrder 10 20 30
      List<Integer> expectedOrder = new ArrayList<Integer>();
      expectedOrder.add(10); // L
      expectedOrder.add(20); // V
      expectedOrder.add(30); // R

      // GET lever-ORDER and check
      List<Integer> actualOrder = bst2.getLevelOrderTraversal();
      assertEquals(expectedOrder, actualOrder);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 011: " + e.getMessage());
    }

  }


  // TODO: Add your own tests

  /**
 *  test to insert 3 and get 1 for bst2
 */
@Test
  void testBST_011_bet2_insert() {
    try {
      bst2.insert(10, "a");// insert 10 20 30
      bst2.insert(20, "b");
      bst2.insert(30, "c");
      
      if (bst2.get(10) != "a" | bst2.get(20) != "b" | bst2.get(30) != "c") {
        fail("this test does not return value a b c in bst2");
      }
    } catch (Exception e) {
    	e.printStackTrace();
      fail("Unexpected exception 012: " + e.getMessage());
    }
  }

  /**
 *  insert test for bst1
 */
@Test
  void testBST_012_bst_insert() {
    try {
      bst.insert("1", "a");//insert 1 2 3
      bst.insert("2", "b");
      bst.insert("3", "c");
      if (bst.get("1") != "a" | bst.get("2") != "b" | bst.get("3") != "c") {
          fail("this test does not return value a b c in bst");
        }
    } catch (Exception e) {
    	e.printStackTrace();
      fail("Unexpected exception 013: " + e.getMessage());
    }
  }

  @Test
  void testBST013_check_bst2_unbalcend_height() {
    try {
      bst2.insert(1, "a");// insert 10
      bst2.insert(2, "b");
      bst2.insert(3, "c");

      if (bst2.getHeight() != 3) {
        fail("three unbalanced height does not generate the right height which should be 3 in bst2");
      }
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 014: " + e.getMessage());
    }
  }
  
  void testBST014_check_bst_unbalcend_height() {
	    try {
	      bst.insert("1", "e");// insert 10
	      bst.insert("2", "f");
	      bst.insert("3", "g");

	      if (bst.getHeight() != 3) {
	        fail("three unbalanced height does not generate the right height which should be 3 in bst");
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	      fail("Unexpected exception 015: " + e.getMessage());
	    }
	  }

  @Test
  void testBST015_check_balanced_height() {
    try {
      bst2.insert(2, "a");
      bst2.insert(1, "b");
      bst2.insert(3, "c");

      if (bst2.getHeight() != 2) {
        fail("the height of avl should be 2, but it is not in bst2 ");
      }
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 016: " + e.getMessage());
    }
  }

  // Does the height of the tree reflect it's actual and its expected height?
  // Use the traversal orders to check.

  // Can you insert many and still "get" them back out?
  @Test
  void testBST016_insert_remove_bst2() {
    try {
      bst2.insert(20, "a");
      bst2.insert(10, "b");
      bst2.insert(30, "c");
      bst2.remove(20);
      bst2.remove(10);
      bst2.remove(30);

      if (bst2.contains(10)) {
        fail("insert remove test fails in bst2");
      }
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 017: " + e.getMessage());
    }
  }

  @Test
  void testBST017_insert_remove_bst() {
	    try {
	      bst.insert("1", "a");
	      bst.insert("2", "b");
	      bst.insert("3", "c");
	      bst.remove("1");
	      bst.remove("2");
	      bst.remove("3");

	      if (bst.contains("1")) {
	        fail("insert remove test fails in bst1");
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	      fail("Unexpected exception 018: " + e.getMessage());
	    }
	  }

  // Does delete work?
  @Test
  void testBST018_test_insert_with_child_remove() {
    try {
      bst2.insert(40, "a");
      bst2.insert(20, "b");
      bst2.insert(50, "c");
      bst2.insert(10, "d");
      bst2.insert(30, "e");
      bst2.remove(20);

      if (!bst2.getKeyOfLeftChildOf(40).equals(30)) {
        fail("insert and remvoe can not get the right child");
      }
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 019: " + e.getMessage());
    }
  }

  // When the key is a leaf? node with one left child?
  // node with one right child? node with two children?
  @Test
  void testBST019_insert_remove_many() {
    try {
      bst2.insert(40, "a");
      bst2.remove(40);
      bst2.insert(20, "b");
      bst2.insert(50, "c");
      bst2.remove(50);
      bst2.insert(10, "d");
      bst2.remove(20);
      bst2.insert(100, "d");

      if (!bst2.contains(100)) {
        fail("this does not contains 100");
      }
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 020: " + e.getMessage());
    }
  }
  // Write replacement value did you choose?
  // in-order precessor? in-order successor?
  // How can you test if it is replaced correctly?



}
