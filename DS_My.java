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
import javax.management.RuntimeErrorException;
import javax.xml.crypto.dsig.keyinfo.KeyValue;

/**
 * class to implement data structure
 * 
 * @author Yida Wu
 *
 */
public class DS_My implements DataStructureADT {
  /**
   *  for storing key and value as a pair 
   *  such a class and its members should be "private"
   * 
   * @author Yida Wu
 */
private class KeyValueArray<K extends Comparable<K>, V> {
    // key the object
    private K key;
    // value
    private V value;

    /**
     * initialize the initial value
     * 
     * @param key the objectkey
     * @param value the value to use
     */
    private KeyValueArray(K key, V value) {
      this.key = key;
      this.value = value;
    }
  }

  private KeyValueArray[] pairs; //array to store data
  private int size; // the size of array

  // Private Fields of the class
  // TODO create field(s) here to store data pairs

  /**
   * initialize variables
   */
  public DS_My() {
    // TODO Auto-generated method stub
    pairs = new KeyValueArray[1000];
    size = 0;

  }

  @Override
  /**
   * insert one pair in to array
   */
  public void insert(Comparable k, Object v) {
    // TODO Auto-generated method stub
    if (k == null)
      throw new IllegalArgumentException("Null Key");
    if (contains(k))
      throw new RuntimeException("Duplicate Key");
    KeyValueArray newpair = new KeyValueArray(k, v);
    pairs[size] = newpair;
    size++;
  }

  @Override
  /**
   * remove specific pair
   */
  public boolean remove(Comparable k) {
    if (k == null)
      throw new IllegalArgumentException("null keys");
    for (int i = 0; i < size; i++) {
      if (pairs[i].key.equals(k)) {
        // pairs[i].key = null;
        pairs[i] = pairs[size - 1];
        pairs[size - 1] = null;
        // i--;
        size--;
        return true;
      }
    }
    return false;// when remove fails
  }

  @Override
  /**
   * check whether a key is present
   */
  public boolean contains(Comparable k) {
    // TODO Auto-generated method stub
    if (k == null)
      return false;
    for (int i = 0; i < size; i++)
      if (pairs[i].key.equals(k)) {
        return true;
      }
    return false; // when the pair is not found
  }

  @Override
  /**
   * get a specific key value
   */
  public Object get(Comparable k) {
    // TODO Auto-generated method stub
    if (k == null)
      throw new IllegalArgumentException("Null Key");
    for (int i = 0; i < size; i++) {
      // if (pairs[i].key.compareTo(k) > 0)
      if (pairs[i].key.equals(k)) {
        return pairs[i].value;
      }
    }
    return null;
  }

  @Override
  /**
   * return the size of the array
   */
  public int size() {
    // TODO Auto-generated method stub

    return this.size;

  }
}
