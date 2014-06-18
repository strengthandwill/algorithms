package com.algomized.datastructures.trees;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Design an algorithm and write code to find the first common ancestor of two
 * nodes in a binary tree. Avoid storing additional nodes in a data structure. NOTE:
 * This is not necessarily a binary search tree.
 * </p>
 * 
 */
public class CrackingTheCodingInterviewC4Q7 {

	public static void main(String[] args) {
		TreeNode<Integer> root = null;
		root = TreeNode.insert(root, 3);
		root = TreeNode.insert(root, 1);
		root = TreeNode.insert(root, 0);
		root = TreeNode.insert(root, 2);
		root = TreeNode.insert(root, 5);
		root = TreeNode.insert(root, 4);
		root = TreeNode.insert(root, 6);		
		System.out.println(findAncestor(root, root.left.right, root.right.right));
		System.out.println(findAncestorWithCounter(root, root.left.right, root.right.right));
		System.out.println(findAncestorWithPreCheckingContainsNode(root, root.left.left, root.right.right));
	}
	
	/**
	 * Time:  Average = Worst = O(n)<br>
	 * Space: Time = O(h), h = height of tree 
	 */
	public static TreeNode<Integer> findAncestor(TreeNode<Integer> root, 
			TreeNode<Integer> a, TreeNode<Integer> b) {
		Result result = findAncestorHelper(root, a, b);
		return result.isAncestor ? result.node : null;
	}
	
	private static Result findAncestorHelper(TreeNode<Integer> node, 
			TreeNode<Integer> a, TreeNode<Integer> b) {
		// base case
		if (node == null) {
			return new Result(null, false);
		}
				
		// pre-order
		boolean found = node == a || node == b;	
		
		Result left = findAncestorHelper(node.left, a, b);
		
		// in-order
		if (left.isAncestor) {
			return left;
		}		
		if (found && left.node != null) { // found ancestor			
			return new Result(node, true);
		}
		
		Result right = findAncestorHelper(node.right, a, b);
		
		// post-order
		if (right.isAncestor) {
			return right;
		}
		if ((found && right.node != null) || (left.node != null && right.node != null)) { // found ancestor
			return new Result(node, true);
		}		
		if (found) { // found a or b
			return new Result(node, false);
		}
		return left.node != null ? left : right;
	}	
	
	static class Result {
		TreeNode<Integer> node;
		boolean isAncestor;
		
		public Result(TreeNode<Integer> node, boolean isAncestor) {
			this.node = node;
			this.isAncestor = isAncestor;
		}		
	}
	
	/**
	 * Time:  Average = Worst = O(n)<br>
	 * Space: Worst = O(h), h = height of tree 
	 */
	public static TreeNode<Integer> findAncestorWithCounter(TreeNode<Integer> root, 
			TreeNode<Integer> a, TreeNode<Integer> b) {
		IntWrapper counter = new IntWrapper();
		TreeNode<Integer> ancestor = findAncestorWithCounter(root, a, b, counter);
		return counter.value == 2 ? ancestor : null; 
	}

	public static TreeNode<Integer> findAncestorWithCounter(TreeNode<Integer> node, 
			TreeNode<Integer> a, TreeNode<Integer> b, IntWrapper counter) {
		if (node == null) {
			return null;
		}
		
		// pre-order
		boolean found = node == a || node == b;	
		
		TreeNode<Integer> left = findAncestorWithCounter(node.left, a, b, counter);
		
		// in-order
		if (found && left != null) { // found ancestor
			return node;
		}
		
		TreeNode<Integer> right = findAncestorWithCounter(node.right, a, b, counter);
		
		// pre-order
		if ((found && right != null) || (left != null && right != null)) { // found ancestor
			return node;
		}		
		if (found) { // found a or b
			counter.value++;
			return node;
		}
		return left != null ? left : right;
	}
	
	static class IntWrapper {
		int value;
	}
	
	/**
	 * Time:  Average = Worst = O(n)<br>
	 * Space: Worst = O(h), h = height of tree<br>
	 */
	public static TreeNode<Integer> findAncestorWithPreCheckingContainsNode(TreeNode<Integer> root, 
			TreeNode<Integer> a, TreeNode<Integer> b) {
		if (!containsNode(root, a) || !containsNode(root, b)) { // does not contain a and b 
			return null;
		}
		return findAncestorWithPreCheckingContainsNodeHelper(root, a, b);
	}
	
	/**
	 * Time:  Average = Worst = O(n)<br>
	 * Space: Worst = O(h), h = height of tree
	 */
	private static TreeNode<Integer> findAncestorWithPreCheckingContainsNodeHelper(TreeNode<Integer> node, 
			TreeNode<Integer> a, TreeNode<Integer> b) {		
		if (node == null) {
			return null;
		}
		
		// pre-order
		/* 
		 * If tree contains a and b and only a is returned, b must be in its subtree, 
		 * so a is the ancestor and vice versa
		 */
		if (node == a || node == b) {
			return node;
		}		
		TreeNode<Integer> left = findAncestorWithPreCheckingContainsNodeHelper(node.left, a, b);
		TreeNode<Integer> right = findAncestorWithPreCheckingContainsNodeHelper(node.right, a, b);
		// post-order
		if (left != null && right != null) { // found ancestor
			return node;
		}
		return left != null ? left : right;	// return a or b or ancestor	
	}
	
	/**
	 * Time:  Average = O(log(n)), Worst = O(n)<br>
	 * Space: Worst = O(h), h = height of tree 
	 */
	private static boolean containsNode(TreeNode<Integer> node, TreeNode<Integer> target) {
		if (node == null) {
			return false;
		}
		if (node == target) {
			return true;
		}
		return containsNode(node.left, target) || containsNode(node.right, target);
	}
}
