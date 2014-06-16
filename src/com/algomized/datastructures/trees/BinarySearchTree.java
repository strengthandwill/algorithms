package com.algomized.datastructures.trees;

import com.algomized.datastructures.queues.Queue;

/**
 * 
 * @author Poh Kah Kong
 *
 * <p>
 * Has low space complexity as linked list. Has lower time complexity than linked list 
 * with parent node having two chid nodes, allowing binary tree traversal.<br>
 * <br>
 * Left nodes has smaller value than the parent node, and right nodes have greater value 
 * than the parent node.<br>  
 * <br>
 * Space: O(n)
 * </p>
 * 
 */
public class BinarySearchTree <Key extends Comparable<Key>, Value> 
	implements BinarySearchTreeAPI<Key, Value> {
	
	public static void main(String[] args) {
		BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<Integer, Integer>();
		bst.put(24, 24);
		bst.put(15, 15);
		bst.put(31, 31);
		bst.put(42, 42);
		bst.put(30, 30);
		
		bst.put(54, 54);
		bst.put(35, 35);
		bst.put(12, 12);
		bst.put(41, 41);		
		bst.put(23, 23);
				
		System.out.println(bst.get(31));
		System.out.println(bst.size());
		System.out.println(bst.min());
		System.out.println(bst.max());	
		System.out.println(bst.floor(35));
		System.out.println(bst.ceiling(35));
		System.out.println(bst.select(2));
		System.out.println(bst.rank(31));
		bst.deleteMin();
		System.out.println(bst.size());
		bst.deleteMax();		
		System.out.println(bst.size());
		bst.delete(31);
		System.out.println(bst.size());
		System.out.println(bst.rank(31));
		bst.delete(31);
		System.out.println(bst.get(31));
		System.out.println(bst);
		System.out.println(bst.keys(25, 36));
	}
	
	protected Node root;
	
	class Node {
		Key key;
		Value value;
		int size;
		Node left, right;

		public Node(Key key, Value value, int size) {
			this.key = key;
			this.value = value;
			this.size = size;
		}
		
		public String toString() {
			return "[" + value + "]";
		}
	}
	
	/**
	 * <b>Insert</b><br>
	 * Time:  Average = O(log(n)), Worst = O(n)<br>
	 * Space: Worst = O(n)
	 */
	public void put(Key key, Value value) {
		root = put(root, key, value);
	}
	
	private Node put(Node node, Key key, Value value) {
		if (node == null) {
			return new Node(key, value, 1);
		}
		int cmp = key.compareTo(node.key);
		if (cmp < 0) {
			node.left = put(node.left, key, value);
		} else if (cmp > 0) {
			node.right = put(node.right, key, value);
		} else {
			node.value = value;
		}
		node.size = size(node.left) + size(node.right) + 1;
		return node;
	}
	
	/**
	 * <b>Search</b><br>
	 * Time:  Average = O(log(n)), Worst = O(n)<br>
	 * Space: Worst = O(n)
	 */
	public Value get(Key key) {
		return get(root, key);
	}
	
	private Value get(Node node, Key key) {
		if (node == null) {
			return null;
		}
		int cmp = key.compareTo(node.key);
		if (cmp < 0) {
			return get(node.left, key);
		} else if (cmp > 0) {
			return get(node.right, key);
		} else {
			return node.value;
		}
	}
	
	public boolean contains(Key key) {
		return get(key) != null;
	}
	
	/**
	 * <b>Delete</b><br>
	 * Time:  Average = O(log(n)) [Traversal] + O(log(n)) [min()] + O(log(n)) [deleteMin()] = O(log(n)),
	 * Worst = O(n) [Traversal] + O(n) [min()] + O(n) [deleteMin()] = O(n)<br>
	 * Space: Worst = O(n)
	 */
	public void delete(Key key) {
		root = delete(root, key);
	}
	
	private Node delete(Node node, Key key) {
		if (node == null) {
			return null;
		}
		int cmp = key.compareTo(node.key);
		if (cmp < 0) {
			node.left = delete(node.left, key);
		} else if (cmp > 0) {
			node.right = delete(node.right, key);
		} else {
			Node successor = min(node.right);
			successor.right = deleteMin(node.right);
			successor.left = node.left;
			node = successor;
		}
		node.size = size(node.left) + size(node.right) + 1;
		return node;
	}	
	
	public int size() {
		return size(root);
	}
	
	private int size(Node node) {
		if (node == null) {
			return 0;
		}
		return node.size;
	}
	
	public boolean isEmpty() {
		return root == null;
	}
	
	public Iterable<Key> keys() {
		return keys(min(), max());
	}
	
	public Iterable<Key> keys(Key lo, Key hi) {
		Queue<Key> queue = new Queue<Key>();
		keys(root, lo, hi, queue);
		return queue;
	}
	
	public void keys(Node node, Key lo, Key hi, Queue<Key> queue) {
		if (node == null) {
			return;
		}
		keys(node.left, lo, hi, queue);
		if (node.key.compareTo(lo) >= 0 && node.key.compareTo(hi) <= 0) {
			queue.enqueue(node.key);
		}
		keys(node.right, lo, hi, queue);
	}
	
	/**
	 * Time:  Average = O(log(n)), Worst = O(n)<br>
	 * Space: Worst = O(n)
	 */
	public Key min() {
		return min(root).key;
	}
	
	private Node min(Node node) {
		if (node.left == null) {
			return node;
		}
		return min(node.left);
	}

	/**
	 * Time:  Average = O(log(n)), Worst = O(n)<br>
	 * Space: Worst = O(n)
	 */	
	public Key max() {
		return max(root).key;
	}
	
	private Node max(Node node) {
		if (node.right == null) {
			return node;
		}
		return max(node.right);
	}
	
	/**
	 * Time:  Average = O(log(n)), Worst = O(n)<br>
	 * Space: Worst = O(n)
	 */	
	public Key floor(Key key) {
		return floor(root, key).key;
	}
	
	private Node floor(Node node, Key key) {
		if (node == null) {
			return null;
		}		
		int cmp = key.compareTo(node.key);
		if (cmp < 0) {
			return floor(node.left, key);
		} else if (cmp == 0) {
			return node;
		} else {
			Node temp = floor(node.right, key);
			return temp != null ? temp : node;
		}
	}
	
	/**
	 * Time:  Average = O(log(n)), Worst = O(n)<br>
	 * Space: Worst = O(n)
	 */	
	public Key ceiling(Key key) {
		return ceiling(root, key).key;
	}
	
	private Node ceiling(Node node, Key key) {
		if (node == null) {
			return null;
		}		
		int cmp = key.compareTo(node.key);
		if (cmp > 0) {
			return ceiling(node.right, key);
		} else if (cmp == 0) {
			return node;
		} else {
			Node temp = ceiling(node.left, key);
			return temp != null ? temp : node;
		}
	}
	
	/**
	 * Time:  Average = O(log(n)), Worst = O(n)<br>
	 * Space: Worst = O(n)
	 */	
	public Key select(int rank) {
		if (rank < 0 || rank >= size()) {
			return null;
		}
		return select(root, rank).key;
	}

	private Node select(Node node, int rank) {
		if (node == null) {
			return null;
		}
		int leftSize = size(node.left);
		if (leftSize > rank) {
			return select(node.left, rank);
		} else if (leftSize < rank) {
			return select(node.right, rank - leftSize - 1);
		} else {
			return node;
		}
	}
	
	/**
	 * Time:  Average = O(log(n)), Worst = O(n)<br>
	 * Space: Worst = O(n)
	 */	
	public int rank(Key key) {
		return rank(root, key);
	}
	
	private int rank(Node node, Key key) {
		if (node == null) {
			return -1;
		}
		int cmp = key.compareTo(node.key);
		if (cmp < 0) {
			return rank(node.left, key);
		} else if (cmp > 0) {
			return 1 + size(node.left) + rank(node.right, key);
		} else {
			return size(node.left);
		}
	}		
	
	/**
	 * Time:  Average = Worst = O(log(n))<br>
	 * Space: Worst = O(n)
	 */
	public void deleteMin() {
		root = deleteMin(root);
	}
	
	private Node deleteMin(Node node) {
		if (node.left == null) {
			return node.right;
		}
		node.left = deleteMin(node.left);
		node.size = size(node.left) + size(node.right) + 1;
		return node;
	}
	
	/**
	 * Time:  Average = Worst = O(log(n))<br>
	 * Space: Worst = O(n)
	 */
	public void deleteMax() {
		root = deleteMax(root);
	}
	
	private Node deleteMax(Node node) {
		if (node.right == null) {
			return node.left;
		}
		node.right = deleteMax(node.right);
		node.size = size(node.left) + size(node.right) + 1;
		return node;
	}
	
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		inOrderTraversal(root, strBuf);
		return strBuf.toString();		
	}
	
	/**
	 * <b>Pre-order traversal</b><br>
	 * Time:  Average = O(log(n)), Worst= O(n)<br>
	 * Space: Worst = O(n)
	 */
	public String preOrderTraversal() {
		StringBuffer strBuf = new StringBuffer();
		preOrderTraversal(root, strBuf);
		return strBuf.toString();		
	}
	
	private void preOrderTraversal(Node node, StringBuffer strBuf) {
		if (node == null) {
			return;
		}
		strBuf.append(node);
		preOrderTraversal(node.left, strBuf);
		preOrderTraversal(node.right, strBuf);
	}	

	/**
	 * <b>In-order traversal</b><br>
	 * Time:  Average = O(log(n)), Worst= O(n)<br>
	 * Space: Worst = O(n)
	 */	
	public String inOrderTraversal() {
		StringBuffer strBuf = new StringBuffer();
		inOrderTraversal(root, strBuf);
		return strBuf.toString();				
	}
	
	private void inOrderTraversal(Node node, StringBuffer strBuf) {
		if (node == null) {
			return;
		}
		inOrderTraversal(node.left, strBuf);
		strBuf.append(node);
		inOrderTraversal(node.right, strBuf);	
	}
	
	/**
	 * <b>Post-order traversal</b><br>
	 * Time:  Average = O(log(n)), Worst= O(n)<br>
	 * Space: Worst = O(n)
	 */		
	public String postOrderTraversal() {
		StringBuffer strBuf = new StringBuffer();
		postOrderTraversal(root, strBuf);
		return strBuf.toString();	
	}
	
	private void postOrderTraversal(Node node, StringBuffer strBuf) {
		if (node == null) {
			return;
		}
		postOrderTraversal(node.left, strBuf);
		postOrderTraversal(node.right, strBuf);
		strBuf.append(node);
	}	
}