package com.algomized.datastructures.trees;

public class CrackingTheCodingInterviewC4Q1 <Key extends Comparable<Key>, Value> 
	extends BinarySearchTree <Key, Value> {

	public static void main(String[] args) {
		CrackingTheCodingInterviewC4Q1<Integer, Integer> bst = 
			new CrackingTheCodingInterviewC4Q1<Integer, Integer>();
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
		
		System.out.println(bst.isBalanced());		
	}
	
	/**
	 * Time:  Worst = Average = O(n)<br>
	 * Space: Worst = O(h), h = height of tree
	 */
	public boolean isBalanced() {
		if (root == null) {
			return false;
		}
		return isBalanced(root) != -1;
	}

	private int isBalanced(Node node) {
		if (node == null) {
			return 0;
		}
		int left = isBalanced(node.left); // check left subtree
		if (left == -1) {
			return -1;
		}
		int right = isBalanced(node.right); // check right subtree
		if (right == -1) {
			return -1;
		}		
		if (Math.abs(left - right) > 1) { // check height difference
			return -1;
		}
		return 1 + Math.max(left, right);
	}
}
