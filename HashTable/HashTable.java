
// TODO: comment and complete your HashTableADT implementation
// DO ADD UNIMPLEMENTED PUBLIC METHODS FROM HashTableADT and DataStructureADT TO YOUR CLASS
// DO IMPLEMENT THE PUBLIC CONSTRUCTORS STARTED
// DO NOT ADD OTHER PUBLIC MEMBERS (fields or methods) TO YOUR CLASS
//
// TODO: implement all required methods
//
// TODO: describe the collision resolution scheme you have chosen
// identify your scheme as open addressing or bucket
//
// TODO: explain your hashing algorithm here
// NOTE: you are not required to design your own algorithm for hashing,
// since you do not know the type for K,
// you must use the hashCode provided by the <K key> object
// and one of the techniques presented in lecture
//
/**
 * this is hash table class that implements the an arrays of linked lists.
 * 
 * @author Yida Wu
 * @param <K> generic type input
 * @param <V> generic type input
 */
public class HashTable<K extends Comparable<K>, V> implements HashTableADT<K, V> {
	private K key;
	private V value;
	private int size;
	private int numberofcollision;
	private hashNode<K, V>[] hashArray;
	private double loadFactorThreshold;

	/**
	 * this is the inner class that defines the data type.
	 * 
	 * @author Yida Wu
	 * @param <K> key 
	 * @param <V> value
	 */
	private static class hashNode<K, V> {//  nodes are stored in the array
		private K key;
		private V value;
		private hashNode<K, V> nextNode;

		private hashNode(K k, V v) {
			key = k;
			value = v;
		}
	}

	// TODO: ADD and comment DATA FIELD MEMBERS needed for your implementation

	// TODO: comment and complete a default no-arg constructor
	/**
	 * this method is the dedault constructor that will automatically create an instance
	 */
	public HashTable() {
		size = 0;
		loadFactorThreshold = 1;
		hashArray = new hashNode[97];
	}

	// TODO: comment and complete a constructor that accepts
	// initial capacity and load factor threshold
	// threshold is the load factor that causes a resize and rehash
	/**
	 * this is also a constructor that initializes the hash table
	 * 
	 * @param initialCapacity the defined capacity the hash table
	 * @param loadFactorThreshold  the threshold defined for following inplementaions
	 */
	public HashTable(int initialCapacity, double loadFactorThreshold) {
		// check the initial conditions
		if (initialCapacity < 0)
			throw new IllegalArgumentException("initialCapacity is unresonable.");
		if (loadFactorThreshold <= 0)
			throw new IllegalArgumentException("loadFactorThreshold can not be negative.");
		this.size = 0;
		this.loadFactorThreshold = loadFactorThreshold;
		this.hashArray = new hashNode[initialCapacity];
	}

	/**
	 * the insert method inserts a new node or inserts after resizing
	 */
	@Override
	public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
		// TODO Auto-generated method stub
		if (key == null)
			throw new IllegalNullKeyException();
		int hashIndex = ((Math.abs(key.hashCode()) % hashArray.length));// use math to compute index
		if (getLoadFactor() >= loadFactorThreshold) {// to resize
			resize();
		}
		hashNode<K, V> node = new hashNode<K, V>(key, value);// create a new node
		if (hashArray[hashIndex] != null) {
			inserthelp(node, hashArray[hashIndex]);
		}else {
			hashArray[hashIndex] = node;
			size++;
		}
	}

	/**
	 * help to insert node when there are collisions
	 * 
	 * @param node node to insert
	 * @param next the current node
	 * @return the node after resizing
	 * @throws DuplicateKeyException when there is a same node
	 */
	private hashNode<K, V> inserthelp(hashNode<K, V> node, hashNode<K, V> next) throws DuplicateKeyException {
		if(node.key.equals(next.key))
			throw new DuplicateKeyException();
		if (next.nextNode == null) {
			next.nextNode = node;
			size++;
			return next;
		}
		return inserthelp(node, next.nextNode);
	}

	/**
	 * resize the array
	 * 
	 * @throws IllegalNullKeyException
	 * @throws DuplicateKeyException
	 */
	private void resize() throws IllegalNullKeyException, DuplicateKeyException {
		// TODO Auto-generated method stub
		hashNode<K, V>[] old = hashArray;
		// expand the capacity of array
		int newCapacity = old.length * 2 + 1;
		hashNode<K, V>[] newTable = new hashNode[newCapacity];
		this.hashArray = newTable;
		for (int i = 0; i < old.length; i++) {// iterate elements in the hash table
			if (old[i] != null) {// to put nodes in old into new ones
				if (old[i].nextNode != null) {
					resizeHelper(old[i].nextNode);
				}
				insert(old[i].key, old[i].value);
				size--;
			}
		}
	}

	/**
	 * help to resize
	 * 
	 * @param node the node in order
	 * @throws IllegalNullKeyException when the key is illegal
	 * @throws DuplicateKeyException
	 */
	private void resizeHelper(hashNode<K, V> node) throws IllegalNullKeyException, DuplicateKeyException {
		// TODO Auto-generated method stub
		if (node.nextNode != null) {
			resizeHelper(node.nextNode);
		}
		insert(node.key, node.value);
		size--;
	}

	/**
	   * @see remove
	   */
	@Override
	public boolean remove(K key) throws IllegalNullKeyException {
		// TODO Auto-generated method stub
		if (key == null) {
			throw new IllegalNullKeyException();
		}
		int index = ((Math.abs(key.hashCode()) % hashArray.length));
		if(hashArray[index]==null)
			return false;
		if (key.equals(hashArray[index].key)) {
			hashArray[index] = hashArray[index].nextNode;
			return true;
		}else {
			return removeHelper(key, hashArray[index]);
			//return false;
		}
	}

	/**
	 * help to remove the nodes
	 * 
	 * @param key the node to be removed
	 * @param node the node to remove
	 * @return the node after removes
	 */
	private Boolean removeHelper(K key, hashNode<K, V> node) {
		// TODO Auto-generated method stub
		if (node.nextNode == null)
			return false;
		if (node.nextNode.key.equals(key))
			node.nextNode = node.nextNode.nextNode;
		else
			removeHelper(key, node.nextNode);
		return true;
	}

	/**
	 *  @see get
	 */
	@Override
	public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
		if (key == null)
			throw new IllegalNullKeyException();
		int index =  ((Math.abs(key.hashCode()) % hashArray.length));
		hashNode<K, V> temp = hashArray[index];
		while (temp != null) {
			if (temp.key.equals(key)) {
				return temp.value;
			}
			temp = temp.nextNode;
		}
		if(temp == null)
		    throw new KeyNotFoundException();
		return null;
	}

	/**
	 * @see numKeys
	 */
	@Override
	public int numKeys() {
		// TODO Auto-generated method stub
		return size;
	}

	/**
	 *  @see getLoadFactorThreshold
	 */
	@Override
	public double getLoadFactorThreshold() {
		// TODO Auto-generated method stub
		return loadFactorThreshold;
	}

	/**
	 * @see getLoadFactor
	 */
	@Override
	public double getLoadFactor() {
		// TODO Auto-generated method stub
		return (double)size / (double)hashArray.length;
	}

	/**
	 * @see getCaoacity
	 */
	@Override
	public int getCapacity() {
		// TODO Auto-generated method stub
		return hashArray.length;
	}

	/**
	 * @see getCollisionReslution
	 */
	@Override
	public int getCollisionResolution() {
		// TODO Auto-generated method stub
		return 5;
	}

}
