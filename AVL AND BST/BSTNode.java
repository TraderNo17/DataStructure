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

import java.util.ArrayList;
import java.util.List;

// Students may use and edit this class as they choose
// Students may add or remove or edit fields, methods, constructors for this class
// Students may inherit from an use this class in any way internally in other classes.
// Students are not required to use this class. 
// BUT, IF YOUR CODE USES THIS CLASS, BE SURE TO SUBMIT THIS CLASS
//
// RECOMMENDED: do not use public or private visibility modifiers
// package level access means that all classes in the package can access directly.
//
// Classes that use this type:  <TODO, list which if any classes use this type>
/**
 * Node that will be used in AVL and BST.
 * 
 * @author Yida Wu
 * @param <K> A Comparable type to be used as a key to an associated value.  
 * @param <V>A value associated with the given key.
 */
class BSTNode<K extends Comparable<K>, V> {

	K key;  //key in node
	V value; // value stored in node
	BSTNode<K, V> left; // list child
	BSTNode<K, V> right; // right child 
	int balanceFactor; // indicating balance
	int height; // only used in AVL

	BSTNode(K key, V value, BSTNode<K, V> leftChild, BSTNode<K, V> rightChild) {
		this.key = key;
		this.value = value;
		this.left = leftChild;
		this.right = rightChild;
		this.height = 0;
		this.balanceFactor = 0;
	}

	BSTNode(K key, V value) {
		this(key, value, null, null);
	}

	/**
	 * remove helper to remove nodes
	 * 
	 * @param node start node
	 * @param key the node key to be removed
	 * @return the tree after removing
	 * @throws KeyNotFoundException
	 */
	BSTNode<K, V> remove(BSTNode<K, V> node, K key) throws KeyNotFoundException {
		// TODO Auto-generated method stub
		if (node == null)
			return null;

		if (key.compareTo(node.key) < 0)
			node.left = remove(node.left, key);
		else if (key.compareTo(node.key) > 0)
			node.right = remove(node.right, key);
		else {
			if (node.right == null)
				return node.left;
			if (node.left == null)
				return node.right;
			BSTNode<K, V> temp = node;
			node = min(temp.right);
			node.right = removeMin(temp.right);
			node.left = temp.left;
		}
		return node;
	}

	/**
	 * remove the minimum value
	 * 
	 * @param node the start node
	 * @return new tree without minimum
	 */
	BSTNode<K, V> removeMin(BSTNode<K, V> node) {
		if (node.left == null)
			return node.right;
		node.left = removeMin(node.left);
		return node;
	}

	/**
	 * find the min node in sub tree
	 * 
	 * @param node start node
	 * @return the min node
	 */
	BSTNode<K, V> min(BSTNode<K, V> node) {
		if (node.left == null)
			return node;
		else
			return min(node.left);
	}
	
	/**
	 * find the max node in sub tree
	 * 
	 * @param node start node
	 * @return the max node
	 */
	BSTNode<K, V> max(BSTNode<K, V> node) {
		if (node.right == null)
			return node;
		else
			return max(node.right);
	}

	/**
	 * help pre order traversal
	 * 
	 * @param list the traversal list
	 * @param node note to traversal
	 * @return the tarversal list
	 */
	List<K> getPreOrderTraversal(ArrayList<K> list, BSTNode<K, V> node) {
		// TODO Auto-generated method stub
		if (node == null) {
			return list;
		}
		list.add(node.key);
		getPreOrderTraversal(list, node.left);
		getPreOrderTraversal(list, node.right);
		return list;
	}

	/**
	 * help post order traversal
	 * 
	 * @param list the traversal list
	 * @param node note to traversal
	 * @return the tarversal list
	 */
	List<K> getPostOrderTraversal(ArrayList<K> list, BSTNode<K, V> node) {
		// TODO Auto-generated method stub
		if (node == null) {
			return list;
		}
		getPostOrderTraversal(list, node.left);
		getPostOrderTraversal(list, node.right);
		list.add(node.key);
		return list;
	}

	/**
	 * help in order traversal
	 * 
	 * @param list the traversal list
	 * @param node note to traversal
	 * @return the tarversal list
	 */
	List<K> getInOrderTraversal(ArrayList<K> list, BSTNode<K, V> node) {
		// TODO Auto-generated method stub
		if (node == null) {
			return list;
		}
		getInOrderTraversal(list, node.left);
		list.add(node.key);
		getInOrderTraversal(list, node.right);
		return list;
	}

}
