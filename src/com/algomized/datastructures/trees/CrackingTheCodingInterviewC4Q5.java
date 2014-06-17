package com.algomized.datastructures.trees;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Implement a function to check if a binary tree is a binary search tree.
 * </p>
 *
 */
public class CrackingTheCodingInterviewC4Q5 {

	public static void main(String[] args) {
		TreeNode<Integer> root = null;
		root = TreeNode.insert(root, 30);
		root = TreeNode.insert(root, 10);
		root = TreeNode.insert(root, 0);
		root = TreeNode.insert(root, 20);
		root = TreeNode.insert(root, 50);
		root = TreeNode.insert(root, 40);
		root = TreeNode.insert(root, 60);		
		//root.left.right.item = 45;
		
		System.out.println(isBinarySearchTree(root));			
		System.out.println(isBinarySearchTreeUsingMinMaxResult(root));
		System.out.println(isBinarySearchTreeUsingMinMax(root));
	}
	
	public static boolean isBinarySearchTree(TreeNode<Integer> root) {
		return isBinarySearchTree(root, new IntWrapper());
	}
	
	/**
	 * Time:  Average = Worst = O(n)<br>
	 * Space: Worst = O(h), h = height of tree
	 */
	private static boolean isBinarySearchTree(TreeNode<Integer> node, IntWrapper prev) {
		if (node == null) {
			return true;
		}
		if (!isBinarySearchTree(node.left, prev)) {
			return false;
		}		
		if (node.item.compareTo(prev.value) < 0) {
			return false;
		}
		prev.value = node.item;
		return isBinarySearchTree(node.right, prev);
	}
	
	static class IntWrapper {
		int value = Integer.MIN_VALUE;
	}	
	
	/**
	 * Time:  Average = Worst = O(n)<br>
	 * Space: Worst = O(h), h = height of tree
	 */
	public static boolean isBinarySearchTreeUsingMinMaxResult(TreeNode<Integer> root) {
		return minMax(root) != null;
	}
	
	private static Result minMax(TreeNode<Integer> node) {
		if (node == null) {
			return null;
		}
		if (node.left == null && node.right == null) {
			return new Result(node.item, node.item);
		}
		Result left = minMax(node.left);
		if (left == null || node.item < left.max) {
			return null;
		}		
		Result right = minMax(node.right);
		if (right == null || node.item >= right.min) {
			return null;
		}
		return new Result(Math.min(left.min, right.min), 
				Math.max(left.max, right.max));
	}
	
	static class Result {
		int min;
		int max;
		
		public Result(int min, int max) {
			this.min = min;
			this.max = max;
		}
	}
	
	/**
	 * Time:  Average = Worst = O(n)<br>
	 * Space: Worst = O(h), h = height of tree
	 */
	public static boolean isBinarySearchTreeUsingMinMax(TreeNode<Integer> root) {
		return isBinarySearchTreeUsingMinMax(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	private static boolean isBinarySearchTreeUsingMinMax(TreeNode<Integer> node, int min, int max) {
		if (node == null) {
			return true;
		}
		if (node.item.compareTo(min) < 0 || node.item.compareTo(max) >= 0) {
			return false;
		}
		if (!isBinarySearchTreeUsingMinMax(node.left, min, node.item) ||
			!isBinarySearchTreeUsingMinMax(node.right, node.item, max)) {
			return false;
		}
		return true;
	}
}