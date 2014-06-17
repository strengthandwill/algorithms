package com.algomized.datastructures.trees;

import com.algomized.datastructures.SymbolTableAPI;
import com.algomized.datastructures.queues.Queue;

/**
 * 
 * @author Poh Kah Kong
 *
 * <p>
 * Red Black BST is both BST and 2-3 tree, having fast searching 
 * from BST and fast balancing from 2-3 tree.<br>
 * <br>
 * Left node with red link is smaller or equal than the root node, and 
 * right node with black link is greater than the root node.
 * </p>
 * 
 */
public class RedBlackBST <Key extends Comparable<Key>, Value> implements SymbolTableAPI<Key, Value> {
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	
	public static void main(String[] args) {
		RedBlackBST<Integer, Integer> bst = new RedBlackBST<Integer, Integer>();
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
		System.out.println(bst);
		bst.deleteMin();
		System.out.println(bst);
		bst.deleteMax();
		System.out.println(bst);
		bst.delete(31);
		System.out.println(bst);		
	}
	
	private Node root;
	
	class Node {
		Key key;
		Value value;
		Node left, right;
		int size;
		boolean color;
		
		public Node(Key key, Value value, int size, boolean color) {
			this.key = key;
			this.value = value;
			this.size = size;
			this.color = color;
		}
		
		public String toString() {
			return "[" + value + "]";
		}
	}
	
	/**
	 * <b>Insert</b><br>
	 * Time:  Average = Worst = O(log(n))<br>
	 * Space: Worst = O(log(n))
	 */
	public void put(Key key, Value value) {
		root = put(root, key, value);
		root.color = BLACK;
	}
	
	private Node put(Node node, Key key, Value value) {
		if (node == null) {
			return new Node(key, value, 1, RED);
		}
		int cmp = key.compareTo(node.key);
		if (cmp < 0) {
			node.left = put(node.left, key, value);
		} else if (cmp > 0) {
			node.right = put(node.right, key, value);
		} else {
			node.value = value;
		}		
		
		if (isRed(node.right) && !isRed(node.left)) {
			node = rotateLeft(node);
		}
		if (isRed(node.left) && isRed(node.left.left)) {
			node = rotateRight(node);
		}
		if (isRed(node.left) && isRed(node.right)) {
			flipColors(node);
		}
		
		node.size = 1 + size(node.left) + size(node.right);
		return node;
	}
	
	/**
	 * <b>Search</b><br>
	 * Time:  Average = Worst = O(log(n))<br>
	 * Space: Worst = O(log(n))
	 */
	public Value get(Key key) {
		return get(root, key).value;
	}
	
	private Node get(Node node, Key key) {
		if (node == null) {
			return null;
		}
		int cmp = key.compareTo(node.key);
		if (cmp < 0) {
			return get(node.left, key);
		} else if (cmp > 0) {
			return get(node.right, key);
		} else {
			return node;
		}
	}
	
	/**
	 * <b>Delete</b><br>
	 * Time:  Average = Worst = O(log(n))<br>
	 * Space: Worst = O(log(n))
	 */
	public void delete(Key key) {
		if (!isRed(root.left) && !isRed(root.right)) {
			root.color = RED;
		}
		root = delete(root, key);
		if (!isEmpty()) {
			root.color = BLACK;
		}
	}
	
	private Node delete(Node node, Key key) {
		if (key.compareTo(node.key) < 0) {
			if (!isRed(node.left) && !isRed(node.left.left)) {
				node = moveRedLeft(node);
			}
			node.left = delete(node.left, key);
		} else {
			if (isRed(node.left)) {
				node = rotateRight(node);
			}
			if (key.compareTo(node.key) == 0 && node.right == null) {
				return null;
			}
			if (!isRed(node.right) && !isRed(node.right.left)) {
				node = moveRedRight(node);
			}
			if (key.compareTo(node.key) == 0) {
				Node min = min(node.right);
				node.value = min.value;
				node.key = min.key;
				node.right = deleteMin(node.right);
			} else {
				node.right = delete(node.right, key);
			}
		}
		return balance(node);
	}
	
	public boolean contains(Key key) {
		return get(key) != null;
	}
	
	public int size() {
		return size(root);
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
	
	private int size(Node node) {
		if (node == null) {
			return 0;
		}
		return node.size;
	}	
	
	public boolean isEmpty() {
		return root == null;
	}
	
	public void deleteMin() {
		if (!isRed(root.left) && !isRed(root.right)) {
			root.color = RED;
		}
		root = deleteMin(root);
		if (!isEmpty()) {
			root.color = BLACK;
		}
	}
	
	private Node deleteMin(Node node) {
		if (node.left == null) {
			return null;
		}
		if (!isRed(node.left) && !isRed(node.left.left)) {
			node = moveRedLeft(node);
		}
		node.left = deleteMin(node.left);
		return balance(node);
	}	
	
	public void deleteMax() {
		if (!isRed(root.left) && !isRed(root.right)) {
			root.color = RED;
		}
		root = deleteMax(root);
		if (!isEmpty()) {
			root.color = BLACK;
		}
	}
	
	private Node deleteMax(Node node) {
		if (isRed(node.left)) {
			node = rotateRight(node);
		}
		if (node.right == null) {
			return null;
		}
		if (!isRed(node.right) && !isRed(node.right.left)) {
			node = moveRedRight(node);
		}
		node.right = deleteMax(node.right);
		return balance(node);
	}		
	
	/**
	 * Time:  Average = Worst = O(log(n))<br>
	 * Space: Worst = O(log(n))
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
	 * Time:  Average = Worst = O(log(n))<br>
	 * Space: Worst = O(log(n))
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
	
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		inOrderTraversal(root, strBuf);
		return strBuf.toString();		
	}	
	
	/**
	 * <b>Pre-order traversal</b><br>
	 * Time:  Average = Worst= O(n)<br>
	 * Space: Worst = O(log(n))
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
	 * Time:  Average = Worst= O(n)<br>
	 * Space: Worst = O(log(n))
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
	 * Time:  Average = Worst= O(n)<br>
	 * Space: Worst = O(log(n))
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
	
	private boolean isRed(Node node) {
		if (node == null) {
			return BLACK;
		}
		return node.color == RED;
	}	
	
	private Node rotateLeft(Node node) {
		Node target = node.right;
		node.right = target.left;
		target.left = node;
		target.color = node.color;
		node.color = RED;
		target.size = node.size;
		node.size = 1 + size(node.left) + size(node.right);
		return target;		
	}
	
	private Node rotateRight(Node node) {
		Node target = node.left;
		node.left = target.right;
		target.right = node;
		target.color = node.color;
		node.color = RED;
		target.size = node.size;
		node.size = 1 + size(node.left) + size(node.right);
		return target;
	}
	
	private void flipColors(Node node) {
		node.color = !node.color;
		node.left.color = !node.left.color;
		node.right.color = !node.right.color;
	}	
	
	private Node balance(Node node) {
		if (isRed(node.right)) {
			node = rotateLeft(node);
		}
		
		if (isRed(node.right) && !isRed(node.left)) {
			node = rotateLeft(node);
		}
		if (isRed(node.left) && isRed(node.left.left)) {
			node = rotateRight(node);
		}
		if (isRed(node.left) && isRed(node.right)) {
			flipColors(node);
		}
		
		node.size = 1 + size(node.left) + size(node.right);
		return node;
	}
	
	private Node moveRedLeft(Node node) {
		flipColors(node);
		if (isRed(node.right.left)) {
			node.right = rotateRight(node.right);
			node = rotateLeft(node);
		}
		return node;
	}
	
	private Node moveRedRight(Node node) {
		flipColors(node);
		if (!isRed(node.left.left)) {
			node = rotateRight(node);
		}
		return node;
	}	
}