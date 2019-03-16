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

// A BST search tree that maintains its balance using AVL rotations.
/**
 * Expend BST to AVL tree.
 * 
 * @author Yida Wu
 * @param <K> A Comparable type to be used as a key to an associated value.  
 * @param <V>A value associated with the given key.
 */
public class AVL<K extends Comparable<K>, V> extends BST<K, V> {

	// must add rebalancing to BST via rotate operations

	/* (non-Javadoc)
	 * @see BST#insert(java.lang.Comparable, java.lang.Object)
	 */
	@Override
	public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
		super.insert(key, value);

		int cmp = key.compareTo(root.key);
		if (cmp < 0) {
			if (getHeight(root.left) - getHeight(root.right) == 2) {
				if (key.compareTo(root.left.key) < 0)
					root = leftRotation(root);
				else {
					root.left = rightRotation(root.left);
					root = leftRotation(root);
				}
			}
		} else if (cmp > 0) {
			if (getHeight(root.right) - getHeight(root.left) == 2) {
				if (key.compareTo(root.right.key) > 0)
					root = rightRotation(root);
				else {
					root.right = leftRotation(root.right);
					root = rightRotation(root);
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see BST#remove(java.lang.Comparable)
	 */
	@Override
	public boolean remove(K key) throws IllegalNullKeyException, KeyNotFoundException {
		int cmp = key.compareTo(root.key);
	    if (cmp < 0) {
	    	super.remove(key);
	        if (getHeight(root.right) - getHeight(root.left) == 2) {
	            BSTNode<K,V> r =  root.right;
	            if (getHeight(r.left) > getHeight(r.right)) {
	            	root.right = leftRotation(root.right);
	            	root = rightRotation(root);
	            }
	            else
	                root = rightRotation(root);
	        }
	    } else if (cmp > 0) {
	    	super.remove(key);
	        if (getHeight(root.left) - getHeight(root.right) == 2) {
	            BSTNode<K,V> l =  root.left;
	            if (getHeight(l.right) > getHeight(l.left)) {
	            	root.left = rightRotation(root.left);
	            	root = leftRotation(root);
	            }
	            else
	                root = leftRotation(root);
	        }
	    } else {
	        if ((root.left!=null) && (root.right!=null)) {
	            if (getHeight(root.left) > getHeight(root.right)) {
	                BSTNode<K,V> max = root.max(root.left);
	                root.key = max.key;
	                super.remove(max.key);
	            } else {
	                BSTNode<K,V> min = root.min(root.right);
	                root.key = min.key;
	                super.remove(min.key); 
	            }
	        } else {
	            root = (root.left!=null) ? root.left : root.right;
	        }
	    }
		return true;
	}

	/**
	 * find max
	 * 
	 * @param a variable
	 * @param b variable
	 * @return max
	 */
	private int max(int a, int b) {
		return a > b ? a : b;
	}

	/**
	 * get the height
	 * 
	 * @param node input node
	 * @return the height
	 */
	public int getHeight(BSTNode<K, V> node) {
		// TODO Auto-generated method stub
		int forward = -1, rear = -1;
		int parent = 0, height = 0;
		BSTNode<K, V>[] list = new BSTNode[10000];
		if (node == null) {
			return 0;
		}
		list[++rear] = node;
		BSTNode<K, V> p;
		while (forward < rear) {
			p = list[++forward];
			if (p.left != null) {
				list[++rear] = p.left;
			}
			if (p.right != null) {
				list[++rear] = p.right;
			}
			if (forward == parent) {
				height++;
				parent = rear;
			}
		}
		return height;
	}

	/**
	 * left rotation method
	 * 
	 * @param node rotated node
	 * @return rotated result
	 */
	private BSTNode<K, V> leftRotation(BSTNode<K, V> node) {
		BSTNode<K, V> k1;
		k1 = node.left;
		node.left = k1.right;
		k1.right = node;

		node.height = max(getHeight(node.left), getHeight(node.right)) + 1;
		k1.height = max(getHeight(k1.left), node.height) + 1;

		return k1;
	}

	/**
	 * right rotation method
	 * 
	 * @param node rotated node
	 * @return rotated result
	 */
	private BSTNode<K, V> rightRotation(BSTNode<K, V> node) {
		BSTNode<K, V> k2;
		k2 = node.right;
		node.right = k2.left;
		k2.left = node;

		node.height = max(getHeight(node.left), getHeight(node.right)) + 1;
		k2.height = max(getHeight(k2.right), node.height) + 1;

		return k2;
	}

	/**
	 * rebalancing method
	 * 
	 * @param node start node for revalancing
	 * @return balanced results
	 * @throws IllegalNullKeyException
	 * @throws KeyNotFoundException
	 */
	public BSTNode<K, V> rebalancing(BSTNode<K, V> node) throws IllegalNullKeyException, KeyNotFoundException {
		List<K> keys = new ArrayList<K>();
		keys = getInOrderTraversal();
		int size = keys.size();
		node = rebalancingHelper(keys, 0, size - 1);
		return node;
	}

	/**
	 * this method help to rebalancing the AVL or BST tree.
	 * 
	 * @param keys node lists
	 * @param start start point
	 * @param end end point
	 * @return the balanced tree
	 * @throws IllegalNullKeyException
	 * @throws KeyNotFoundException
	 */
	private BSTNode<K, V> rebalancingHelper(List<K> keys, int start, int end)
			throws IllegalNullKeyException, KeyNotFoundException {
		// TODO Auto-generated method stub
		if (start > end)
			return null;

		int mid = (start + end) / 2;
		BSTNode<K, V> node = new BSTNode<K, V>(keys.get(mid), get(keys.get(mid)));
		node.left = rebalancingHelper(keys, start, mid - 1);
		node.right = rebalancingHelper(keys, mid + 1, end);
		return node;
	}
}
