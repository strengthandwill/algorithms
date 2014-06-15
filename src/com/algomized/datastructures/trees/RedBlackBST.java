package com.algomized.datastructures.trees;

/**
 * 
 * @author Poh Kah Kong
 *
 * <p>
 * Red Black BST is both BST and 2-3 tree, having fast searching 
 * from BST and fast balancing from 2-3 tree.<br>
 * <br>
 * Left node with red link is smaller than the root node, and 
 * right node with black link is greater than the root node.
 * </p>
 */
public class RedBlackBST <Key extends Comparable<Key>, Value> {
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

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
	
	public int size() {
		return size(root);
	}
	
	private int size(Node node) {
		if (node == null) {
			return 0;
		}
		return node.size;
	}	
	
	private boolean isRed(Node node) {
		if (node == null) {
			return RED;
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
		node.color = RED;
		node.left.color = BLACK;
		node.right.color = BLACK;
	}
	
	/**
	 * <b>Insert</b><br>
	 * Time:  Average = Worst = O(log(n))<br>
	 * Space: Worst = O(log(n)) [Recursive]
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

}
