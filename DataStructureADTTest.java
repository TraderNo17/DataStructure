//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: p01 
// Files: ds_my,testds_my
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
// Online Sources: (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * test class for all test files
 * 
 * @author Yida Wu
 *
 * @param <T> generic type
 */
abstract class DataStructureADTTest<T extends DataStructureADT<String, String>> {

  private T dataStructureInstance;

  protected abstract T createInstance();

  @BeforeAll
  static void setUpBeforeClass() throws Exception {}

  @AfterAll
  static void tearDownAfterClass() throws Exception {}

  @BeforeEach
  void setUp() throws Exception {
    dataStructureInstance = createInstance();
  }

  @AfterEach
  void tearDown() throws Exception {
    dataStructureInstance = null;
  }


  @Test
  void test00_empty_ds_size() {
    if (dataStructureInstance.size() != 0)
      fail("data structure should be empty, with size=0, but size=" + dataStructureInstance.size());
  }

  // TODO: implement tests 01 - 04

  // test01_after_insert_one_size_is_one
  /**
 * test the size after inserting one key.
 */
@Test
  void test01_after_insert_one_size_is_one() {
    // insert(K key, V value);
    dataStructureInstance.insert("1", "a");
    if (dataStructureInstance.size() != 1)
      fail("data structure's size should be 1, but size =" + dataStructureInstance.size());
  }

  // test02_after_insert_one_remove_one_size_is_0
  /**
 * test whether size is correct
 */
@Test
  void test02_after_insert_one_remove_one_size_is_0() {
    dataStructureInstance.insert("1", "2");
    dataStructureInstance.remove("1");
    if (dataStructureInstance.size() != 0)
      fail("data structure should be empty, with size=0, but size=" + dataStructureInstance.size());
  }

  // test03_duplicate_exception_is_thrown
  /**
 * test duplication
 */
@Test
  void test03_duplicate_exception_is_thrown() {
    dataStructureInstance.insert("1", "2");
    dataStructureInstance.insert("2", "4");
    try {
      dataStructureInstance.insert("1", "3");
    } catch (RuntimeException e) {
      return;
    }
    fail("data structure should report exception message but not!");
  }

  // test04_remove_returns_false_when_key_not_present
  /**
 * test remove a key is not present
 */
@Test
  void test04_remove_returns_false_when_key_not_present() {
    dataStructureInstance.insert("1", "a");
    dataStructureInstance.insert("2", "b");
    if (dataStructureInstance.remove("4")) {
      fail(
          "data structure should return false because there is no 4 in the list, but it return true;");
    }
  }

  // TODO: add tests to ensure that you can detect implementation that fail

  // Tip: consider different numbers of inserts and removes and how different combinations of insert
  // and removes
  /**
 * test contains when the key is not present
 */
@Test
  void test05_contains_return_false_when_key_not_present() {
    dataStructureInstance.insert("1", "a");
    dataStructureInstance.insert("3", "b");
    if (dataStructureInstance.contains("4")) {
      fail(
          "data structure should return false because there is no 4 in the list, but it return true!");
    }
  }

  // test get the key when there is no such key
  /**
 *  test get when a key is not present
 */
@Test
  void test06_get_return_null_key_not_present() {
    dataStructureInstance.insert("1", "a");
    dataStructureInstance.insert("3", "b");
    if (dataStructureInstance.get("4") != null) {
      fail("data structure should return null because there is no 4 in the list, but it does not!");
    }
  }

  // test contains ween insert one and remove one
  /**
 *  test contains after inserting and romoving
 */
@Test
  void test07_contains_return_false_after_insert_and_remove() {
    dataStructureInstance.insert("1", "a");
    dataStructureInstance.insert("2", "b");
    dataStructureInstance.remove("2");
    if (dataStructureInstance.contains("2")) {
      fail(
          "data structure should return false because there is no 2 in the list any more, but it return true!");
    }
  }

  // test get when insert and remove one key
  /**
 * test get after inserting and removing
 */
@Test
  void test08_get_return_null_insert_and_remove_one_key() {
    dataStructureInstance.insert("1", "a");
    dataStructureInstance.insert("4", "b");
    dataStructureInstance.remove("4");
    if (dataStructureInstance.get("4") != null) {
      fail(
          "data structure should return null because there is no 2 in the list any more, but it does not!");

    }
  }

  // test insert null key and size is 0
  /**
 * test inserting null
 */
@Test
  void test_09_insert_null_size_is_0() {
    try {
      dataStructureInstance.insert("1", null);
    } catch (RuntimeException e) {
      fail("can not insert a null key.");
    }
  }

  // test inset 2 key and remove one check the size
  /**
 * test insert size after inserting and removing
 */
@Test
  void test10_insert_two_remove_one_size_shouldbe_1() {
    dataStructureInstance.insert("1", "2");
    dataStructureInstance.insert("3", "b");
    dataStructureInstance.remove("3");
    if (dataStructureInstance.size() != 1) {
      fail("size should be 1 but the size is " + dataStructureInstance.size());
    }
  }
}
