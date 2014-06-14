package com.algomized.datastructures.trees;

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
public class BinarySearchTree <K extends Comparable<K>, V> 
	implements BinarySearchTreeAPI<K, V>{
	
	public static void main(String[] args) {
		BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<Integer, Integer>();
		bst.put(24, 24);
		bst.put(15, 15);
		bst.put(31, 31);
		bst.put(42, 42);
		bst.put(30, 30);
		System.out.println(bst.get(31));
		System.out.println(bst.size());
		System.out.println(bst.min());
		System.out.println(bst.max());	
		System.out.println(bst.floor(35));
		System.out.println(bst.ceiling(35));
		System.out.println(bst.select(2));
		System.out.println(bst.rank(30));
		bst.deleteMin();
		System.out.println(bst.size());
		bst.deleteMax();		
		System.out.println(bst.size());
		//bst.delete(1);
		//System.out.println(bst.get(1));
	}
	
	private Node root;
	
	class Node {
		K key;
		V value;
		int size;
		Node left;
		Node right;

		public Node(K key, V value, int size) {
			this.key = key;
			this.value = value;
			this.size = size;
		}
	}
	
	/**
	 * <b>Insert</b><br>
	 * Time:  Average = O(log(n)), Worst = O(n)<br>
	 * Space: Worst = O(log(n)) (Recursive)
	 */
	public void put(K key, V value) {
		root = put(root, key, value);
	}
	
	private Node put(Node node, K key, V value) {
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
	 * Space: Worst = O(logn(n)) (Recursive)
	 */
	public V get(K key) {
		return get(root, key);
	}
	
	private V get(Node node, K key) {
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
		return size(root) == 0;
	}
	
	/**
	 * Time:  Average = O(log(n)), Worst = O(n)<br>
	 * Space: Worst = O(log(n)) (Recursive)
	 */
	public K min() {
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
	 * Space: Worst = O(log(n)) (Recursive)
	 */	
	public K max() {
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
	 * Space: Worst = O(log(n)) (Recursive)
	 */	
	public K floor(K key) {
		return floor(root, key).key;
	}
	
	private Node floor(Node node, K key) {
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
	 * Space: Worst = O(log(n)) (Recursive)
	 */	
	public K ceiling(K key) {
		return ceiling(root, key).key;
	}
	
	private Node ceiling(Node node, K key) {
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
	 * Space: Worst = O(log(n)) (Recursive)
	 */	
	public K select(int rank) {
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
	 * Space: Worst = O(log(n)) (Recursive)
	 */	
	public int rank(K key) {
		return rank(root, key);
	}
	
	private int rank(Node node, K key) {
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
	 * Space: Worst = O(log(n)) (Recursive)
	 */
	public void deleteMin() {
		root = deleteMin(root);
	}
	
	private Node deleteMin(Node node) {
		if (node.right == null) {
			return node.left;
		}
		node.right = deleteMin(node.right);
		node.size = size(node.left) + size(node.right) + 1;
		return node;
	}
	
	/**
	 * Time:  Average = Worst = O(log(n))<br>
	 * Space: Worst = O(log(n)) (Recursive)
	 */
	public void deleteMax() {
		root = deleteMax(root);
	}
	
	private Node deleteMax(Node node) {
		if (node.left == null) {
			return node.right;
		}
		node.left = deleteMax(node.left);
		node.size = size(node.left) + size(node.right) + 1;
		return node;
	}		
}