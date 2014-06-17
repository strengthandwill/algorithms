package com.algomized.datastructures.trees;

/**
 * 
 * @author Poh Kah Kong
 *
 * <p>
 * Implement a function to check if a binary tree is balanced. For the purposes of
 * this question, a balanced tree is defined to be a tree such that the heights of the
 * two subtrees of any node never differ by more than one.
 * </p>
 * 
 */
public class CrackingTheCodingInterviewC4Q1 {

	public static void main(String[] args) {
		TreeNode<Integer> root = null;
		root = TreeNode.insert(root, 3);
		root = TreeNode.insert(root, 1);
		root = TreeNode.insert(root, 0);
		root = TreeNode.insert(root, 2);
		root = TreeNode.insert(root, 5);
		root = TreeNode.insert(root, 4);
		root = TreeNode.insert(root, 6);
		
		System.out.println(isBalanced(root));		
	}
	
	/**
	 * Time:  Worst = Average = O(n)<br>
	 * Space: Worst = O(h), h = height of tree
	 */
	public static boolean isBalanced(TreeNode<Integer> root) {
		if (root == null) {
			return false;
		}
		return isBalancedHeight(root) != -1;
	}

	public static int isBalancedHeight(TreeNode<Integer> node) {
		if (node == null) {
			return 0;
		}
		int left = isBalancedHeight(node.left); // check left subtree
		if (left == -1) {
			return -1;
		}
		int right = isBalancedHeight(node.right); // check right subtree
		if (right == -1) {
			return -1;
		}		
		if (Math.abs(left - right) > 1) { // check height difference
			return -1;
		}
		return 1 + Math.max(left, right);
	}
}
