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

import java.util.ArrayList;  // allowed for creating traversal lists
import java.util.List;       // required for returning List<K>

// TODO: Implement a Binary Search Tree class here
/**
 * BST implemantation.
 * 
 * @author Yida Wu
 * @param <K> A Comparable type to be used as a key to an associated value.  
 * @param <V>A value associated with the given key.
 */
public class BST<K extends Comparable<K>,V> implements BSTADT<K, V> {

	// Tip: Use protected fields so that they may be inherited by AVL
	protected BSTNode<K,V> root;
	protected int numKeys; // number of keys in BST

	// Must have a public, default no-arg constructor
	public BST() { 
		root = null; // start BST
		numKeys = 0;
	}

	/* (non-Javadoc)
	 * @see SearchTreeADT#getPreOrderTraversal()
	 */
	@Override
	public List<K> getPreOrderTraversal() {
		// TODO Auto-generated method stub
		ArrayList<K> list = new ArrayList<K>();
		return root.getPreOrderTraversal(list,root);
	}

	/* (non-Javadoc)
	 * @see SearchTreeADT#getPostOrderTraversal()
	 */
	@Override
	public List<K> getPostOrderTraversal() {
		ArrayList<K> list = new ArrayList<K>();
		return root.getPostOrderTraversal(list,root);
	}

	/* (non-Javadoc)
	 * @see SearchTreeADT#getLevelOrderTraversal()
	 */
	@Override
	public List<K> getLevelOrderTraversal() {
		// TODO Auto-generated method stub
		ArrayList<K> keys = new ArrayList<K>();
        ArrayList<BSTNode<K,V>> arr = new ArrayList<BSTNode<K,V>>();
        arr.add(root);
        while (!arr.isEmpty()) {
        	BSTNode<K,V> node = arr.get(0);
            arr.remove(0);
            if (node == null) continue;
            keys.add(node.key);
            arr.add(node.left);
            arr.add(node.right);
        }
        return keys;
	}

	/* (non-Javadoc)
	 * @see SearchTreeADT#getInOrderTraversal()
	 */
	@Override
	public List<K> getInOrderTraversal() {
		// TODO Auto-generated method stub
		ArrayList<K> list = new ArrayList<K>();
		return root.getInOrderTraversal(list,root);
	}

	/* (non-Javadoc)
	 * @see DataStructureADT#insert(java.lang.Comparable, java.lang.Object)
	 */
	@Override
	public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
		// TODO Auto-generated method stub
		if(key.equals(null)) throw new IllegalNullKeyException();
		if(root == null) {
			root = new BSTNode<K, V>(key,value,null,null);
			numKeys++;
		}else insertHelper(root,key,value);
		
	}

	/**
	 * this method helps to insert nodes.
	 * 
	 * @param node insert node
	 * @param key the key value
	 * @param value the value of the node
	 * @throws DuplicateKeyException
	 */
	private void insertHelper(BSTNode<K, V> node, K key, V value) throws DuplicateKeyException {
		// TODO Auto-generated method stub
		if(node.key.equals(key)) throw new DuplicateKeyException();
		if(key.compareTo(node.key)<0) {
			if(node.left==null) {
				node.left = new BSTNode<K, V>(key,value);
				numKeys++;
			}
			else insertHelper(node.left,key,value);
		}else {
			if(node.right==null) {
				node.right = new BSTNode<K, V>(key,value);
				numKeys++;
			}
			else insertHelper(node.right,key,value);
		}
	}

	/* (non-Javadoc)
	 * @see DataStructureADT#remove(java.lang.Comparable)
	 */
	@Override
	public boolean remove(K key) throws IllegalNullKeyException, KeyNotFoundException {
		// TODO Auto-generated method stub
		if(key == null) throw new IllegalNullKeyException();
		if(root == null) throw new KeyNotFoundException();
		BSTNode<K,V> temp = root.remove(root,key);
			root = temp; 
			numKeys--;
			return true;
		//return false;
	}
	
	/* (non-Javadoc)
	 * @see DataStructureADT#get(java.lang.Comparable)
	 */
	@Override
	public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
		// TODO Auto-generated method stub
		BSTNode<K, V> temp = root;
		if(key == null) throw new IllegalNullKeyException();
		if(root == null) throw new KeyNotFoundException();
		while(!key.equals(temp.key)) {
			if(key.compareTo(temp.key)<0) {
				temp = temp.left;
			}else if(key.compareTo(temp.key)>0) {
				temp = temp.right;
			}
			if(temp == null) throw new KeyNotFoundException();
		}
		return temp.value;
	}

	/* (non-Javadoc)
	 * @see DataStructureADT#contains(java.lang.Comparable)
	 */
	@Override
	public boolean contains(K key) throws IllegalNullKeyException {
		// TODO Auto-generated method stub
		BSTNode<K, V> temp = root;
		if(key == null) throw new IllegalNullKeyException();
		if(root == null) return false;
		while(!key.equals(temp.key)) {
			if(key.compareTo(temp.key)<0) {
				temp = temp.left;
			}else if(key.compareTo(temp.key)>0) {
				temp = temp.right;
			}
			if(temp == null) return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see DataStructureADT#numKeys()
	 */
	@Override
	public int numKeys() {
		// TODO Auto-generated method stub
		return numKeys;
	}

	/* (non-Javadoc)
	 * @see BSTADT#getKeyAtRoot()
	 */
	@Override
	public K getKeyAtRoot() {
		// TODO Auto-generated method stub
		return root.key;
	}

	/* (non-Javadoc)
	 * @see BSTADT#getKeyOfLeftChildOf(java.lang.Comparable)
	 */
	@Override
	public K getKeyOfLeftChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
		// TODO Auto-generated method stub
		BSTNode<K, V> temp = root;
		if(key == null) throw new IllegalNullKeyException();
		if(root == null) throw new KeyNotFoundException();
		while(!key.equals(temp.key)) {
			if(key.compareTo(temp.key)<0) {
				temp = temp.left;
			}else if(key.compareTo(temp.key)>0) {
				temp = temp.right;
			}
			if(temp == null) throw new KeyNotFoundException();
		}
		return temp.left.key;
	}

	/* (non-Javadoc)
	 * @see BSTADT#getKeyOfRightChildOf(java.lang.Comparable)
	 */
	@Override
	public K getKeyOfRightChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
		// TODO Auto-generated method stub
		BSTNode<K, V> temp = root;
		if(key == null) throw new IllegalNullKeyException();
		if(root == null) throw new KeyNotFoundException();
		while(!key.equals(temp.key)) {
			if(key.compareTo(temp.key)<0) {
				temp = temp.left;
			}else if(key.compareTo(temp.key)>0) {
				temp = temp.right;
			}
			if(temp == null) throw new KeyNotFoundException();
		}
		return temp.right.key;
	}

	/* (non-Javadoc)
	 * @see BSTADT#getHeight()
	 */
	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		int forward = -1, rear = -1;
        int parent = 0, height = 0;
        BSTNode<K,V>[] list = new BSTNode[10000];
        if (root == null) {
            return 0;
        }
        list[++rear]=root;
        BSTNode<K,V> p;
        while(forward<rear){
            p=list[++forward];
            if(p.left!=null){
                list[++rear]=p.left;
            }
            if(p.right!=null){
                list[++rear]=p.right;
            }
            if(forward==parent){
                height++;
                parent=rear;
            }
        }
        return height;
	}



}
